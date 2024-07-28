package az.springlesson.travel.travel.dao;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TripRequest {
    private String customerName;
    private Integer adults;
    private Integer children;
    private Integer infrant;
    private Integer tripTime;
    private Float price;
    private String place;
    private String additionalServices;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime tripDate;
}
