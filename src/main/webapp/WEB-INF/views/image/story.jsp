<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<main class="main">
	<section class="container">
		<!--전체 리스트 시작-->
		<article class="story-list" id="storyList">

			<!--전체 리스트 아이템-->
			<div class="story-list__item">
				<div class="sl__item__header">
					<div>
						<img src="#"
							onerror="this.src='/images/profile.JPG'" />
					</div>
					<div>허군스시</div>
					<button class="story__review" onclick="location.href='/image/popular'">
						리뷰 보러가기
					</button>
				</div>

				<div class="sl__item__img">
					<img src="https://hajl.athuman.com/karuta/uploads/6e45128aad8bdcf39055b81840ecbe0186605633.jpeg" />
				</div>

				<div class="sl__item__contents">
					<div class="sl__item__contents__icon">
						<button>
							<i class="fas fa-heart active" id="storyLikeIcon-1" onclick="toggleLike()"></i>
						</button>
						<span class="like"><b id="storyLikeCount-1">3 </b>명이 이 음식점을 좋아합니다.</span>
					</div>


					<div class="sl__item__contents__content">
						<p>신선한 회를 제공합니다. 많관부😍</p>
					</div>

					<div id="storyCommentList-1">

						<div class="sl__item__contents__comment" id="storyCommentItem-1"">
							<p>
								<b>먹보 :</b> 당장 먹으러갈게요.
							</p>

							<button>
								<i class="fas fa-times"></i>
							</button>

						</div>

					</div>

					<div class="sl__item__input">
						<input type="text" placeholder="댓글 달기..." id="storyCommentInput-1" />
						<button type="button" onClick="addComment()">게시</button>
					</div>

				</div>
			</div>

		</article>
	</section>
</main>
<script src="/js/story.js"></script>
</body>
</html>
