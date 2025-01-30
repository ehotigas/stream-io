package stream.io.api.channel;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import stream.io.api.channel.subscriber.ChannelSubscriberEntity;


@Entity
@Table(name = "channel")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChannelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @Column(unique = true)
    private String username;

    @Column(nullable = true)
    private String description;

    @Column
    private String password;

    @Column(name = "subscribers_amount")
    private Long subscribersAmount;

    @Column(name = "views_amount")
    private Long viewsAmount;

    @Column(name = "videos_amount")
    private Long videosAmount;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "is_active")
    private Boolean isActive;

    @OneToMany(mappedBy = "targetChannel", cascade = CascadeType.PERSIST, orphanRemoval = false)
    @JsonIgnore
    private List<ChannelSubscriberEntity> subscribedChannelList;

    @OneToMany(mappedBy = "subscriber", cascade = CascadeType.PERSIST, orphanRemoval = false)
    @JsonIgnore
    private List<ChannelSubscriberEntity> subscriberChannelList;
}
