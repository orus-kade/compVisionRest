package ru.sfedu.computervisionrest.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author orus-kade
 */
public class FileUtil {

    static final Logger logger = LogManager.getLogger(FileUtil.class);

    public static void writeFile(byte[] data, String fileName, String dir) {
        try {
            FileUtils.writeByteArrayToFile(new File(dir + fileName), data);

        } catch (IOException e) {
            logger.error("Can`t write file");
        }
    }

    public static byte[] readFile(String fileName, String dir) {
        try {
            File file = new File(dir + fileName);
            return FileUtils.readFileToByteArray(file);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    public static boolean deleteFile(String filepath) {
        if (filepath != null && !filepath.isEmpty()) {
            logger.info("Start method deleteFile(), input param: " + filepath);

            boolean result = false;
            Path path = Paths.get(filepath);
            logger.info("File exist");
            try {
                result = Files.deleteIfExists(path);
            } catch (IOException e) {
                logger.error("Can`t delete file with path: " + path);
            }
            return result;
        }
        logger.info("File path is empty or null");
        return false;
    }

}
