import { Link } from "react-router-dom";

export default function Navbar(){
    return(
        <nav>
            <Link to="/">Home</Link> {"|"}
            <Link to="/donor/register">Register Donor</Link>
            {" | "}
            <Link to="/modify">Modify Donor</Link> {`|`}
            <Link to="/search/donors">Search a Donor</Link> {`|`}
            <Link to="/receiver/register">Register Reciever</Link>
        </nav>
    )
}