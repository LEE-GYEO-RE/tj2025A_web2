import { useEffect, useRef, useState } from "react"

export default function Component11 ( props ){

    // [1] 렌더링 하지 않고 데이터를 참조하는 훅 , useRef vs useState
    const inputRef = useRef( null ) // 1. import 또는 자동완성
                                    // 2. useRef (초기값)

    const 등록함수 = ( ) => { 
        console.log( inputRef );                // 3. inputRef : 현재 참조중인 객체 정보 { current : 참조값 }
        console.log( inputRef.current );        // 4. useRef.current : 참조값 , <input>
        inputRef.current.focus();               // - 포커스( 마우스커서 깜빡임 ) 이동
        console.log( inputRef.current.value );  // 단순 입력이라면 useState 보다 useRef 가 좀 더 편함 : Component 7 과 비교 / 하지만 화면에 노출시킬 수는 없음 
    }

    // [2] useState 와 useRef 차이점
    const [ count , setCount ] = useState( 0 );
    const countRef = useRef( count );           // 1. useRef(초기값)
    useEffect ( ()=> {                          // 특정한 상태 변경될때마다 실행되는 훅(함수)
        countRef.current = count;
    } , [ count ] );     // count가 변경될 때마다 해당 함수 자동 실행

    // [3] 
    const formRef = useRef();
    const 전송함수 = () => {            // 폼 내용물들을 한번에 가져와서 한번에 자바(스프링)에게 전송
        console.log( formRef.current ); // form 마크업 안에 type="butten" 무조건 써야함
        console.log( formRef.current.elements['textData'].value ); // formRef.current.elements[ name속성명값 ]
        console.log( formRef.current.querySelector(".textData").value );
    }

    return ( <>
        <h3> useRef 예제1 : 입력 </h3>
        <input ref={ inputRef } />
        <button onClick={ 등록함수 }> 등록 </button>

        <h3> useRef 예제2 : 이전값 기억 </h3>
        <p> 현재 count : { count } / 이전 count : { countRef.current } </p>
        <button onClick={ (e) => { setCount( count + 1 ); } }> 증가 </button>

        <h3> useRef 예제3 : 입력 폼</h3>
        <form ref={ formRef }>
            <input name="textData" id="textData" class="textData" />
            <select name="selectData"> <option> 바나나 </option> </select>
            <textarea name="text2Data"> </textarea>
            <button onClick={ 전송함수 } type="button" > 폼 전송 </button>
        </form>
    </>)
} // func e