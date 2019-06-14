
package ru.sfedu.computervisionrest.model;

import javax.ws.rs.FormParam;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

/**
 *
 * @author orus-kade
 */
public class FileUploadFindRectForm {
    
    public FileUploadFindRectForm() {
    }

    private byte[] data;
    private String fileName;
    private Integer wMax;
    private Integer wMin;
    private Integer hMax;
    private Integer hMin;

    public Integer getwMax() {
        return wMax;
    }

    @FormParam("wMax")
    public void setwMax(Integer wMax) {
        this.wMax = wMax;
    }

    public Integer getwMin() {
        return wMin;
    }

    @FormParam("wMin")
    public void setwMin(Integer wMin) {
        this.wMin = wMin;
    }

    public Integer gethMax() {
        return hMax;
    }

    @FormParam("hMax")
    public void sethMax(Integer hMax) {
        this.hMax = hMax;
    }

    public Integer gethMin() {
        return hMin;
    }

    @FormParam("hMin")
    public void sethMin(Integer hMin) {
        this.hMin = hMin;
    }

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
