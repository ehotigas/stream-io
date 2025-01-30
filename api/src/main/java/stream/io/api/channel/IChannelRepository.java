package stream.io.api.channel;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IChannelRepository extends JpaRepository<ChannelEntity, Long> {
    Optional<ChannelEntity> findByUsername(String username);
}
