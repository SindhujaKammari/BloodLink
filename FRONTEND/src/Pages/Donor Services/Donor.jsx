import DonorRegister from "./DonorRegister";
import ModifyDonor from "./ModifyDonor";
import { Navigate, Route, Routes } from "react-router-dom";

export default function DonorPage(){
    return(
        <Routes>
            
            <Route path="/home" element={<DonorRegister/>} />
            <Route path="/modify" element={<ModifyDonor />} />
        </Routes>
    )
}