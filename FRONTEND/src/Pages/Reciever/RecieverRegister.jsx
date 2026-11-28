import { useState } from "react"
import { RecieverRegister as registerReciever } from "../../API/bloodlinkAPI";
import "../Styles.css";

export default function RecieverRegister(){
    const [formData , setFormData] = useState(
        {
            recieverName:"",
            bloodType:"",
            emergencyLevel:"",
            hospitalName:"",
            surgeryType:"",
            recieverArea:"",
            unitRequired:""
        }
    )
    const [msg , setMsg] = useState("In-Progress");

    function handleChange(event){
        const {name , value} = event.target ;
        setFormData((prev)=>({ ...prev , [name]:value}) ); 
    }

    async function handleSubmit(event){
        event.preventDefault();
        setMsg("In-Progress");
        try {
            await registerReciever({
                ...formData,
                unitRequired: Number(formData.unitRequired)
            });
            setFormData({
                recieverName:"",
                bloodType:"",
                emergencyLevel:"",
                hospitalName:"",
                surgeryType:"",
                recieverArea:"",
                unitRequired:""
            });
            setMsg("Completed");
        } catch (error) {
            setMsg(error.message);
        }
    }
    
    return(<div id="container">
        <form onSubmit={handleSubmit}>
            <label htmlFor="recieverName"> Patient's Name <span aria-hidden="true" style={{ color: "red", background:"inherit" }}>*</span> <br/>
                <input id="recieverName" type="text" placeholder="Enter Reciever Name" name="recieverName" value={formData.recieverName} onChange={handleChange} required />
            </label>
            <br/>
            <label htmlFor="bloodType"> Blood Type <span aria-hidden="true" style={{ color: "red", background:"inherit" }}>*</span> <br/>
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
            <br/>
            <label htmlFor="emergencyLevel"> Emergency level <span aria-hidden="true" style={{ color: "red", background:"inherit" }}>*</span> <br/>
                <select id="emergencyLevel" name="emergencyLevel" value={formData.emergencyLevel} onChange={handleChange} required>
                    <option value="">Select emergency level</option>
                    <option value="high">high</option>
                    <option value="medium">medium</option>
                    <option value="low">low</option>
                </select>
            </label>
            <br/>
            <label htmlFor="hospitalName">Hospital Name and Branch(Address) <span aria-hidden="true" style={{ color: "red", background:"inherit" }}>*</span> <br/>
                <input id="hospitalName" type="text" placeholder="Enter Hospital Name" name="hospitalName" value={formData.hospitalName} onChange={handleChange} required />
            </label>
            <br/>
            <label htmlFor="surgeryType"> Surgery <span aria-hidden="true" style={{ color: "red", background:"inherit" }}>*</span> <br/>
                <input id="surgeryType" type="text" placeholder="Enter Surgery Name" name="surgeryType" value={formData.surgeryType} onChange={handleChange} required />
            </label>
            <br/>
            <label htmlFor="recieverArea">Enter Reciever Area <span aria-hidden="true" style={{ color: "red", background:"inherit" }}>*</span> <br/>
                <input id="recieverArea" type="text" placeholder="Enter Reciever Area or just hospital Area" name="recieverArea" value={formData.recieverArea} onChange={handleChange} required />
            </label>
            <br/>
            <label htmlFor="unitRequired">Enter Units Required <span aria-hidden="true" style={{ color: "red", background:"inherit" }}>*</span> <br/>
                <input id="unitRequired" type="number" min="1" placeholder="Enter No of units required for surgery" name="unitRequired" value={formData.unitRequired} onChange={handleChange} required />
            </label>
            <br/>

            <button type="submit">Submit</button>
            
        </form>
        
        
    </div>)
}