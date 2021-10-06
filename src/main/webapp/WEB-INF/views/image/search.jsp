<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../layout/header.jsp" %>

<main class="main">
    <section class="container">
        <!--전체 리스트 시작-->
        <article class="story-list" id="storyList">
            <c:forEach items="${u}" var="u">
                <div class="story-list__item">
                    <div class="sl__item__header">
                        <div style="display: flex">
                            <div>${u.name}</div>
                            <div>
                                <button class="story__review" onclick="location.href='/image/popular'">
                                    리뷰 보러가기
                                </button>
                            </div>
                        </div>
                        <div style="margin-right: 1rem">
                            <c:choose>
                                <c:when test="${u.favoriteState}">
                                    <button class="cta" onclick="toggleFavoriteModal(this, ${u.id})">즐겨찾기 해제</button>
                                </c:when>
                                <c:otherwise>
                                    <button class="cta blue" onclick="toggleFavoriteModal(this, ${u.id})">즐겨찾기 추가</button>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>

                    <div class="sl__item__img">
                        <img src="${u.image}" onerror="this.src='/images/foodImg.png'"/>
                    </div>

                    <div class="sl__item__contents">
                        <div class="sl__item__contents__icon">
                            <button>
                                <i class="fas fa-heart active" id="storyLikeIcon-1" onclick="toggleLike()"></i>
                            </button>
                            <span class="like"><b id="storyLikeCount-1">3 </b>명이 이 음식점을 좋아합니다.</span>
                        </div>


                        <div class="sl__item__contents__content">
                            <div><i class="fas fa-tags"></i></div> ${u.category}
                        </div>

                        <div class="sl__item__contents__content">
                            <div><i class="fas fa-map-marker-alt"></i></div> ${u.address}
                        </div>

                        <div class="sl__item__contents__content">
                            <div><i class="fas fa-phone"></i></div> ${u.phone}
                        </div>

                        <div class="sl__item__contents__content">
                            <div><i class="fas fa-info-circle"></i></div> <a href="${u.detailUrl}">상세보기</a>
                        </div>
                    </div>
                </div>
            </c:forEach>


        </article>
    </section>
</main>
<script src="/js/story.js"></script>
</body>
</html>
