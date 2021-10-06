<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../layout/header.jsp" %>

<main class="main">
    <section class="container">
        <!--전체 리스트 시작-->
        <article class="story-list" id="storyList">
            <c:forEach items="${u}" var="u" varStatus="idx">
                <div class="story-list__item">
                    <div class="sl__item__header">
                        <!--                    <div>-->
                        <!--                       <img src="/upload/Profile.png"-->
                        <!--                            onerror="this.src='/images/Profile.png'"/>-->
                        <!--                    </div>-->
                        <div style="display: flex">
                            <div>${u.name}</div>
                            <div class="story-cnt" style="font-size: 15px">
                                <i class="fas fa-star"></i>
                                <span id="favoriteCnt-${idx.count}">${u.favoriteCnt}</span> 명이 이 음식점을 즐겨찾습니다
                                <i class="fas fa-star"></i>
                            </div>
<%--                            <div>--%>
<%--                                <button class="story__review" onclick="location.href='/image/popular'">--%>
<%--                                    리뷰 보러가기--%>
<%--                                </button>--%>
<%--                            </div>--%>
                        </div>
                        <div style="margin-right: 1rem">
                            <c:choose>
                                <c:when test="${u.favoriteState}">
                                    <button class="cta" onclick="toggleFavorite(this, ${u.id}, ${idx.count})">즐겨찾기 해제</button>
                                </c:when>
                                <c:otherwise>
                                    <button class="cta blue" onclick="toggleFavorite(this, ${u.id}, ${idx.count}, '${u.name}', '${u.image}', '${u.detailUrl}')">즐겨찾기 추가</button>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>

                    <div class="sl__item__img">
                        <img src="${u.image}" onerror="this.src='/images/foodImg.png'"/>
                    </div>

                    <div class="sl__item__contents">
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

                        <!--                    <div id="storyCommentList-1">-->

                        <!--                        <div class="sl__item__contents__comment" id="storyCommentItem-1">-->
                        <!--                            <p>-->
                        <!--                                <b>먹보 :</b> 당장 먹으러갈게요.-->
                        <!--                            </p>-->

                        <!--                            <button>-->
                        <!--                                <i class="fas fa-times"></i>-->
                        <!--                            </button>-->
                        <!--                        </div>-->
                        <!--                    </div>-->

                        <!--                    <div class="sl__item__input">-->
                        <!--                        <input type="text" placeholder="댓글 달기..." id="storyCommentInput-1"/>-->
                        <!--                        <button type="button" onClick="addComment()">게시</button>-->
                        <!--                    </div>-->

                    </div>
                </div>
            </c:forEach>


        </article>
        <article class="story-list" id="searchList">

            <!--검색 리스트 아이템-->

        </article>
    </section>
</main>
<script src="/js/story.js"></script>
</body>
</html>
