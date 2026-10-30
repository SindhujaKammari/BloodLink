package blood_donor.blood_donor.Java;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Entity 
public class RecieverResponse {
    @Id
    private Long recieverId;
    private String recieverName;
    private String bloodType;
    private String hospitalName;
    private String donorName;
    private String donorPhoneNum;
    private String donorArea;
    private String donorMandal;
    private String donorDistrict;
}
