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
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import ru.sfedu.computervisionlab.Constants;
import ru.sfedu.computervisionlab.api.ImageApi;
import ru.sfedu.computervisionlab.utils.Utils;
import ru.sfedu.computervisionrest.model.FileUploadFillFloodForm;
import ru.sfedu.computervisionrest.model.FileUploadFindRectForm;
import ru.sfedu.computervisionrest.model.FileUploadForm;
import ru.sfedu.computervisionrest.utils.FileUtil;

/**
 *
 * @author orus-kade
 */
@Path("/service")
public class Service {

    static final Logger logger = LogManager.getLogger(Service.class);
    private String dir;
    private String dirRes;
    private ImageApi api = new ImageApi();

    public Service() throws Exception {
        if (System.getProperty("myPath") != null) {
            logger.info("Current path: " + System.getProperty("myPath"));
        }
        dir = Utils.getDir();
        dirRes = Utils.getDirResults();

    }

    @GET
    @Path("/test")
    public Response test() {
        logger.info("method test");
        return Response.ok("test").build();
    }

    @POST
    @Path("/setChannelZero/{c}")
    @Consumes("multipart/form-data")
    @Produces({"image/jpeg", "image/png"})
    public Response setChannelZero(@PathParam("c") Character c, @MultipartForm FileUploadForm form) throws IOException {
        logger.info("setChannelZero method");
        logger.info("Upload file");
        String fileName = form.getFileName();
        byte[] arr = form.getData();
        FileUtil.writeFile(arr, fileName, dir);
        Constants.Channels ch;
        switch (c) {
            case 'b':
                ch = Constants.Channels.BLUE;
                break;
            case 'g':
                ch = Constants.Channels.GREEN;
                break;
            case 'r':
                ch = Constants.Channels.RED;
                break;
            default:
                logger.error("Incorrect channel");
                return Response.status(Response.Status.BAD_REQUEST).build();
        }
        String newName = "upd_channel_" + fileName;
        api.saveImage(api.setChannelZero(api.loadImage(fileName), ch), newName);
        byte[] data = FileUtil.readFile(newName, dirRes);
        FileUtil.deleteFile(dirRes + newName);
        FileUtil.deleteFile(dir + fileName);
        //Build a response to return
        return Response.ok((Object) data).header("Content-Disposition", "attachment;filename=" + newName).build();
    }

    @POST
    @Path("/filtration/blur/{i}")
    @Consumes("multipart/form-data")
    @Produces({"image/jpeg", "image/png"})
    public Response filtrationBlur(@PathParam("i") int i, @MultipartForm FileUploadForm form) throws IOException {
        logger.info("filtrationBlur method");
        logger.info("Upload file");
        if (i <= 0) {
            logger.error("param i <= 0");
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        String fileName = form.getFileName();
        byte[] arr = form.getData();
        FileUtil.writeFile(arr, fileName, dir);
        String newName = "blur_" + i + "_" + fileName;
        api.saveImage(api.filtrationBlur(api.loadImage(fileName), i), newName);
        byte[] data = FileUtil.readFile(newName, dirRes);
        FileUtil.deleteFile(dirRes + newName);
        FileUtil.deleteFile(dir + fileName);
        return Response.ok((Object) data).header("Content-Disposition", "attachment;filename=" + newName).build();
    }

    @POST
    @Path("/filtration/median/{i}")
    @Consumes("multipart/form-data")
    @Produces({"image/jpeg", "image/png"})
    public Response filtrationMedianBlur(@PathParam("i") int i, @MultipartForm FileUploadForm form) throws IOException {
        logger.info("filtrationMedianBlur method");
        logger.info("Upload file");
        if (i <= 0) {
            logger.error("param i <= 0");
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        String fileName = form.getFileName();
        byte[] arr = form.getData();
        FileUtil.writeFile(arr, fileName, dir);
        String newName = "median_" + i + "_" + fileName;
        api.saveImage(api.filtrationMedianBlur(api.loadImage(fileName), i), newName);
        byte[] data = FileUtil.readFile(newName, dirRes);
        FileUtil.deleteFile(dirRes + newName);
        FileUtil.deleteFile(dir + fileName);
        return Response.ok((Object) data).header("Content-Disposition", "attachment;filename=" + newName).build();
    }

    @POST
    @Path("/filtration/gaussian/{i}")
    @Consumes("multipart/form-data")
    @Produces({"image/jpeg", "image/png"})
    public Response filtrationGaussianBlur(@PathParam("i") int i, @MultipartForm FileUploadForm form) throws IOException {
        logger.info("filtrationGaussianBlur method");
        logger.info("Upload file");
        if (i <= 0) {
            logger.error("param i <= 0");
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        String fileName = form.getFileName();
        byte[] arr = form.getData();
        FileUtil.writeFile(arr, fileName, dir);
        String newName = "gaussian_" + i + "_" + fileName;
        api.saveImage(api.filtrationGaussianBlur(api.loadImage(fileName), i), newName);
        byte[] data = FileUtil.readFile(newName, dirRes);
        FileUtil.deleteFile(dirRes + newName);
        FileUtil.deleteFile(dir + fileName);
        return Response.ok((Object) data).header("Content-Disposition", "attachment;filename=" + newName).build();
    }

    @POST
    @Path("/filtration/bilateral/{i}")
    @Consumes("multipart/form-data")
    @Produces({"image/jpeg", "image/png"})
    public Response filtrationBilateralFilter(@PathParam("i") int i, @MultipartForm FileUploadForm form) throws IOException {
        logger.info("filtrationBilateralFilter method");
        logger.info("Upload file");
        if (i <= 0) {
            logger.error("param i <= 0");
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        String fileName = form.getFileName();
        byte[] arr = form.getData();
        FileUtil.writeFile(arr, fileName, dir);
        String newName = "bilateral_" + i + "_" + fileName;
        api.saveImage(api.filtrationBilateralFilter(api.loadImage(fileName), i), newName);
        byte[] data = FileUtil.readFile(newName, dirRes);
        FileUtil.deleteFile(dirRes + newName);
        FileUtil.deleteFile(dir + fileName);
        return Response.ok((Object) data).header("Content-Disposition", "attachment;filename=" + newName).build();
    }

    @POST
    @Path("/erode/{type}/{i}")
    @Consumes("multipart/form-data")
    @Produces({"image/jpeg", "image/png"})
    public Response erode(@PathParam("i") int i, @PathParam("type") String mType, @MultipartForm FileUploadForm form) throws IOException {
        logger.info("erode method");
        logger.info("Upload file");
        if (i <= 0) {
            logger.error("param i <= 0");
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        String fileName = form.getFileName();
        byte[] arr = form.getData();
        FileUtil.writeFile(arr, fileName, dir);
        int type;
        switch (mType) {
            case "cross":
                type = Imgproc.MORPH_CROSS;
                break;
            case "rect":
                type = Imgproc.MORPH_RECT;
                break;
            case "ellipse":
                type = Imgproc.MORPH_ELLIPSE;
                break;
            default:
                logger.error("Incorrect morph type");
                return Response.status(Response.Status.BAD_REQUEST).build();
        }
        String newName = "erode_" + i + "_" + mType + "_" + fileName;
        api.saveImage(api.erode(api.loadImage(fileName), i, type), newName);
        byte[] data = FileUtil.readFile(newName, dirRes);
        FileUtil.deleteFile(dirRes + newName);
        FileUtil.deleteFile(dir + fileName);
        return Response.ok((Object) data).header("Content-Disposition", "attachment;filename=" + newName).build();
    }

    @POST
    @Path("/dilate/{type}/{i}")
    @Consumes("multipart/form-data")
    @Produces({"image/jpeg", "image/png"})
    public Response dilate(@PathParam("i") int i, @PathParam("type") String mType, @MultipartForm FileUploadForm form) throws IOException {
        logger.info("erode method");
        logger.info("Upload file");
        if (i <= 0) {
            logger.error("param i <= 0");
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        String fileName = form.getFileName();
        byte[] arr = form.getData();
        FileUtil.writeFile(arr, fileName, dir);
        int type;
        switch (mType) {
            case "cross":
                type = Imgproc.MORPH_CROSS;
                break;
            case "rect":
                type = Imgproc.MORPH_RECT;
                break;
            case "ellipse":
                type = Imgproc.MORPH_ELLIPSE;
                break;
            default:
                logger.error("Incorrect morph type");
                return Response.status(Response.Status.BAD_REQUEST).build();
        }
        String newName = "dilate_" + i + "_" + mType + "_" + fileName;
        api.saveImage(api.dilate(api.loadImage(fileName), i, type), newName);
        byte[] data = FileUtil.readFile(newName, dirRes);
        FileUtil.deleteFile(dirRes + newName);
        FileUtil.deleteFile(dir + fileName);
        return Response.ok((Object) data).header("Content-Disposition", "attachment;filename=" + newName).build();
    }

    @POST
    @Path("/fillFlood")
    @Consumes("multipart/form-data")
    @Produces({"image/jpeg", "image/png"})
    public Response fillFlood(@MultipartForm FileUploadFillFloodForm form) throws IOException {
        logger.info("fillFlood method");
        logger.info("Upload file");
        String fileName = form.getFileName();
        byte[] arr = form.getData();
        FileUtil.writeFile(arr, fileName, dir);
        String newName = "fillFlood_" + fileName;
        Point point;
        if (form.getxPoint() == null || form.getyPoint() == null) {
            point = null;
        } else {
            point = new Point(form.getxPoint(), form.getyPoint());
        }
        Scalar color;
        if (form.getR() == null || form.getG() == null || form.getB() == null) {
            color = null;
        } else {
            color = new Scalar(form.getR(), form.getG(), form.getB());
        }
        Scalar lowDiff;
        if (form.getrLow() == null || form.getgLow() == null || form.getbLow() == null) {
            lowDiff = null;
        } else {
            lowDiff = new Scalar(form.getrLow(), form.getgLow(), form.getbLow());
        }
        Scalar upDiff;
        if (form.getrUp() == null || form.getgUp() == null || form.getbUp() == null) {
            upDiff = null;
        } else {
            upDiff = new Scalar(form.getrUp(), form.getgUp(), form.getbUp());
        }
        api.saveImage(api.fillFlood(api.loadImage(fileName), point, color, lowDiff, upDiff), newName);
        byte[] data = FileUtil.readFile(newName, dirRes);
        FileUtil.deleteFile(dirRes + newName);
        FileUtil.deleteFile(dir + fileName);
        return Response.ok((Object) data).header("Content-Disposition", "attachment;filename=" + newName).build();
    }

    @POST
    @Path("/pyramids/up/{i}")
    @Consumes("multipart/form-data")
    @Produces({"image/jpeg", "image/png"})
    public Response pyramidUp(@PathParam("i") int i, @MultipartForm FileUploadForm form) throws IOException {
        logger.info("pyramidUp method");
        logger.info("Upload file");
        if (i <= 0) {
            logger.error("param i <= 0");
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        String fileName = form.getFileName();
        byte[] arr = form.getData();
        FileUtil.writeFile(arr, fileName, dir);
        String newName = "pyrUp_" + i + "_" + fileName;
        api.saveImage(api.pyramidUp(api.loadImage(fileName), i), newName);
        byte[] data = FileUtil.readFile(newName, dirRes);
        FileUtil.deleteFile(dirRes + newName);
        FileUtil.deleteFile(dir + fileName);
        return Response.ok((Object) data).header("Content-Disposition", "attachment;filename=" + newName).build();
    }

    @POST
    @Path("/pyramids/down/{i}")
    @Consumes("multipart/form-data")
    @Produces({"image/jpeg", "image/png"})
    public Response pyramidDown(@PathParam("i") int i, @MultipartForm FileUploadForm form) throws IOException {
        logger.info("pyramidDown method");
        logger.info("Upload file");
        if (i <= 0) {
            logger.error("param i <= 0");
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        String fileName = form.getFileName();
        byte[] arr = form.getData();
        FileUtil.writeFile(arr, fileName, dir);
        String newName = "pyrUp_" + i + "_" + fileName;
        api.saveImage(api.pyramidDown(api.loadImage(fileName), i), newName);
        byte[] data = FileUtil.readFile(newName, dirRes);
        FileUtil.deleteFile(dirRes + newName);
        FileUtil.deleteFile(dir + fileName);
        return Response.ok((Object) data).header("Content-Disposition", "attachment;filename=" + newName).build();
    }
    
    @POST
    @Path("/pyramids/subtract")
    @Consumes("multipart/form-data")
    @Produces({"image/jpeg", "image/png"})
    public Response pyramidsSubtract(@MultipartForm FileUploadForm form) throws IOException {
        logger.info("pyramidsSubtract method");
        String fileName = form.getFileName();
        byte[] arr = form.getData();
        FileUtil.writeFile(arr, fileName, dir);
        String newName = "pyrSub_" + fileName;
        try{
            api.saveImage(api.pyramids(api.loadImage(fileName)), newName);
        } catch (Exception ex){
            logger.error(ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        }        
        byte[] data = FileUtil.readFile(newName, dirRes);
        FileUtil.deleteFile(dirRes + newName);
        FileUtil.deleteFile(dir + fileName);
        return Response.ok((Object) data).header("Content-Disposition", "attachment;filename=" + newName).build();
    }
    
    @POST
    @Path("/findRect/")
    @Consumes("multipart/form-data")
//    @Produces({"image/jpeg", "image/png"})
    public Response findRect(@MultipartForm FileUploadFindRectForm form) throws IOException {
        logger.info("findRect method");
        logger.info("Upload file");
        String fileName = form.getFileName();
        byte[] arr = form.getData();
        FileUtil.writeFile(arr, fileName, dir);
        if (form.gethMax() == null || form.gethMin() == null || form.getwMax() == null || form.getwMin() == null){
            logger.error("Wrong size");
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Size min = new Size(form.getwMin(), form.gethMin());
        Size max = new Size(form.getwMax(), form.gethMax());
        int count = api.findRect(api.loadImage(fileName), min, max);
        FileUtil.deleteFile(dir + fileName);
        return Response.ok(count).build();
    }
}
