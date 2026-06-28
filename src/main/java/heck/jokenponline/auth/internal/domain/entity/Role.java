package heck.jokenponline.auth.internal.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;

import java.util.Objects;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "role", schema = "auth")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, unique = true)
    String role;

    public Role(String role) {
        this.role = role;
    }

    @Override
    public @Nullable String getAuthority() {
        return this.role;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || this.getClass() != obj.getClass()) return false;

        Role role = (Role) obj;
        return Objects.equals(id, role.id);
    }
}
