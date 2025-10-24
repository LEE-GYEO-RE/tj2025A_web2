import { BrowserRouter as Route, Router, Routes , Link } from "react-router-dom";

function App() {

  return (
    <Router>
    <>
      <h1> web2 App </h1>
      <Routes>
        {/* 권한엥 따른 조건 */}
        {/* 1. 누구나 접근 가능 */}

        {/* 2. USER 또는 그외 접근 가능 */}

        {/* 3. ADMIN 또는 그외 접근 가능 */}




      </Routes>

    </>
    </Router>
  )
  
} // func e

export default App;