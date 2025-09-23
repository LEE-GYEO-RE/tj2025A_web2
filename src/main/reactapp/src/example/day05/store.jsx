// ==== 여러개의 slice 들을 하나의 store 에서 관리하는 코드 ====

import { configureStore } from "@reduxjs/toolkit";
import userReducer from "./userSlice.jsx";

// [1] store 생성 : 모든 컴포넌트에서 store 참조하여 store에 저장된 슬라이스를 사용
// configureStore({ reducer : { 상태명1 : 슬라이스명 , 상태명2 : 슬라이스명 } } )
const store = configureStore( { 
    reducer : {
        // [2] user 상태에 개발자(사용자정의)가 만든 슬라이스를 대입
        user : userReducer , 
        // 부가적인 상태관리 필요한 상태들 가능 : 부가상태명 : 내가만든슬라이스
    }
});

// [3] store를 다른 컴포넌트가 사용할 수 있게 export default
export default store;