package blood_donor.blood_donor.Java;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
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

}
