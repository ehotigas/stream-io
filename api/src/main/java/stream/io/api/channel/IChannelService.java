package stream.io.api.channel;

import stream.io.api.channel.dto.CreateChannelDto;
import stream.io.api.channel.dto.GetChannelDto;
import stream.io.api.channel.dto.UpdateChannelDto;

public interface IChannelService {
    GetChannelDto findAll(ChannelEntity filters);
    ChannelEntity findById(Long id);
    ChannelEntity findByUsername(String username);
    ChannelEntity save(CreateChannelDto dto);
    ChannelEntity update(Long id, UpdateChannelDto dto);
    ChannelEntity remove(Long id);
    ChannelEntity addSubscriberAmount(Long id);
    ChannelEntity addViewAmount(Long id);
    ChannelEntity addVideoAmount(Long id);
    ChannelEntity subtractSubscriberAmount(Long id);
    ChannelEntity subtractVideoAmount(Long id);
}
