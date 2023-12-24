package nta.bookstore.api.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
public class Base64ToMultipartFile implements MultipartFile {
    @Override
    public String getName() {
        return "param_" + System.currentTimeMillis();
    }

    @Override
    public String getOriginalFilename() {
        return "file_" + System.currentTimeMillis() + "." + extension;
    }

    @Override
    public String getContentType() {
        return contentType;
    }

    @Override
    public boolean isEmpty() {
        return fileContent == null || fileContent.length == 0;
    }

    @Override
    public long getSize() {
        return fileContent.length;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return fileContent;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(fileContent);
    }

    @Override
    public void transferTo(File file) throws IOException, IllegalStateException {
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(fileContent);
        }
    }

    private final byte[] fileContent;
    private final String extension;     // ex: png
    private final String contentType;   // ex: image/png

//    example of data source: <img src='data:image/png;base64,iVBORw0KGgoAAAANSUhEU...' />
    public Base64ToMultipartFile(String source) {
        String dataUri = source.split(",")[0];
        String base64 = source.split(",")[1];
        this.fileContent = Base64.getDecoder().decode(base64.getBytes(StandardCharsets.UTF_8));
        this.extension = dataUri.split(";")[0].split("/")[1];
        this.contentType = dataUri.split(";")[0].split(":")[1];
    }

    /**
     * @param base64 : iVBORw0KGgoAAAANSUhEU...
     * @param dataUri : The format is similar to: data:image/png;base64
     * example of data source: <img src='data:image/png;base64,iVBORw0KGgoAAAANSUhEU...' />
     */
    public Base64ToMultipartFile(String base64, String dataUri) {
        this.fileContent = Base64.getDecoder().decode(base64.getBytes(StandardCharsets.UTF_8));
        this.extension = dataUri.split(";")[0].split("/")[1];
        this.contentType = dataUri.split(";")[0].split(":")[1];
    }

    public Base64ToMultipartFile(String base64, String extension, String contentType) {
        this.fileContent = Base64.getDecoder().decode(base64.getBytes(StandardCharsets.UTF_8));
        this.extension = extension;
        this.contentType = contentType;
    }

    public static MultipartFile convertBase64SourceToMultipart(String source) {
        try {
            return new Base64ToMultipartFile(source);
        } catch (Exception exception) {
            log.error(exception.getMessage());
            return null;
        }
    }

    public static String convertFileToBase64Source(File file) {
        try {
            byte[] fileContent = FileUtils.readFileToByteArray(file);
            String dataUri = "data:image/png;base64";
            String base64 = Base64.getEncoder().encodeToString(fileContent);
            return dataUri + "," + base64;
        } catch (Exception exception) {
            log.error(exception.getMessage());
            return null;
        }
    }
}
