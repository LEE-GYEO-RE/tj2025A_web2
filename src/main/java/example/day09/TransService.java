package example.day09;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class TransService {

    private final TransMapper transMapper;

    // 1. 유재석 , 강호동 insert 하는게 목적(commit)
    // 만약 한명이라도 insert 실패하면 취소(rollback)
    @Transactional
    public boolean trans1(){
        // 1-1 유재석 insert 하고
        transMapper.trans1("유재석");
        // 1-2 강호동 insert 한다.
        transMapper.trans2("강호동");
        return true;
    }

    // 2.
    @Transactional // 만약 지정 함수내 예외( RuntimeException 실행예외    ) 발생하면 함수 내 SQL 모두 취소
    public boolean transfer(Map<String , Object> transInfo){

        int money = Integer.parseInt(String.valueOf(transInfo.get("money")));

        String fromname = String.valueOf(transInfo.get("fromname"));
        transMapper.deposit( fromname , money );

        // 만약 강제로 예외 발생해서 rollback
//        if( true ){
//            throw  new RuntimeException("강제 예외");
//        }


        String toname = String.valueOf(transInfo.get("toname"));
        transMapper.withdraw( toname , money );

        return true;

    } // func e
} // class e
