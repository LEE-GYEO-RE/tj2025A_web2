    import { useDispatch } from "react-redux";
    import { add } from "../store/cartSlice.jsx";

    export default function MenuPage (props){
        
        const menu = [
            { id : 1 , name : "아메리카노" , price : 3000 } , 
            { id : 2 , name : "카페라떼" , price : 4000 } , 
            { id : 3 , name : "카푸치노" , price : 4500 } , 
        ]

        // [1] 담기 버튼 클릭했을 때 리덕스 상태에 저장
        const dispatch = useDispatch();
        const cartAdd = async ( item ) =>{
            dispatch( add(item) );
        } 
        
        return (<>
            <h3> 메뉴 페이지 </h3>
            { /* 컴포넌트 내 return 안에서는 jsx 문법을 따라야 함 */}
            { 
            // jsx 시작 알림
            menu.map( (item , index ) => { //  map반복문은 1번 반복할때 마다 하나의 리턴값(HTML)을 반환
                return (
                    <div key={index}>
                        <span> {item.name} </span>
                        <span> {item.price} </span>
                        <button onClick={ ()=>{ cartAdd(item)} }> 담기 </button>
                    </div>
                )
            } )
            // js 끝 알림
            }
        </>)
    }