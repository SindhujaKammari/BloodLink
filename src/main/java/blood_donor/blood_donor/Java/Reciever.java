package blood_donor.blood_donor.Java;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor 
@Entity 
public class Reciever {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recieverId;
    
    private String recieverName;
    private String bloodType;
    private String emergencyLevel;
    private String hospitalName;    
    private String surgeryType;
    private String RecieverArea;
    private Integer unitRequired;
}
