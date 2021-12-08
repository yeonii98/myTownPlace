//현재 로그인한 사용자 아이디
let principalId = $("#principalId").val();

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
                    <div style="display: -webkit-inline-box; align-items: center; cursor: pointer" onclick="location.href='/user/${review.user.id}'">
                        <img class="profile-image" src="${review.user.profileImageUrl}" onerror="this.src='/images/Profile.png'"/>
                            <div style="margin-left: 1rem; margin-top: 4px">${review.user.name}</div>
                    </div>
                    <div class="review__hash">
                        <p>#${review.town}</p>
                        <p>#${review.place}</p>
                    </div>
                    <div style="font-size: 2rem; margin-right: 1rem">`
    for(var i = 0 ; i < 5; i ++) {
        if( i < review.rating) {
            item  +=   `<b style='color: #f7e600;'>★</b>` ;
        }else{
            item  +=  `<b style='color: transparent;text-shadow: 0 0 0 #f0f0f0;'>☆</b>` ;
        }
    }
    item += `</div>
                </div>

                <div class="sl__item__img">
                    <img src="${review.postImageUrl}" onerror="this.src='/images/foodImg.png'"/>
                </div>
                <div class="sl__item__contents">
                    <div class="sl__item__contents__icon">`

        if(review.likeState){
            item += ` <button>
                            <i class="fas fa-thumbs-up active" id="storyLikeIcon-${review.id}" onclick="toggleLike(${review.id})"></i>
                        </button>`
        }
        else{
            item += `<button>
                            <i class="far fa-thumbs-up" id="storyLikeIcon-${review.id}" onclick="toggleLike(${review.id})"></i>
                        </button>`
        }

                    item +=    `<button>
                            <i class="far fa-comment"></i>
                        </button>
                    </div>
                    
                    <span class="like"><b id="storyLikeCount-${review.id}">${review.likeCount}</b>명이 공감합니다.</span>
                    <div class="sl__item__contents__content">
                        <p>${review.caption}</p>
                    </div>`


                    item += `<div class="sl__item__contents__content story__comment-list--more" id="storyCommentListMore-${review.id}" onclick="openList(${review.id})">댓글&nbsp <b id="storyCommentCount-${review.id}">${review.commentCount}</b>개 모두 보기</div>`



                    item += `<div id="storyCommentList-${review.id}" class="story__comment-list">`
                        review.comments.forEach((comment)=>{
                            item +=`<div class="sl__item__contents__comment" id="storyCommentItem-${comment.id}">
                            <p>
                                <b>${comment.user.username} </b> ${comment.content}
                            </p>`
                            if(Number(principalId) === Number(comment.user.id)){
                            item += `<button onclick="deleteComment(${comment.id}, ${review.id})">
                                    <i class="fas fa-times"></i>
                                    </button>`
                            }
                        item += `</div>`

                        })
            item += `</div>
                    <div class="sl__item__input">
                        <input type="text" placeholder="댓글 달기..." id="storyCommentInput-${review.id}"/>
                        <button type="button" onClick="addComment(${review.id})">게시</button>
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

function openList(id) {
    let cnt = document.getElementById('storyCommentCount-'+ id).innerText;
    console.log(cnt);
    if ($(`#storyCommentList-${id}`).css('display') === 'block') {
        $(`#storyCommentList-${id}`).css('display', 'none');
        $(`#storyCommentListMore-${id}`).html(`댓글&nbsp  <b id="storyCommentCount-${id}">${cnt}</b>개 모두 보기`);
    } else {
        $(`#storyCommentList-${id}`).css('display', 'block');
        $(`#storyCommentListMore-${id}`).html(`댓글&nbsp  <b id="storyCommentCount-${id}">${cnt}</b>개 접기`);
    }
}

// (3) 좋아요, 좋아요 취소
function toggleLike(reviewId) {
    let likeIcon = $(`#storyLikeIcon-${reviewId}`);
    const resultElement = document.getElementById('storyLikeCount-'+ reviewId);
    let number = resultElement.innerText;

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
function addComment(reviewId) {
    console.log(reviewId);
    let commentInput = $(`#storyCommentInput-${reviewId}`);
    let commentList = $(`#storyCommentList-${reviewId}`);

    const resultElement = document.getElementById('storyCommentCount-'+ reviewId);
    let number = resultElement.innerText;

    let data = {
        reviewId: reviewId,
        content: commentInput.val()
    }

    if (data.content === "") {
        alert("댓글을 작성해주세요!");
        return;
    }
    $.ajax({
        url: `/api/comment`,
        type: "post",
        data: JSON.stringify(data),
        contentType: "application/json; charset=utf-8", // 보내는 데이터 타입
        dataType: "json" // 응답 받을 타임
    }).done(res => {
        console.log("성공", res);
        number++;
        resultElement.innerText = number;
        let content = `
			  <div class="sl__item__contents__comment" id="storyCommentItem-${res.data.id}"> 
			    <p>
			      <b>${res.data.user.username} </b>
			      ${res.data.content}
			    </p>
			    <button onclick="deleteComment(${res.data.id}, ${reviewId})"><i class="fas fa-times"></i></button>
			  </div>
	`;
        commentList.append(content);

    }).fail(error => {
        console.log("오류", error.responseJSON.data.content);
        alert(error.responseJSON.data.content);
    });
    commentInput.val("");

}

// (5) 댓글 삭제
function deleteComment(commentId, reviewId) {
    const resultElement = document.getElementById('storyCommentCount-' + reviewId);
    let number = resultElement.innerText;
    if(confirm("댓글을 삭제하겠습니까?")){

        $.ajax({
            url: `/api/comment/${commentId}`,
            type: "delete",
            dataType: "json" // 응답 받을 타임
        }).done(res => {
            console.log("성공", res);
            number--;
            resultElement.innerText = number;
            $(`#storyCommentItem-${commentId}`).remove();
        }).fail(error => {
            console.log("오류", error);
        });
    }
}







