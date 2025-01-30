package stream.io.api.channel.subscriber;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import stream.io.api.channel.ChannelEntity;


@Entity
@Table(name = "channel_subscriber")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChannelSubscriberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "subscriber_id")
    private ChannelEntity subscriber;
    
    @ManyToOne
    @JoinColumn(name = "target_channel_id")
    private ChannelEntity targetChannel;

    @Column(name = "subscribed_at")
    private LocalDateTime subscribedAt;
}
