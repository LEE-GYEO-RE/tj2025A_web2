import { BrowserRouter, Route, Routes, Link, useNavigate } from "react-router-dom"
import './Task6.css';
import { use, useRef } from "react";


// 홈 화면 컴포넌트
function Home(props) {
    return (<>
        <h3> 홈 페이지 </h3>
        <div> 좌측메뉴에서 회원가입 또는 로그인으로 이동해보세요 </div>
    </>)
}

// 회원가입 화면 컴포넌트
function Sign(props) {

    // 입력상자들을 참조하는 useRef
    const idRef = useRef(null);
    const pwdRef = useRef(null);
    const navigate = useNavigate();
    // 특징 이벤트에서 참조중인 useRef current 확인하기
    const 회원가입 = async () => {
        console.log( idRef.current.value );
        const id = idRef.current.value; // idRef : 참조객체 , idRef.current : 참조객체가 참조중인 값
        const pwd = pwdRef.current.value;
        // axios로 서버에 통신 요청
        alert('회원가입 성공');
        navigate('/login');
    }

    return (<>
        <h3> 회원가입 페이지 </h3>
        <input ref={idRef} placeholder="아이디" /> <br/>
        <input ref={pwdRef} placeholder="비밀번호" type="password" /> <br/>
        <button onClick={ 회원가입 }> 회원가입 </button>
    </>)
}

// 로그인 화면 컴포넌트
function Login(props) {

    const formRef = useRef(null);
    const idRef = useRef(null);
    const pwdRef = useRef(null);
    const navigate = useNavigate();

    const 로그인 = async () => {
        const id = formRef.current.elements['id'].value;
        const pwd = formRef.current.elements['pwd'].value;
        alert('로그인 성공')
        navigate('/');
    }

    return (<>
        <h3> 로그인 페이지 </h3>
        <form ref={ formRef }>
            <input name="id" placeholder="아이디" /> <br/>
            <input name="pwd" placeholder="비밀번호" type="password" /> <br/>
            <button type="button" onClick={ 로그인 }> 로그인 </button>
        </form>
    </>)
}


// 라우터
export default function Task6(props) {

    return (<>
        <BrowserRouter>
            <div className="container">
                <ul>
                    <li><Link to='/'> 홈 </Link></li>
                    <li><Link to="/sign"> 회원가입 </Link> </li>
                    <li><Link to="/login"> 로그인 </Link> </li>
                </ul>
                <div className="render">
                    <Routes>
                        <Route path="/" element={<Home />} />
                        <Route path="/sign" element={<Sign />} />
                        <Route path="/login" element={<Login />} />
                    </Routes>
                </div>
            </div>

        </BrowserRouter>
    </>)
} // func e