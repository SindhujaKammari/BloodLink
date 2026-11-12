package blood_donor.blood_donor.Repos;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import blood_donor.blood_donor.Java.Donor;

public interface DonorRepo extends JpaRepository<Donor, Long> {
    List<Donor> findByBloodTypeIgnoreCase(String bloodType);
}
