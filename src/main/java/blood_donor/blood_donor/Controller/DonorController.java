package blood_donor.blood_donor.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import blood_donor.blood_donor.Java.donor;
import blood_donor.blood_donor.Service.DonorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import Repos.DonorRepo;


@Controller
public class DonorController {
    private final DonorService donorService ; 
    private final DonorRepo donorRepo;
    private final donor donor;

    public DonorController(DonorService donorService , donor donor , DonorRepo donorRepo){
        this.donorService = donorService;
        this.donor = donor;
        this.donorRepo = donorRepo;
    }

    @GetMapping("/Check")
    public  String justCheck(){return "works fine!!";}

    @PostMapping("/donor/register")
    public ResponseEntity<?> postMethodName(@RequestBody donor donor) {
        if(donor!=null){
            donorRepo.save(donor);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Data Accepted! uff the donor is registered");
        }
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Bad Link");
    }
    
}
