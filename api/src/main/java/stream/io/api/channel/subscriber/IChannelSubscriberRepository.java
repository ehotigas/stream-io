package stream.io.api.channel.subscriber;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import stream.io.api.channel.ChannelEntity;


@Repository
public interface IChannelSubscriberRepository extends JpaRepository<ChannelSubscriberEntity, Long> {
    Optional<ChannelSubscriberEntity> findBySubscriberAndTargetChannel(ChannelEntity subscriber, ChannelEntity targetChannel);
}
