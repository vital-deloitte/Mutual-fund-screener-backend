package Mutualfundscreenercom.example.Mutualfundapp.extrabody;

import Mutualfundscreenercom.example.Mutualfundapp.entities.MutualFund;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
public class ReturnUserDetails {

    private Long Id;
    private String userName;
    private String email;
    private Boolean isActive;
    private List<MutualFund> wishList;
}
