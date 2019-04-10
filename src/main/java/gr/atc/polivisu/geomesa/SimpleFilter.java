package gr.atc.polivisu.geomesa;

import gr.atc.polivisu.geomesa.model.JWTSecret;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebFilter(urlPatterns = {"/*"}, description = "JWT Filter")
public class SimpleFilter implements Filter {

    private FilterConfig config = null;

    private static final int INVALID_TOKEN = 401; 
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleFilter.class);

    @Override
    public void init(FilterConfig config) throws ServletException {
        this.config = config;
        config.getServletContext().log("JWT Filter");
    }

    @Override
    public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
            throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        final boolean continueChain = "/email-verification/verify".equals(request.getRequestURI())
                //>>allow the following for swagger
                || "/swagger-ui.html".equals(request.getRequestURI())
                || "/v2/api-docs".equals(request.getRequestURI())
                || "/configuration/ui".equals(request.getRequestURI())
                || "/swagger-resources".equals(request.getRequestURI())
                || "/configuration/security".equals(request.getRequestURI())
                || request.getRequestURI().startsWith("/webjars")
                || request.getRequestURI().startsWith("/swagger-resources/")
                || request.getRequestURI().startsWith("/csrf")
                || request.getRequestURI().startsWith("/images");
                //<<end
//                || "/stats/allUsers".equals(request.getRequestURI());

        LOGGER.info(request.getRequestURI() + " -- " + request.getMethod());

        if ("OPTIONS".equals(request.getMethod())) {

            chain.doFilter(req, res);

        } else if (continueChain) {

            chain.doFilter(req, res);

        } else {

            final String authHeader = request.getHeader("Authorization");

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                LOGGER.error("Missing or invalid Authorization header.");
                response.setHeader("Access-Control-Allow-Origin", "*");
                response.setStatus(INVALID_TOKEN);
                return;

            }

            // The part after
            final String token = authHeader.substring(7);
            Claims claims;

            try {
                claims = Jwts.parser().setSigningKey(JWTSecret.getSecret().getBytes()).parseClaimsJws(token).getBody();
                request.setAttribute("claims", claims);
            } catch (final SignatureException ex) {
                LOGGER.error("Invalid token: " + token, ex);
                response.setHeader("Access-Control-Allow-Origin", "*");
                response.setStatus(INVALID_TOKEN);
                return;

            }

            chain.doFilter(req, response);
        }

    }

    @Override
    public void destroy() {
        config.getServletContext().log("Destroying SessionCheckerFilter");
    }

}
