package nta.bookstore.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class YourController {
    private final MessageSource messageSource;

    @GetMapping("/test")
    public String exampleApi(@RequestParam(name = "lang", defaultValue = "en") String language) {
        // Convert the language string to a Locale object
        Locale locale = new Locale(language);

        // Get the message for the given key and locale

        // Your API logic here using the message
        return messageSource.getMessage("hello", null, locale);
    }
}