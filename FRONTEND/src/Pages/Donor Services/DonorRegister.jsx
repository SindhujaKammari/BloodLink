import { useState } from "react";
import { DonorRegister as registerDonor } from "../../API/bloodlinkAPI";
import "../Styles.css";

function ModifyDate(lastDonate){
    if(!lastDonate) return "";
    const [year, month, date] = lastDonate.split("-");
    return `${date}-${month}-${year}`;
}

export default function DonorRegister(){
    const [formData, setFormData] = useState({
        donorName: "",
        bloodType:"",
        phoneNum:"",
        donorArea:"",
        donorMandal:"",
        donorDistrict:"",
        lastDonate:""
    });

    const [message, setMessage] = useState("");
    
    

    function handleChanges(event){
        const {name , value} = event.target;
        setFormData( (data)=> ({...data , [name]:value }) );
    }

    async function handleSubmit(event){
        event.preventDefault();
        setMessage("In-progress");
        try {
            const donorData = {
                ...formData,
                lastDonate: ModifyDate(formData.lastDonate)
            };
            await registerDonor(donorData);
            setMessage("Registration successful!");
            setFormData({
                donorName: "",
                bloodType:"",
                phoneNum:"",
                donorArea:"",
                donorMandal:"",
                donorDistrict:"",
                lastDonate:""
            });
        } catch (error) {
            setMessage(error.message || "Registration failed!");
        }
    }

    return(<div id="container">
        <form onSubmit={handleSubmit} >
            <label htmlFor="donorName">Enter Donor's Name
                <input id="donorName" type="text" placeholder="Enter DonorName" name="donorName" value={formData.donorName} onChange={handleChanges} required/>
            </label>
            <br/>

            <label htmlFor="bloodType"> Donor's Blood Group
                <input id="bloodType" type="text" placeholder="Enter bloodType" name="bloodType" value={formData.bloodType} onChange={handleChanges} required/>
            </label>
            <br/>

            <label htmlFor="phoneNum"> Contact (phone Num)
                <input id="phoneNum" type="text" placeholder="Enter phone number" name="phoneNum" value={formData.phoneNum} onChange={handleChanges} required/>
            </label>
            <br/>

            <label htmlFor="donorArea"> Enter Area
                <input id="donorArea" type="text" placeholder="Enter DonorArea" name="donorArea" value={formData.donorArea} onChange={handleChanges}/>
            </label>
            <br/>

            <label htmlFor="donorMandal"> Enter Mandal
                <input id="donorMandal" type="text" placeholder="Enter DonorMandal" name="donorMandal" value={formData.donorMandal} onChange={handleChanges}/>
            </label>
            <br/>

            <label htmlFor="donorDistrict"> Enter District
                <input id="donorDistrict" type="text" placeholder="Enter DonorDistrict" name="donorDistrict" value={formData.donorDistrict} onChange={handleChanges}/>
            </label>
            <br/>

            <label htmlFor="lastDonate"> When did you donate your blood ? or is it your first time
                <input id="lastDonate" type="date" name="lastDonate" value={formData.lastDonate} onChange={handleChanges}/>
            </label>
            <br/>

            <button type="submit">Register Donor</button>
        </form>
        {message && <p role="status">{message}</p>}
    </div>);
}