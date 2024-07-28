package az.springlesson.travel.travel.repository;

import az.springlesson.travel.travel.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
}
