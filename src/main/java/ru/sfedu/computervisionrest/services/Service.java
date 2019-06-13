
package ru.sfedu.computervisionrest.services;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author orus-kade
 */
@Stateless
@Path("service")
public class Service {
    
    static final Logger logger = LogManager.getLogger(Service.class);
    
    @GET
    @Path("test")
    public Response find(){
        logger.info("method test");
        return Response.status(200).build();
    }   

}
