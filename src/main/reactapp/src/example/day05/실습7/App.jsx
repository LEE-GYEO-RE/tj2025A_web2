import { BrowserRouter, Link, Route, Routes } from "react-router-dom";
import HomePage from "./pages/HomePage";
import LoginPage from "./pages/LoginPage";
import ProfilePage from "./pages/ProfilePage";
import Header from "./components/Header";
import { useDispatch, useSelector } from "react-redux";




export default function App(props){


    return(<>
        <BrowserRouter>

            <h3> 루트 페이지 </h3>
            <Header/>
            <Routes>
                <Route path="/" element = { <HomePage/> }/>
                <Route path="/login" element = { <LoginPage/> }/>
                <Route path="/profile" element = { <ProfilePage/> }/>
            </Routes>
        </BrowserRouter>    
    </>)
}