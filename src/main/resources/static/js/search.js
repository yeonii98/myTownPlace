/**
 2. 스토리 페이지
 (1) 스토리 로드하기
 (2) 스토리 스크롤 페이징하기
 (3) 좋아요, 안좋아요
 (4) 댓글쓰기
 (5) 댓글삭제
 */

// (1) 스토리 로드하기
let mPage = 1;


function search() {
	let data = $("#searchText").val();
	$("#storyList").empty();
	function searchLoad(){
        $.ajax({
            url:`/api/search/${mPage}`,
            data: {"location" : data},
            dataType:"json"//서버가 요청 URL을 통해서 응답하는 내용의 타입
        }).done(res=>{
            console.log(res);
            res.data.forEach((u) => {
                console.log(u);
                let item = getSearchItem(u);
                $("#storyList").append(item);
            })
        }).fail(error=>{
            console.log("오류",error);
        });
    }
    searchLoad();
    $(window).scroll(() => {
        let checkNum = $(window).scrollTop() - ($(document).height() - $(window).height());

        console.log(checkNum);
        if(checkNum < 1 && checkNum > -1000){
            mPage++;
            searchLoad();
        }
    });

}



function getSearchItem(u) {
	let item = `<div class="story-list__item">
                <div class="sl__item__header">
                    <div>
<!--                        <img src="/upload/Profile.png"-->
<!--                             onerror="this.src='/images/Profile.png'"/>-->
                    </div>
                    <div>${u.name}</div>
                    <button class="story__review" onclick="location.href='/image/popular'">
                        리뷰 보러가기
                    </button>
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
            </div>`;
	return item;
}

// (2) 스토리 스크롤 페이징하기



// (3) 좋아요, 좋아요 취소
function toggleLike() {
	let likeIcon = $("#storyLikeIcon-1");
	if (likeIcon.hasClass("far")) {
		likeIcon.addClass("fas");
		likeIcon.addClass("active");
		likeIcon.removeClass("far");
	} else {
		likeIcon.removeClass("fas");
		likeIcon.removeClass("active");
		likeIcon.addClass("far");
	}
}

// (4) 댓글쓰기
function addComment() {

	let commentInput = $("#storyCommentInput-1");
	let commentList = $("#storyCommentList-1");

	let data = {
		content: commentInput.val()
	}

	if (data.content === "") {
		alert("댓글을 작성해주세요!");
		return;
	}

	let content = `
			  <div class="sl__item__contents__comment" id="storyCommentItem-2"">
			    <p>
			      <b>GilDong :</b>
			      댓글 샘플입니다.
			    </p>
			    <button><i class="fas fa-times"></i></button>
			  </div>
	`;
	commentList.prepend(content);
	commentInput.val("");
}

// (5) 댓글 삭제
function deleteComment() {

}







