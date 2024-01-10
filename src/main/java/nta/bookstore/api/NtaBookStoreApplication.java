package nta.bookstore.api;

import nta.bookstore.api.config.MediaStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableConfigurationProperties(MediaStorageProperties.class)
@EnableAsync
public class NtaBookStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(NtaBookStoreApplication.class, args);
    }

}
