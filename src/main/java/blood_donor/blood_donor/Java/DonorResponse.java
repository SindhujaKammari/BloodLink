package blood_donor.blood_donor.Java;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor 
@Entity
public class DonorResponse {
    @Id
    private Long responseDonorId;
    private String donorName;
    private String bloodType;
    private String area;
    private String phoneNum;
}
