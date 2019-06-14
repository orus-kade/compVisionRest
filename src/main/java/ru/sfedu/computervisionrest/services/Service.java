package ru.sfedu.computervisionrest.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import ru.sfedu.computervisionlab.api.ImageApi;
import ru.sfedu.computervisionrest.model.FileUploadForm;
import ru.sfedu.computervisionrest.utils.FileUtil;

/**
 *
 * @author orus-kade
 */

@Path("/service")
public class Service {

    static final Logger logger = LogManager.getLogger(Service.class);

    public Service() throws Exception {
        ImageApi api = new ImageApi();
        if (System.getProperty("myPath") != null) {
            logger.info("Current path: " + System.getProperty("myPath"));
        }
    }

    @GET
    @Path("/test")
    public Response find() {
        logger.info("method test");
        return Response.ok("test").build();
    }

    @POST
    @Path("/test")
    @Consumes("multipart/form-data")
    @Produces({"image/jpeg", "image/png"})
    public Response uploadFile(@MultipartForm FileUploadForm form) {

        logger.info("Upload file");

        String fileName = form.getFileName();
        String dir = "/home/orus-kade/";
        
        byte[] arr = form.getData();
        
        FileUtil.writeFile(arr, fileName, dir);
        
        File file = FileUtil.readFile(fileName, dir); 
        
        //Build a response to return
        return Response.ok((Object) file).header("Content-Disposition","attachment;filename=" + fileName).build();
                
    }

    
}
