import { useEffect, useState } from "react"
import axios from "axios" // 필수 필수

// ========== 스프링 서버내 day07(boardservice) =======
export default function Component10 ( props ){


    // [1] 입력받은 데이터들을 관리하는 useState
    const [ bcontent , setBcontent] = useState('')
    const [ bwriter , setBwriter ] = useState('')

    // [2] axios 이용하여 스프링 서버에게 등록 요청
    const boardWrite = async()=>{
        const obj = { bcontent , bwriter } // 2-1 : 입력받은 데이터들을 객체화
        const response = await axios.post("http://localhost:8080/board" , obj ) // 2-2 : axios 요청 
        console.log(response.data);
        boardPrint(); // 2-3 : 출력함수 실행
    }

    // [3] 출력할 데이터들을 관리하는 useState
    const [ boards , setBoards ] = useState( [{ bno : 1 , bcontent : 'test' , bwriter : 'test' }] );
    // [4] 출력할 데이터들을 axios 이용하여 스프링에게 요청 , [2-3]실행 , [5] 실행
    const boardPrint = async()=>{
        const response = await axios.get("http://localhost:8080/board"); // 4-1 : axios 요청
        console.log(response.data);
        setBoards(response.data); // 4-2 : axios 요청값 setState 이용한 재렌더링
    }

    // [5] useEffect 이용한 최초 컴포넌트 실행시 4번 출력함수 실행
    useEffect( ()=>{boardPrint()} , [] ) // 의존성 배열이 비어있는 경우 딱1번 실행


    // 삭제 버튼
    const onDelete = ( deleteBno )=>{
        const obj = { bno }
        const response = axios.delete("http://localhost:8080/board" , obj )
        console.log(response.data);
        const newBoards = boards.filter((b)=>{
            return b.bno != deleteBno;
        })
        setBoards([...newBoards])
    }

    return (<>
        <h3> 스프링 서버의 boardservice(day07) 통신 </h3>
        내용 : <input value={bcontent} onChange={(e)=>{setBcontent(e.target.value)}}/>
        작성자 : <input value={bwriter} onChange={(e)=>{setBwriter(e.target.value)}}/>
        <button onClick={ boardWrite }> 등록 </button> <br/>
        {
            boards.map((board)=>{
                return <div>
                    {board.bno} {board.bcontent} {board.bwriter}
                    <button onClick={()=> { onDelete(board.bno)}}> 삭제 </button>
                </div>
            })
        }

    </>)
} // func e