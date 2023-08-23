package org.fastcampus.api.controller;


import org.fastcampus.api.model.kakao.ResponseKakao;
import org.fastcampus.api.service.MapSearchService;
import org.fastcampus.api.service.MapSearchServiceImpl;

/**
 * 지도 검색 Controller
 *
 * @since 2023-08-21
 * @author byungsang jo
 */
public class MapSearchController {

    /**
     * keyword와 radius을 입력하면 keyword 반경 radius 안에 있는 약국을 검색해서 return 해주는 메서드
     *
     * @param keyword 검색 할 키워드
     * @param radius 반경 (1km = 1000)
     * @return api 응답 결과
     */
    public ResponseKakao findPharmacy(String keyword, String radius) {
        MapSearchService mapSearchService = new MapSearchServiceImpl();
        ResponseKakao responseKakao = mapSearchService.findPharmacy(keyword, radius); // 약국 찾기 호출
        if(responseKakao!=null){
            return responseKakao;
        }
        return null;
    }
}
