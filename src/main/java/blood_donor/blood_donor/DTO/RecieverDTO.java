package blood_donor.blood_donor.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor 
public class RecieverDTO {
    private Long recieverId;

    @NotBlank (message = "Reciever name is required")
    private String recieverName;

    @NotBlank (message = "Blood type is required")
    private String bloodType;

    @NotBlank (message = "Emergency level is required")
    private String emergencyLevel;
    @NotBlank (message = "Hospital name is required")
    private String hospitalName;
    @NotBlank (message = "Surgery type is required")
    private String surgeryType;
    @NotBlank (message = "Area is required")
    private String RecieverArea;
    @NotBlank(message="Mentioning Units is mandatory!!!!!")
    private String unitsRequired;


}
