package az.springlesson.travel.travel.service.ServiceImpl;

import az.springlesson.travel.travel.dao.UserRequest;
import az.springlesson.travel.travel.dao.UserResponse;
import az.springlesson.travel.travel.entity.UserEntity;
import az.springlesson.travel.travel.entity.UserRole;
import az.springlesson.travel.travel.mapper.UserMapper;
import az.springlesson.travel.travel.repository.UserRepository;
import az.springlesson.travel.travel.repository.UserRoleRepository;
import az.springlesson.travel.travel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public UserEntity findUserByEmail(String email) {

        return userRepository.findByEmail(email).orElse(null);
    }


    @Override
    public void saveUser(UserRequest userRequest) {
         userRepository.save(UserMapper.USER_MAPPER.mapToEntity(userRequest));
    }

    @Override
    public List<UserResponse> getAllUsers(){
        return userRepository.findAll().stream().map(UserMapper.USER_MAPPER::mapToResponse).toList();
    }
    @Override
    public void setUserRole(Long userId,Long roleId){
        UserEntity userEntity=userRepository.findById(userId).orElse(null);
        UserRole userRole = userRoleRepository.findById(roleId).orElse(null);
        if(userEntity==null){
            throw new UsernameNotFoundException("User not found");
        }
        else if(userRole==null){
            throw new UsernameNotFoundException("Role not found");
        }
        else{
            userEntity.getRoles().add(userRole);
            userRepository.save(userEntity);
        }

    }











}
