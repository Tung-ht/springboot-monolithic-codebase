package nta.bookstore.api.controller;

import lombok.RequiredArgsConstructor;
import nta.bookstore.api.dto.ImageDto;
import nta.bookstore.api.service.ImageService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

// api for testing
@RequiredArgsConstructor
@RestController
@RequestMapping("/images")
public class ImageController {
    private final ImageService imageService;

    @PostMapping("/upload")
    public String uploadImage(@RequestBody ImageDto dto) throws IOException {
        return imageService.uploadImage(dto.getBase64Source(), dto.getCustomFileName());
    }

    @GetMapping("/{name}")
    public String getImage(@PathVariable("name") String name){
        return imageService.loadImageAsBase64Source(name);
    }
}
