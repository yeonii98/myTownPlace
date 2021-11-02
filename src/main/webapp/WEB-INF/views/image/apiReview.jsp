<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../layout/header.jsp" %>
<link href="https://fonts.googleapis.com/css2?family=Single+Day&display=swap" rel="stylesheet">
<main class="main">
    <input type="hidden" id="apiId" value="${apiId}"/>
    <section class="container">
        <!--전체 리스트 시작-->
        <article class="story-list" id="storyList">
            <div style="text-align: center; margin-bottom: 2rem">
                <div style="display: inline-block">
                    <div style="display: flex">
                        <div class="star-ratings-avg--text">평점 ${dto.avgRating}</div>
                        <div class="star-ratings">
                            <div
                                    class="star-ratings-fill space-x-2 text-lg"
                                    style="width: ${dto.avgRating * 20}%"
                            >
                                <span>★</span><span>★</span><span>★</span><span>★</span><span>★</span>
                            </div>
                            <div class="star-ratings-base space-x-2 text-lg">
                                <span>★</span><span>★</span><span>★</span><span>★</span><span>★</span>
                            </div>
                        </div>
                    </div>

                </div>
            </div>

        </article>
    </section>
</main>
<script src="/js/apiReview.js"></script>
</body>
</html>
