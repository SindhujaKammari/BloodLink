import { useState } from "react";
import { DonorUpdate, getDonor } from "../../API/bloodlinkAPI";


export default function ModifyDonor() {
    let [donorId , setDonorId] = useState("");
    const [formData, setFormData] = useState({
        donorName: "",
        bloodType:"",
        phoneNum:"",
        donorArea:"",
        donorMandal:"",
        donorDistrict:"",
        lastDonate:""
    });

    const [visibility , setVisibility] = useState(false);
    const [message, setMessage] = useState("");

    function handleChanges(event) {
        const { name, value } = event.target;
        setFormData(data => ({ ...data, [name]: value }));
    }

    async function handleSearch(event) {
        event.preventDefault();
        setMessage("");
        try {
            const donor = await getDonor(donorId);
            setFormData({
                donorName: donor.donorName || "",
                bloodType: donor.bloodType || "",
                phoneNum: donor.phoneNum || "",
                donorArea: donor.donorArea || "",
                donorMandal: donor.donorMandal || "",
                donorDistrict: donor.donorDistrict || "",
                lastDonate: donor.lastDonate ? donor.lastDonate.split("-").reverse().join("-") : ""
            });
            setVisibility(true);
        } catch (error) {
            setVisibility(false);
            setMessage(error.message);
        }
    }

    async function handleSubmit(event) {
        event.preventDefault();
        setMessage("Updating...");
        try {
            const [year, month, date] = formData.lastDonate.split("-");
            await DonorUpdate(donorId, {
                ...formData,
                lastDonate: formData.lastDonate ? `${date}-${month}-${year}` : ""
            });
            setMessage("Donor updated successfully.");
        } catch (error) {
            setMessage(error.message);
        }
    }

    return(
        <div id="container">
            <form onSubmit={handleSearch}>
                <input type="text" placeholder="Enter Donor Unique Id" value={donorId} name="donorId" onChange={event => setDonorId(event.target.value)} required/>
                <br/>
                <button type="submit">Search</button>
            </form>

            {visibility && <form onSubmit={handleSubmit}>
                <label htmlFor="donorName">
                    <input id="donorName" type="text" placeholder="Enter DonorName" name="donorName" value={formData.donorName} onChange={handleChanges} required/>
                </label>
                <br/>

                <label htmlFor="bloodType">
                    <input id="bloodType" type="text" placeholder="Enter bloodType" name="bloodType" value={formData.bloodType} onChange={handleChanges} required/>
                </label>
                <br/>

                <label htmlFor="phoneNum">
                    <input id="phoneNum" type="text" placeholder="Enter phone number" name="phoneNum" value={formData.phoneNum} onChange={handleChanges} required/>
                </label>
                <br/>

                <label htmlFor="donorArea">
                    <input id="donorArea" type="text" placeholder="Enter DonorArea" name="donorArea" value={formData.donorArea} onChange={handleChanges}/>
                </label>
                <br/>

                <label htmlFor="donorMandal">
                    <input id="donorMandal" type="text" placeholder="Enter DonorMandal" name="donorMandal" value={formData.donorMandal} onChange={handleChanges}/>
                </label>
                <br/>

                <label htmlFor="donorDistrict">
                    <input id="donorDistrict" type="text" placeholder="Enter DonorDistrict" name="donorDistrict" value={formData.donorDistrict} onChange={handleChanges}/>
                </label>
                <br/>

                <label htmlFor="lastDonate">
                    <input id="lastDonate" type="date" name="lastDonate" value={formData.lastDonate} onChange={handleChanges}/>
                </label>
                <br/>

                <button type="submit">Update Donor</button>
            </form>}
            {message && <p role="status">{message}</p>}
        </div>
    )
}