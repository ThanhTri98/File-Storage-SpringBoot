package com.api.filestorage.services;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.bind.DatatypeConverter;

public class HelperClass {
    private static String PATH_FILE = "D:/Study/2021_HK8/ServelLocal";
    // PICTURE
    private static final String IMAGE = "image";
    private static final String JPG = "jpg";
    private static final String JPEG = "jpeg";
    private static final String PNG = "png";
    private static final String SVG = "svg";

    public static void saveBinaryBase64ToFile(String base64String, String fileName) {
        String[] strings = base64String.split(","); // [0]: data:image/jpeg;base64
        String extension;
        String dataHeader = strings[0];
        if (dataHeader.contains(IMAGE)) {
            PATH_FILE += "/Pictures/" + fileName + ".";
            if (dataHeader.contains(JPG))
                extension = JPG;
            else if (dataHeader.contains(PNG))
                extension = PNG;
            else if (dataHeader.contains(JPEG))
                extension = JPEG;
            else
                extension = SVG;
        } else {
            extension = SVG;
        }
        // convert base64 string to binary data
        try (OutputStream outputStream = new BufferedOutputStream(
                new FileOutputStream(new File(PATH_FILE + extension)))) {
            outputStream.write(DatatypeConverter.parseBase64Binary(strings[1]));
            System.out.println(PATH_FILE + extension);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
