import DonorRegister from "../Pages/Donor Services/DonorRegister";
import ModifyDonor from "../Pages/Donor Services/ModifyDonor";
import { Navigate, Route, Routes } from "react-router-dom";
import SearchDonors from "../Pages/Donor Services/SearchDonors";
import RecieverRegister from "../Pages/Reciever/RecieverRegister";

export default function DonorPage(){
    return(
        <Routes>
            <Route path="/" element={<Navigate to="/search/donors" replace />} />
            <Route path="/donor/register" element={<DonorRegister/>} />
            <Route path="/modify" element={<ModifyDonor />} />
            <Route path="/search/donors" element={<SearchDonors/>} />
            <Route path="/receiver/register" element={<RecieverRegister/>}/>
            
        </Routes>
    )
}