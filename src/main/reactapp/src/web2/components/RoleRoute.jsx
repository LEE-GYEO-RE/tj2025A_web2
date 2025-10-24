import { useEffect, useState } from "react";

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

    return (
        <>
        
        
        </>
    )
}
export default RoleRoute;