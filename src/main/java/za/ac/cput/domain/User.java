package za.ac.cput.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    private String firstName;
    private String lastName;
    @Column(unique = true, nullable = false)
    private String userName;
    private String password;
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private List<Role> roles;

    public User(long userId, String firstName, String lastName, String userName, String password, List<Role> roles) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}
