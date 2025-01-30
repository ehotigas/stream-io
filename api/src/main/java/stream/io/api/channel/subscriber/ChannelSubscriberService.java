package stream.io.api.channel.subscriber;

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
import stream.io.api.channel.subscriber.dto.CreateChannelSubscriberDto;
import stream.io.api.channel.subscriber.dto.GetChannelSubscriberDto;
import stream.io.api.global.exception.BadRequestException;
import stream.io.api.global.exception.NotFoundException;


@Service
public class ChannelSubscriberService implements IChannelSubscriberService {
    @Autowired
    private IChannelSubscriberRepository repository;

    @Autowired
    private IChannelService channelService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public GetChannelSubscriberDto findAll(Long id, Long subscriber, Long targetChannel, LocalDateTime subscribedAt) {
        ChannelSubscriberEntity.ChannelSubscriberEntityBuilder filtersBuilder = ChannelSubscriberEntity.builder().id(id).subscribedAt(subscribedAt);
        logger.info("test");
        if (subscriber != null) {
            ChannelEntity entity = channelService.findById(subscriber);
            logger.info(entity.toString());
            filtersBuilder = filtersBuilder.subscriber(entity);
        }
        if (targetChannel != null) {
            filtersBuilder = filtersBuilder.targetChannel(channelService.findById(targetChannel));
        }
        ChannelSubscriberEntity filters = filtersBuilder.build();
        logger.info("Fetching all channel subscriber with: " + filters);
        List<ChannelSubscriberEntity> channelSubscriberList = repository.findAll(
            Example.of(filters, ExampleMatcher.matchingAll().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING))
        );
        return new GetChannelSubscriberDto(channelSubscriberList);
    }

    @Override
    public ChannelSubscriberEntity findById(Long id) {
        logger.info("Fetching channel subscriber with id: " + id);
        return repository.findById(id).orElseThrow(() -> {
            logger.warn("Fail to fetch channel subscriber with id: " + id + ". Channel subscriber not found");
            throw new NotFoundException("Fail to fetch channel subscriber with id: " + id + ". Channel subscriber not found");
        });
    }

    @Override
    public ChannelSubscriberEntity remove(Long id) {
        logger.info("Removing channel subscriber with id: " + id);
        ChannelSubscriberEntity channelSubscriberEntity = repository.findById(id).orElseThrow(() -> {
            logger.warn("Fail to remove channel subscriber with id: " + id + ". Channel subscriber not found");
            throw new NotFoundException("Fail to remove channel subscriber with id: " + id + ". Channel subscriber not found");
        });
        repository.delete(channelSubscriberEntity);
        return channelSubscriberEntity;
    }

    @Override
    public ChannelSubscriberEntity save(CreateChannelSubscriberDto dto) {
        logger.info("Saving new channel subscriber with: " + dto);
        if (dto.getSubscriber().equals(dto.getTargetChannel())) {
            throw new BadRequestException("Fail to subscribe on channel with id: " + dto.getTargetChannel() + ". Cannot subscribe in your channel.");
        }
        ChannelEntity subscriber = channelService.findById(dto.getSubscriber());
        ChannelEntity target = channelService.findById(dto.getTargetChannel());

        if (repository.findBySubscriberAndTargetChannel(subscriber, target).isPresent()) {
            throw new BadRequestException("You already subscribed on channel id: " + dto.getTargetChannel());
        }
        ChannelSubscriberEntity entity = ChannelSubscriberEntity.builder().subscribedAt(
            LocalDateTime.now()
        ).subscriber(
            subscriber
        ).targetChannel(
            target
        ).build();
        return repository.save(entity);
    }
}
