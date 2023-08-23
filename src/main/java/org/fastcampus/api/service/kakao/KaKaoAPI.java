package org.fastcampus.api.service.kakao;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.fastcampus.api.enums.Key;
import org.fastcampus.api.exception.BlankException;
import org.fastcampus.api.model.kakao.ResponseKakao;
import org.fastcampus.api.util.GsonUtils;

import java.net.URI;

/**
 * kakao API 기능 별 호출
 *
 * @author byungsang jo
 * @since 2023-08-21
 */
@Slf4j
public class KaKaoAPI {

    /**
     * keyword로 장소 검색하기
     *
     * @param keyword 검색 할 키워드
     * @return
     */
    public ResponseKakao findByKeyword(String keyword) {
        // 유효 값 여부 처리
        if (keyword == null || keyword.isBlank()) {
            log.warn("findByKeyword 메서드: 입력 키워드가 공백입니다.");
            return null;
        }

        // 키워드 겁색시 사용하는 기본 url
        String apiUrl = "https://dapi.kakao.com/v2/local/search/keyword";

        // 카카오 API와 통신하는 로직
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) { // try이 문이 종료될 때 httpClient도 종료된다. (메모리 누수 방지)
            /*
             * 기본 url에 쿼리스트링을 추가하여 호출 시 사용할 완성된 url을 만듬
             */
            URI uri = new URIBuilder(apiUrl)
                    .setParameter("query", keyword)
                    .build();

            /*
             * get 요청을 할 객체를 생성하고 API-key, Content-Type과 같은 헤더를 추가함.
             */
            HttpGet httpGet = new HttpGet(uri);
            httpGet.addHeader("Authorization", Key.KAKAO.getKey());
            httpGet.addHeader("Content-Type", "application/json;charset=UTF-8");

            /*
             * 실제로 요청을 하고 그 응답을 받는다.
             */
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) { // try 문이 종료될 때 response는 종료된다.
                HttpEntity entity = response.getEntity(); // body 만을 가져옴
                String responseBody = EntityUtils.toString(entity); // response body to String
                return GsonUtils.fromJson(responseBody, ResponseKakao.class); // ResponseKakao 객체로 역직렬화
            }
        } catch (Exception e) {
            log.error("findByKeyword 메서드에서 error 발생: {}", e);
        }

        return null;
    }


    /**
     * 카테고리로 장소 검색하기
     *
     * @param x                 경도
     * @param y                 위도
     * @param radius            반경
     * @param categoryGroupCode 검색할 카테고리 코드
     * @return api 응답 결과
     */
    public ResponseKakao findByCategory(String x, String y, String radius, String categoryGroupCode) {

        // 유효 값 체크
        if (x == null || x.isBlank() || y == null || y.isBlank() || radius == null || radius.isBlank()) {
            log.warn("findByCategory 메서드: x 좌표 또는 y 좌표 또는 카테고리 코드가 공백이거나 null 입니다.");
            return null;
        }
        // 유효 값 체크
        if (Integer.parseInt(radius) < 0 || Integer.parseInt(radius) > 20000) {
            log.warn("findByCategory 메서드: 유효값을 벗어난 radius입니다. (유효범위 0~20000)");
            return null;
        }

        /*
         * 카테고리로 검색할 때 사용하는 api 기본 url
         */
        String apiUrl = "https://dapi.kakao.com/v2/local/search/category.json";

        /*
         * kakao API를 호출하여 response body를 ResponseKakao객체로 역직렬화 하여 리턴.
         */
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) { // try이 문이 종료될 때 httpClient도 종료된다. (메모리 누수 방지)
            /*
             * 기본 url에 쿼리스트링을 추가하여 호출 시 사용할 완성된 url을 만듬
             */
            URI uri = new URIBuilder(apiUrl)
                    .setParameter("x", x)
                    .setParameter("y", y)
                    .setParameter("radius", radius)
                    .setParameter("category_group_code", categoryGroupCode)
                    .setParameter("size", "10")
                    .build();

            /*
             * get 요청을 할 객체를 생성 후 헤더 추가.
             */
            HttpGet httpGet = new HttpGet(uri);
            httpGet.addHeader("Authorization", Key.KAKAO.getKey());
            httpGet.addHeader("Content-Type", "application/json;charset=UTF-8");

            /*
             * 요청을 하고 그 응답을 받는다.
             */
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) { // try 문이 종료될 때 response는 종료된다.
                HttpEntity entity = response.getEntity(); // body 만을 가져옴
                String responseBody = EntityUtils.toString(entity); // response body to String
                return GsonUtils.fromJson(responseBody, ResponseKakao.class); // ResponseKakao 객체로 역직렬화
            }
        } catch (Exception e) {
            log.error("findByKeyword 메서드에서 error 발생: {}", e);
        }
        return null;
    }
}
