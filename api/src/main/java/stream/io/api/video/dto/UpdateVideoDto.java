package stream.io.api.video.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateVideoDto {
    private String title;
    // private String content;
    private String description;
    private Boolean isPublic;
}
