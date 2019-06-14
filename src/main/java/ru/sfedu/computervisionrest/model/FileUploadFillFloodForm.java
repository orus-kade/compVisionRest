
package ru.sfedu.computervisionrest.model;

/**
 *
 * @author orus-kade
 */

import javax.ws.rs.FormParam;
import org.jboss.resteasy.annotations.providers.multipart.PartType;


public class FileUploadFillFloodForm {
    
    public FileUploadFillFloodForm() {
    }

    private byte[] data;
    private String fileName;
    private Integer xPoint;
    private Integer yPoint;
    private Integer r;
    private Integer b;
    private Integer g;
    private Integer rLow;
    private Integer bLow;
    private Integer gLow;
    private Integer rUp;
    private Integer bUp;
    private Integer gUp;

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
    public void setFileName(String filename) {
        this.fileName = filename;
    }

    public Integer getxPoint() {
        if (xPoint < 0){
            return null;
        }
        return xPoint;
    }

    @FormParam("xPoint")
    public void setxPoint(int xPoint) {
        this.xPoint = xPoint;
    }

    public Integer getyPoint() {
        if (yPoint < 0){
            return null;
        }
        return yPoint;
    }

    @FormParam("yPoint")
    public void setyPoint(int yPoint) {
        this.yPoint = yPoint;
    }

    public Integer getR() {
        if (r == null || r < 0 || r > 255){
            return null;
        }
        return r;
    }

    @FormParam("r")
    public void setR(int r) {
        this.r = r;
    }

    public Integer getB() {
        if (b == null || b < 0 || b > 255){
            return null;
        }
        return b;
    }

    @FormParam("b")
    public void setB(int b) {
        this.b = b;
    }

    public Integer getG() {
        if (g == null || g < 0 || g > 255){
            return null;
        }
        return g;
    }

    @FormParam("g")
    public void setG(int g) {
        this.g = g;
    }

    public Integer getrLow() {
        if (rLow == null || rLow < 0 || rLow > 255){
            return null;
        }
        return rLow;
    }

    @FormParam("rLow")
    public void setrLow(int rLow) {
        this.rLow = rLow;
    }

    public Integer getbLow() {
        if (bLow == null || bLow < 0 || bLow > 255){
            return null;
        }
        return bLow;
    }

    @FormParam("bLow")
    public void setbLow(int bLow) {
        this.bLow = bLow;
    }

    public Integer getgLow() {
        if (gLow == null || gLow < 0 || gLow > 255){
            return null;
        }
        return gLow;
    }

    @FormParam("gLow")
    public void setgLow(int gLow) {
        this.gLow = gLow;
    }

    public Integer getrUp() {
        if (rUp == null || rUp < 0 || rUp > 255){
            return null;
        }
        return rUp;
    }

    @FormParam("rUp")
    public void setrUp(int rUp) {
        this.rUp = rUp;
    }

    public Integer getbUp() {
        if (bUp == null || bUp < 0 || bUp > 255){
            return null;
        }
        return bUp;
    }

    @FormParam("bUp")
    public void setbUp(int bUp) {
        this.bUp = bUp;
    }

    public Integer getgUp() {
        if (gUp == null || gUp < 0 || gUp > 255){
            return null;
        }
        return gUp;
    }

    @FormParam("gUp")
    public void setgUp(int gUp) {
        this.gUp = gUp;
    }

}
