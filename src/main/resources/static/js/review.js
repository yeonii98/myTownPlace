// (1) 스토리 로드하기
let page = 0;

function storyLoad() {
    $.ajax({
        url: `/api/review?page=${page}`,
        dataType: "json"
    }).done(res => {
        console.log(res);
        res.data.content.forEach((review) => {
            console.log(review.user);
            let item = getStoryItem(review);
            $("#storyList").append(item);
        })
    }).fail(error => {
        console.log("오류", error);
    });
}

storyLoad();

function getStoryItem(review) {
    let item = `<div class="story-list__item">
                <div class="sl__item__header sl__item__header--review">
                    <div>
                        <img class="profile-image" src="${review.user.profileImageUrl}" onerror="this.src='/images/Profile.png'"/>
                    </div>
                    <div>${review.user.name}</div>
                    <div class="review__hash">#${review.town}</div>
                    <div class="review__hash">#${review.place}</div>
                </div>

                <div class="sl__item__img">
                    <img src="/upload/${review.postImageUrl}" onerror="this.src='/images/foodImg.png'"/>
                </div>
                <div class="sl__item__contents">
                    <div class="sl__item__contents__icon">`

        if(review.likeState){
            item += ` <button>
                            <i class="fas fa-heart active" id="storyLikeIcon-${review.id}" onclick="toggleLike(${review.id})"></i>
                        </button>`
        }
        else{
            item += `<button>
                            <i class="far fa-heart" id="storyLikeIcon-${review.id}" onclick="toggleLike(${review.id})"></i>
                        </button>`
        }

                    item +=    `<button>
                            <i class="far fa-comment"></i>
                        </button>
                    </div>
                    
                    <span class="like"><b id="storyLikeCount-${review.id}">${review.likeCount}</b>명이 좋아합니다.</span>
                    <div class="sl__item__contents__content">
                        <p>${review.caption}</p>
                    </div>
                    <div id="storyCommentList-1">

                        <div class="sl__item__contents__comment" id="storyCommentItem-1">
                            <p>
                                <b>먹보 </b> 당장 먹으러갈게요.
                            </p>
                            <button>
                                <i class="fas fa-times"></i>
                                
                            </button>
                        </div>
                    </div>

                    <div class="sl__item__input">
                        <input type="text" placeholder="댓글 달기..." id="storyCommentInput-1"/>
                        <button type="button" onClick="addComment()">게시</button>
                    </div>

                </div>

            </div>`;
    return item;
}

//(2) 스토리 스크롤 페이징하기
$(window).scroll(() => {
    let checkNum = $(window).scrollTop() - ($(document).height() - $(window).height());

    if (checkNum < 1 && checkNum > -1) {
        page++;
        storyLoad();
    }
});

// (3) 좋아요, 좋아요 취소
function toggleLike(reviewId) {
    let likeIcon = $(`#storyLikeIcon-${reviewId}`);
    const resultElement = document.getElementById('storyLikeCount-'+ reviewId);
    let number = resultElement.innerText;
    console.log(number);
    if (likeIcon.hasClass("far")) {
        number++;
        resultElement.innerText = number;
        likeIcon.addClass("fas");
        likeIcon.addClass("active");
        likeIcon.removeClass("far");
        $.ajax({
            url: `/api/review/${reviewId}/likes`,
            type: "post",
            dataType: "json"
        }).done(res => {
            console.log(res);
        }).fail(error => {
            console.log("오류", error);
        });
    } else {
        number--;
        resultElement.innerText = number;
        likeIcon.removeClass("fas");
        likeIcon.removeClass("active");
        likeIcon.addClass("far");
        $.ajax({
            url: `/api/review/${reviewId}/likes`,
            type: "delete",
            dataType: "json"
        }).done(res => {
            console.log(res);
        }).fail(error => {
            console.log("오류", error);
        });
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







