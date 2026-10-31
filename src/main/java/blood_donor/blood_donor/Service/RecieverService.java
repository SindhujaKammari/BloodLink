package blood_donor.blood_donor.Service;

import org.springframework.stereotype.Service;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.modelmapper.ModelMapper;

import blood_donor.blood_donor.DTO.RecieverDTO;
import blood_donor.blood_donor.DTO.RecieverResponseDTO;
import blood_donor.blood_donor.Java.Reciever;
import blood_donor.blood_donor.Java.RecieverResponse;
import blood_donor.blood_donor.Java.donor;
import blood_donor.blood_donor.Repos.DonorRepo;
import blood_donor.blood_donor.Repos.RecieverRepo;
import blood_donor.blood_donor.Repos.RecieverResponseRepo;


@Service
public class RecieverService {
    private final ModelMapper modelMapper;
    private final RecieverResponseRepo recieverResponseRepo;
    private final RecieverRepo recieverRepo;
    private final DonorRepo donorRepo;

    public RecieverService(
            ModelMapper modelMapper,
            RecieverResponseRepo recieverResponseRepo,
            RecieverRepo recieverRepo,
            DonorRepo donorRepo) {
        this.modelMapper = modelMapper;
        this.recieverResponseRepo = recieverResponseRepo;
        this.recieverRepo = recieverRepo;
        this.donorRepo = donorRepo;
    }

    public RecieverResponse registerReciever(RecieverDTO newReciever ) {
        Reciever newRegistration = modelMapper.map(newReciever, Reciever.class);
        Reciever savedReciever = recieverRepo.save(newRegistration);

        RecieverResponse response = modelMapper.map(savedReciever, RecieverResponse.class);
        response.setRecieverId(savedReciever.getRecieverId());
        recieverResponseRepo.save(response);

        return response;
    }

    public List<donor> searchDonors(String bloodType,String location , String place){
        
        List<donor> matchedDonors = donorRepo.findByBloodTypeIgnoreCase(bloodType);

        if(location.equalsIgnoreCase("area") || location.equalsIgnoreCase("mandal") || location.equalsIgnoreCase("district") || location.equalsIgnoreCase("all")){
            if(location.equalsIgnoreCase("area")){
                List<donor> donorByArea = matchedDonors.stream()
                                                        .filter(donorArea -> donorArea.getDonorArea().equalsIgnoreCase(place)).toList();
                return donorByArea;
            }
            else if(location.equalsIgnoreCase("mandal")){
                List<donor> donorByMandal = matchedDonors.stream()
                                                        .filter(donorMandal -> donorMandal.getDonorArea().equalsIgnoreCase(place)).toList();
                return donorByMandal;
            }
            else if(location.equalsIgnoreCase("district")){
                List<donor> donorByDistrict = matchedDonors.stream()
                                                        .filter(donordistrict -> donordistrict.getDonorArea().equalsIgnoreCase(place)).toList();
                return donorByDistrict;
            }
            else if(location.equalsIgnoreCase("all")){
                return matchedDonors;
            }
            else{
                throw new RuntimeException("No data found for this bloodGroup");
            }
        }
        return null;
    }



}
