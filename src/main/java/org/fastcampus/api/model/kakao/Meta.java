package org.fastcampus.api.model.kakao;

import com.google.gson.annotations.SerializedName;
import lombok.*;


/**
 * kakao API 응답 Meta 객체
 *
 * @since 2023-08-21
 * @author byungsang jo
 */
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Meta {

    /**
     * 	검색된 문서 수
     */
    @SerializedName("total_count")
    private Integer totalCount;

    /**
     * total_count 중 노출 가능 문서 수 (최대값: 45)
     */
    @SerializedName("pageable_count")
    private Integer pageableCount;

    /**
     * 현재 페이지가 마지막 페이지인지 여부
     * 값이 false면 다음 요청 시 page 값을 증가시켜 다음 페이지 요청 가능
     */
    @SerializedName("is_end")
    private boolean isEnd;

    /**
     * 질의어의 지역 및 키워드 분석 정보
     */
    @SerializedName("same_name")
    private SameName sameName;


}
