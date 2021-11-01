<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../layout/header.jsp" %>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<main class="main">
    <section class="container">
        <!--전체 리스트 시작-->
        <article class="story-list" id="storyList">
            <c:forEach items="${u}" var="u" varStatus="idx">
                <div class="story-list__item">
                    <div class="sl__item__header sl__item__header--home">
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
                        <div style="margin-right: 1rem;font-size: 13px">
                            <c:choose>
                                <c:when test="${u.favoriteState}">
                                    <button class="cta" onclick="toggleFavorite(this, ${u.id})">즐겨찾기 해제</button>
                                </c:when>
                                <c:otherwise>
                                    <button class="cta blue" onclick="toggleFavorite(this, ${u.id}, ${idx.count}, '${u.name}', '${u.image}', '${u.detailUrl}')">즐겨찾기 추가</button>
                                </c:otherwise>
                            </c:choose>
                            <button class="cta green" onclick="location.href='/apiReview/${u.id}/${u.name}/${location}'">리뷰 보기</button>
                            <button class="cta blue" onclick="location.href='/apiReview/upload/${u.id}/${u.name}/${location}'">리뷰 쓰기</button>
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
        <ul class="pagination justify-content-center">
            <c:choose>
                <c:when test="${page eq 1}">
                    <li class="page-item disabled"><a class="page-link" href="?location=${location}&page=${page-1}">Previous</a></li>
                </c:when>
                <c:otherwise>
                    <li class="page-item"><a class="page-link" href="?location=${location}&page=${page-1}">Previous</a></li>
                </c:otherwise>
            </c:choose>
            <c:forEach var="i" begin="1" end="5">
                <c:choose>
                    <c:when test="${page eq i}">
                        <li class="page-item active"><a class="page-link" href="?location=${location}&page=${i}">${i}</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item"><a class="page-link" href="?location=${location}&page=${i}">${i}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:choose>
                <c:when test="${page eq 5 }">
                    <li class="page-item disabled"><a class="page-link" href="?location=${location}&page=${page+1}">Next</a></li>
                </c:when>
                <c:otherwise>
                    <li class="page-item"><a class="page-link" href="?location=${location}&page=${page+1}">Next</a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </section>
</main>
<script src="/js/story.js"></script>
</body>
</html>
