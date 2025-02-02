package stream.io.api.video.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateVideoAnalyticsDto {
    private Long likeAmount;
    private Long dislikeAmount;
    private Long viewAmount;
    private Long commentAmount;
}
