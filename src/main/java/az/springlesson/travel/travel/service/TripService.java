package az.springlesson.travel.travel.service;

import az.springlesson.travel.travel.dao.TripRequest;
import az.springlesson.travel.travel.dao.TripResponse;
import az.springlesson.travel.travel.entity.TripEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public interface TripService {
    List <TripResponse> getAllTrips();
    void createCustomTrip(TripRequest tripRequest,String username);
    void  createTripWithBlueprint(TripRequest tripRequest,Long id,String username);
    Map<String,Object> getBenefit(LocalDateTime start, LocalDateTime end);
}
