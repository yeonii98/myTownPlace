<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../layout/header.jsp" %>

<main class="main">

    <input type="hidden" value="${principal.user.location}" id="location">
    <section class="container">
        <div class="row">
            <div class="map_wrap col-md-7">
                <div id="map" style="width:100%;height:100%;position:relative;overflow:hidden;"></div>

                <div id="menu_wrap" class="bg_white" style="visibility: hidden">
                    <ul id="placesList"></ul>

                </div>
            </div>
            <div class="story-list col-md-5" id="storyList">
                <c:forEach var="i" begin="0" end="8">
                    <div class="story-list__item" id="item-${i}">
                    </div>
                </c:forEach>
            </div>
        </div>
        <div id="pagination"></div>

        <!--전체 리스트 시작-->

<%--        <ul class="pagination justify-content-center">--%>
<%--            <c:choose>--%>
<%--                <c:when test="${page eq 1}">--%>
<%--                    <li class="page-item disabled"><a class="page-link" href="?page=${page-1}">Previous</a></li>--%>
<%--                </c:when>--%>
<%--                <c:otherwise>--%>
<%--                    <li class="page-item"><a class="page-link" href="?page=${page-1}">Previous</a></li>--%>
<%--                </c:otherwise>--%>
<%--            </c:choose>--%>
<%--            <c:forEach var="i" begin="1" end="3">--%>
<%--                <c:choose>--%>
<%--                    <c:when test="${page eq i}">--%>
<%--                        <li class="page-item active" id="page-${page}"><a class="page-link" href="?page=${i}">${i}</a>--%>
<%--                        </li>--%>
<%--                    </c:when>--%>
<%--                    <c:otherwise>--%>
<%--                        <li class="page-item" id="page-${page}"><a class="page-link" href="?page=${i}">${i}</a></li>--%>
<%--                    </c:otherwise>--%>
<%--                </c:choose>--%>
<%--            </c:forEach>--%>
<%--            <c:choose>--%>
<%--                <c:when test="${page eq 3 }">--%>
<%--                    <li class="page-item disabled"><a class="page-link" href="?page=${page+1}">Next</a></li>--%>
<%--                </c:when>--%>
<%--                <c:otherwise>--%>
<%--                    <li class="page-item"><a class="page-link" href="?page=${page+1}">Next</a></li>--%>
<%--                </c:otherwise>--%>
<%--            </c:choose>--%>
<%--        </ul>--%>
    </section>
</main>
<script src="/js/story.js"></script>
<script type="text/javascript"
        src="//dapi.kakao.com/v2/maps/sdk.js?appkey=85261d48bd7d56d5095b625de18c2e35&libraries=services"></script>
<script src="/js/map.js"></script>
</body>
</html>
