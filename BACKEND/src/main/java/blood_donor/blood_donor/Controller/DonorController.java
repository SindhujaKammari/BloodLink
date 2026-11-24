package blood_donor.blood_donor.Controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CrossOrigin;

import jakarta.validation.Valid;
import blood_donor.blood_donor.Java.Donor;
import blood_donor.blood_donor.Repos.DonorRepo;
import blood_donor.blood_donor.Service.DonorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin(origins = {"http://localhost:5173", "http://127.0.0.1:5173"})
public class DonorController {
    private final DonorService donorService;
    private final DonorRepo donorRepo;

    public DonorController(DonorService donorService , DonorRepo donorRepo) {
        this.donorService = donorService;
        this.donorRepo = donorRepo;
    }

    @GetMapping("/check/donor/{donor_id}")
    public boolean CheckDonor(@PathVariable UUID donorId) {
        if(donorId==null|| donorId.equals("")){throw new RuntimeException("Enter Proper DonorId");}
        return donorRepo.existsById(donorId);
    }

    @PostMapping("/donor/register")
    public ResponseEntity<String> registerDonor(@Valid @RequestBody Donor donor) {
        Donor response = donorService.registerDonor(donor);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body("Yay!! A new donor❤️..  \nThank you for becoming a blood donor🩸" + "\nName:" +response.getDonorName() + "\nID:"+response.getDonorId() +
                "\nBloodType:"+response.getBloodType());
    }

    @PutMapping("/donor/update/{donor_id}")
    public ResponseEntity<?> modifyDonor(@PathVariable String donor_id , @RequestBody Donor donor){
        UUID parsedDonorId;
        try {
            parsedDonorId = parseDonorId(donor_id);
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid donor ID.");
        }

        Donor particularDonor = donorRepo.findById(parsedDonorId).orElse(null);
        if(donor == null || particularDonor == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No donor found for the provided ID.");
        }
        return ResponseEntity.ok(donorService.modifyDonor(donor, parsedDonorId));
    }

    

    private UUID parseDonorId(String donorId) {
        String normalizedId = donorId.trim();
        if (normalizedId.toLowerCase().startsWith("0x")) {
            normalizedId = normalizedId.substring(2);
        }
        if (normalizedId.length() == 32) {
            normalizedId = normalizedId.substring(0, 8) + "-"
                    + normalizedId.substring(8, 12) + "-"
                    + normalizedId.substring(12, 16) + "-"
                    + normalizedId.substring(16, 20) + "-"
                    + normalizedId.substring(20);
        }
        return UUID.fromString(normalizedId);
    }

}
