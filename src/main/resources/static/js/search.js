/**
 2. 스토리 페이지
 (1) 스토리 로드하기
 (2) 스토리 스크롤 페이징하기
 (3) 좋아요, 안좋아요
 (4) 댓글쓰기
 (5) 댓글삭제
 */

// (1) 스토리 로드하기
let mPage;
let data;
function search() {
    mPage = 1;
	data = $("#searchText").val();
	$("#storyList").empty();
    $("#searchList").empty();

    searchLoad();
}

function searchLoad(){
	console.log(mPage);
	$.ajax({
		url:`/api/search/${mPage}`,
		data: {"location" : data},
		dataType:"json"//서버가 요청 URL을 통해서 응답하는 내용의 타입
	}).done(res=>{
		console.log(res);
		res.data.forEach((u) => {
			console.log(u);
			let item = getSearchItem(u);
			$("#storyList").empty();
			$("#searchList").append(item);
		})
	}).fail(error=>{
		console.log("오류",error);
	});
}

$(window).scroll(() => {
	let checkNum = $(window).scrollTop() - ($(document).height() - $(window).height());

	if(checkNum < 1 && checkNum > -500){
		mPage++;
		searchLoad();
	}
});
function enterkey() {
	if(window.event.keyCode === 13){
		search();
	}
}

function getSearchItem(u) {
	let item = `<div class="story-list__item">
                <div class="sl__item__header">
                    <div>
                    </div>
                    <div>${u.name}</div>
                    <button class="story__review" onclick="location.href='/image/popular'">
                        리뷰 보러가기
                    </button>
                </div>

                <div class="sl__item__img">
                    <img src="https://d1fs6z327bfwly.cloudfront.net/${u.image}" onerror="this.src='/images/foodImg.png'"/>
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
            </div>`;
	return item;
}







