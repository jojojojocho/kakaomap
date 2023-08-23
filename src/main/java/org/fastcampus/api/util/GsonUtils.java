package org.fastcampus.api.util;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * JSON 직렬화 역직렬화용
 * <p>
 * @since 2023-08-21
 * @author byungsang jo
 */
public class GsonUtils {
    /**
     * 인스턴스 하나를 생성 해 놓고 필요할 때마다 사용하는 것이 편리하여 static final로 선언.
     */
    private static final Gson gson =
            new GsonBuilder().setPrettyPrinting()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

    /**
     * 인스턴스를 Json 문자열로 변환
     *
     * @param obj 직렬화할 object
     * @return 직렬화된 json 문자열
     */
    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    /**
     * Json 문자열을 인스턴스로 변환
     *
     * @param json json 문자열
     * @param classOfT object type class
     * @return json 문자열의 정보를 포함하고 있는 매개변수로 넣은 class 타입의 인스턴스
     * @param <T>
     */
    public static <T> T fromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }

}
