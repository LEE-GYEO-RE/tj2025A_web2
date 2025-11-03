import { BrowserRouter , Route, Router, Routes, Link } from "react-router-dom";
import RoleRoute from "./components/RoleRoute.jsx";
import Header from "./components/Header.jsx";
import Login from "./pages/user/Login.jsx";

export default function App() {

    return (
        <BrowserRouter>
            <Header />
            <Routes>
                {/* 권한에 따른 조건 */}

                {/* 1. 누구나 접근 가능 */}
                <Route path="/" element={<h1> 메인페이지 </h1>} />
                <Route path="/signup" element={<h1> 회원가입 </h1>} />
                <Route path="/login" element={<Login/>} />

                {/* 2. USER 또는 그외 접근 가능 */}
                <Route element={<RoleRoute roles={["USER", "ADMIN"]} />}>
                    <Route path="/user/info" element={<h1> 마이페이지 </h1>} />
                </Route>

                {/* 3. ADMIN 또는 그외 접근 가능 */}
                <Route element={<RoleRoute roles={["ADMIN"]} />}>
                    <Route path="/admin/dashboard" element={<h1> 관리자 페이지 </h1>} />
                </Route>

                {/* 4. 에러 페이지 : 404 , 403 등등 */}
                <Route path="/forbidden" element={<h1> 접근 권한 없음</h1>} />

            </Routes>
        </BrowserRouter>
    );

} // func e