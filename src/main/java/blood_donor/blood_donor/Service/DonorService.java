package blood_donor.blood_donor.Service;

import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import blood_donor.blood_donor.DTO.DonorDTO;
import blood_donor.blood_donor.Java.DonorResponse;
import blood_donor.blood_donor.Java.donor;
import blood_donor.blood_donor.Repos.DonorRepo;
import blood_donor.blood_donor.Repos.DonorResponseRepo;
@Service
public class DonorService {
    private final DonorRepo donorRepo;
    private final ModelMapper modelMapper;
    private final DonorResponseRepo donorResponseRepo;

    public DonorService(DonorRepo donorRepo, ModelMapper modelMapper, DonorResponseRepo donorResponseRepo) {
        this.donorRepo = donorRepo;
        this.modelMapper = modelMapper;
        this.donorResponseRepo = donorResponseRepo;
    }

    public DonorResponse registerDonor(DonorDTO donorDto) {
        donor newDonor = modelMapper.map(donorDto, donor.class);
        donor savedDonor = donorRepo.save(newDonor);

        DonorResponse newDonorResponse = modelMapper.map(savedDonor, DonorResponse.class);
        newDonorResponse.setResponseDonorId(savedDonor.getDonorId());
        DonorResponse savedDonorResponse = donorResponseRepo.save(newDonorResponse);

        return savedDonorResponse;
    }

    public donor modifyDonor(DonorDTO donorDto , Long donor_id){
        donor existingDonor = donorRepo.findById(donor_id).orElseThrow(()-> new RuntimeException("NO id found"));
        modelMapper.map(donorDto , existingDonor);
        donorRepo.save(existingDonor);

        return existingDonor;
    }

}
