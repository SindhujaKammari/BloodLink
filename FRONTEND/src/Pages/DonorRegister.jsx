import { useState } from "react";
import { DonorRegister } from "../API/bloodlinkAPI";

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
            await DonorRegister(donorData);
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

    return(<>
        <form onSubmit={handleSubmit}>
            <label htmlFor="donorName">
                <input id="donorName" type="text" placeholder="Enter DonorName" name="donorName" value={formData.donorName} onChange={handleChanges} required/>
            </label>

            <label htmlFor="bloodType">
                <input id="bloodType" type="text" placeholder="Enter bloodType" name="bloodType" value={formData.bloodType} onChange={handleChanges} required/>
            </label>

            <label htmlFor="phoneNum">
                <input id="phoneNum" type="text" placeholder="Enter phone number" name="phoneNum" value={formData.phoneNum} onChange={handleChanges} required/>
            </label>

            <label htmlFor="donorArea">
                <input id="donorArea" type="text" placeholder="Enter DonorArea" name="donorArea" value={formData.donorArea} onChange={handleChanges}/>
            </label>

            <label htmlFor="donorMandal">
                <input id="donorMandal" type="text" placeholder="Enter DonorMandal" name="donorMandal" value={formData.donorMandal} onChange={handleChanges}/>
            </label>

            <label htmlFor="donorDistrict">
                <input id="donorDistrict" type="text" placeholder="Enter DonorDistrict" name="donorDistrict" value={formData.donorDistrict} onChange={handleChanges}/>
            </label>

            <label htmlFor="lastDonate">
                <input id="lastDonate" type="date" name="lastDonate" value={formData.lastDonate} onChange={handleChanges}/>
            </label>

            <button type="submit">Register Donor</button>
        </form>
        {message && <p role="status">{message}</p>}
    </>);
}