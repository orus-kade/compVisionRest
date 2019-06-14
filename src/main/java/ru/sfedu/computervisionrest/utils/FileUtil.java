
package ru.sfedu.computervisionrest.utils;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author orus-kade
 */
public class FileUtil {
    
    static final Logger logger = LogManager.getLogger(FileUtil.class);
    
    public static void writeFile(byte[] data, String fileName, String dir){
        try {
            FileUtils.writeByteArrayToFile(new File(dir + fileName), data);
            
        } catch (IOException e) {
            logger.error("Can`t write file");
        }
    }
    
    public static File readFile(String fileName, String dir){
        try{
            File file = new File(dir + fileName);
            return file;
        } catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }        
    }

}
