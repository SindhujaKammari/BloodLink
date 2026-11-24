import React, { useState } from "react";
import { DonorUpdate , checkDonor } from "../API/bloodlinkAPI";


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
    const [msg , setMsg] = useState("");

    function getDonorId(event){
        let id = event.target.value();
        const verify = checkDonor(id);
        if(!verify){
            setMessage("this donor doesnt Exist");
            setVisibility(false);
        }
        setVisibility(true);
    }

    return(
        <>
            <form>
                <input type="text" placeholder="Enter Donor Unique Id" value={donorId} name="donorId" onChange={getDonorId}/>
                <button>Search</button>
            </form>

            <form onSubmit={handleSubmit} style={{visibility: visibility? "visible" : "hidden"}}>
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
        </>
    )
}