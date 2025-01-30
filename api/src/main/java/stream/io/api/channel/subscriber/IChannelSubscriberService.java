package stream.io.api.channel.subscriber;

import java.time.LocalDateTime;

import stream.io.api.channel.subscriber.dto.CreateChannelSubscriberDto;
import stream.io.api.channel.subscriber.dto.GetChannelSubscriberDto;

public interface IChannelSubscriberService {
    GetChannelSubscriberDto findAll(Long id, Long subscriber, Long targetChannel, LocalDateTime subscribedAt);
    ChannelSubscriberEntity findById(Long id);
    ChannelSubscriberEntity save(CreateChannelSubscriberDto dto);
    ChannelSubscriberEntity remove(Long id);
}
