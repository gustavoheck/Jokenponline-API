package heck.jokenponline.auth.internal.domain.aggregate;

import heck.jokenponline.auth.internal.domain.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "email_validation_token")
public class EmailValidationToken {

    @Id
    @OneToOne
    private UUID token;

    @Column(name = "expiration_time", nullable = false)
    private LocalDateTime expirationTime;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_users", referencedColumnName = "id")
    private User user;

    public EmailValidationToken(UUID token) {
        this.token = UUID.randomUUID();
        this.expirationTime = LocalDateTime.now().plusMinutes(10);
    }

    public Boolean isTokenValid () {
        return LocalDateTime.now().isBefore(expirationTime);
    }
}
