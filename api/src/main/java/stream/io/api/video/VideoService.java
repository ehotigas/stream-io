package stream.io.api.video;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import stream.io.api.channel.ChannelEntity;
import stream.io.api.channel.IChannelService;
import stream.io.api.global.exception.NotFoundException;
import stream.io.api.video.dto.CreateVideoDto;
import stream.io.api.video.dto.GetVideoDto;
import stream.io.api.video.dto.UpdateVideoAnalyticsDto;
import stream.io.api.video.dto.UpdateVideoDto;


@Service
public class VideoService implements IVideoService {
    @Autowired private IVideoRepository repository;
    @Autowired private IChannelService channelService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public GetVideoDto findAll(Long id, String title, String content, Long channel, String description, Long likeAmount, Long dislikeAmount, Long viewAmount, Long commentAmount, LocalDateTime uploadedAt, Boolean isPublic, Boolean isDeleted, LocalDateTime deletedAt) {
        VideoEntity filters = new VideoEntity(id, title, content, null, description, likeAmount, dislikeAmount, viewAmount, commentAmount, uploadedAt, isPublic, isDeleted, deletedAt);
        if (channel != null) filters.setChannel(channelService.findById(id));
        logger.info("Fetching all videos with: " + filters);
        List<VideoEntity> videoList = repository.findAll(
            Example.of(filters, ExampleMatcher.matchingAll().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING))
        );
        return new GetVideoDto(videoList);
    }

    @Override
    public VideoEntity findById(Long id) {
        logger.info("Fetching video with id: " + id);
        return repository.findById(id).orElseThrow(() -> {
            logger.warn("Fail to fetch video with id: " + id + ". Video not found");
            throw new NotFoundException("Fail to fetch video with id: " + id + ". Video not found");
        });
    }

    @Override
    public VideoEntity save(CreateVideoDto dto) {
        logger.info("Saving new video with: " + dto);
        ChannelEntity channel = channelService.findById(dto.getChannelId());
        VideoEntity videoEntity = new VideoEntity(
            null, dto.getTitle(), dto.getContent(), channel, dto.getDescription(),
            Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0),
            LocalDateTime.now(), dto.getIsPublic(), false, null
        );
        return repository.save(videoEntity);
    }

    @Override
    public VideoEntity update(Long id, UpdateVideoDto dto) {
        logger.info("Updating video with id: " + id);
        VideoEntity videoEntity = repository.findById(id).orElseThrow(() -> {
            logger.warn("Fail to remove video with id: " + id + ". Video not found");
            throw new NotFoundException("Fail to remove video with id: " + id + ". Video not found");
        });
        if (dto.getIsPublic() != null) {
            videoEntity.setIsPublic(dto.getIsPublic());
        }
        if (dto.getDescription() != null) {
            videoEntity.setDescription(dto.getDescription());
        }
        if (dto.getTitle() != null) {
            videoEntity.setTitle(dto.getTitle());
        }
        return repository.save(videoEntity);
    }

    public VideoEntity updateVideoAnalytics(Long id, UpdateVideoAnalyticsDto dto) {
        VideoEntity video = findById(id);
        if (dto.getCommentAmount() != null) {
            video.setCommentAmount(dto.getCommentAmount());
        }
        if (dto.getDislikeAmount() != null) {
            video.setDislikeAmount(dto.getDislikeAmount());
        }
        if (dto.getLikeAmount() != null) {
            video.setLikeAmount(dto.getLikeAmount());
        }
        if (dto.getViewAmount() != null) {
            video.setViewAmount(dto.getViewAmount());
        }
        return repository.save(video);
    }

    @Override
    public VideoEntity remove(Long id) {
        logger.info("Removing video with id: " + id);
        VideoEntity video = repository.findById(id).orElseThrow(() -> {
            logger.warn("Fail to remove video with id: " + id + ". Video not found");
            throw new NotFoundException("Fail to remove video with id: " + id + ". Video not found");
        });
        video.setIsDeleted(true);
        video.setDeletedAt(LocalDateTime.now());
        return repository.save(video);
    }
}
