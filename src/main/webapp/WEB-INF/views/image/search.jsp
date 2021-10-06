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
                        <div style="display: flex">
                            <div>${u.name}</div>
                            <div class="story-cnt" style="font-size: 15px">
                                <i class="fas fa-star"></i>
                                <span id="favoriteCnt-${idx.count}">${u.favoriteCnt}</span> 명이 이 음식점을 즐겨찾습니다
                                <i class="fas fa-star"></i>
                            </div>
                        </div>
                        <div style="margin-right: 1rem">
                            <c:choose>
                                <c:when test="${u.favoriteState}">
                                    <button class="cta" onclick="toggleFavorite(this, ${u.id})">즐겨찾기 해제</button>
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
                    </div>
                </div>
            </c:forEach>


        </article>
    </section>
</main>
<script src="/js/story.js"></script>
</body>
</html>
