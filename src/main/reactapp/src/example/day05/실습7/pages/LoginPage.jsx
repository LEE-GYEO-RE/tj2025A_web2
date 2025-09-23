import { useRef } from "react";
import { useDispatch } from "react-redux";
import { BrowserRouter, useNavigate } from "react-router-dom";
import { login } from "../store/userSlice";

export default function LoginPage(props){

    const navigate = useNavigate();
    const dispatch = useDispatch();

    const onLogin = async () => {
        alert('로그인 성공');
        // dispatch( login() );
        const obj = { id : 3 , name : "유재석" } // [1-2] login 액션에 보낼 데이터
        dispatch( login( obj ) ); // [1-3] 매개변수를 포함한 login 액션 요청
        navigate('/');
    }
    

    return(<>
        <h3> 로그인 페이지 </h3>
            <input name="id" placeholder="아이디" /> 
            <input name="pwd" placeholder="비밀번호" type="password" />
            <button type="button" onClick={ onLogin }> 로그인 </button>    
    </>)
}