let socket = new WebSocket("ws://localhost:8080/alarm");

// 서버 접속 성공
socket.onopen = () => {
    console.log("서버에 접속되었습니다.");
};

// 서버에서 메시지 수신 → alert으로 출력
socket.onmessage = (event) => {
    alert(event.data);
};

// 서버와 연결 종료
socket.onclose = () => {
    console.log("서버 연결이 종료되었습니다.");
};