import { useSelector } from "react-redux";

export default function CartPage (props){

    // 리덕스에 저장된 카트 정보 가져오기 (가정)
    // const cart = [
    //     {id:1, name:'아메리카노', price:4000, qty:2},
    //     {id:2, name:'카페라떼', price:4500, qty:1},
    // ]

    // [1] 리덕스로 부터 카트 목록으로 가져오기
    const cartList = useSelector( (state) => (state.cart.cartList)) 

    return (<>
        <h3> 카트 페이지 </h3>
        {
            cartList.map( (item) => {
                return (
                    <div key={item.id}>
                        <span> {item.name} </span>
                        <span> {item.price} </span>
                        <span> {item.qty*item.price} </span>
                    </div>
                )
            } )
        }
    </>)
}
