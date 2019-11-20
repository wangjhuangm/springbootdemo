package com.wangj.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class FileUtil {

    private final static Logger logger = LoggerFactory.getLogger(FileUtil.class);
    private final static String UFT_8 = "UTF-8";

    public static String readFile(InputStream is) {
        BufferedReader br = null;
        StringBuffer sb = new StringBuffer();
        try {
            br = new BufferedReader(new InputStreamReader(is, UFT_8));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\r\n");
            }
        } catch (UnsupportedEncodingException e) {
            logger.error("no such encoding:" + e.getMessage());
        } catch (IOException e) {
            logger.error("read failed:" + e.getMessage());
        }finally {
            try {
                br.close();
                is.close();
            } catch (IOException e) {
                logger.error("io close failed:" + e.getMessage());
            }
        }
        return sb.toString();
    }

    public static String readFile(String path) {
        File file = new File(path);
        InputStream is = null;
        try {
            is = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            logger.error("new inputstream failed:" + e.getMessage());
        }
        return readFile(is);
    }

    public static void main(String[] args) {
        String path = "/opt/cloud/logs/info.log";
        String content = readFile(path);
        System.out.println(content);
    }
}
