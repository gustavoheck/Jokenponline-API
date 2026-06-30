package heck.jokenponline.auth.internal.domain.entity;

import heck.jokenponline.auth.internal.domain.aggregate.EmailValidationToken;
import heck.jokenponline.auth.internal.domain.exception.CantCreateEmailValidationTokenException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users", schema = "auth")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, updatable = false)
    private UUID uuid;

    @OneToOne(mappedBy = "token", cascade = CascadeType.ALL, orphanRemoval = true)
    private EmailValidationToken token;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private Boolean banned;

    private Boolean active;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_role",
            schema = "auth",
            joinColumns = @JoinColumn(name = "id_users"),
            inverseJoinColumns = @JoinColumn(name = "id_role")
    )
    private Set<Role> roles = new HashSet<>();

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.banned = false;
        this.active = false;
    }

    @PrePersist
    protected void generateEmailValidationToken() {
        this.generateNewToken();
    }

    public void generateNewToken () {
        if (this.uuid == null) {
            this.token = new EmailValidationToken();
        } else {
            throw new CantCreateEmailValidationTokenException("The user already have a UUID! Cannot create a validation token");
        }
    }

    public Boolean validateUser() {
        if (token != null) {
            if (token.isTokenValid()) {
                this.active = true;
                this.token = null;
                this.uuid = UUID.randomUUID();
                return true;
            }
            return false;
        }
        throw new CantCreateEmailValidationTokenException("Can't check if token is valid because it is null");
    }

    @Override
    public @NonNull Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public @NonNull String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
