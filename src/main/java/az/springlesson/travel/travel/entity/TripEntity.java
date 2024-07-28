package az.springlesson.travel.travel.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "trips")
@Entity

public class TripEntity {
    @Id
    @SequenceGenerator(name = "trip_id",sequenceName = "trip_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trip_id")
    private Long id;

    @Column(name = "customer_name")
    private String customerName;
    private Integer adults;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime tripDate;
    private Integer children;
    private Integer infrant;
    @Column(name = "trip_time")
    private Integer tripTime;
    @NotBlank(message = "Price must be written")
    private Float price;
    @NotBlank(message = "place must be written")
    private String place;
    @Column(name = "additional_services")
    private String additionalServices;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private UserEntity user;

}
