import { BrowserRouter } from 'react-router-dom'
import './App.css'
import Navbar from './Component/Navbar'
import LinkPages from './Component/LinkPages'

function App() {
  return (
    <BrowserRouter>
      <Navbar/>
      <LinkPages/>
    </BrowserRouter>
  )
}

export default App
