package org.fastcampus.api.service;

import lombok.extern.slf4j.Slf4j;
import org.fastcampus.api.model.kakao.ResponseKakao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
class MapSearchServiceImplTest {

    /**
     * findPharmacy(String keyword, String radius)
     * tc1: keyword가 null일 경우 null 값 리턴
     */
    @Test
    void findPharmacyTC1() {
        // given
        MapSearchServiceImpl mapSearchService = new MapSearchServiceImpl();
        String keyword = null;
        String radius = "20000";

        // when
        ResponseKakao responseKakao = mapSearchService.findPharmacy(keyword, radius);

        // then
        Assertions.assertEquals(null, responseKakao);
    }

    /**
     * findPharmacy(String keyword, String radius)
     * tc2: keyword가 문자열 경우 검색 결과 값 리턴
     */
    @Test
    void findPharmacyTC2() {
        // given
        MapSearchServiceImpl mapSearchService = new MapSearchServiceImpl();
        String keyword = "광주 서구 종원아파트";
        String radius = "20000";

        // when
        ResponseKakao responseKakao = mapSearchService.findPharmacy(keyword, radius);

        // then
        Assertions.assertEquals(true, responseKakao.getDocuments().size()>0);
    }

    /**
     * findPharmacy(String keyword, String radius)
     * tc3: keyword가 공백 일 경우 null 값 리턴
     */
    @Test
    void findPharmacyTC3() {
        // given
        MapSearchServiceImpl mapSearchService = new MapSearchServiceImpl();
        String keyword = "";
        String radius = "20000";

        // when
        ResponseKakao responseKakao = mapSearchService.findPharmacy(keyword, radius);

        // then
        Assertions.assertEquals(true, responseKakao==null);
    }

    /**
     * findPharmacy(String keyword, String radius)
     * tc4: keyword가 공백 일 경우 null 값 리턴
     */
    @Test
    void findPharmacyTC4() {
        // given
        MapSearchServiceImpl mapSearchService = new MapSearchServiceImpl();
        String keyword = "";
        String radius = "20000";

        // when
        ResponseKakao responseKakao = mapSearchService.findPharmacy(keyword, radius);

        // then
        Assertions.assertEquals(true, responseKakao==null);
    }

    /**
     * findPharmacy(String keyword, String radius)
     * tc5: radius가 공백 일 경우 null 값 리턴
     */
    @Test
    void findPharmacyTC5() {
        // given
        MapSearchServiceImpl mapSearchService = new MapSearchServiceImpl();
        String keyword = "광주 서구";
        String radius = "";

        // when
        ResponseKakao responseKakao = mapSearchService.findPharmacy(keyword, radius);

        // then
        Assertions.assertEquals(true, responseKakao==null);
    }

    /**
     * findPharmacy(String keyword, String radius)
     * tc6: radius가 0보다 작을 경우 null 값 리턴
     */
    @Test
    void findPharmacyTC6() {
        // given
        MapSearchServiceImpl mapSearchService = new MapSearchServiceImpl();
        String keyword = "광주 서구";
        String radius = "-1";

        // when
        ResponseKakao responseKakao = mapSearchService.findPharmacy(keyword, radius);

        // then
        Assertions.assertEquals(true, responseKakao==null);
    }

    /**
     * findPharmacy(String keyword, String radius)
     * tc7: radius가 20000보다 클 경우 null 값 리턴
     */
    @Test
    void findPharmacyTC7() {
        // given
        MapSearchServiceImpl mapSearchService = new MapSearchServiceImpl();
        String keyword = "광주 서구";
        String radius = "20001";

        // when
        ResponseKakao responseKakao = mapSearchService.findPharmacy(keyword, radius);

        // then
        Assertions.assertEquals(true, responseKakao==null);
    }

    /**
     * findPharmacy(String keyword, String radius)
     * tc8: radius가 0~2000 일경우 정상 값 리턴
     */
    @Test
    void findPharmacyTC8() {
        // given
        MapSearchServiceImpl mapSearchService = new MapSearchServiceImpl();
        String keyword = "광주 서구";
        String radius = "15000";

        // when
        ResponseKakao responseKakao = mapSearchService.findPharmacy(keyword, radius);

        // then
        Assertions.assertEquals(true, responseKakao!=null);
    }




}