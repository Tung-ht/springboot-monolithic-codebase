package nta.bookstore.api.service.impl;

import lombok.extern.slf4j.Slf4j;
import nta.bookstore.api.common.utils.Base64ToMultipartFile;
import nta.bookstore.api.config.MediaStorageProperties;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
@Slf4j
public class ImageService {
    private final Path fileStorageLocation;

    public ImageService(MediaStorageProperties mediaStorageProperties) throws IOException {
        this.fileStorageLocation = Paths.get(mediaStorageProperties.getUploadDirectory()).toAbsolutePath().normalize();
        Files.createDirectories(this.fileStorageLocation);
    }

    public String uploadImage(MultipartFile image, String customFileName) {
        try {
            if (image != null) {
                Path targetLocation = this.fileStorageLocation.resolve(customFileName);
                Files.copy(image.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
                return customFileName;
            } else {
                return "";
            }
        } catch (Exception e) {
            log.error("file_upload_failed: {}", e.getMessage());
            return "";
        }
    }

    public String uploadImage(String base64Source, String customFileName) {
        try {
            MultipartFile file = convertBase64SourceToMultipart(base64Source);
            if (file != null) {
                Path targetLocation = this.fileStorageLocation.resolve(customFileName);
                Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
                return customFileName;
            } else {
                return "";
            }
        } catch (Exception e) {
            log.error("file_upload_failed: {}", e.getMessage());
            return "";
        }
    }

    public String loadImageAsBase64Source(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return Base64ToMultipartFile.convertFileToBase64Source(resource.getFile());
            } else {
                return "";
            }
        } catch (Exception e) {
            log.error("get_file_failed: {}", e.getMessage());
            return "";
        }
    }

    public MultipartFile convertBase64SourceToMultipart(String source) {
        try {
            return new Base64ToMultipartFile(source);
        } catch (Exception exception) {
            log.error("get_base64_from_multipart_failed: {}", exception.getMessage());
            return null;
        }
    }

    public boolean deleteImage(String imageName) {
        Path imagePath = this.fileStorageLocation.resolve(imageName).normalize();
        File image = imagePath.toFile();
        return image.delete();
    }
}
