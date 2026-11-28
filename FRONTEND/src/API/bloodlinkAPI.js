const BASE_URL = "http://localhost:8080";

/*DONOR SERVICES */
/* DONOR REGISTER */
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

/* GET A DONOR BY ID */
export async function getDonor(id) {
    const response = await fetch(`${BASE_URL}/donor/${id}`);
    if (!response.ok) {
        const error = await response.text();
        throw new Error(error || "Failed to get the donor.");
    }
    return response.json();
}

/**=MODIFY DONOR DETAILS */
export async function DonorUpdate(id, donorData) {
    const response = await fetch(`${BASE_URL}/donor/update/${id}`,{
                                                                method:"PUT",
                                                                headers:{"Content-type": "application/json"},
                                                                body:JSON.stringify(donorData)
                                                             });

    if(!response.ok){
        const error = await response.text();
        throw new Error(error || "Failed to update the current donor");
    }
    return response.json();                                                          
}

/* RECIEVER SERVICES */
export async function RecieverRegister(RecieverData) {
    const response = await fetch(`${BASE_URL}/register/reciever` , {
        method:"POST",
        headers:{"Content-type":"application/json"},
        body: JSON.stringify(RecieverData)
    });

    if(!response.ok){
        const error = await response.text();
        throw new Error(error || "Failed to register the receiver.");
    }
    return response.json();
}

/* SEARCH FOR VALID DONORS */
export async function ValidDonors(bloodType, location, place) {
    const params = new URLSearchParams();
    params.set("location", location);
    if (place?.trim()) {
        params.set("place", place.trim());
    }

    const response = await fetch(`${BASE_URL}/search/donors/${encodeURIComponent(bloodType)}?${params}`, {
        method:"GET",
    });

    if(!response.ok){
        const error = await response.text();
        throw new Error(error || "Failed to search for donors.");
    }
    return response.json();

}