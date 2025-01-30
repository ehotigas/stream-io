package stream.io.api.channel.subscriber.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateChannelSubscriberDto {
    private Long subscriber;
    private Long targetChannel;
}
