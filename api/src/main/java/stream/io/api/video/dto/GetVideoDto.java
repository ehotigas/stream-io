package stream.io.api.video.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import stream.io.api.video.VideoEntity;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetVideoDto {
    private List<VideoEntity> videoList;
}
