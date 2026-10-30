package blood_donor.blood_donor.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.validation.Valid;
import blood_donor.blood_donor.DTO.DonorDTO;
import blood_donor.blood_donor.Java.DonorResponse;
import blood_donor.blood_donor.Java.donor;
import blood_donor.blood_donor.Repos.DonorRepo;
import blood_donor.blood_donor.Service.DonorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class DonorController {
    private final DonorService donorService;
    private final DonorRepo donorRepo;

    public DonorController(DonorService donorService , DonorRepo donorRepo) {
        this.donorService = donorService;
        this.donorRepo = donorRepo;
    }

    @GetMapping("/Check")
    public String justCheck() {
        return "works fine!!";
    }

    @PostMapping("/donor/register")
    public ResponseEntity<String> registerDonor(@Valid @RequestBody DonorDTO donorDto) {
        DonorResponse response = donorService.registerDonor(donorDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body("Yay!! A new donor❤️..  \nThank you for becoming a blood donor🩸" + "\nName:" +response.getDonorName() + "\nID:"+response.getResponseDonorId() +
                "\nBloodType:"+response.getBloodType());
    }

    @PostMapping("/modify/{donor_id}")
    public ResponseEntity<?> modifyDonor(@PathVariable Long donor_id , @RequestBody DonorDTO donorDto){
        donor particularDonor = donorRepo.findById(donor_id).orElseThrow(()-> new RuntimeException("NO id found"));
        if(donorDto == null || particularDonor == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request. Donor ID mismatch.");
        }
        return ResponseEntity.ok(donorService.modifyDonor(donorDto, donor_id));
    }

}
