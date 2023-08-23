package org.fastcampus.api.model.kakao;

import com.google.gson.annotations.SerializedName;
import lombok.*;

/**
 * kakao API 응답 document 객체
 *
 * @author byungsang jo
 * @since 2023-08-21
 */
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Document {

    /**
     * 장소 ID
     */
    @SerializedName("id")
    private String id;

    /**
     * 장소명, 업체명
     */
    @SerializedName("place_name")
    private String placeName;

    /**
     * 카테고리 이름
     */
    @SerializedName("category_name")
    private String categoryName;

    /**
     * 중요 카테고리만 그룹핑한 카테고리 그룹 코드
     */
    @SerializedName("category_group_code")
    private String categoryGroupCode;

    /**
     * 중요 카테고리만 그룹핑한 카테고리 그룹 코드
     */
    @SerializedName("category_group_name")
    private String categoryGroupName;

    /**
     * 전화번호
     */
    @SerializedName("phone")
    private String phone;

    /**
     * 전체 지번 주소
     */
    @SerializedName("address_name")
    private String addressName;

    /**
     * 전체 도로명 주소
     */
    @SerializedName("road_address_name")
    private String roadAddressName;

    /**
     * X 좌표 혹은 경도(longitude)
     */
    @SerializedName("x")
    private String x;

    /**
     * Y 좌표 혹은 위도(latitude)
     */
    @SerializedName("y")
    private String y;

    /**
     * 카카오맵 url 주소
     */
    @SerializedName("place_url")
    private String placeUrl;

    /**
     * 거리
     */
    @SerializedName("distance")
    private String distance;

    /**
     * meter를 km로 변경해주는 메서드
     *
     * @return km 문자열
     */
    public String meterToKm() {
        if (!this.distance.isBlank()) {
            return String.valueOf(String.format("%.3fkm", Integer.parseInt(this.distance) * 0.001));
        }
        return null;
    }

}
