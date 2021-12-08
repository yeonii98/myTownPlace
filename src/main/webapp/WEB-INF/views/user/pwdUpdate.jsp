<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ include file="../layout/header.jsp" %>

<!--프로필셋팅 메인-->
<main class="main">
    <!--프로필셋팅 섹션-->
    <section class="setting-container">
        <!--프로필셋팅 아티클-->
        <article class="setting__content">

            <!--프로필셋팅 아이디영역-->
            <div class="content-item__01">
                <div class="item__img">
                    <img src="${principal.user.profileImageUrl}" onerror="this.src='/images/Profile.png'"
                         id="userProfileImage"/>
                </div>
                <div class="item__username pwd__username">
                    <h3>${principal.username}</h3>
                </div>
            </div>
            <!--프로필셋팅 아이디영역end-->

            <!--프로필 수정-->
            <form id="profilePwdUpdate" onsubmit="pwdUpdate(${principal.user.id}, event)">
                <div class="content-item__01">
                    <div class="item__title" style="flex: 2;">이전 비밀번호</div>
                    <div class="item__input pwd__input">
                        <input type="password" name="password" placeholder="이전 비밀번호" required/>
                    </div>
                </div>
                <div class="content-item__02">
                    <div class="item__title" style="flex: 2;">새 비밀번호</div>
                    <div class="item__input pwd__input">
                        <input type="password" name="newPassword" placeholder="새 비밀번호" required/>
                    </div>
                </div>
                <div class="content-item__03">
                    <div class="item__title" style="flex: 2;">새 비밀번호 확인</div>
                    <div class="item__input pwd__input">
                        <input type="password" name="newPasswordConfirm" placeholder="새 비밀번호 확인" required/>
                    </div>
                </div>

                <!--제출버튼-->
                <div class="content-item__12">
                    <div class="item__title"></div>
                    <div class="item__input pwd__input">
                        <button>비밀번호 변경</button>
                    </div>
                </div>
                <!--제출버튼end-->

            </form>
            <!--프로필수정 form end-->
        </article>
    </section>
</main>
<!--프로필사진 바꾸기 모달-->
<div class="modal-image" onclick="modalImage()">
    <div class="modal">
        <div>프로필 사진 바꾸기</div>
        <button onclick="profileImageUpload(${principal.user.id}, ${principal.user.id})">사진 업로드</button>
        <button onclick="profileImageDelete(${principal.user.id}, ${principal.user.id})">현재 사진 삭제</button>
        <button onclick="closePopup('.modal-image')">취소</button>
    </div>
</div>
<script src="/js/profile.js"></script>
<script src="/js/update.js"></script>
<script src="/js/getLocation.js"></script>

<%@ include file="../layout/footer.jsp" %>