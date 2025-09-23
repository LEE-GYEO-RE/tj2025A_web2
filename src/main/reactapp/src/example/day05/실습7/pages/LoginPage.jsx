import { useRef } from "react";
import { BrowserRouter, useNavigate } from "react-router-dom";

export default function LoginPage(props){

    const formRef = useRef(null);
    const idRef = useRef(null);
    const pwdRef = useRef(null);
    const navigate = useNavigate();

    const login = async () => {
        const id = formRef.current.elements['id'].value;
        const pwd = formRef.current.elements['pwd'].value;
        alert('로그인 성공')
        navigate('/');
    }

    return(<>
        <h3> 로그인 페이지 </h3>
        <form ref={ formRef }>
            <input name="id" placeholder="아이디" /> 
            <input name="pwd" placeholder="비밀번호" type="password" />
            <button type="button" onClick={ login }> 로그인 </button>
        </form>    
    </>)
}