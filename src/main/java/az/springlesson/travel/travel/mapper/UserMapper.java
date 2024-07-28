package az.springlesson.travel.travel.mapper;

import az.springlesson.travel.travel.dao.UserRequest;
import az.springlesson.travel.travel.dao.UserResponse;
import az.springlesson.travel.travel.entity.UserEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public enum UserMapper {
    USER_MAPPER;

    public UserEntity mapToEntity(UserRequest userRequest) {
        return UserEntity.builder()
                .age(userRequest.getAge())
                .email(userRequest.getEmail())
                .password(new BCryptPasswordEncoder().encode(userRequest.getPassword()))
                .username(userRequest.getUsername()).build();

    }

    public UserResponse mapToResponse(UserEntity userEntity) {
        return UserResponse.builder()
                .age(userEntity.getAge())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .username(userEntity.getUsername()).build();
    }
}
