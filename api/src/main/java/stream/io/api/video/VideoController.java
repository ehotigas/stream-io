package stream.io.api.video;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import stream.io.api.video.dto.CreateVideoDto;
import stream.io.api.video.dto.GetVideoDto;
import stream.io.api.video.dto.UpdateVideoDto;


@RestController
@RequestMapping("/video")
public class VideoController {
    @Autowired private IVideoService service;

    @GetMapping
    public ResponseEntity<GetVideoDto> findAll(
        @RequestParam(name = "id", required = false) Long id,
        @RequestParam(name = "title", required = false) String title,
        @RequestParam(name = "content", required = false) String content,
        @RequestParam(name = "channel", required = false) Long channel,
        @RequestParam(name = "description", required = false) String description,
        @RequestParam(name = "like-amount", required = false) Long likeAmount,
        @RequestParam(name = "dislike-amount", required = false) Long dislikeAmount,
        @RequestParam(name = "view-amount", required = false) Long viewAmount,
        @RequestParam(name = "comment-amount", required = false) Long commentAmount,
        @RequestParam(name = "uploaded-at", required = false) LocalDateTime uploadedAt,
        @RequestParam(name = "is-public", required = false) Boolean isPublic,
        @RequestParam(name = "is-deleted", required = false) Boolean isDeleted,
        @RequestParam(name = "deleted-at", required = false) LocalDateTime deletedAt
    ) {
        return ResponseEntity.ok().body(service.findAll(id, title, content, channel, description, likeAmount, dislikeAmount, viewAmount, commentAmount, uploadedAt, isPublic, isDeleted, deletedAt));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideoEntity> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<VideoEntity> save(@Valid @RequestBody CreateVideoDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<VideoEntity> update(@PathVariable("id") Long id, @Valid @RequestBody UpdateVideoDto dto) {
        return ResponseEntity.ok().body(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<VideoEntity> remove(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(service.remove(id));
    }
}
