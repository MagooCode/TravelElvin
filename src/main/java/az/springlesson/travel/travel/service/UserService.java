package az.springlesson.travel.travel.service;

import az.springlesson.travel.travel.dao.UserRequest;
import az.springlesson.travel.travel.dao.UserResponse;
import az.springlesson.travel.travel.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserEntity findUserByEmail(String email);
    void saveUser(UserRequest userRequest);
    List<UserResponse> getAllUsers();
    void setUserRole(Long userId,Long roleId);

}
