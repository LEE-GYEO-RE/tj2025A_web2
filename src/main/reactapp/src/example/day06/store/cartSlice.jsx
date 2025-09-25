
import { createSlice } from "@reduxjs/toolkit";
// [1] 카트 전역 상태 관리 초기값 선언 , 테스트 과정에서 샘플데이터
const initialState = {
    cartList: [
        // { id:1, name:'아메리카노', price:4000, qty:2 },
        // { id:2, name:'카페라떼', price:4500, qty:1 },
    ]
}

// [2] 슬라이스 구성 : 상태와 상태변경 함수 구성(객체)
const cartSlice = createSlice({
    name: "cart", // 슬라이스 이름
    initialState, // 초기값
    reducers: {
        add: (state, action) => {
            // 액션으로 부터 전달된 데이터
            // 1. 액션으로 부터 전달된 데이터 확인
            const item = action.payload;
            let find = false;
            state.cartList.forEach((p) => {
                // 2. 만약에 기존 카트에 동일한 상품이 있으면 수량만 증가
                if (p.id == item.id) {
                    p.qty += 1;
                    find = true;
                }
            })
            // 3. 없으면 카트에 새롭게 추가
            item.qty = 1;
            if (!find) {
                state.cartList.push(item);
            }
        },
        취소: (state, action) => { }
    }
})

// [3] 액션함수 내보내기
export default cartSlice.reducer;
export const { add, 취소 } = cartSlice.actions;