package ru.sfedu.computervisionrest.model;

/**
 *
 * @author orus-kade
 */
import javax.ws.rs.FormParam;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

public class FileUploadForm {

    public FileUploadForm() {
    }

    private byte[] data;
    private String fileName;

    public byte[] getData() {
        return data;
    }

    public String getFileName() {
        return fileName;
    }

    @FormParam("uploadedFile")
    @PartType("application/octet-stream")
    public void setData(byte[] data) {
        this.data = data;
    }
    
    @FormParam("uploadedFileName")
//    @PartType("application/octet-stream")
    public void setFileName(String filename) {
        this.fileName = filename;
    }
}
