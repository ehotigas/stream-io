package stream.io.api.video;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVideoRepository extends JpaRepository<VideoEntity, Long> {
    
}
