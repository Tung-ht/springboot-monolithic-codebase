package nta.bookstore.api.service;

import org.springframework.core.io.Resource;

import java.io.IOException;
import java.net.MalformedURLException;

public interface ImageService {
    String uploadImage(String base64Source, String customFileName) throws IOException;
    String loadImageAsBase64Source(String fileName);
}
