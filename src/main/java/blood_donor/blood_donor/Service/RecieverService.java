package blood_donor.blood_donor.Service;

import org.springframework.stereotype.Service;

import java.util.List;


import org.modelmapper.ModelMapper;

import blood_donor.blood_donor.DTO.DonorDTO;
import blood_donor.blood_donor.DTO.RecieverDTO;
import blood_donor.blood_donor.Java.Reciever;
import blood_donor.blood_donor.Repos.DonorRepo;
import blood_donor.blood_donor.Repos.RecieverRepo;


@Service
public class RecieverService {
    private final ModelMapper modelMapper;
    private final RecieverRepo recieverRepo;
    private final DonorRepo donorRepo;
   
    public RecieverService(
            ModelMapper modelMapper,
            RecieverRepo recieverRepo,
            DonorRepo donorRepo
            
            ) {
        this.modelMapper = modelMapper;
        this.recieverRepo = recieverRepo;
        this.donorRepo = donorRepo;
        
    }

    public Reciever registerReciever(RecieverDTO newReciever ) {
        Reciever newRegistration = modelMapper.map(newReciever, Reciever.class);
        Reciever savedReciever = recieverRepo.save(newRegistration);

        return savedReciever;
    }

    public List<DonorDTO> searchDonors(String bloodType,String location , String place){
        
        List<DonorDTO> matchedDonors = donorRepo.findByBloodTypeIgnoreCase(bloodType).stream()
                                                .map(donor -> modelMapper.map(donor, DonorDTO.class))
                                                .toList();

        if(location.equalsIgnoreCase("area") || location.equalsIgnoreCase("mandal") || location.equalsIgnoreCase("district") || location.equalsIgnoreCase("all")){
            if(location.equalsIgnoreCase("area")){
                List<DonorDTO> donorId = matchedDonors.stream()
                                                    .filter(d ->d.getDonorArea()!=null && d.getDonorArea().equalsIgnoreCase(place) )
                                                    .map(donorData ->{
                                                        DonorDTO donor = new DonorDTO();
                                                        donor.setDonorName(donorData.getDonorName());
                                                        donor.setDonorArea(donorData.getDonorArea());
                                                        donor.setPhoneNum(donorData.getPhoneNum());
                                                        return  donor;
                                                    }).toList();
                return donorId;
            }
            else if(location.equalsIgnoreCase("mandal")){
                List<DonorDTO> donorId = matchedDonors.stream()
                                                    .filter(d ->d.getDonorMandal().equalsIgnoreCase(place) )
                                                    .map(donorData ->{
                                                        DonorDTO donor = new DonorDTO();
                                                        donor.setDonorName(donorData.getDonorName());
                                                        donor.setDonorArea(donorData.getDonorArea());
                                                        donor.setPhoneNum(donorData.getPhoneNum());
                                                        return  donor;
                                                    }).toList();
                return donorId;
            }
            else if(location.equalsIgnoreCase("district")){
                List<DonorDTO> donorId = matchedDonors.stream()
                                                    .filter(d ->d.getDonorDistrict().equalsIgnoreCase(place) )
                                                    .map(donorData ->{
                                                    DonorDTO donor = new DonorDTO();
                                                    donor.setDonorName(donorData.getDonorName());
                                                    donor.setDonorArea(donorData.getDonorArea());
                                                    donor.setPhoneNum(donorData.getPhoneNum());
                                                    return  donor;
                                                }).toList();
                return donorId;
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
