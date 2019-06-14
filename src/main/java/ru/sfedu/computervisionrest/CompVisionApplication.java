
package ru.sfedu.computervisionrest;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.computervisionrest.services.Service;

/**
 *
 * @author orus-kade
 */
@ApplicationPath("/testapp")
public class CompVisionApplication extends Application{

    private static final Logger logger = LogManager.getLogger(CompVisionApplication.class);
   
    
    private Set<Object> var = new HashSet<Object>();
    private Set<Class<?>> empty = new HashSet<Class<?>>();

    public CompVisionApplication() throws Exception {
        var.add(new ru.sfedu.computervisionrest.services.Service());
        logger.info("App starting...");
    }    

    @Override
    public Set<Class<?>> getClasses() {
        return empty; 
    }

    @Override
    public Set<Object> getSingletons() {
        return var; 
    }
      
}
