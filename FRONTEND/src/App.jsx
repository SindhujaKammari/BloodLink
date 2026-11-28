import { BrowserRouter } from 'react-router-dom'
import './App.css'
import Navbar from './Component/Navbar'
import DonorPage from './Pages/Donor Services/Donor'
import RecieverRegister from './Pages/Reciever/RecieverRegister'
import SearchDonors from './Pages/Donor Services/SearchDonors'

function App() {
  

  return (
    <BrowserRouter>
      <Navbar/>
      <DonorPage/>
      <RecieverRegister/>
      <SearchDonors/>
    </BrowserRouter>
  )
}

export default App
