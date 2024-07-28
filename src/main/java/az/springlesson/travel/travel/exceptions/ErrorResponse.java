package az.springlesson.travel.travel.exceptions;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ErrorResponse {
    private String message;
    private Integer status;
    private String path;
    private LocalDateTime timestamp;
    private List<String> errors;
}
