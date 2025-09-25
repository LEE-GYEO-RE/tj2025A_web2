import { Link } from "react-router-dom"; 
export default function Header(props){

    return (<>
        <h3> 헤더 페이지 </h3>
        <ul>
            <li> <Link to="/"> 홈 </Link> </li>
            <li> <Link to="/menu"> 메뉴 </Link> </li>
            <li> <Link to="/cart"> 카트 </Link> </li>

        </ul>
    </>)
}