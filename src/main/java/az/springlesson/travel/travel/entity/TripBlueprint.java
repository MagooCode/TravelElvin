package az.springlesson.travel.travel.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="trip_blueprint")
public class TripBlueprint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "default_place")
    private String defaultPlace;
    @Column(name="default_trip_time")
    private Integer defaultTripTime;
    @Column(name = "additional_services")
    private String additionalServices;



}
