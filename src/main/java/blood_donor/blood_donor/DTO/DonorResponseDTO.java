package blood_donor.blood_donor.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor 
public class DonorResponseDTO {
    @NotNull(message = "Cannot be empty")
    private Long responseDonorId;

    @NotNull(message = "Cannot be empty")
    private String donorName;

    @NotNull(message = "Cannot be empty")
    private String bloodType;

    @NotNull(message = "Cannot be empty")
    private String area;

    @NotNull(message = "Cannot be empty")
    private String phoneNum; 
}
