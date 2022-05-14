package Mutualfundscreenercom.example.Mutualfundapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.engine.spi.CascadeStyle;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class MutualFund{

    @Id
    private Long id;

    @Column(unique = true)
    private String name;
    private  String sub_category;
    private String plan;
    private float aum;
    private float cagr;
    private float expense_ratio;
    private String sebi_risk;
    private Boolean is_active=true;
}
