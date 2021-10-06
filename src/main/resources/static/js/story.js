/**
 2. 스토리 페이지
 (1) 스토리 로드하기
 (2) 스토리 스크롤 페이징하기
 (3) 좋아요, 안좋아요
 (4) 댓글쓰기
 (5) 댓글삭제
 */

// (1) 스토리 로드하기
let page = 1;

// function storyLoad() {
//     $.ajax({
//         url: `/api/story/${page}`,
//         dataType: "json"
//     }).done(res => {
//         res.data.forEach((u) => {
//             console.log(u);
//             let item = getStoryItem(u);
//             $("#storyList").append(item);
//         })
//     }).fail(error => {
//         console.log("오류", error);
//     });
// }
//
// storyLoad();
//
// function getStoryItem(u) {
//     let item = ``;
//     return item;
// }

// (2) 스토리 스크롤 페이징하기
// $(window).scroll(() => {
// //     let checkNum = $(window).scrollTop() - ($(document).height() - $(window).height());
// //
// //     if (checkNum < 1 && checkNum > -500) {
// //         page++;
// //         storyLoad();
// //     }
// // });


function toggleFavoriteModal(obj, id) {
    if ($(obj).text() === "즐겨찾기 해제") {
        $.ajax({
            type: "delete",
            url: `/api/favorite/${id}`,
            dataType: "json"
        }).done(res => {
            console.log(res);
                $(obj).text("즐겨찾기 추가");
                $(obj).toggleClass("blue");
            }
        ).fail(error => {
            console.log("즐겨찾기 취소에 실패했습니다.", error);
        });

    } else {
        $.ajax({
            type: "post",
            url: `/api/favorite/${id}`,
            dataType: "json"
        }).done(res => {
            console.log(res);
                $(obj).text("즐겨찾기 해제");
                $(obj).toggleClass("blue");
            }
        ).fail(error => {
            console.log("즐겨찾기 추가에 실패했습니다.", error);
        });
    }
}

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







