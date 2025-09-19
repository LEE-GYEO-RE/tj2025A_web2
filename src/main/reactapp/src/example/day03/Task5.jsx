import axios from "axios";
import { useEffect, useState } from "react";

export default function Task5 (props){

    const [ name , setName ] = useState('')
    const [ phone , setPhone ] = useState('')
    const [ age , setAge ] = useState('')
    const [ members , setMembers ] = useState( [ ] );

    // 등록
    const addMember = async () => {
        const obj = { name , phone , age }
        const response = await axios.post("http://localhost:8080/member" , obj );
        console.log(response.data);
        memberPrint();
    }

    // 조회
    const memberPrint = async () => {
        const response = await axios.get("http://localhost:8080/member");
        setMembers(response.data);
    }
    

    // 삭제
    const deleteMember = async (deleteMno) => {
        const response = await axios.delete("http://localhost:8080/member?mno=" + deleteMno )
        const newMembers = members.filter( (member) => { return member.mno != deleteMno})
        setMembers([...newMembers])
    }

    // 최초 랜더링
    useEffect(() => { memberPrint() } , [ ])

    return(<>
        <h3> 회원 목록 </h3>
        <input placeholder="이름" value={name} onChange={(e)=>{setName(e.target.value)}} />
        <input placeholder="연락처" value={phone} onChange={(e)=>{setPhone(e.target.value)}} />
        <input placeholder="나이" value={age} onChange={(e)=>{setAge(e.target.value)}} />
        <button onClick={addMember}> 등록 </button><br/>
        {
            members.map( (member) => {
                return <div>
                        { member.mno } {member.phone} {member.age}
                        <button onClick={() => {deleteMember(member.mno)}}> 삭제 </button>
                    </div>
            })
        }

    
    
    </>)

} // func e