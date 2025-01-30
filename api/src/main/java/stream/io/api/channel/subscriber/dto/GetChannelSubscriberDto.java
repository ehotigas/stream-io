package stream.io.api.channel.subscriber.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import stream.io.api.channel.subscriber.ChannelSubscriberEntity;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetChannelSubscriberDto {
    private List<ChannelSubscriberEntity> channelSubscriberList;
}
