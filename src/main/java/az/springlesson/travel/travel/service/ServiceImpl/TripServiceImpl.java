package az.springlesson.travel.travel.service.ServiceImpl;

import az.springlesson.travel.travel.dao.TripRequest;
import az.springlesson.travel.travel.dao.TripResponse;
import az.springlesson.travel.travel.entity.TripBlueprint;
import az.springlesson.travel.travel.entity.TripEntity;
import az.springlesson.travel.travel.entity.UserEntity;
import az.springlesson.travel.travel.repository.TripBlueprintRepository;
import az.springlesson.travel.travel.repository.TripRepository;
import az.springlesson.travel.travel.repository.UserRepository;
import az.springlesson.travel.travel.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import  az.springlesson.travel.travel.mapper.TripMapper;
import static az.springlesson.travel.travel.mapper.TripMapper.TRIP_MAPPER;

@Service
public class TripServiceImpl implements TripService {

    private final TripRepository tripRepository;
    private final UserRepository userRepository;
    private final TripBlueprintRepository tripBlueprintRepository;

    @Autowired
    public TripServiceImpl(TripRepository tripRepository, UserRepository userRepository, TripBlueprintRepository tripBlueprintRepository) {
        this.tripRepository = tripRepository;
        this.userRepository = userRepository;
        this.tripBlueprintRepository = tripBlueprintRepository;
    }

    @Override
    public List<TripResponse> getAllTrips(){
        return tripRepository.findAll().stream().map(TRIP_MAPPER :: mapToResponse ).toList();
    }

    @Override
    public void createCustomTrip(TripRequest tripRequest,String username) {

        TripEntity tripEntity = TRIP_MAPPER.mapToEntity(tripRequest);
        tripEntity.setUser(userRepository.findByUsername(username));
        tripRepository.save(tripEntity);
    }

    @Override
    public void createTripWithBlueprint(TripRequest tripRequest,Long id,String username) {
        TripBlueprint tripBlueprint = tripBlueprintRepository.findById(id).orElse(null);
        UserEntity userEntity = userRepository.findByUsername(username);
        TripEntity tripEntity = TripEntity.builder()
                        .adults(tripRequest.getAdults())
                        .children(tripRequest.getChildren())
                        .infrant(tripRequest.getInfrant())
                        .tripTime(tripBlueprint.getDefaultTripTime())
                        .price(tripRequest.getPrice())
                        .place(tripBlueprint.getDefaultPlace())
                        .additionalServices(tripBlueprint.getAdditionalServices())
                        .user(userEntity).build();


        tripRepository.save(tripEntity);

    }

    @Override
    public Map<String, Object> getBenefit(LocalDateTime start, LocalDateTime end){
        List<TripEntity> trips = tripRepository.findAllByTripDateBetween(start,end);
        Float totalBenefit = trips.stream().
                map(TripEntity::getPrice)
                .reduce(0.0f,Float::sum);

        Map<String,Object> result = new HashMap<>();
        result.put("Total Benefit is : "+totalBenefit,trips);
        return result;


    }







}
