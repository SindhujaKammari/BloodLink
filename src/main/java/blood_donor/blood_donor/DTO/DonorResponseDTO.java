package blood_donor.blood_donor.DTO;

import jakarta.validation.constraints.NotBlank;
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

    @NotBlank (message = "Mandal name is mandatory")
    private String mandal;
    
    @NotBlank (message = "district name is mandatory")
    private String district;

    @NotBlank(message = "Please donate ATLEAST 1 unit of blood")
    private Integer unitsDonated;

    @NotBlank (message = "Please specify the status of the donor")
    private String status;
}
