  const products = [
    { title: "무선 키보드", price: 45000, inStock: true },
    { title: "게이밍 마우스", price: 32000, inStock: false },
    { title: "27인치 모니터", price: 280000, inStock: true }
  ]; 

// [3] CSS 파일 불러오기 
// 컴포넌트에서 css 호출하는 방법 : import 'css파일경로'
import './Task2.css' 

// [1] 대표 컴포넌트
export default function Task2( props ){


    return (<>
    <div class="products">
        {/* 하위 컴포넌트 호출과 동시에 props 속성 자료 전달 */}
        <Info info = {products[0]} />
        <Info info = {products[1]} />
        <Info info = {products[2]} />
    </div>
    </>)

} // func e   


// [2] 하위 컴포넌트
function Info (props){
    // 구문 분해 , props 현재 상태 : { info : { title , price , inStock }}
    const { title , price , inStock } = props.info 
    console.log(title); console.log(price);  console.log(inStock);
    return (<>
            <ul>
                <li> {title} </li>
                <li> 가격 : {price.toLocaleString()} </li>
                <li> {inStock == true ? "재고있음" : "재고없음"} </li>
            </ul>
    
    </>)

} // func e