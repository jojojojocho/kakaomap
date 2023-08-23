package org.fastcampus;

import lombok.extern.slf4j.Slf4j;
import org.fastcampus.api.controller.MapSearchController;
import org.fastcampus.api.model.kakao.Document;
import org.fastcampus.api.model.kakao.ResponseKakao;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 약국을 찾는 api를 불러 console로 출력하도록 구현한 클래스
 *
 * @author byungsang jo
 * @since 2023-08-22
 */
@Slf4j
public class Console {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {

        /*
         * 위치 키워드 입력
         */
        String keyword = inputKeyword(br);

        /*
         * 검색 반경 입력
         */
        String radius = inputRadius(br);

        /*
         * 검색 결과 출력
         */
        System.out.println("입력한 위치 키워드:" + keyword);
        System.out.println("검색반경:" + String.format("%.1fkm", Integer.parseInt(radius) * 0.001));
        System.out.println();

        /*
         * 결과 출력
         */
        MapSearchController mapSearchController = new MapSearchController(); // controller 생성
        ResponseKakao responseKakao;
        List<Document> documents;
        responseKakao = mapSearchController.findPharmacy(keyword, radius); // 약국 찾는 메서드 호출

        // 키워드로 검색하고, 장소가 결과로 나오지 않았을 경우 유효한 장소가 나올 때까지 input값을 다시 받는다.
        while (checkResponseKakao(responseKakao)) {
            System.out.println("조회된 결과가 없습니다. 주소와 반경을 다시 입력해 주세요!");
            keyword = inputKeyword(br);
            radius = inputRadius(br);
            responseKakao = mapSearchController.findPharmacy(keyword, radius);
        }
        documents = responseKakao.getDocuments(); // 장소 정보 가져오기


        /*
         * 검색 결과 출력
         */
        System.out.println();
        System.out.println("**약국 검색결과**");
        for (Document document : documents) {
            System.out.println("장소 URL(지도 위치):" + document.getPlaceUrl());
            System.out.println("상호명:" + document.getPlaceName());
            System.out.println("주소:" + document.getAddressName());
            System.out.println("전화번호:" + document.getPhone());
            System.out.println("거리:" + document.meterToKm());
            System.out.println("----------------------------------------------");
        }


        /*
         * url 입력 및 브라우저 오픈
         */
        try {
            String url;
            while (true) {
                System.out.print("kakaomap URL(장소 URL):");
                url = br.readLine();
                if (url.equals("exit")) {
                    break;
                }

                if (!isValidUrl(url)) {
                    log.warn("유효한 url 주소가 아닙니다. 다시 입력해 주세요!");
                    continue;
                }
                openBrowser(url);
            }
            System.out.println("프로그램 종료");
        } catch (Exception e) {
            log.error("장소 URL 입력 error: {}", e);
        }

    }

    public static boolean checkResponseKakao(ResponseKakao responseKakao) {
        return responseKakao == null || responseKakao.getDocuments().isEmpty();
    }

    /**
     * url이 유효한지 확인해주는 메서드
     *
     * @param url input값으로 입력된 url 주소
     * @return 유효여부 T/F
     */
    public static boolean isValidUrl(String url) {
        Pattern urlPattern = Pattern.compile("^https?://(www\\.)?([a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}(/.*)?$");

        Matcher matcher = urlPattern.matcher(url);
        return matcher.matches();
    }

    /**
     * 위치 키워드 입력을 재 사용하기 위해서 만든 메서드
     *
     * @param br inputData 읽기용 버퍼
     * @return 위치 키워드
     */
    public static String inputKeyword(BufferedReader br) {
        System.out.print("위치 키워드를 입력하세요:");
        String keyword = "";
        try {
            keyword = br.readLine();
            while (isBlank(keyword)) {
                System.out.print("위치 키워드를 입력하세요:");
                keyword = br.readLine();
            }
            System.out.println();

        } catch (Exception e) {
            log.error("위치 키워드 입력 error: {}", e);
        }
        return keyword;
    }

    /**
     * 검색 반경 입력을 재 사용하기 위해서 만든 메서드
     *
     * @param br inputData 읽기용 버퍼
     * @return 검색 반경
     */
    public static String inputRadius(BufferedReader br) {
        System.out.print("검색 반경을 입력하세요(1000:1km):");
        String radius = "";
        try {
            radius = br.readLine();
            while (isBlank(radius) || !isNumeric(radius)) {
                System.out.print("검색 반경을 입력하세요(1000:1km):");
                radius = br.readLine();
            }
            System.out.println();
        } catch (Exception e) {
            log.error("검색 반경 입력 error: {}", e);
        }
        return radius;
    }

    /**
     * url을 받아 브라우저를 열어주는 메서드
     *
     * @param url url 주소
     */
    public static void openBrowser(String url) {
        try {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI(url));
            } else {
                log.warn("브라우저를 여는 것이 지원되지 않습니다.");
            }
        } catch (Exception e) {
            log.error("openBrowser error: {}", e);
        }
    }

    /**
     * 입력된 문자열이 공백인지 아닌지 체크하는 메서드
     *
     * @param input 입력 문자열
     * @return 공백 여부 T/F
     */
    public static boolean isBlank(String input) {
        if (input.isBlank()) {
            log.warn("input 값이 공백입니다. 다시 입력 해주세요.");
            return true;
        }
        return false;
    }

    /**
     * 입력된 문자열이 숫자인지 아닌지 확인하는 메서드
     *
     * @param input 입력 문자열
     * @return 숫자인지 아닌지 여부 T/F
     */
    public static boolean isNumeric(String input) {
        if (!input.matches("\\d+")) {
            log.warn("input 값이 숫자가 아닙니다. 다시 입력 해주세요.");
            return false;
        }
        return true;
    }

}



