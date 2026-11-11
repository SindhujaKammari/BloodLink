package blood_donor.blood_donor.Java;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor 
@Entity @JsonInclude(JsonInclude.Include.NON_NULL)
public class DonorResponse {
    @Id
    private Long responseDonorId;
    private String donorName;
    private String bloodType;
    private String area;
    private String mandal;
    private String district;
    private Integer unitsDonated;
    private String status;
}
