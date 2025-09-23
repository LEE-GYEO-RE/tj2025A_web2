/*
    슬라이스 : 상태(state) , 리듀서(reducer) , 액션(action) 정의하는 곳
    createSlice

*/

import { createSlice } from "@reduxjs/toolkit"

// [1] 상태의 초기값 정의 , 로그인 여부 , 로그인한 회원 정보
const initialState = { isAuthenticated : false , userInfo : null }

// [2] 슬라이스 정의
const userSlice = createSlice( {
    name : 'user', // 슬라이스(상태) 명 
    initialState , 
    reducers : { // 여러개의 상태변경함수를 정의한 곳 { key : value } 형식
        login : ( state , action ) => { 
            state.isAuthenticated = true; 
            // action 할때 전달되는 매개변수들이 payload 안에 값에 들어있음.
            // 예) dispatch( login("안녕") ) , payload = "안녕" 
            state.userInfo = action.payload; 
        },
        logout : ( state ) => { 
            state.isAuthenticated = false; // 로그인 여부 false로 변경 
            state.userInfo = null;  // 로그인회원정보 null로 변경
        }
    }

});

// [3] store 에 저장할 수 있게 export 해주기
export default userSlice.reducer // 현재 정의한 reducers 를 store에 등록 예정

// [4] 다른 컴포넌트에서 액션이 가능하도록 login , logout export 해주기
export const { login , logout } = userSlice.actions

