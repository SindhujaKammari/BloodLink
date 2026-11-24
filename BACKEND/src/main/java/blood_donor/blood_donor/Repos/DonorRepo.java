package blood_donor.blood_donor.Repos;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import blood_donor.blood_donor.Java.Donor;

public interface DonorRepo extends JpaRepository<Donor, UUID> {
    List<Donor> findByBloodTypeIgnoreCase(String bloodType);

    boolean existsByDonorArea(String place);
    boolean existsByDonorMandal(String place);
    boolean existsByDonorDistrict(String place);
}
