package stream.io.api.channel.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import stream.io.api.channel.ChannelEntity;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetChannelDto {
    private List<ChannelEntity> channelList;
}
