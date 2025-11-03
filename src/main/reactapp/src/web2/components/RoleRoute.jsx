import { useEffect, useState } from "react";
import { Navigate , Outlet } from "react-router-dom";
import axios from "axios";

// 1. 서버로 부터 권한을 확인하여 해당 권한에 따른 컴포넌트를 제약
function RoleRoute( props ) {

    // props : 상위 컴포넌트에서 해당 컴포넌트 
    console.log( props );

    // -userState : 현재 컴포넌트내 상태(값저장) 변수 + 렌더링(새로고침)
    const [ auth , setAuth ] = useState( { isAuth : null , urole : null})

    const checkAuth = async () => {
        try{

            const url = "http://localhost:8080/api/user/check"
            const res = await axios.get(url , {withCredentials:true} );
                // withCredentials : httpOnly 쿠키 자동 포함하기 위한 필수 옵션
            setAuth(res.data);
            console.log( res.data );    

        }catch(e){
            console.log( e );
        }
    }

    // 컴포넌트가 처음 렌더링 될때 1회 실행
    // useEffect : 컴포넌트의 생명주기에 따른 특정 작업 실행
    useEffect( () => { checkAuth();} , [])


    // 서버로 부터 권한을 못받았다면
    if( auth.isAuth == null ) return <div> 권한 확인중... </div>

    // 로그인(쿠키/토큰) 안했다면 로그인페이지 이동
    if(auth.isAuth == false ) return <Navigate to = "/login"/>;

    // 상위 컴포넌트(App.jsx) 로부터 전달받은 권한중에 서버가 전달한 권한이 없으면
    if( !props.roles.includes( auth.urole)) return <Navigate to = "/forbidden"/>;


    // 모든 권한이 통과되면 자식 컴포넌트 랜더링
    return <Outlet/>;
}
export default RoleRoute;