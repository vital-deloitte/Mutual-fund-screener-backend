package Mutualfundscreenercom.example.Mutualfundapp.extrabody;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class ReturnUserDetails {

    private Long Id;
    private String userName;
    private String email;
    private Boolean isActive;
}
