package blood_donor.blood_donor.DTO;

import java.util.List;

import blood_donor.blood_donor.Java.donor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class RecieverResponseDTO {
    @NotNull(message = "Receiver ID is required")
    private Long recieverId;

    @NotBlank(message = "Receiver name is required")
    private String recieverName;

    @NotBlank(message = "Blood type is required")
    private String bloodType;

    @NotBlank(message = "Hospital name is required")
    private String hospitalName;

   
}