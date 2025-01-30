package stream.io.api.channel;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import stream.io.api.channel.dto.CreateChannelDto;
import stream.io.api.channel.dto.UpdateChannelDto;


@Mapper(componentModel = "spring")
public interface IChannelMapper {
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "subscribersAmount", ignore = true)
    @Mapping(target = "videosAmount", ignore = true)
    @Mapping(target = "viewsAmount", ignore = true)
    @Mapping(target = "subscribedChannelList", ignore = true)
    @Mapping(target = "subscriberChannelList", ignore = true)
    ChannelEntity toEntity(CreateChannelDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "subscribersAmount", ignore = true)
    @Mapping(target = "videosAmount", ignore = true)
    @Mapping(target = "viewsAmount", ignore = true)
    @Mapping(target = "subscribedChannelList", ignore = true)
    @Mapping(target = "subscriberChannelList", ignore = true)
    ChannelEntity partialUpdate(@MappingTarget ChannelEntity entity, UpdateChannelDto dto);
}
