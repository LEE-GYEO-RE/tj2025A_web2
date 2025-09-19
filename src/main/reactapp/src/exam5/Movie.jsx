import { useEffect, useState } from "react"
import axios from "axios"
import './Movie.css';

export default function Movie( props ){


    // ================== 영화 =================== //

    // 입력받은 데이터 관리하는 useState
    const [ title , setTitle ] = useState('')
    const [ director , setDirector ] = useState('')
    const [ genre , setGenre ] = useState('')
    const [ content , setContent ] = useState('')
    const [ pwd , setPwd ] = useState('')
    const [ selectedMovie , setSelectetMovie ] = useState(null);

    // axios로 스프링 서버에 게시물 등록 요청
    const movieWrite = async() => {
        const obj = { title , director , genre , content , pwd }
        const response = await axios.post("http://localhost:8080/movie" , obj )
        console.log(response.data);
        movieList();
    }
    
    // 출력할 데이터 관리하는 useState
    const [ movies , setMovies ] = useState([{ mno : 1 , title : 'test' , director : 'test' , genre : 'test' , content : 'test' }])

    // axios로 스프링 서버에 게시물 출력 요청
    const movieList = async () => {
        const response = await axios.get("http://localhost:8080/movie");
        console.log(response.data);
        setMovies(response.data);
    }

    // axios로 스프링 서버에 게시물 삭제 요청
    const deleteMovie = async(deleteMno) => {
        // 비밀번호 입력받기
        const pwd = prompt("비밀번호를 입력하세요");
        if(!pwd) return alert("비밀번호를 입력해야 합니다.");


        // axios로 스프링 서버에 삭제 요청
        try {
        const response = await axios.delete(`http://localhost:8080/movie?mno=${deleteMno}&pwd=${pwd}` );
            if(response.data){
                alert("삭제 완료");
                movieList();
            }else{
                alert("비밀번호가 틀렸습니다.")
            }
        }catch(e){
            console.log(e)
            alert("오류 발생")
        }
    }

    
    // ================== 리뷰 =================== //
    const [ rTitle , setRTitle ] = useState('')
    const [ rContent , setRContent ] = useState('')
    const [ rPwd , setRPwd ] = useState('')

    const [ reviews , setReviews ] = useState([{ mno : 1 , rTitle : 'test' , rContent : 'test' }])

    // 리뷰 등록 요청
    const reviewWrite = async () =>{
        const mno = selectedMovie.mno
        const obj = { mno , r_title : rTitle , r_content : rContent , r_pwd : rPwd }
        const response = await axios.post("http://localhost:8080/movie/review" , obj);
        console.log(response.data);
        reviewList( selectedMovie.mno);
    } 

    // 리뷰 개별 조회 요청
    const reviewList = async ( mno ) =>{
        const response = await axios.get(`http://localhost:8080/movie/review?mno=${mno}`);
        console.log(response.data);
        setReviews(response.data);

        // 단일 객체면 배열로 변환
        const data = Array.isArray(response.data) ? response.data : [response.data];
        setReviews(data);
    }

    // 리뷰 삭제 요청
    const deleteReivew = async( deleteRno ) =>{
        const rPwd = prompt("비밀번호를 입력하세요");
        if(!rPwd) return alert("비밀번호를 입력해야합니다.");

        try{
            const response = await axios.delete(`http://localhost:8080/movie/review?rno=${deleteRno}&r_pwd=${rPwd}`);
            if(response.data){
                alert("삭제 완료")
                reviewList(selectedMovie.mno);
            }else{
                alert("비밀번호가 틀렸습니다.")
            }

        }catch(e){
            console.log(e)
            alert("오류 발생")
        }
    }

    // 초기실행
    useEffect( ()=>{movieList()} , [ ]) // 딱 1번 실행


    // 랜더링
    if( selectedMovie ){
        return(<>
            <h3> {selectedMovie.title} 상세페이지 </h3>
            <p> 감독 : {selectedMovie.director}</p>
            <p> 장르 : {selectedMovie.genre}</p>
            <p> {selectedMovie.content}</p>

            <button onClick={() => {setSelectetMovie(null); setReviews([]); }}> 목록으로 </button>

            <h4> 리뷰 작성 </h4>
            <input placeholder="제목 " value={rTitle} onChange={(e)=>setRTitle(e.target.value)}/>
            <input placeholder="내용 " value={rContent} onChange={(e)=>setRContent(e.target.value)}/>
            <input placeholder="비밀번호 " type="password" value={rPwd} onChange={(e)=>setRPwd(e.target.value)}/>
            <button onClick={reviewWrite}> 등록 </button>
            

            <h4> 리뷰 목록 </h4>
            { Array.isArray(reviews) && reviews.map(r => (
                <div key={r.rno}>
                    <strong>{r.r_title}</strong>
                    <p>{r.r_content}</p>
                    <button onClick={() => deleteReivew(r.rno)}> 삭제 </button>
                </div>
            ))}
        </>)
    }

    return(<>
        <h3> 영화 목록 </h3>
        <input placeholder="제목" value={title} onChange={(e)=>{setTitle(e.target.value)}} />
        <input placeholder="감독" value={director} onChange={(e)=>{setDirector(e.target.value)}} />
        <input placeholder="장르" value={genre} onChange={(e)=>{setGenre(e.target.value)}} />
        <input placeholder="소개" value={content} onChange={(e)=>{setContent(e.target.value)}} />
        <input placeholder="비밀번호" type="password" value={pwd} onChange={(e)=>{setPwd(e.target.value)}} />
        <button onClick={ movieWrite }> 등록 </button><br/>
        {
            movies.map((movie)=>{
                return <div key={movie.mno}>
                    {movie.mno} 
                    <span style={{ cursor: "pointer", color: "blue" }} 
                    onClick={()=> {setSelectetMovie(movie); reviewList(movie.mno)}} > {movie.title} </span>
                    {movie.director} {movie.genre} {movie.content}
                    <button onClick={()=>{deleteMovie(movie.mno)}}> 삭제 </button>        
                </div>
            })
        }

    </>)
} // func e