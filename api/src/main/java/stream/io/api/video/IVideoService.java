package stream.io.api.video;

import java.time.LocalDateTime;

import stream.io.api.video.dto.CreateVideoDto;
import stream.io.api.video.dto.GetVideoDto;
import stream.io.api.video.dto.UpdateVideoDto;


public interface IVideoService {
    GetVideoDto findAll(Long id, String title, String content, Long channel, String description, Long likeAmount, Long dislikeAmount, Long viewAmount, Long commentAmount, LocalDateTime uploadedAt, Boolean isPublic, Boolean isDeleted, LocalDateTime deletedAt);
    VideoEntity findById(Long id);
    VideoEntity save(CreateVideoDto dto);
    VideoEntity update(Long id, UpdateVideoDto dto);
    VideoEntity remove(Long id);
}
