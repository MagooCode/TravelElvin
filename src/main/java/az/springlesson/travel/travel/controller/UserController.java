package az.springlesson.travel.travel.controller;

import az.springlesson.travel.travel.dao.UserResponse;
import az.springlesson.travel.travel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/list")
    public List<UserResponse> list() {
        return userService.getAllUsers();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("setRole")
    public void setRole(@RequestParam Long userId,@RequestParam Long roleId){
         userService.setUserRole(userId,roleId);
    }



}
