/**
 * 
 */
package gr.atc.polivisu.service;

import java.util.HashMap;
import java.util.Map;
import com.auth0.jwt.JWTSigner;
import gr.atc.polivisu.exception.SubmitCommandException;
import gr.atc.polivisu.model.Command;
import gr.atc.polivisu.model.JWTSecret;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author Stamatis
 */
@Service
public class CommandService {
    
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CommandService.class);
    
    /**
     * 
     * @param username
     * @return 
     */
    public String createJWTToken(String username) {

        Map<String, Object> payload = new HashMap<>();
        
        payload.put("username", username);
        JWTSigner signer = new JWTSigner(JWTSecret.getSecret());
        return signer.sign(payload);
    }

    /**
     * 
     * @param username
     * @param command
     * @return 
     * @throws gr.atc.polivisu.exception.SubmitCommandException 
     */
    public String submitCommand(String username, Command command) throws SubmitCommandException {
        
        if (StringUtils.isBlank(username)){
            throw new SubmitCommandException("Please set the Authorization header.");
        }
        
        if ((command != null) && (StringUtils.isNotBlank(command.getCommand()))) {
            LOGGER.info("{} has submitted: {}", username, command.getCommand());
            
            return "sample execution result";
        }
        
        throw new SubmitCommandException("No command has been submitted");
    }
    
}
