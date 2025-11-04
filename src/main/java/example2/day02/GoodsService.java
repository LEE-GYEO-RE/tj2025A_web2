package example2.day02;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class GoodsService {

    private final GoodsRepository goodsRepository;

    // 1. 등록
    public GoodsDto save( GoodsDto goods ){
        // 1. 저장할 dto를 매개변수로 받는다
        // 2. 저장할 dto를 entity 반환한다
        // 3. save() 이용한 엔티티
        GoodsEntity goodsEntity = goods.toEntity();
        GoodsEntity result = goodsRepository.save(goodsEntity);

        if( result.getGno() >= 0 ){
            return result.toDto();
        }
        return goods;
    } // func e

    // 2. 전체 조회
    public List<GoodsDto> print(){
        List<GoodsEntity> goodsEntityList = goodsRepository.findAll();
        // 모든 엔티티 DTO 반환

        // 방법 1 : for문
//        List<GoodsDto> goodsDtoList = new ArrayList<>();
//        for (int i = 0; i < list.size() ; i++){
//            GoodsEntity entity = list.get(i); // i번째 엔티티 꺼내서
//            goodsDtoList.add(entity.toDto()); // 엔티티를 dto로 변환 후 리스트에 저장
//        }

        // 방법 2 : 스트림 API
        List<GoodsDto> goodsDtoList = goodsEntityList
                .stream()
                .map(GoodsEntity :: toDto )
                .collect(Collectors.toList());
        return goodsDtoList; // DTO list 반환

    } // func e

    // 3. 개별 조회
    public GoodsDto printId( int gno ){

        Optional<GoodsEntity> optional = goodsRepository.findById(gno);

        if(optional.isPresent()){
            GoodsEntity entity = optional.get();
            return entity.toDto();
        }
        return null;

    } // func e

    // 4. 개별 삭제
    public Boolean delete( int gno ){
        if(goodsRepository.existsById(gno) ) { // pk값이 존재하면 true 아니면 false
            goodsRepository.deleteById(gno);
            return true;
        }
        return false;
    } // func e

    // 5. 개별 수정
    public GoodsDto put( GoodsDto goodsDto){
        Optional<GoodsEntity> optional = goodsRepository.findById(goodsDto.getGno());

        if(optional.isPresent()){
            GoodsEntity goodsEntity = optional.get();
            goodsEntity.setGname(goodsDto.getGname());
            goodsEntity.setGprice(goodsDto.getGprice());
            goodsEntity.setGdesc(goodsDto.getGdesc());
            // commit 되면 자동으로 수정날짜(JPA auditing) 변경됨
            return goodsEntity.toDto();
        }
        return goodsDto;
    } // func e

} // class e
