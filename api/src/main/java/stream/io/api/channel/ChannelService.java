package stream.io.api.channel;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import stream.io.api.channel.dto.CreateChannelDto;
import stream.io.api.channel.dto.GetChannelDto;
import stream.io.api.channel.dto.UpdateChannelDto;
import stream.io.api.global.exception.BadRequestException;
import stream.io.api.global.exception.NotFoundException;

@Service
public class ChannelService implements IChannelService {
    @Autowired
    private IChannelRepository channelRepository;

    @Autowired
    private IChannelMapper mapper;

    private final Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public GetChannelDto findAll(ChannelEntity filters) {
        logger.info("Fetching all channels with: " + filters);
        List<ChannelEntity> channelList = channelRepository.findAll(
            Example.of(filters, ExampleMatcher.matchingAll().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING))
        );
        return new GetChannelDto(channelList);
    }

    @Override
    public ChannelEntity findById(Long id) {
        logger.info("Fetching channel with id: " + id);
        return channelRepository.findById(id).orElseThrow(() -> {
            logger.warn("Fail to fetch channel with id: " + id + ". Channel not found");
            throw new NotFoundException("Fail to fetch channel with id: " + id + ". Channel not found");
        });
    }

    @Override
    public ChannelEntity findByUsername(String username) {
        logger.info("Fetching channel with username: " + username);
        return channelRepository.findByUsername(username).orElseThrow(() -> {
            logger.warn("Fail to fetch channel with username: " + username + ". Channel not found");
            throw new NotFoundException("Fail to fetch channel with username: " + username + ". Channel not found");
        });
    }

    @Override
    public ChannelEntity remove(Long id) {
        logger.info("Removing channel with id: " + id);
        ChannelEntity channel = channelRepository.findById(id).orElseThrow(() -> {
            logger.warn("Fail to remove channel with id: " + id + ". Channel not found");
            throw new NotFoundException("Fail to remove channel with id: " + id + ". Channel not found");
        });
        channelRepository.delete(channel);
        return channel;
    }

    @Override
    public ChannelEntity save(CreateChannelDto dto) {
        logger.info("Saving new channel with: " + dto);
        if (channelRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new BadRequestException("Fail to create channel. " + dto.getUsername() + " username already exists.");
        }
        ChannelEntity channelToSave = mapper.toEntity(dto);
        channelToSave.setCreatedAt(LocalDateTime.now());
        channelToSave.setIsActive(true);
        // channelToSave.getPassword()
        channelToSave.setSubscribersAmount(Long.valueOf(0));
        channelToSave.setVideosAmount(Long.valueOf(0));
        channelToSave.setViewsAmount(Long.valueOf(0));
        return channelRepository.save(channelToSave);
    }

    @Override
    public ChannelEntity update(Long id, UpdateChannelDto dto) {
        logger.info("Updating channel with id: " + id);
        ChannelEntity channel = channelRepository.findById(id).orElseThrow(() -> {
            logger.warn("Fail to update channel with id: " + id + ". Channel not found");
            throw new NotFoundException("Fail to update channel with id: " + id + ". Channel not found");
        });
        if (dto.getUsername() != null && channelRepository.findByUsername(dto.getUsername()).isPresent() && !channel.getUsername().equals(dto.getUsername())) {
            throw new BadRequestException("Fail to update channel. " + dto.getUsername() + " username already exists.");
        }
        System.out.println(channel);
        channel = mapper.partialUpdate(channel, dto);
        System.out.println(channel);
        return channelRepository.save(channel);
    }

    public ChannelEntity addSubscriberAmount(Long id) {
        ChannelEntity channel = findById(id);
        channel.setSubscribersAmount(channel.getSubscribersAmount() + 1);
        return channelRepository.save(channel);
    }
    
    public ChannelEntity addViewAmount(Long id) {
        ChannelEntity channel = findById(id);
        channel.setViewsAmount(channel.getViewsAmount() + 1);
        return channelRepository.save(channel);
    }

    public ChannelEntity addVideoAmount(Long id) {
        ChannelEntity channel = findById(id);
        channel.setVideosAmount(channel.getVideosAmount() + 1);
        return channelRepository.save(channel);
    }

    public ChannelEntity subtractSubscriberAmount(Long id) {
        ChannelEntity channel = findById(id);
        if (channel.getSubscribersAmount() <= 0) {
            throw new BadRequestException("Fail to subtract subscribers amount from channel with id: " + id + ". Subscribers amount cannot be less than 0");
        }
        channel.setSubscribersAmount(channel.getSubscribersAmount() - 1);
        return channelRepository.save(channel);
    }

    public ChannelEntity subtractVideoAmount(Long id) {
        ChannelEntity channel = findById(id);
        if (channel.getVideosAmount() <= 0) {
            throw new BadRequestException("Fail to subtract videos amount from channel with id: " + id + ". Videos amount cannot be less than 0");
        }
        channel.setVideosAmount(channel.getVideosAmount() - 1);
        return channelRepository.save(channel);
    }
}
