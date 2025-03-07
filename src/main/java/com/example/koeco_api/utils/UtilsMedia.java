package com.example.koeco_api.utils;

import com.example.koeco_api.common.CommonException;
import com.example.koeco_api.common.ErrorCode;
import com.example.koeco_api.enums.TypeFileEnums;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.UUID;

@Slf4j
public class UtilsMedia {
    private static String[] IMAGE_EXTENSION = {"jpg", "jpeg", "png", "gif", "bmp", "tiff", "webp", "ico", "svg", "heif", "avif"};
    private static String[] VIDEO_EXTENSION = {"mp4", "avi", "mkv", "mov", "wmv", "flv", "webm", "mpeg"};
    private static String URL_FOLDER_FILE = UtilsValue.URL_FOLDER_FILE;

    public static String uploadFile(MultipartFile file, TypeFileEnums typeFile, Double maxSize) {
        log.info("start in here");
        validateFile(file, typeFile);
        validateSize(file, maxSize);
        createDirectory(URL_FOLDER_FILE);

        try {
            String newFileName = createNameFile(file.getOriginalFilename());
            Path pathSaveFile = Path.of(URL_FOLDER_FILE, newFileName);

            Files.copy(file.getInputStream(), pathSaveFile, StandardCopyOption.REPLACE_EXISTING);
            return newFileName;
        } catch (IOException e) {
            log.error("err when upload file: ", e.getMessage());
            throw new RuntimeException(e);
        }

    }

    private static void createDirectory(String directory) {
        try {
            Path path = Path.of(directory);
            if(!Files.exists(path))
                Files.createDirectory(path);
        } catch (Exception e) {
            log.error("err when create directory " + e.getMessage());
            throw new CommonException(ErrorCode.CAN_NOT_CREATE_FOLDER);
        }

    }

    private static boolean validateSize(MultipartFile file, Double maxSize) {
        if (file.getSize() / (1024.0 * 1024.0) > maxSize)
            throw new CommonException(ErrorCode.FILE_TOO_LARGE);
        return true;
    }

    private static boolean validateFile(MultipartFile file, TypeFileEnums typeFiles) {
        if (file == null)
            throw new CommonException(ErrorCode.FILE_IS_NULL);

        if (typeFiles.equals(TypeFileEnums.IMAGE)) {
            var isImage = checkExtensionFile(file, IMAGE_EXTENSION);
            if (isImage == false)
                throw new CommonException(ErrorCode.IMAGE_NOT_VALID);
        }

        if (typeFiles.equals(TypeFileEnums.VIDEO)) {
            var isVideo = checkExtensionFile(file, VIDEO_EXTENSION);
            if (isVideo == false)
                throw new CommonException(ErrorCode.VIDEO_NOT_VALID);
        }
        return true;
    }

    private static boolean checkExtensionFile(MultipartFile file, String[] extensions) {
        for (var it : extensions)
            if (it.equals(getExtensionFile(file)))
                return true;
        return false;
    }

    private static String getExtensionFile(MultipartFile file) {
        String name = file.getOriginalFilename();

        if(name != null && name.contains(".")) {
            int pos = name.lastIndexOf(".");
            return name.substring(pos + 1);
        }
        return "";
    }

    private static String createNameFile(String name) {
        if(name != null && name.contains(".")) {
            int pos = name.lastIndexOf(".");
            String first = name.substring(0, pos);
            String last = name.substring(pos);
            return first + "_" + UUID.randomUUID().toString() + last;
        }

        return name + "_" + UUID.randomUUID().toString();
    }

}
