package blood_donor.blood_donor.Java;

import jakarta.validation.constraints.NotNull;
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
    
    @NotNull(message = "What should we call u if its empty")
    private String donorName;

    @NotNull(message="Really!! please give us accurate info")
    private String bloodType;

    @NotNull(message="We really need this info , if emergency hits its the only way we can contact you!")
    private String phoneNum; 

    @NotNull(message="Area name is required to identify donors near the recipient")
    private String area;
    @NotNull(message="Mandal name is required to identify donors near the recipient")
    private String mandal;
    @NotNull(message="District name is required to identify donors near the recipient")
    private String district;

    
    private String donorEmail;
    private String lastDonate;
}
