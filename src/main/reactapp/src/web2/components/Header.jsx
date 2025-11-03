import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import axios from "axios";

export default function Header() {


    // 로그인된 유저 정보 저장
    const [user, setUser] = useState(null);

    // 최초로 컴포넌트 실행시 유저 정보 요청하기
    const getMyInfo = async () => {
        try {

            const url = "http://localhost:8080/api/user/info"
            const res = await axios.get(url, { withCredentials: true });
            setUser(res.data); // 반환된 유저 정보 저장

        } catch (e) { // 오류시 null 처리
            setUser(null);
            console.log(e);
        }
    }
    useEffect(() => { getMyInfo(); }, []);

    // 로그아웃
    const getLogout = async () => {
        try {
            const url = "http://localhost:8080/api/user/logout"
            const res = await axios.delete(url,{ withCredentials: true });
            alert("로그아웃 되었습니다.");
            location.href = "/login"; // 로그인 페이지 이동

        } catch (e) {
            console.log(e);
        }

    }

    return (<>
        <div>
            <nav>
                {user ? <>
                    {user ? <> 로그인 </> : <> 비로그인 </>}
                    {/* 로그인 상태 */}
                    <span to="/login"> {user.uid} </span>
                    <button onClick={getLogout}> 로그아웃 </button>
                    <Link to="/user/info"> 마이페이지 </Link>

                    {/* 관리자이면 */}
                    {user.urole == "ADMIN" ?
                        <>
                            <Link to="/admin/dashboard"> 관리자페이지 </Link>
                        </> : <></>}
                </> :
                    <>

                        {/* 비로그인 상태 */}
                        <Link to="/"> 홈 </Link>
                        <Link to="/login"> 로그인 </Link>
                        <Link to="/signup"> 회원가입 </Link>

                    </>}

            </nav>

        </div>

    </>);
}
