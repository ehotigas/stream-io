package stream.io.api.video;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stream.io.api.channel.IChannelService;
import stream.io.api.video.dto.CreateVideoDto;
import stream.io.api.video.dto.GetVideoDto;
import stream.io.api.video.dto.UpdateVideoDto;

@Service
public class VideoService implements IVideoService {
    @Autowired private IVideoRepository repository;
    @Autowired private IChannelService channelService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public GetVideoDto findAll(Long id, String title, String content, Long channel, String description, Long likeAmount, Long dislikeAmount, Long viewAmount, Long commentAmount, LocalDateTime uploadedAt, Boolean isPublic, Boolean isDeleted, LocalDateTime deletedAt) {
        return null;
    }

    @Override
    public VideoEntity findById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public VideoEntity remove(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public VideoEntity save(CreateVideoDto dto) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public VideoEntity update(Long id, UpdateVideoDto dto) {
        // TODO Auto-generated method stub
        return null;
    }
}
