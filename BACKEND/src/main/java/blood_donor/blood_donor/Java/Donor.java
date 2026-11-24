package blood_donor.blood_donor.Java;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonSetter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Donor{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID donorId;
    private String donorName;
    private String bloodType;
    private String phoneNum; 
    private String donorArea;
    private String donorMandal;
    private String donorDistrict;
    private LocalDate lastDonate;

    @JsonSetter("lastDonate")
    public void setLastDonateFromJson(String value) {
        if (value == null || value.isBlank()) {
            this.lastDonate = null;
            return;
        }
        try {
            this.lastDonate = LocalDate.parse(value, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException exception) {
            this.lastDonate = LocalDate.parse(value, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        }
    }

}
