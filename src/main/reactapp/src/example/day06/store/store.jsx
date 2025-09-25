import { configureStore } from "@reduxjs/toolkit";
import cartSlice from "./cartSlice";

// [4] persist 적용 : f5 새로고침 리덕스 상태 유지
import storage from "redux-persist/lib/storage";
const persistOptions = {
    key : "cart" , // 스토리지 key 이름
    storage  
}

// [5] 내가 만든 슬라이스 퍼시스트 옵션 적용
import { persistReducer , persistStore } from "redux-persist";
const persistSlice = persistReducer( persistOptions , cartSlice );


// [1] 스토어 만들기
const store = configureStore({
    // [2] 내가 만든 슬라이스 등록
    // reducer : { cart : cartSlice }

    // [6] persist 적용 : [2]번 주석 대신
    reducer : { cart : persistSlice }
});

// [3] 스토어 내보내기
export default store;
export const persistor = persistStore( store ); // persist 적용 : 스토어 내보내기