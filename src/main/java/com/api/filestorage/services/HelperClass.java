package com.api.filestorage.services;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.imageio.ImageIO;

import com.api.filestorage.entities.FilesEntity;
import com.api.filestorage.entities.MusicFileEntity;
import com.api.filestorage.entities.PictureFileEntity;
import com.api.filestorage.entities.VideoFileEntity;
import com.coremedia.iso.IsoFile;

import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;

public class HelperClass {
    // PATH
    private static String PATH_FILE = "D:/Study/2021_HK8/ServelLocal";
    // PICTURE
    private static final String IMAGE = "image";
    private static final String JPG = "jpg";
    private static final String JPEG = "jpeg";
    private static final String PNG = "png";
    private static final String SVG = "svg";
    // VIDEO
    // private static final String VIDEO = "video";
    private static final String MP4 = "mp4";

    // AUDIO, MUSIC
    private static final String AUDIO = "audio";
    private static final String MP3 = "mpeg";

    public static String[] getPathAndExtension(String type) { // image/jpeg
        String[] rs = new String[2];
        String head = PATH_FILE;
        String extension = "";
        // System.out.println(type);
        if (type.contains(IMAGE)) {
            head += "/Pictures/";
            if (type.contains(PNG))
                extension = PNG;
            else if (type.contains(JPG))
                extension = JPG;
            else if (type.contains(JPEG))
                extension = JPG;
            else
                extension = SVG;
        } else if (type.contains(AUDIO)) {
            head += "/Musics/";
            if (type.contains(MP3))
                extension = "mp3";
        } else { // music
            head += "/Videos/";
            if (type.contains(MP4))
                extension = MP4;
        }
        rs[0] = head;
        rs[1] = extension;

        return rs;
    }

    public static String[] getParentAndCreatorFromURI(String uri) {
        String[] rs = new String[] { "", "" };
        try {
            String decodeURI = URLDecoder.decode(uri, "UTF-8");
            String[] s = decodeURI.split("/");
            String parent = "";
            String creator = "";
            if (s.length == 7) { // full
                parent = s[s.length - 1];
                creator = s[s.length - 2];
            } else {
                creator = s[s.length - 1];
            }
            rs[0] = parent;
            rs[1] = creator;
            return rs;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static FilesEntity getFilesEntity(String extension, String path) {
        if (extension.equals("mp3")) {
            MusicFileEntity musicFileEntity = new MusicFileEntity();
            musicFileEntity.setLength(getMp3Duration(path).intValue());
            return musicFileEntity;
        } else if (extension.equals("mp4")) {
            VideoFileEntity videoFileEntity = new VideoFileEntity();
            videoFileEntity.setLength(getMp4Duration(path));
            return videoFileEntity;
        } else {
            PictureFileEntity pictureFileEntity = new PictureFileEntity();
            if (!extension.equals(SVG)) {
                int[] wh = getWidthHeightImage(path);
                int width = wh[0];
                int height = wh[1];
                pictureFileEntity.setWidth(width);
                pictureFileEntity.setHeight(height);
            }
            return pictureFileEntity;
        }
    }

    private static int[] getWidthHeightImage(String path) {
        int[] rs = new int[2];
        try {
            BufferedImage bimg = ImageIO.read(new File(path));
            rs[0] = bimg.getWidth();
            rs[1] = bimg.getHeight();
            return rs;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static long getMp4Duration(String videoPath) {
        try (IsoFile isoFile = new IsoFile(videoPath);) {
            long lengthInSeconds = isoFile.getMovieBox().getMovieHeaderBox().getDuration()
                    / isoFile.getMovieBox().getMovieHeaderBox().getTimescale();
            return lengthInSeconds;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }

    }

    public static void main(String[] args) throws IOException {
        // System.out.println(Arrays.toString(getWidthHeightImage("C:\\Users\\ThanhTri98\\Downloads\\andre-benz-_T35CPjjSik-unsplash.jpg")));
        // System.out.println(getDuration("D:\\Study\\2021_HK8\\ServelLocal\\Musics\\DapVoCayDan.mp3"));
        // System.out.println(getMp4Duration("D:\\Study\\2021_HK8\\ServelLocal\\Musics\\abc.mp4"));
    }

    private static Float getMp3Duration(String filePath) {
        try {
            File mp3File = new File(filePath);
            MP3File f = (MP3File) AudioFileIO.read(mp3File);
            MP3AudioHeader audioHeader = (MP3AudioHeader) f.getAudioHeader();
            return Float.parseFloat(audioHeader.getTrackLength() + "");
        } catch (Exception e) {
            e.printStackTrace();
            return 0f;
        }
    }

    public static FilesEntity transferBaseToInstance(FilesEntity filesEntity) {
        if (filesEntity instanceof MusicFileEntity){
            MusicFileEntity currEntity = (MusicFileEntity) filesEntity;
            MusicFileEntity entity = new MusicFileEntity();
            entity.setFile_sk(currEntity.getFile_sk());
            entity.setName(currEntity.getName());
            entity.setSize(currEntity.getSize());
            entity.setExtension(currEntity.getExtension());
            entity.setParent(currEntity.getParent());
            entity.setCreator(currEntity.getCreator());
            entity.setModifyDate(currEntity.getDT());
            entity.setState(currEntity.getState());
            entity.setLength(currEntity.getLength());
            entity.setBitRate(currEntity.getBitRate());
            return entity;
        }
        else if (filesEntity instanceof PictureFileEntity){
            PictureFileEntity currEntity = (PictureFileEntity) filesEntity;
            PictureFileEntity entity = new PictureFileEntity();
            entity.setFile_sk(currEntity.getFile_sk());
            entity.setName(currEntity.getName());
            entity.setSize(currEntity.getSize());
            entity.setExtension(currEntity.getExtension());
            entity.setParent(currEntity.getParent());
            entity.setCreator(currEntity.getCreator());
            entity.setModifyDate(currEntity.getDT());
            entity.setState(currEntity.getState());
            entity.setWidth(currEntity.getWidth());
            entity.setHeight(currEntity.getHeight());
            return entity;
        }else{
            VideoFileEntity currEntity = (VideoFileEntity) filesEntity;
            VideoFileEntity entity = new VideoFileEntity();
            entity.setFile_sk(currEntity.getFile_sk());
            entity.setName(currEntity.getName());
            entity.setSize(currEntity.getSize());
            entity.setExtension(currEntity.getExtension());
            entity.setParent(currEntity.getParent());
            entity.setCreator(currEntity.getCreator());
            entity.setModifyDate(currEntity.getDT());
            entity.setState(currEntity.getState());
            entity.setLength(currEntity.getLength());
            return entity;
        }
        
    }

}
