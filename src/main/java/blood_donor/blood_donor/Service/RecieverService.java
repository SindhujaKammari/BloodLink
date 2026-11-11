package blood_donor.blood_donor.Service;

import org.springframework.stereotype.Service;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.modelmapper.ModelMapper;

import blood_donor.blood_donor.DTO.RecieverDTO;
import blood_donor.blood_donor.DTO.RecieverResponseDTO;
import blood_donor.blood_donor.Java.DonorResponse;
import blood_donor.blood_donor.Java.Reciever;
import blood_donor.blood_donor.Java.RecieverResponse;
import blood_donor.blood_donor.Java.donor;
import blood_donor.blood_donor.Repos.DonorRepo;
import blood_donor.blood_donor.Repos.DonorResponseRepo;
import blood_donor.blood_donor.Repos.RecieverRepo;
import blood_donor.blood_donor.Repos.RecieverResponseRepo;


@Service
public class RecieverService {
    private final ModelMapper modelMapper;
    private final RecieverResponseRepo recieverResponseRepo;
    private final RecieverRepo recieverRepo;
    private final DonorRepo donorRepo;
    private final DonorResponseRepo donorResponseRepo;
   
    public RecieverService(
            ModelMapper modelMapper,
            RecieverResponseRepo recieverResponseRepo,
            RecieverRepo recieverRepo,
            DonorRepo donorRepo,
            DonorResponseRepo donorResponseRepo
            ) {
        this.modelMapper = modelMapper;
        this.recieverResponseRepo = recieverResponseRepo;
        this.recieverRepo = recieverRepo;
        this.donorRepo = donorRepo;
        this.donorResponseRepo = donorResponseRepo;
        
    }

    public RecieverResponse registerReciever(RecieverDTO newReciever ) {
        Reciever newRegistration = modelMapper.map(newReciever, Reciever.class);
        Reciever savedReciever = recieverRepo.save(newRegistration);

        RecieverResponse response = modelMapper.map(savedReciever, RecieverResponse.class);
        response.setRecieverId(savedReciever.getRecieverId());
        recieverResponseRepo.save(response);

        return response;
    }

    public List<DonorResponse> searchDonors(String bloodType,String location , String place){
        
        List<DonorResponse> matchedDonors = DonorResponseRepo.findByBloodTypeIgnoreCase(bloodType);

        if(location.equalsIgnoreCase("area") || location.equalsIgnoreCase("mandal") || location.equalsIgnoreCase("district") || location.equalsIgnoreCase("all")){
            if(location.equalsIgnoreCase("area")){
                List<DonorResponse> donorId = matchedDonors.stream()
                                                    .map(d ->{
                                                        DonorResponse newDonorByArea = modelMapper.map(d, DonorResponse.class);
                                                        return newDonorByArea;
                                                    } )
                                                    .toList();
                return donorId;
            }
            else if(location.equalsIgnoreCase("mandal")){
                List<DonorResponse> donorId = matchedDonors.stream()
                                                    .map(d ->{
                                                        DonorResponse newDonorByMandal = modelMapper.map(d, DonorResponse.class);
                                                        return newDonorByMandal;
                                                    } )
                                                    .toList();
                return donorId;
            }
            else if(location.equalsIgnoreCase("district")){
                List<DonorResponse> donorId = matchedDonors.stream()
                                                    .map(d ->{
                                                        DonorResponse newDonorByDistrict = modelMapper.map(d, DonorResponse.class);
                                                        return newDonorByDistrict;
                                                    } )
                                                    .toList();
                return donorId;
            }
            else if(location.equalsIgnoreCase("all")){
                List<DonorResponse> donorId = matchedDonors.stream()
                                                    .map(d ->{
                                                        DonorResponse newDonorResponse = modelMapper.map(d, DonorResponse.class);
                                                        return newDonorResponse;
                                                    } )
                                                    .toList();
                return donorId;
            }
            else{
                throw new RuntimeException("No data found for this bloodGroup");
            }
        }
        return null;
    }


                                                        newDonorResponse.setResponseDonorId(d.getDonorId());
                                                        newDonorResponse.setBloodType(d.getBloodType());
                                                        newDonorResponse.setDistrict(d.getDonorDistrict());
                                                        newDonorResponse.setMandal(d.getDonorMandal());
                                                        newDonorResponse.setArea(d.getDonorArea());
                                                        return newDonorResponse;
                                                    } )
                                                    .toList();
                return donorId;
            }
            else{
                throw new RuntimeException("No data found for this bloodGroup");
            }
        }
        return null;
    }



}
