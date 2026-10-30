package blood_donor.blood_donor.DTO;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter @NoArgsConstructor @AllArgsConstructor 
public class DonorDTO {
    
    @NotBlank(message = "What should we call u if its empty")
    private String donorName;
    @NotBlank(message="Really!! please give us accurate info")
    private String bloodType;
    @NotBlank(message="We really need this info , if emergency hits its the only way we can contact you!")
    private String phoneNum; 
    @NotBlank(message="Area name is required to identify donors near the recipient")
    private String donorArea;
    @NotBlank(message="Mandal name is required to identify donors near the recipient")
    private String donorMandal;
    @NotBlank(message="District name is required to identify donors near the recipient")
    private String donorDistrict;
    private String donorEmail;
    private LocalDate lastDonate;
}
