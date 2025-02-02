package stream.io.api.video;

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
@Table(name = "video")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VideoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(length = 255)
    private String title;

    @Column
    private String content;

    @ManyToOne
    @JoinColumn(name = "channel_id")
    private ChannelEntity channel;

    @Column
    private String description;

    @Column(name = "like_amount")
    private Long likeAmount;

    @Column(name = "dislike_amount")
    private Long dislikeAmount;
    
    @Column(name = "view_amount")
    private Long viewAmount;
    
    @Column(name = "comment_amount")
    private Long commentAmount;
    
    @Column(name = "uploaded_at")
    private LocalDateTime uploadedAt;
    
    @Column(name = "is_public")
    private Boolean isPublic;
    
    @Column(name = "is_deleted")
    private Boolean isDeleted;
    
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}
