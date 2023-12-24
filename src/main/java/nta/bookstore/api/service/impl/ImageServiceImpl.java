package nta.bookstore.api.service.impl;

import lombok.extern.slf4j.Slf4j;
import nta.bookstore.api.common.constant.ResponseConst;
import nta.bookstore.api.common.exception.AppException;
import nta.bookstore.api.common.utils.Base64ToMultipartFile;
import nta.bookstore.api.config.MediaStorageProperties;
import nta.bookstore.api.service.ImageService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Component
@Slf4j
public class ImageServiceImpl implements ImageService {
    private final Path fileStorageLocation;

    public ImageServiceImpl(MediaStorageProperties mediaStorageProperties) throws IOException {
        this.fileStorageLocation = Paths.get(mediaStorageProperties.getUploadDirectory()).toAbsolutePath().normalize();
        Files.createDirectories(this.fileStorageLocation);
    }

    @Override
    public String uploadImage(String base64Source, String customFileName) throws IOException {
        MultipartFile file = Base64ToMultipartFile.convertBase64SourceToMultipart(base64Source);
        if (file != null) {
                Path targetLocation = this.fileStorageLocation.resolve(customFileName);
                Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
                return customFileName;
        } else {
            return "";
        }
    }

    @Override
    public String loadImageAsBase64Source(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return Base64ToMultipartFile.convertFileToBase64Source(resource.getFile());
            } else {
                throw new AppException(ResponseConst.IMAGE_ERROR_CODE, ResponseConst.IMAGE_ERROR_MESSAGE_NOT_EXIST);
            }
        } catch (Exception ex) {
            log.error("Load image error {}", ex.getMessage());
            throw new AppException(ResponseConst.IMAGE_ERROR_CODE, ResponseConst.IMAGE_ERROR_MESSAGE_GET_IMAGE);
        }
    }
}
