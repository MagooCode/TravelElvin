package az.springlesson.travel.travel.repository;

import az.springlesson.travel.travel.entity.TripEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<TripEntity,Long> {
    List<TripEntity> findAllByTripDateBetween(LocalDateTime start, LocalDateTime end);
}
