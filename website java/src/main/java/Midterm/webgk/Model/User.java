package Midterm.webgk.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer UserID;
    private String UserName;
    private String Password;
    private String Email;
    private String Role;

    public User(String userName, String password, String email, String role) {
        UserName = userName;
        Password = password;
        Email = email;
        Role = role;
    }
}
