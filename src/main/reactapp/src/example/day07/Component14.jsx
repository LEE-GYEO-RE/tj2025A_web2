import * as React from 'react';
import Button from '@mui/joy/Button';
import Box from '@mui/joy/Box';
import Input from '@mui/joy/Input';
import Select from '@mui/joy/Select';
import Option from '@mui/joy/Option';
import Switch from '@mui/joy/Switch';
import Avatar from '@mui/joy/Avatar';
import SideBar from './SideBar';

export default function Component14() {

    // 4. select 박스 선택시 이벤트
    const handleChange = (event, newValue
    ) => {
        alert(`You chose "${newValue}"`);
    };

    // 5. 스위치 체크박스 상태
    const [checked, setChecked] = React.useState(false);

    return (<>

        <h2> mui 링크 : https://mui.com/ </h2>

        <h3> 1. mui 버튼 </h3>
        <Button variant="solid">Hello world</Button>

        <h3> 2. mui 로딩 버튼 </h3>
        <Box sx={{ display: 'flex', gap: 2, flexWrap: 'wrap' }}>
            <Button>Button</Button>
            <Button disabled>Disabled</Button>
            <Button loading>Loading</Button>
        </Box>

        <h3> 3. mui input 박스 </h3>
        <Input placeholder="Type in here…" variant="outlined" color="primary" />

        <h3> 4. mui select 박스 </h3>
        <Select defaultValue="dog" onChange={handleChange}>
            <Option value="dog">Dog</Option>
            <Option value="cat">Cat</Option>
            <Option value="fish">Fish</Option>
            <Option value="bird">Bird</Option>
        </Select>

        <h3> 5. mui 스위치 </h3>
        <Switch
            checked={checked}
            onChange={(event) => setChecked(event.target.checked)}
        />

        <h3> 6. mui 아바타 </h3>
        <Box sx={{ display: 'flex', gap: 2 }} style={{backgroundColor : 'blue'}}>
            <Avatar />
            <Avatar>JG</Avatar>
            <Avatar alt="Remy Sharp" src="/static/images/avatar/1.jpg" />
        </Box>

        <h3> 7. mui 사이드바 </h3>
        <p> 아이콘 설치 필요(터미널) </p>
        <SideBar/>


    </>)
    /* 

        리액트에서 CSS 적용하는 법
            1. CSS 파일 생성 --> CSS 파일 적용할 컴포넌트에서 import 'css 파일 경로'
            2. CSS 객체 --> JSX에서 객체유형으로 CSS 작성 <컴포넌트명 style={ { CSS 속성명 : 값 , CSS 속성명 : 값 } } />
                주의할점 : - 하이푼 쓰면 안되고 , 카멜표기법 써야함
                 
    */


}