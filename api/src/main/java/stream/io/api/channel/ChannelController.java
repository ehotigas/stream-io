package stream.io.api.channel;

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
import stream.io.api.channel.dto.CreateChannelDto;
import stream.io.api.channel.dto.GetChannelDto;
import stream.io.api.channel.dto.UpdateChannelDto;


@RestController
@RequestMapping("/channel")
public class ChannelController {
    @Autowired
    private IChannelService channelService;

    @GetMapping
    public ResponseEntity<GetChannelDto> findAll(
        @RequestParam(name = "id", required = false) Long id,
        @RequestParam(name = "name", required = false) String name,
        @RequestParam(name = "username", required = false) String username,
        @RequestParam(name = "description", required = false) String description,
        @RequestParam(name = "password", required = false) String password,
        @RequestParam(name = "subscribers-amount", required = false) Long subscribersAmount,
        @RequestParam(name = "views-amount", required = false) Long viewsAmount,
        @RequestParam(name = "videos-amount", required = false) Long videosAmount,
        @RequestParam(name = "created-at", required = false) LocalDateTime createdAt,
        @RequestParam(name = "is-active", required = false) Boolean isActive
    ) {
        ChannelEntity filters = new ChannelEntity(id, name, username, description, password, subscribersAmount, viewsAmount, videosAmount, createdAt, isActive, null, null);
        return ResponseEntity.ok().body(channelService.findAll(filters));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChannelEntity> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(channelService.findById(id));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<ChannelEntity> findByUsername(@PathVariable("username") String username) {
        return ResponseEntity.ok().body(channelService.findByUsername(username));
    }

    @PostMapping
    public ResponseEntity<ChannelEntity> save(@Valid @RequestBody CreateChannelDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(channelService.save(dto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ChannelEntity> update(@PathVariable("id") Long id, @Valid @RequestBody UpdateChannelDto dto) {
        return ResponseEntity.ok().body(channelService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ChannelEntity> remove(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(channelService.remove(id));
    }


    @PatchMapping("/subscriber-amount/add/{id}")
    public ResponseEntity<ChannelEntity> addSubscriberAmount(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(channelService.addSubscriberAmount(id));
    }

    @PatchMapping("/view-amount/add/{id}")
    public ResponseEntity<ChannelEntity> addViewAmount(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(channelService.addViewAmount(id));
    }

    @PatchMapping("/video-amount/add/{id}")
    public ResponseEntity<ChannelEntity> addVideoAmount(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(channelService.addVideoAmount(id));
    }

    @PatchMapping("/subscriber-amount/subtract/{id}")
    public ResponseEntity<ChannelEntity> subtractSubscriberAmount(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(channelService.subtractSubscriberAmount(id));
    }

    @PatchMapping("/video-amount/subtract/{id}")
    public ResponseEntity<ChannelEntity> subtractVideoAmount(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(channelService.subtractVideoAmount(id));
    }
}
