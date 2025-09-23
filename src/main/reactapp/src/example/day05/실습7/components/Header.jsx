import { useDispatch, useSelector } from "react-redux";
import { BrowserRouter, Link, useNavigate } from "react-router-dom";
import { login, logout } from "../store/userSlice";

export default function Header(props) {

    // const { isAuthenticated } = useSelector( (state) => state.user );
    const { isAuthenticated , userInfo } = useSelector( (state) => state.user );

    const dispatch = useDispatch();

    const loginHandle = () =>{
        dispatch( login() );
    }
    
    const navigate = useNavigate();

    const logoutHandle = () => {
        alert('로그아웃 되었습니다.');
        dispatch( logout() ); // 상태 변경되면 **리렌더링**
        navigate('/')
    }

    return (<>
        {isAuthenticated
            ?
            <div>
                <ul>
                    <li> <span> 안녕하세요 { userInfo.name } 님 </span></li>
                    <li> <Link to="/"> 메인페이지 </Link></li>
                    <li> <Link to="/profile"> 프로필페이지 </Link></li>
                    <button onClick={logoutHandle} > 로그아웃 </button>
                </ul>
            </div>
            :
            <div>
                <ul>
                    <li> <Link to="/"> 메인페이지 </Link></li>
                    <li> <Link to="/login"> 로그인 페이지 </Link></li>
                </ul>
            </div>
        }
    </>)
}