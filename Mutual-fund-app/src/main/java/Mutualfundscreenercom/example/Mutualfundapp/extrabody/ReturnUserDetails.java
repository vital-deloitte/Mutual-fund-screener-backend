package Mutualfundscreenercom.example.Mutualfundapp.extrabody;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Data @AllArgsConstructor @NoArgsConstructor @ToString @Component
public class ReturnUserDetails {

    private Long Id;
    private String userName;
    private String email;
    private Boolean isActive;
}
