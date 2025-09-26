import * as React from 'react';
import Box from '@mui/joy/Box';
import List from '@mui/joy/List';
import ListItem from '@mui/joy/ListItem';
import ListItemButton, { listItemButtonClasses } from '@mui/joy/ListItemButton';
import IconButton from '@mui/joy/IconButton';
import Typography from '@mui/joy/Typography';
import ReceiptLong from '@mui/icons-material/ReceiptLong';
import KeyboardArrowDown from '@mui/icons-material/KeyboardArrowDown';

export default function SideBar(props) {

    // 6. 사이드바 상태
    const [open, setOpen] = React.useState(false);
    const [open2, setOpen2] = React.useState(false);
    return (
        <Box sx={{ width: 320, pl: '24px' }}>
            <List
                size="sm"
                sx={(theme) => ({
                    // Gatsby colors
                    '--joy-palette-primary-plainColor': '#8a4baf',
                    '--joy-palette-neutral-plainHoverBg': 'transparent',
                    '--joy-palette-neutral-plainActiveBg': 'transparent',
                    '--joy-palette-primary-plainHoverBg': 'transparent',
                    '--joy-palette-primary-plainActiveBg': 'transparent',
                    [theme.getColorSchemeSelector('dark')]: {
                        '--joy-palette-text-secondary': '#635e69',
                        '--joy-palette-primary-plainColor': '#d48cff',
                    },
                    '--List-insetStart': '32px',
                    '--ListItem-paddingY': '0px',
                    '--ListItem-paddingRight': '16px',
                    '--ListItem-paddingLeft': '21px',
                    '--ListItem-startActionWidth': '0px',
                    '--ListItem-startActionTranslateX': '-50%',
                    [`& .${listItemButtonClasses.root}`]: {
                        borderLeftColor: 'divider',
                    },
                    [`& .${listItemButtonClasses.root}.${listItemButtonClasses.selected}`]: {
                        borderLeftColor: 'currentColor',
                    },
                    '& [class*="startAction"]': {
                        color: 'var(--joy-palette-text-tertiary)',
                    },
                })}
            >
                <ListItem nested>
                    <ListItem component="div" startAction={<ReceiptLong />}>
                        <Typography level="body-xs" sx={{ textTransform: 'uppercase' }}>
                            사이드바
                        </Typography>
                    </ListItem>
                    <List sx={{ '--List-gap': '0px' }}>
                        <ListItem>
                            <ListItemButton selected>
                                개요</ListItemButton>
                        </ListItem>
                    </List>
                </ListItem>
                <ListItem sx={{ '--List-gap': '0px' }}>
                    <ListItemButton>빠른 시작</ListItemButton>
                </ListItem>
                <ListItem
                    nested
                    sx={{ my: 1 }}
                    startAction={
                        <IconButton
                            variant="plain"
                            size="sm"
                            color="neutral"
                            onClick={() => setOpen(!open)}
                        >
                            <KeyboardArrowDown
                                sx={[
                                    open ? { transform: 'initial' } : { transform: 'rotate(-90deg)' },
                                ]}
                            />
                        </IconButton>
                    }
                >
                    <ListItem>
                        <Typography
                            level="inherit"
                            sx={[
                                open
                                    ? { fontWeight: 'bold', color: 'text.primary' }
                                    : { fontWeight: null, color: 'inherit' },
                            ]}
                        >
                            환경 설정
                        </Typography>
                        <Typography component="span" level="body-xs">
                            9
                        </Typography>
                    </ListItem>
                    {open && (
                        <List sx={{ '--ListItem-paddingY': '8px' }}>
                            <ListItem>
                                <ListItemButton>
                                    개요</ListItemButton>
                            </ListItem>
                            <ListItem>
                                <ListItemButton>
                                    0. 개발 환경 설정
                                </ListItemButton>
                            </ListItem>
                            <ListItem>
                                <ListItemButton>
                                    1. 첫 번째 Gatsby 사이트 만들기 및 배포                </ListItemButton>
                            </ListItem>
                            <ListItem>
                                <ListItemButton>
                                    2. React 컴포넌트 사용 및 스타일 지정</ListItemButton>
                            </ListItem>
                            <ListItem>
                                <ListItemButton>3. 세번째 </ListItemButton>
                            </ListItem>
                        </List>
                    )}
                </ListItem>
                <ListItem
                    nested
                    sx={{ my: 1 }}
                    startAction={
                        <IconButton
                            variant="plain"
                            size="sm"
                            color="neutral"
                            onClick={() => setOpen2((bool) => !bool)}
                        >
                            <KeyboardArrowDown
                                sx={[
                                    open2 ? { transform: 'initial' } : { transform: 'rotate(-90deg)' },
                                ]}
                            />
                        </IconButton>
                    }
                >
                    <ListItem>
                        <Typography
                            level="inherit"
                            sx={[
                                open2
                                    ? { fontWeight: 'bold', color: 'text.primary' }
                                    : { fontWeight: null, color: 'inherit' },
                            ]}
                        >
                            사용 방법 가이드
                        </Typography>
                        <Typography component="span" level="body-xs">
                            39
                        </Typography>
                    </ListItem>
                    {open2 && (
                        <List sx={{ '--ListItem-paddingY': '8px' }}>
                            <ListItem>
                                <ListItemButton>개요</ListItemButton>
                            </ListItem>
                            <ListItem>
                                <ListItemButton>
                                    지역개발</ListItemButton>
                            </ListItem>
                            <ListItem>
                                <ListItemButton>라우팅</ListItemButton>
                            </ListItem>
                            <ListItem>
                                <ListItemButton>스타일링</ListItemButton>
                            </ListItem>
                        </List>
                    )}
                </ListItem>
            </List>
        </Box>
    );
}