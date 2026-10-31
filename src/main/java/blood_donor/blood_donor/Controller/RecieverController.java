package blood_donor.blood_donor.Controller;

import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import blood_donor.blood_donor.DTO.RecieverDTO;
import blood_donor.blood_donor.Repos.RecieverRepo;
import blood_donor.blood_donor.Service.RecieverService;

@RestController 
public class RecieverController {
    private final RecieverService recieverService;
    private final RecieverRepo recieverRepo;

    public RecieverController(RecieverService recieverService , RecieverRepo recieverRepo){
        this.recieverService = recieverService;
        this.recieverRepo = recieverRepo;
    }


    @GetMapping("/checkReciever")
    public String justCheck(){ return "Works Fine!!";}

    
    @PostMapping("/register/reciever")
    public ResponseEntity<?> registerReciever(@RequestBody RecieverDTO newRecieverDto ){
        if(newRecieverDto == null){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Try to give valid data ");
        }
        return ResponseEntity.ok(recieverService.registerReciever(newRecieverDto));
    }

    @PostMapping("/search/donors/{recieverId}/{bloodType}")
    public ResponseEntity<?> searchDonorForReciever(@PathVariable Long recieverId , @RequestParam String location , @RequestParam(required = false) String place, @PathVariable String bloodType){
        boolean verify = recieverRepo.existsById(recieverId);
        if(verify){
            return ResponseEntity.ok(recieverService.searchDonors(bloodType, location, place));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong Id!! Please check the Id properly.");
    }
}

