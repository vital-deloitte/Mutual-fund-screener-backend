package Mutualfundscreenercom.example.Mutualfundapp.extrabody;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmailVerify {
    private String toEmail;
    private String link;
}
