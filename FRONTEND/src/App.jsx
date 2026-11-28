import { BrowserRouter } from 'react-router-dom'
import './App.css'
import Navbar from './Component/Navbar'
import LinkPages from './Component/LinkPages'
import StickyBar from './Component/StickyBar'
function App() {
  return (
    <BrowserRouter>
      <StickyBar/>
      <Navbar/>
      <LinkPages/>
    </BrowserRouter>
  )
}

export default App
