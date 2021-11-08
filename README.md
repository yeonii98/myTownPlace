# 우리동네 맛집

### 프로젝트 개요
* 동네를 설정하여 해당 동네의 맛집을 불러오고 리뷰 및 별점을 남길 수 있는 웹 페이지 입니다.
---
### 기술
* IDE : InteliJ
* DBMS : MySQL 5.7
* Frontend : BootStrap 4, HTML, CSS, JavaScript
* Backend : Java, Spring Boot, Spring Security
---
### 기능
* 동네 설정하기 - 카카오 지도 api를 사용하여 현재 위치 가져오기
* 맛집 정보 가져오기
    - 카카오 검색 api를 사용하여 "동네" + 맛집 으로 검색된 정보
    - 네이버 이미지 api를 사용하여 "동네" + 맛집 으로 검색된 이미지
* 동네 검색하기
    - 카카오 검색 api를 사용하여 "검색text" + 맛집 으로 검색된 정보
    - 네이버 이미지 api를 사용하여 "검색text" + 맛집 으로 검색된 이미지
* 리뷰 쓰기
    - api 데이터와 연동된 리뷰 작성
* 즐겨찾기
    - api로 가져온 데이터를 즐겨찾기에 추가
* 팔로우
    - 유저간 팔로우 할 수 있음
    - 팔로우 한 유저의 리뷰 불러오기
* 소셜 로그인(OAuth2)
    - 구글 로그인
    - 카카오 로그인
    - 네이버 로그인
