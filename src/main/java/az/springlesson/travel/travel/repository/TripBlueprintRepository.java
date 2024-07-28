package az.springlesson.travel.travel.repository;

import az.springlesson.travel.travel.entity.TripBlueprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripBlueprintRepository extends JpaRepository<TripBlueprint,Long> {
}
