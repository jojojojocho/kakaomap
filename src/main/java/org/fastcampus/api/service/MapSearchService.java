package org.fastcampus.api.service;

import org.fastcampus.api.model.kakao.ResponseKakao;

/**
 * kakaoAPI를 호출하여 구현 할 기능을 선언한 인터페이스
 * <p>
 * @since 2023-08-21
 * @author byungsang jo
 */
public interface MapSearchService {

    /**
     * 키워드 검색해서 반경 radius안에 있는 약국을 찾는 메서드
     * @param keyword 검색 키워드
     * @param radius 반경 거리
     * @return 응답 결과 객체
     */
    ResponseKakao findPharmacy(String keyword, String radius);


}
