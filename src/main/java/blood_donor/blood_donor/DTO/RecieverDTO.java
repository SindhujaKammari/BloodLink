package blood_donor.blood_donor.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor 
public class RecieverDTO {
    private Long recieverId;

    @NotBlank(message = "Receiver name is required")
    private String recieverName;

    @NotBlank(message = "Blood type is required")
    private String bloodType;

    @NotBlank(message = "Emergency level is required")
    private String emergencyLevel;

    @NotBlank(message = "Hospital name is required")
    private String hospitalName;

    @NotBlank(message = "Surgery type is required")
    private String surgeryType;

    @NotBlank(message = "Area is required")
    private String recieverArea;

    @NotNull(message = "Mentioning units is mandatory!")
    @Min(value = 1, message = "At least 1 unit is required")
    private Integer unitRequired;
}
