package Mutualfundscreenercom.example.Mutualfundapp.extrabody;

import Mutualfundscreenercom.example.Mutualfundapp.entities.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class UserExtraBody {
    private String username;
    private String password;
    private String email;

    public Users getUserFromExtraBody(){
        Users user = new Users();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        return user;
    }
}
