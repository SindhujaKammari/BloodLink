

package blood_donor.blood_donor.Repos;

import org.springframework.data.jpa.repository.JpaRepository;

import blood_donor.blood_donor.Java.DonorResponse;
import java.util.List;



public interface DonorResponseRepo extends JpaRepository<DonorResponse, Long>{
    List<DonorResponse> findByBloodTypeIgnoreCase(String bloodType);
}
