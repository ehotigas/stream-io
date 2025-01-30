package stream.io.api.channel.subscriber;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import stream.io.api.channel.subscriber.dto.CreateChannelSubscriberDto;
import stream.io.api.channel.subscriber.dto.GetChannelSubscriberDto;

@RestController
@RequestMapping("/channel-subscriber")
public class ChannelSubscriberController {
    @Autowired
    private IChannelSubscriberService service;

    @GetMapping
    public ResponseEntity<GetChannelSubscriberDto> findAll(
        @RequestParam(name = "id", required = false) Long id,
        @RequestParam(name = "subscriber", required = false) Long subscriber,
        @RequestParam(name = "target-channel", required = false) Long targetChannel,
        @RequestParam(name = "subscribed-at", required = false) LocalDateTime subscribedAt
    ) {
        return ResponseEntity.ok().body(service.findAll(id, subscriber, targetChannel, subscribedAt));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChannelSubscriberEntity> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<ChannelSubscriberEntity> save(@Valid @RequestBody CreateChannelSubscriberDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ChannelSubscriberEntity> remove(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(service.remove(id));
    }
}
