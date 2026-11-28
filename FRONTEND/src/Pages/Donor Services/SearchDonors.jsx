import { useState } from "react";
import { ValidDonors } from "../../API/bloodlinkAPI";

export default function SearchDonors(){
    let [ formData , setFormData] = useState({
        bloodType:"",
        location:"",
        place:""
    });
    let [listOfDonors , setListOfDonors] = useState([]);
    let [errorMessage, setErrorMessage] = useState("");
    let [hasSearched, setHasSearched] = useState(false);

    function handleChange(event){
        const {name , value } = event.target;
        setFormData((prev)=>({...prev , [name]:value}));
    }

    async function handleSubmit(event){
        event.preventDefault();
        setErrorMessage("");
        setHasSearched(true);
        setListOfDonors([]);
        try{
            const data = await ValidDonors(
                formData.bloodType,
                formData.location,
                formData.location === "all" ? null : formData.place
            );
            setListOfDonors(data);
        }catch(err){
            setErrorMessage(err.message || "Sorry! No donors could be found.");
        }
    }

    return(<>
        <form onSubmit={handleSubmit}>
            <label htmlFor="bloodType"> Blood Type
                <select id="bloodType" name="bloodType" value={formData.bloodType} onChange={handleChange} required>
                    <option value="">Select blood type</option>
                    <option value="O+">O+</option>
                    <option value="O-">O-</option>
                    <option value="A+">A+</option>
                    <option value="A-">A-</option>
                    <option value="B+">B+</option>
                    <option value="B-">B-</option>
                    <option value="AB+">AB+</option>
                    <option value="AB-">AB-</option>
                </select>
            </label>

            <label htmlFor="location"> Location scope
                <select id="location" name="location" value={formData.location} onChange={handleChange} required>
                    <option value="">Select location scope</option>
                    <option value="all">All locations</option>
                    <option value="area">Area</option>
                    <option value="mandal">Mandal</option>
                    <option value="district">District</option>
                </select>
            </label>

            <label htmlFor="place">
                Place
                <input
                    id="place"
                    type="text"
                    placeholder="Enter an exact area, mandal, or district"
                    name="place"
                    value={formData.place}
                    onChange={handleChange}
                    required={formData.location !== "" && formData.location !== "all"}
                    disabled={formData.location === "all"}
                />
            </label>

            <button type="submit">Submit</button>
        </form>
        {errorMessage && <p role="alert">{errorMessage}</p>}
        {listOfDonors.length > 0 ? (
            <ul>
                {listOfDonors.map((donor, index) => (
                    <li key={`${donor.donorName}-${index}`}>
                        {donor.donorName}
                        {donor.donorArea && ` - ${donor.donorArea}`}
                        {donor.donorDistrict && ` - ${donor.donorDistrict}`}
                    </li>
                ))}
            </ul>
        ) : (
            hasSearched && !errorMessage && <p>No eligible donors found.</p>
        )}
    </>)
}