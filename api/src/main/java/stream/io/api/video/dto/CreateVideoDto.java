package stream.io.api.video.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateVideoDto {
    @NotNull @NotBlank private String title;

    @NotNull @NotBlank private String content;

    @NotNull private Long channelId;
    
    @NotNull private String description;
    
    @NotNull private Boolean isPublic;
}
