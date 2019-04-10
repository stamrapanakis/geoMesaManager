/**
 * 
 */
package gr.atc.polivisu.geomesa.service;

import gr.atc.polivisu.geomesa.service.CommandService;
import gr.atc.polivisu.geomesa.exception.SubmitCommandException;
import gr.atc.polivisu.geomesa.model.Command;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Stamatis
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CommandServiceTest {
    
    @Autowired
    CommandService commandService;
    
    /**
     * Test of createJWTToken method, of class CommandService.
     */
    @Test
    public void testCreateJWTToken() {
        
        //gert.vervaet@gmail.com
        String email = "gert.vervaet";
        String expResult = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6ImdlcnQudmVydmFldCJ9.3Sf8f-xLmRbRFmC3U7OupkRy_SaAPPtbUaboUCQGNx8";
        String result = commandService.createJWTToken(email);
        assertEquals(expResult, result);
        System.out.println(result);
        
        //fabien.tence@senx.io
        String emailSenx = "fabien.tence";
        String expResultSenx = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6ImZhYmllbi50ZW5jZSJ9.VZ7cE0MoNIMgzFa1f0AvwE7s4cVnLRAzXOVKogK9yik";
        String resultSenx = commandService.createJWTToken(emailSenx);
        assertEquals(expResultSenx, resultSenx);
        System.out.println(resultSenx);
        
        //niels.charlier@geosparc.com
        String emailGeosparc = "niels.charlier";
        String expResultGeosparc = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6Im5pZWxzLmNoYXJsaWVyIn0.w42gVjX099DePeco3hcZkY1dqoxC25o5IajjwknXk9M";
        String resultGeosparc = commandService.createJWTToken(emailGeosparc);
        assertEquals(expResultGeosparc, resultGeosparc);
        System.out.println(resultGeosparc);
        
        //pepijn.viaene@geosparc.com        
        String emailGeosparc2 = "pepijn.viaene";
        String expResultGeosparc2 = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6InBlcGlqbi52aWFlbmUifQ.OVefVenlWfVTNbYTJBWj1CLpj4a44r2PH5v-m47xVZ0";
        String resultGeosparc2 = commandService.createJWTToken(emailGeosparc2);
        assertEquals(expResultGeosparc2, resultGeosparc2);
        System.out.println(resultGeosparc2);
        
        //frank.maes@geosparc.com
        String emailGeosparc3 = "frank.maes";
        String expResultGeosparc3 = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6ImZyYW5rLm1hZXMifQ.QW8aJiuo89sd2FXSj866C8RU8xU0BuqJe3gnAhqKE8I";
        String resultGeosparc3 = commandService.createJWTToken(emailGeosparc3);
        assertEquals(expResultGeosparc3, resultGeosparc3);
        System.out.println(resultGeosparc3);
    }

    /**
     * Test of submitCommand method, of class CommandService.
     * @throws gr.atc.polivisu.geomesa.exception.SubmitCommandException
     */
    @Test
    public void testSubmitCommand() throws SubmitCommandException {
        
        String username = "gert.vervaet";
        Command command = new Command();
        command.setCommand("ls -l");
        String expResult = "sample execution result";
        
        String result = commandService.submitCommand(username, command);
        assertEquals(expResult, result);
    }
    
}
