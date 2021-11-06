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
                    <img src="/upload/${principal.user.profileImageUrl}" onerror="this.src='/images/Profile.png'" id="userProfileImage"/>
                </div>
                <div class="item__username">
                    <h1>${principal.username}</h1>
                    <button class="profile-img__update" onclick="profileImageUpload(${principal.user.id})">프로필 사진 바꾸기</button>
                        <form id="userProfileImageForm">
                            <input type="file" name="profileImageFile" style="display: none;"
                                   id="userProfileImageInput"/>
                        </form>
                </div>
            </div>
            <!--프로필셋팅 아이디영역end-->

            <!--프로필 수정-->
            <form id="profileUpdate" onsubmit="update(${principal.user.id}, event)">
                <div class="content-item__02">
                    <div class="item__title">이름</div>
                    <div class="item__input">
                        <input type="text" name="name" placeholder="이름"
                               value="${principal.user.name}" required/>
                    </div>
                </div>
                <div class="content-item__03">
                    <div class="item__title">사용자 이름</div>
                    <div class="item__input">
                        <input type="text" name="username" placeholder="사용자 이름"
                               value="${principal.user.username}" required/>
                    </div>
                </div>
                <%--				<div class="content-item__04">--%>
                <%--					<div class="item__title">패스워드</div>--%>
                <%--					<div class="item__input">--%>
                <%--						<input type="password" name="password" placeholder="패스워드"  />--%>
                <%--					</div>--%>
                <%--				</div>--%>
                <div class="content-item__05">
                    <div class="item__title">웹사이트</div>
                    <div class="item__input">
                        <input type="text" name="website" placeholder="웹 사이트"
                               value="${principal.user.website}"/>
                    </div>
                </div>
                <div class="content-item__06">
                    <div class="item__title">소개</div>
                    <div class="item__input">
                        <textarea name="bio" id="" rows="3">${principal.user.bio}</textarea>
                    </div>
                </div>

                <div class="content-item__08">
                    <div class="item__title">이메일</div>
                    <div class="item__input">
                        <input type="text" name="email" placeholder="이메일"
                               value="${principal.user.email}" readonly="readonly"
                               style="background-color: rgba(var(--bb2,239,239,239),1)"/>
                    </div>
                </div>
                <div class="content-item__09">
                    <div class="item__title">전화번호</div>
                    <div class="item__input">
                        <input type="text" name="phone" placeholder="전화번호"
                               value="${principal.user.phone}"/>
                    </div>
                </div>
                <div class="content-item__10">
                    <div class="item__title">성별</div>
                    <div class="item__input">
                        <input type="text" name="gender" value="${principal.user.gender}"/>
                    </div>
                </div>
<%--                <div class="content-item__07">--%>
<%--                    <div class="item__title"></div>--%>
<%--                    <div class="item__input">--%>
<%--                        <span><b>홍보용 계정</b></span> <span>맛집 홍보용 계정을 사용하실 유저라면 아래의 정보를 입력해주세요</span>--%>
<%--                    </div>--%>
<%--                </div>--%>
                <div class="content-item__10">
                    <div class="item__title">내 위치(동 이름)</div>
                    <div class="item__input">
                        <input type="text" class="getLocation" name="location" value="${principal.user.location}" required/>
                        <div style="width: 355px; padding-top: 7px" onclick="loadCoords()" class="text_location">위치 받아오기</div>
                    </div>
                </div>

                <!--제출버튼-->
                <div class="content-item__11">
                    <div class="item__title"></div>
                    <div class="item__input">
                        <button>제출</button>
                    </div>
                </div>
                <!--제출버튼end-->

            </form>
            <!--프로필수정 form end-->
        </article>
    </section>
</main>

<script src="/js/update.js"></script>
<script src="/js/getLocation.js"></script>
<script src="/js/profile.js"></script>
<%@ include file="../layout/footer.jsp" %>