package heck.jokenponline.auth.internal.infra.repository;

import heck.jokenponline.auth.internal.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRole (String role);
}
