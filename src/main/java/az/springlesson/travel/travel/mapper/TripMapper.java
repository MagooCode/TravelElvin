package az.springlesson.travel.travel.mapper;

import az.springlesson.travel.travel.dao.TripRequest;
import az.springlesson.travel.travel.dao.TripResponse;
import az.springlesson.travel.travel.entity.TripEntity;

public enum TripMapper {
        TRIP_MAPPER;

        public TripEntity mapToEntity(TripRequest tripRequest){
            return TripEntity.builder().
                    tripDate(tripRequest.getTripDate()).
                    customerName(tripRequest.getCustomerName())
                    .adults(tripRequest.getAdults())
                        .children(tripRequest.getChildren())
                        .price(tripRequest.getPrice())
                        .place(tripRequest.getPlace())
                        .infrant(tripRequest.getInfrant())
                        .tripTime(tripRequest.getTripTime())
                    .additionalServices(tripRequest.getAdditionalServices()).build();
        }
        public TripResponse mapToResponse(TripEntity tripEntity){
                return TripResponse.builder().
                        id(tripEntity.getId()).
                        tripDate(tripEntity.getTripDate()).
                        customerName(tripEntity.getCustomerName())
                        .adults(tripEntity.getAdults())
                        .children(tripEntity.getChildren())
                        .price(tripEntity.getPrice())
                        .place(tripEntity.getPlace())
                        .infrant(tripEntity.getInfrant())
                        .tripTime(tripEntity.getTripTime())
                        .additionalServices(tripEntity.getAdditionalServices()).build();
        }


}
