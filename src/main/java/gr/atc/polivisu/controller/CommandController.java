/**
 * 
 */
package gr.atc.polivisu.controller;

import gr.atc.polivisu.exception.SubmitCommandException;
import gr.atc.polivisu.model.Command;
import gr.atc.polivisu.service.CommandService;
import io.jsonwebtoken.Claims;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Stamatis
 */
@RestController
public class CommandController {
    
    @Autowired
    CommandService commandService;
    
    private static Claims claims = null;
    
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CommandController.class);
    
    @RequestMapping(value = "/command", method = RequestMethod.POST)
    public ResponseEntity command(final HttpServletRequest request, @RequestBody Command command) throws SubmitCommandException {
        
        claims = (Claims) request.getAttribute("claims");
        
        String username = claims.get("username", String.class);
        
        String commandResult = commandService.submitCommand(username, command);
        
        return new ResponseEntity<>(commandResult, HttpStatus.OK);
    }
    
}
