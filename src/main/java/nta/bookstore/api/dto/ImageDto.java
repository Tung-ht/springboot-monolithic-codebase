package nta.bookstore.api.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ImageDto {
    String customFileName;
    String base64Source;
}
