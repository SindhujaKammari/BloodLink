package blood_donor.blood_donor.Controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import blood_donor.blood_donor.DTO.RecieverDTO;
import blood_donor.blood_donor.Service.RecieverService;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = {"http://localhost:5173", "http://127.0.0.1:5173"})
public class RecieverController {
    private final RecieverService recieverService;

    public RecieverController(RecieverService recieverService){
        this.recieverService = recieverService;
    }


    @GetMapping("/checkReciever")
    public String justCheck(){ return "Works Fine!!";}


    @PostMapping("/register/reciever")
    public ResponseEntity<?> registerReciever(@Valid @RequestBody RecieverDTO newRecieverDto ){
        if(newRecieverDto == null){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Try to give valid data ");
        }
        return ResponseEntity.ok(recieverService.registerReciever(newRecieverDto));
    } 

    @GetMapping("/search/donors/{bloodType}")
    public ResponseEntity<?> searchDonorForReciever( @RequestParam(required = true) String location , @RequestParam(required = false) String place, @PathVariable String bloodType){
        boolean validLocation = location != null && !location.isBlank() && (location.equalsIgnoreCase("area") || location.equalsIgnoreCase("mandal") || location.equalsIgnoreCase("district"));
        boolean validPlace = place != null && !place.isBlank();

        if(location.equalsIgnoreCase("all")){
            return ResponseEntity.ok(recieverService.searchDonors(bloodType, location, place));
        }
        if(validLocation && validPlace){
            return ResponseEntity.ok(recieverService.searchDonors(bloodType, location, place));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Location and place must not be blank Or location must be equal to area or mandal or district ");
    } 

    @GetMapping("/search/donors/{bloodType}/valid")
    public ResponseEntity<?> searchValidDonors(@PathVariable String bloodType){
        boolean verify = bloodType.isEmpty();
        if(!verify){
            return ResponseEntity.ok(recieverService.VerifiedDonor(bloodType));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong Blood Type!!");
    }    
}

