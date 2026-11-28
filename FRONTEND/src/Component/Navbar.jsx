import { Link } from "react-router-dom";

export default function Navbar(){
    return(
        <nav>
            <Link to="/">Home</Link> {"|"}
            <Link to="/home">Register Donor</Link>
            {" | "}
            <Link to="/modify">Modify Donor</Link>
        </nav>
    )
}