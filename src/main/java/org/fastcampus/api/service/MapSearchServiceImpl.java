package org.fastcampus.api.service;

import lombok.extern.slf4j.Slf4j;
import org.fastcampus.api.model.kakao.ResponseKakao;
import org.fastcampus.api.service.kakao.KaKaoAPI;

/**
 * 카카오 맵 api에서 제공해주는 서비스를 이용하여 서비스를 구현해놓은 클래스
 * <p>
 *
 * @author byungsang jo
 * @since 2023-08-21
 */
@Slf4j
public class MapSearchServiceImpl implements MapSearchService {
    /**
     * KakaoAPI를 이용하여 반경 내에 있는 약국을 찾는 메서드
     *
     * @param keyword 검색 키워드
     * @param radius  반경
     */
    @Override
    public ResponseKakao findPharmacy(String keyword, String radius) {
        KaKaoAPI kaKaoAPI = new KaKaoAPI();
        ResponseKakao responseKakao = kaKaoAPI.findByKeyword(keyword); // 키워드로 검색하기
        if (responseKakao != null) {

            String x = responseKakao.getFirstDocumentLongitude(); // 경도
            String y = responseKakao.getFirstDocumentLatitude(); // 위도

            if (x == null || x.isBlank() || y == null || y.isBlank()) {
                return null;
            }

            return kaKaoAPI.findByCategory(x, y, radius, "PM9"); // 카테고리로 검색하기 (약국 찾기)
        }
        return null;
    }
}
