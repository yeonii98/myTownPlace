<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Photogram</title>
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="/css/header.css">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
        integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous" />
    <link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
</head>

<body>
    <div class="container">
        <main class="loginMain">
        <!--로그인섹션-->
            <section class="login">
               <!--로그인박스-->
                <article class="login__form__container">
                   <!--로그인 폼-->
                   <div class="login__form">
                        <h1 style="font-family: 'Gowun Dodum', sans-serif">우리 동네 맛집<i class="fas fa-utensils header_icon"></i></h1>
                        
                        <!--로그인 인풋-->
                        <form class="login__input" action="/auth/signin" method="POST">
                            <input type="text" name="username" placeholder="사용자 이름 또는 이메일" required="required" />
                            <input type="password" name="password" placeholder="비밀번호" required="required" />
                            <button>로그인</button>
                        </form>
                        <!--로그인 인풋end-->
                        
                        <!-- 또는 -->
                        <div class="login__horizon">
                            <div class="br"></div>
                            <div class="or">또는</div>
                            <div class="br"></div>
                        </div>
                        <!-- 또는end -->
                        
                        <!-- Oauth 소셜로그인 -->
                        <div class="login__google">
                            <button onclick="location.href='/oauth2/authorization/google'">
                                <i class="fab fa-google"></i>
                                <span>Google로 로그인</span>
                            </button>
                        </div>
                       <div class="login__naver">
                           <button onclick="location.href='/oauth2/authorization/naver'">
                               <img src="/images/btnG_아이콘사각.png"/>
                               <span>Naver로 로그인</span>
                           </button>
                       </div>
                       <div class="login__kakao">
                           <button onclick="location.href='/oauth2/authorization/kakao'">
                               <img src="/images/kakao_login_medium_narrow.png"/>
                           </button>
                       </div>
                        <!-- Oauth 소셜로그인end -->
                    </div>
                    
                    <!--계정이 없으신가요?-->
                    <div class="login__register">
                        <span style="color: black">계정이 없으신가요?</span>
                        <a href="/auth/signup">가입하기</a>
                    </div>
                    <!--계정이 없으신가요?end-->
                </article>
            </section>
        </main>
        
    </div>
</body>

</html>