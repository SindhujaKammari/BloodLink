package blood_donor.blood_donor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import blood_donor.blood_donor.Java.Donor;
import blood_donor.blood_donor.Repos.DonorRepo;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {
		"spring.datasource.url=jdbc:h2:mem:bloodlink-test",
		"spring.datasource.driver-class-name=org.h2.Driver",
		"spring.datasource.username=sa",
		"spring.datasource.password="
})
class BloodDonorApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private DonorRepo donorRepo;

	@BeforeEach
	void clearDonors() {
		donorRepo.deleteAll();
	}

	@Test
	void contextLoads() {
	}

	@Test
	void registeringDonorPersistsInDatabase() throws Exception {
		mockMvc.perform(post("/donor/register")
				.contentType(MediaType.APPLICATION_JSON)
				.content("""
						{
						  "donorName": "Test Donor",
						  "bloodType": "O-",
						  "phoneNum": "9999999999",
						  "donorArea": "Kukatpally",
						  "donorMandal": "Kukatpally",
						  "donorDistrict": "Hyderabad"
						}
						"""))
				.andExpect(status().isAccepted())
				.andExpect(content().string(org.hamcrest.Matchers.containsString("Test Donor")));

		assertEquals(1, donorRepo.count());
		assertEquals("Test Donor", donorRepo.findAll().get(0).getDonorName());
		assertEquals(LocalDate.now(), donorRepo.findAll().get(0).getLastDonate());
	}

	@Test
	void registeringDonorKeepsUserProvidedDonationDate() throws Exception {
		mockMvc.perform(post("/donor/register")
				.contentType(MediaType.APPLICATION_JSON)
				.content("""
						{
						  "donorName": "Date Test Donor",
						  "bloodType": "O-",
						  "phoneNum": "9990000002",
						  "donorArea": "Kukatpally",
						  "donorMandal": "Kukatpally",
						  "donorDistrict": "Hyderabad",
						  "lastDonate": "01-01-2024"
						}
						"""))
				.andExpect(status().isAccepted());

		assertEquals(LocalDate.of(2024, 1, 1), donorRepo.findAll().get(0).getLastDonate());
	}

	@Test
	void donorSearchMatchesBloodTypeAndArea() throws Exception {
		donorRepo.save(createDonor("Area Match", "O-", "Kukatpally", LocalDate.now().minusMonths(6)));
		donorRepo.save(createDonor("Other Area", "O-", "Gachibowli", LocalDate.now().minusMonths(6)));
		donorRepo.save(createDonor("Other Blood Type", "A+", "Kukatpally", LocalDate.now().minusMonths(6)));

		mockMvc.perform(post("/search/donors/O-")
				.param("location", "area")
				.param("place", "Kukatpally"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].donorName").value("Area Match"));
	}

	@Test
	void donorSearchDoesNotExposePhoneNumber() throws Exception {
		donorRepo.save(createDonor("Private Donor", "O-", "Kukatpally", LocalDate.now().minusMonths(6)));

		mockMvc.perform(post("/search/donors/O-")
				.param("location", "area")
				.param("place", "Kukatpally"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].phoneNum").doesNotExist());
	}

	@Test
	void donorSearchExcludesThoseWhoDonatedWithinThreeMonths() throws Exception {
		LocalDate today = LocalDate.now();
		donorRepo.save(createDonor("Eligible Donor", "O-", "Kukatpally", today.minusMonths(6)));
		donorRepo.save(createDonor("Boundary Donor", "O-", "Kukatpally", today.minusMonths(3)));
		donorRepo.save(createDonor("Recent Donor", "O-", "Kukatpally", today.minusMonths(2)));

		mockMvc.perform(post("/search/donors/O-")
				.param("location", "area")
				.param("place", "Kukatpally"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[*].donorName", containsInAnyOrder("Eligible Donor", "Boundary Donor")));
	}

	@Test
	void donorSearchReturnsEmptyListWhenThereAreNoMatches() throws Exception {
		mockMvc.perform(post("/search/donors/O-")
				.param("location", "area")
				.param("place", "Kukatpally"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(0)));
	}

	private Donor createDonor(String name, String bloodType, String area, LocalDate lastDonate) {
		Donor donor = new Donor();
		donor.setDonorName(name);
		donor.setBloodType(bloodType);
		donor.setDonorArea(area);
		donor.setDonorMandal("Kukatpally");
		donor.setDonorDistrict("Hyderabad");
		donor.setPhoneNum("9999999999");
		donor.setLastDonate(lastDonate);
		return donor;
	}
}
