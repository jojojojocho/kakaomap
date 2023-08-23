package org.fastcampus.api.model.kakao;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.util.List;


/**
 * kakao API 응답 Meta 객체 하위 객체
 * 질의어의 지역 및 키워드 분석 정보
 *
 * @since 2023-08-21
 * @author byungsang jo
 */
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SameName {
    /**
     * 질의어에서 인식된 지역의 리스트
     * (예: '중앙로 맛집' 에서 '중앙로'에 해당하는 지역 리스트)
     */
    @SerializedName("region")
    private List<String> region;

    /**
     * 질의어에서 지역 정보를 제외한 키워드
     * (예: '중앙로 맛집' 에서 '맛집')
     */
    @SerializedName("keyword")
    private String keyword;

    /**
     * 인식된 지역 리스트 중 현재 검색에 사용된 지역 정보
     */
    @SerializedName("selected_region")
    private String selectedRegion;

}
