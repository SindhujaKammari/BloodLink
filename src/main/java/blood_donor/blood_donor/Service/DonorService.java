package blood_donor.blood_donor.Service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

import org.modelmapper.ModelMapper;


import blood_donor.blood_donor.Java.Donor;
import blood_donor.blood_donor.Repos.DonorRepo;
@Service
public class DonorService {
    private final DonorRepo donorRepo;
    private final ModelMapper modelMapper;

    public DonorService(DonorRepo donorRepo, ModelMapper modelMapper) {
        this.donorRepo = donorRepo;
        this.modelMapper = modelMapper;

    }

    public Donor registerDonor(Donor Donor) {
        Donor.setLastDonate(LocalDate.now());
        return donorRepo.save(Donor);
    }

    public Donor modifyDonor(Donor donor , UUID donor_id){
        Donor existingDonor = donorRepo.findById(donor_id).orElseThrow(()-> new RuntimeException("No ID found"));
        modelMapper.map(donor , existingDonor);
        existingDonor.setDonorId(donor_id);
        return donorRepo.save(existingDonor);
    }

}
