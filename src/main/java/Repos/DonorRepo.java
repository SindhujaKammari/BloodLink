package Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import blood_donor.blood_donor.Java.donor;


public interface DonorRepo extends JpaRepository<donor , Long>{
}
