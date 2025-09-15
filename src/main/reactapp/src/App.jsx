

function App() {

  const b = "ㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗ"
  let a = 10;
  return (
    <>
      <h1> 최초 랜더링 하는 곳 </h1>
      <div>
        {a}
        <div>
         ㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗ
        </div>
        {b}
      </div>
    </>
  )
}

export default App
