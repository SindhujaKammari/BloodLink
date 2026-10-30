package blood_donor.blood_donor.Java;


import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class donor {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long donorId ; 
    private String donorName;
    private String bloodType;
    private String phoneNum; 
    private String donorArea;
    private String donorMandal;
    private String donorDistrict;
    private String donorEmail;
    private LocalDate lastDonate;
}
