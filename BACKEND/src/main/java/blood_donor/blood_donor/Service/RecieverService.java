package blood_donor.blood_donor.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import org.modelmapper.ModelMapper;

import blood_donor.blood_donor.DTO.DonorDTO;
import blood_donor.blood_donor.DTO.RecieverDTO;
import blood_donor.blood_donor.Java.Donor;
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

    public List<Donor> matchedDonors(String bloodType){
        List<Donor> matchedDonors = donorRepo.findByBloodTypeIgnoreCase(bloodType).stream()
                                            .filter(d -> d.getLastDonate() != null
                                                    && !d.getLastDonate().isAfter(LocalDate.now().minusMonths(3)))
                                            .toList();
        return matchedDonors;
    }
   

    public List<DonorDTO> searchDonors(String bloodType,String location , String place){
        
        List<Donor> matchedDonors = matchedDonors(bloodType);

        if(location.equalsIgnoreCase("area") || location.equalsIgnoreCase("mandal") || location.equalsIgnoreCase("district") || location.equalsIgnoreCase("all")){
            if(location.equalsIgnoreCase("area") && !place.isBlank()){
                boolean area = donorRepo.existsByDonorArea(place);
                if(!area){
                    return List.of();
                }
                    List<DonorDTO> donorId = matchedDonors.stream()
                                                    .filter(d ->d.getDonorArea()!=null && d.getDonorArea().equalsIgnoreCase(place) )
                                                    .map(donorData ->{
                                                        DonorDTO donor = new DonorDTO();
                                                        donor.setDonorName(donorData.getDonorName());
                                                        donor.setDonorArea(donorData.getDonorArea());

                                                        return  donor;
                                                    }).toList();

                    return donorId;
                
            }
            else if(location.equalsIgnoreCase("mandal") && !place.isBlank()){
                boolean mandal = donorRepo.existsByDonorMandal(place);
                if(!mandal){
                    return List.of();
                }
                List<DonorDTO> donorId = matchedDonors.stream()
                                                    .filter(d -> d.getDonorMandal() != null
                                                            && d.getDonorMandal().equalsIgnoreCase(place))
                                                    .map(donorData ->{
                                                        DonorDTO donor = new DonorDTO();
                                                        donor.setDonorName(donorData.getDonorName());
                                                        donor.setDonorArea(donorData.getDonorArea());

                                                        return  donor;
                                                    }).toList();
                return donorId;
            }
            else if(location.equalsIgnoreCase("district") && !place.isBlank()){
                boolean district = donorRepo.existsByDonorDistrict(place);
                if(!district){
                    return List.of();
                }
                List<DonorDTO> donorId = matchedDonors.stream()
                                                    .filter(d -> d.getDonorDistrict() != null
                                                            && d.getDonorDistrict().equalsIgnoreCase(place))
                                                    .map(donorData ->{
                                                    DonorDTO donor = new DonorDTO();
                                                    donor.setDonorName(donorData.getDonorName());
                                                    donor.setDonorDistrict(donorData.getDonorDistrict());
                                                    return  donor;
                                                }).toList();
                return donorId;
            }
            else if(location.equalsIgnoreCase("all")){
                List<DonorDTO> allDonors = matchedDonors.stream()
                .map(d -> {
                    DonorDTO donor = new DonorDTO();
                    donor.setDonorName(d.getDonorName());
                    donor.setDonorArea(d.getDonorArea());
                    return donor;
                }).toList();
                
                return allDonors;
            }
            else{
                throw new RuntimeException("Mention Place for accurate results");
            }
        }
        return null;
    }

    public List<DonorDTO> VerifiedDonor(String bloodType){
        if(!bloodType.isBlank()){
            List<Donor> valid =  matchedDonors(bloodType);
            List<DonorDTO> verified = valid.stream()
                    .map(donor -> {
                        DonorDTO publicDonor = new DonorDTO();
                        publicDonor.setDonorName(donor.getDonorName());
                        publicDonor.setDonorArea(donor.getDonorArea());
                        return publicDonor;
                    })
                    .toList();
            return verified;
        }
        return null;
    }
}
