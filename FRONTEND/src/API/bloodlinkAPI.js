const BASE_URL = "http://localhost:8080";

export async function DonorRegister(donorData) {
    const response = await fetch(`${BASE_URL}/donor/register`,{
                                                                method:"POST",
                                                                headers:{"Content-type": "application/json"},
                                                                body:JSON.stringify(donorData)
                                                             });
    
    if(!response.ok){
        const error = await response.text();
        throw new Error(error || "Failed to register the current donor");
    }
    return response.text();
}

export async function checkDonor(id){
    try{
        const verify = await fetch(`${BASE_URL}/check/donor/${id}`);
        
        if(!verify){
            throw new Error("This Donor Id doesnt exist. Either try to enter the Donor-Id again or Register/Sign-up");
        }
        return verify;
    }
    catch(err){
        throw new Error("Either the donorId doesnt exist or re-enter the donor_id");
    }
}

export async function DonorUpdate(id, donorData) {
    try {
        const response = await fetch(`${BASE_URL}/donor/update/${id}`,{
                                                                    method:"PUT",
                                                                    headers:{"Content-type": "application/json"},
                                                                    body:JSON.stringify(donorData)
                                                                 });

        if(!response.ok){
            const error = await response.text();
            throw new Error(error || "Failed to update the current donor");
        }

        const fetchedData = await response.json();                                                         
        
        
        return fetchedData;
    } 
    catch (error) {
        throw new Error("This id doesnt exist. Try entering the id properly");
    }
                                                            
}