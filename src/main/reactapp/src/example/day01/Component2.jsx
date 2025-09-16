
// * 함수 안에 다른 함수 호출 가능
// 즉] 컴포넌트 안에 다른 컴포넌트(페이지/화면) 호출 가능

// (1) function
// (2) 컴포넌트명 : 아무거나 하되 default 컴포넌트 파일명과 일치
// (3) ( props ) : property속성으로 <컴포넌트 속성=값 속성=값 />
// (4) { } : 중괄호안아ㅔ서 JS 와 HTML 작성
// (5) return : HTML 과 JS가 혼합된 JSX 문법을 사용할 수 있음. 
    // JSP(HTML + JAVA) : 부트스트랩 
    // JSX(HTML + JS) : MUI
// (6) jsx파일내 export default 함수 1개만 정의

// [1] 메인페이지
export default function Component2 (){
    const name = "유재석"; // JS 코드

    // return 뒤로 html 작성
    return <div> <Header/> 메인페이지 <Footer/> </div> 
} // func e

// [2] 헤더페이지
function Header( props ){

    return <div> 헤더 메뉴 </div>
} // func e

// [3] 푸터페이지
function Footer( props ){

    return <div> 푸터 메뉴 </div>
} // func e