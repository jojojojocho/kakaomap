package org.fastcampus.api.model.kakao;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;


/**
 * kakao API 응답 결과(document + meta) 객체
 *
 * @since 2023-08-21
 * @author byungsang jo
 */
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class ResponseKakao {

    /**
     * 응답 결과 (실제 필요한 데이터)
     */
    private List<Document> documents = new ArrayList<>();

    /**
     * 응답 관련 정보 (페이지 번호, 총 응답 갯수)
     */
    private Meta meta;

    /**
     * 응답 결과의 제일 첫 번째 문서를 찾아 x좌표(경도)를 리턴해주는 메서드
     *
     * @return x(경도) 좌표
     */
    public String getFirstDocumentLongitude() {
        Document firstDocument = getFirstDocument();
        if (firstDocument != null) {
            return firstDocument.getX();
        }
        log.warn("위치 검색결과가 없습니다. (x좌표가 null 입니다.)");
        return null;
    }

    /**
     * 응답 결과의 제일 첫 번째 문서를 찾아 y좌표(위도)를 리턴해주는 메서드
     *
     * @return y(위도) 좌표
     */
    public String getFirstDocumentLatitude() {
        Document firstDocument = getFirstDocument();
        if (firstDocument != null) {
            return firstDocument.getY();
        }
        log.warn("위치 검색결과가 없습니다. (y좌표가 null 입니다.)");
        return null;
    }

    /**
     * 응답 결과의 제일 첫 번째 문서를 찾아서 리턴해주는 메서드
     *
     * @return 제일 첫 번째 document
     */
    public Document getFirstDocument() {
        if (!documents.isEmpty()) {
            return documents.get(0);
        }
        log.warn("위치 검색결과가 없습니다. (document가 null입니다.)");
        return null;
    }
}
