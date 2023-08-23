package org.fastcampus.api.service.kakao;

import lombok.extern.slf4j.Slf4j;
import org.fastcampus.api.model.kakao.ResponseKakao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * KakaoAPI 클래스의 테스트 클래스
 *
 * @since 2023-08-23
 * @author byungsang jo
 */
@Slf4j
class KaKaoAPITest {

    /**
     * findByKeyword(String keyword)
     * tc1 : keyword가 null일경우 null값이 리턴되어야 한다.
     */
    @Test
    void findByKeywordTC1() {
        // given
        KaKaoAPI kakaoAPI = new KaKaoAPI();
        String keyword = null;

        //when
        ResponseKakao responseKakao = kakaoAPI.findByKeyword(keyword);

        //then
        Assertions.assertEquals(null, responseKakao);
    }

    /**
     * findByKeyword(String keyword)
     * tc2 : keyword가 공백일경우 null값이 리턴되어야 한다.
     */
    @Test
    void findByKeywordTC2() {
        // given
        KaKaoAPI kakaoAPI = new KaKaoAPI();
        String keyword = " ";

        //when
        ResponseKakao responseKakao = kakaoAPI.findByKeyword(keyword);

        //then
        Assertions.assertEquals(null, responseKakao);
    }

    /**
     * findByKeyword(String keyword)
     * tc3 : keyword가 문자열 일 경우 doucument가 1개 이상 리턴되어야 한다.
     */
    @Test
    void findByKeywordTC3() {
        // given
        KaKaoAPI kakaoAPI = new KaKaoAPI();
        String keyword = "광주 서구 종원아파트";

        //when
        ResponseKakao responseKakao = kakaoAPI.findByKeyword(keyword);

        //then
        responseKakao.getDocuments().stream().forEach(o -> System.out.println(o.getX()+"/"+o.getY()));
        Assertions.assertEquals(true, responseKakao.getDocuments().size()>0);
    }





    /**
     * findByCategory(String x, String y, String radius, String categoryGroupCode)
     * tc1 : radius가 0 미만이면 null을 리턴
     */
    @Test
    void findByCategoryTC1() {
        // given
        KaKaoAPI kakaoAPI = new KaKaoAPI();
        String x="126.8527773663502";
        String y="35.12999610435208";
        String radius="-1";
        String categoryGroupCode="PM9";

        //when
        ResponseKakao responseKakao = kakaoAPI.findByCategory(x,y,radius,categoryGroupCode);

        //then
        Assertions.assertEquals(null, responseKakao);
    }

    /**
     * findByCategory(String x, String y, String radius, String categoryGroupCode)
     * tc2 : radius가 20000 초과이면 null을 리턴
     */
    @Test
    void findByCategoryTC2() {
        // given
        KaKaoAPI kakaoAPI = new KaKaoAPI();
        String x="126.8527773663502";
        String y="35.12999610435208";
        String radius="20001";
        String categoryGroupCode="PM9";

        //when
        ResponseKakao responseKakao = kakaoAPI.findByCategory(x,y,radius,categoryGroupCode);

        //then
        Assertions.assertEquals(null, responseKakao);
    }

    /**
     * findByCategory(String x, String y, String radius, String categoryGroupCode)
     * tc3 : radius가 0~20000 이면 responseKakao는 null이 아니다.
     */
    @Test
    void findByCategoryTC3() {
        // given
        KaKaoAPI kakaoAPI = new KaKaoAPI();
        String x="126.8527773663502";
        String y="35.12999610435208";
        String radius="0";
        String categoryGroupCode="PM9";

        //when
        ResponseKakao responseKakao = kakaoAPI.findByCategory(x,y,radius,categoryGroupCode);

        //then
        Assertions.assertEquals(true, responseKakao != null);
    }

    /**
     * findByCategory(String x, String y, String radius, String categoryGroupCode)
     * tc4 : x값이 "" 일 경우 null을 리턴한다.
     */
    @Test
    void findByCategoryTC4() {
        // given
        KaKaoAPI kakaoAPI = new KaKaoAPI();
        String x="";
        String y="35.12999610435208";
        String radius="0";
        String categoryGroupCode="PM9";

        //when
        ResponseKakao responseKakao = kakaoAPI.findByCategory(x,y,radius,categoryGroupCode);

        //then
        Assertions.assertEquals(true, responseKakao == null);
    }

    /**
     * findByCategory(String x, String y, String radius, String categoryGroupCode)
     * tc5 : y값이 "" 일 경우 null을 리턴한다.
     */
    @Test
    void findByCategoryTC5() {
        // given
        KaKaoAPI kakaoAPI = new KaKaoAPI();
        String x="126.8527773663502";
        String y="";
        String radius="0";
        String categoryGroupCode="PM9";

        //when
        ResponseKakao responseKakao = kakaoAPI.findByCategory(x,y,radius,categoryGroupCode);

        //then
        Assertions.assertEquals(true, responseKakao == null);
    }



}