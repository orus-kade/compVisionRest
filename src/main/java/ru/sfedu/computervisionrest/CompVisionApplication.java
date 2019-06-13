
package ru.sfedu.computervisionrest;

import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author orus-kade
 */
@ApplicationPath("/")
public class CompVisionApplication extends Application{

    private static final Logger logger = LogManager.getLogger("App");

    public CompVisionApplication() {
        logger.info("App starting...");
    }    
    
    
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    } 

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(ru.sfedu.computervisionrest.services.Service.class);
    }
    
}
