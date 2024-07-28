package az.springlesson.travel.travel.controller;

import az.springlesson.travel.travel.dao.TripRequest;
import az.springlesson.travel.travel.dao.TripResponse;
import az.springlesson.travel.travel.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/trip")
public class TripController {

    private final TripService tripService;

    @Autowired
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/list")
    public ResponseEntity<List<TripResponse>> getAllTrips(){
        return ResponseEntity.ok(tripService.getAllTrips());
    }

    @PreAuthorize("hasAuthority('CONSULTANT')")
    @PostMapping("/create")
    public void createCustomTrip(@RequestBody TripRequest tripRequest){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
         tripService.createCustomTrip(tripRequest,username);
    }

    @PreAuthorize("hasAuthority('CONSULTANT')")
    @PostMapping("/create/blueprint/{id}")
    public void createTripWithBlueprint(@RequestBody TripRequest tripRequest,@PathVariable Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        tripService.createTripWithBlueprint(tripRequest,id,username);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/benefit")
    public ResponseEntity<Map<String,Object>> getBenefit(@RequestParam LocalDateTime start, @RequestParam LocalDateTime end){
        return ResponseEntity.ok(tripService.getBenefit(start,end));
    }



}
