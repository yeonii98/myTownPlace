<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ include file="../layout/header.jsp" %>

<!--프로필 섹션-->
<section class="profile">
    <!--유저정보 컨테이너-->
    <div class="profileContainer">

        <!--유저이미지-->
        <div class="profile-left">
            <c:choose>
                <c:when test="${null eq principal.user.profileImageUrl || empty principal.user.profileImageUrl}">
                    <div class="profile-img-wrap" id="profileImg"
                         onclick="profileImageUpload(${principal.user.id}, ${dto.user.id})">
                </c:when>
                <c:otherwise>
                    <div class="profile-img-wrap" id="profileImg"
                         onclick="popup('.modal-image', ${dto.user.id}, ${principal.user.id})">
                </c:otherwise>
            </c:choose>
                <img src="/upload/${dto.user.profileImageUrl}"
                     onerror="this.src='/images/Profile.png'" id="userProfileImage"/>
                </div>
                <form id="userProfileImageForm">
                    <input type="file" name="profileImageFile" style="display: none;"
                           id="userProfileImageInput"/>
                </form>
        </div>
        <!--유저이미지end-->

        <!--유저정보 및 사진등록 구독하기-->
        <div class="profile-right">
            <div class="name-group">
                <h2>${dto.user.username}</h2>
                <c:choose>
                    <c:when test="${dto.pageOwner}">
                        <button class="cta blue" onclick="location.href='/myReview'">내 글 목록</button>
                        <button class="modi" onclick="popup('.modal-info')"><i class="fas fa-cog"></i></button>
                    </c:when>
                    <c:when test="${!dto.pageOwner}">
                        <c:choose>
                            <c:when test="${dto.subscribeState}">
                                <button class="modi" onclick="toggleSubscribe(this, ${dto.user.id})"><i
                                        class="fas fa-star"
                                        style="color: #0095f6"></i>
                                </button>
                            </c:when>
                            <c:otherwise>
                                <button class="modi" onclick="toggleSubscribe(this, ${dto.user.id})"><i
                                        class="far fa-star"
                                        style="color: #0095f6"></i>
                                </button>
                            </c:otherwise>
                        </c:choose>
                    </c:when>
                    <c:otherwise>
                    </c:otherwise>
                </c:choose>
            </div>

            <div class="subscribe">
                <ul>
                    <li><a href=""> 게시물<span id="reviewCnt">${dto.imageCount}</span>
                    </a></li>

                    <li><a href="javascript:favoriteModalOpen(${dto.user.id});">
                        즐겨찾기<span>${dto.favoriteCount}</span>
                    </a></li>
                    <li><a href="javascript:subscribeInfoModalOpen(${dto.user.id});">
                        팔로우<span>${dto.subscribeCount}</span>
                    </a></li>

                </ul>
            </div>
            <div class="state">
                <h5>${dto.user.name}</h5>
                <div>${dto.user.bio}</div>
                <div><a href="${dto.user.website}" style="color: #00376b">${dto.user.website}</a></div>
            </div>
        </div>
        <!--유저정보 및 사진등록 구독하기-->

    </div>
</section>
<div style="display: flex; justify-content: center">
    <div class="profile-div"></div>
</div>
<!--게시물컨섹션-->
<section id="tab-content">
    <!--게시물컨컨테이너-->
    <div class="profileContainer">
        <!--그냥 감싸는 div (지우면이미지커짐)-->
        <div id="tab-1-content" class="tab-content-item show">
            <!--게시물컨 그리드배열-->
            <div class="tab-1-content-inner">
                <!--아이템들-->
                <c:set var = "size" value="${fn:length(dto.user.reviews)}"/>
                <c:forEach var="i" begin="1" end="${size}">
                    <div class="img-box" id="review-${dto.user.reviews[size-i].id}" onclick="popup2('.modal-review', ${dto.user.id}, ${principal.user.id}, ${dto.user.reviews[size-i].id})">
                        <a href=""> <img src="/upload/${dto.user.reviews[size-i].postImageUrl}"/>
                        </a>
                        <div class="comment">
                            <a href="#" class=""> <i class="fas fa-thumbs-up"></i><span>${dto.user.reviews[size-i].likeCount}</span>&nbsp&nbsp<i class="fas fa-comment"></i><span>${dto.user.reviews[size-i].commentCount}</span></a>
                        </div>
                    </div>
                </c:forEach>

                <!--아이템들end-->
            </div>
        </div>
    </div>
</section>

<div class="modal-review" onclick="modalReview()">
    <div class="modal" id="reviewModal">

    </div>
</div>
<!--로그아웃, 회원정보변경 모달-->
<div class="modal-info" onclick="modalInfo()">
    <div class="modal">
        <button onclick="location.href='/user/update'">프로필 변경</button>
        <button onclick="location.href='/user/pwdUpdate'">비밀번호 변경</button>
        <button onclick="location.href='/logout'">로그아웃</button>
        <button onclick="closePopup('.modal-info')">취소</button>
    </div>
</div>
<!--로그아웃, 회원정보변경 모달 end-->

<!--프로필사진 바꾸기 모달-->
<div class="modal-image" onclick="modalImage()">
    <div class="modal">
        <div>프로필 사진 바꾸기</div>
        <button onclick="profileImageUpload(${principal.user.id}, ${dto.user.id})">사진 업로드</button>
        <button onclick="profileImageDelete(${principal.user.id}, ${dto.user.id})">현재 사진 삭제</button>
        <button onclick="closePopup('.modal-image')">취소</button>
    </div>
</div>

<!--프로필사진 바꾸기 모달end-->
<div class="modal-subscribe m-favorite">
    <div class="subscribe">
        <div class="subscribe-header">
            <span>즐겨찾기</span>
            <button onclick="modalClose()">
                <i class="fas fa-times"></i>
            </button>
        </div>

        <div class="subscribe-list" id="favoriteModalList">
        </div>
    </div>
</div>

<div class="modal-subscribe m-sub">
    <div class="subscribe">
        <div class="subscribe-header">
            <span>팔로우</span>
            <button onclick="modalClose()">
                <i class="fas fa-times"></i>
            </button>
        </div>

        <div class="subscribe-list" id="subscribeModalList">
        </div>
    </div>
</div>


<script src="/js/profile.js"></script>

<%@ include file="../layout/footer.jsp" %>