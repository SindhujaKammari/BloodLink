package blood_donor.blood_donor.Service;

import org.springframework.stereotype.Service;
import blood_donor.blood_donor.Java.donor;

@Service
public class DonorService {
    private final donor donor;

    public DonorService(donor donor){
        this.donor = donor;
    }

}
