# kakaomap
## 위치기반 장소 검색 Java 애플리케이션 개발

이 Java 어플리케이션은 입력에 기반하여 특정 위치 내의 장소를 검색할 수 있도록 합니다.

특정 위치(키워드) 주변의 지정된 반경 내에서 약국을 검색하는 Java 어플리케이션을 개발합니다.

## 개발환경
1. JAVA17
2. HttpClient(apache) 4.5.1
3. lombok 1.18.12
4. gson 2.10.1
5. slf4j-api 2.0.5, logback-classic 1.4.7
6. junit 5.9.2
7. maven
8. intellij

## 기능 흐름 설명
1. 키보드로 특정 위치에 대한 키워드와 반경을 입력.
2. 입력된 키워드 기반으로 KakaoAPI를 호출하여 경도, 위도값을 받아온다.
3. 응답 받은 Json 데이터를 파싱하여 경도, 위도 값을 추출한다.
4. 경도, 위도, 반경 값을 이용하여 해당 위치 반경안에 있는 약국을 검색하도록 KakaoAPI를 호출한다.
5. 입력받은 약국 정보들을 파싱하여 콘솔창에 출력한다. (10개)
6. 출력된 약국 정보들 중 url을 복사하여 console 창에 다시 입력하면 브라우저가 열리면서 해당 페이지가 열린다.
7. exit을 입력하면 종료한다.

## 프로젝트 구조
![image](https://github.com/jojojojocho/kakaomap/assets/43841476/7bbe9c23-a050-43e3-b0b4-a790fe58cace)





## 예시 입력 및 출력

### 입력화면
위치 키워드를 입력하세요:  
검색 반경을 입력하세요(1000:1km):  
![입력화면](https://github.com/jojojojocho/kakaomap/assets/43841476/80a6067a-7362-4f6d-9bb5-28762fb29484)


### 출력화면

입력한 위치 키워드:  
검색 반경:  

**주유소 검색 결과**
- 장소 URL(지도 위치):
- 상호명:
- 주소:
- 전화번호:
- 거리(km):

**약국 검색 결과**
- 장소 URL(지도 위치):
- 상호명:
- 주소:
- 전화번호:
- 거리(km):

kakaomap URL(장소 URL):http://place.map.kakao.com/26338954 -> 검색된 결과에서 장소 URL을 복사하여 붙여넣기 한 후 엔터 -> 브라우져가 실행
kakaomap URL(장소 URL):exit -> exit 입력하면 프로그램이 종료된다.

프로그램 종료
![출력화면](https://github.com/jojojojocho/kakaomap/assets/43841476/70708b42-31e3-4129-bca3-5de783410a88)

