package stream.io.api.channel.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateChannelDto {
    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String username;
    
    private String description;
    
    @NotNull
    @NotBlank
    private String password;
}
