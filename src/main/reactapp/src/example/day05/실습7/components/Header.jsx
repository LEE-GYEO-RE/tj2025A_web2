import { BrowserRouter, Link } from "react-router-dom";

export default function Header(props) {

    return (<>
        <div>
            <ul>
                <li> <Link to="/"> 메인페이지 </Link></li>
                <li> <Link to="/login"> 로그인 페이지 </Link></li>
                <li> <Link to="/profile"> 프로필페이지 </Link></li>
            </ul>
        </div>

    </>)
}