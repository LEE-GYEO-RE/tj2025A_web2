import { BrowserRouter, Route, Routes } from "react-router-dom";
import Header from "./components/Header";
import HomePage from "./pages/HomePage";
import MenuPage from "./pages/MenuPage";
import CartPage from "./pages/CartPage";

function App() {
  return (
    <>
        <BrowserRouter>
            <h1> 실습 8 </h1>
            <Header/>
            <Routes>
                <Route path="/" element ={ <HomePage/>}/>
                <Route path="/menu" element = {<MenuPage/>}/>
                <Route path="/cart" element = {<CartPage/>}/>
            </Routes>
        </BrowserRouter>

    </>
  )
  
} // func e

export default App;