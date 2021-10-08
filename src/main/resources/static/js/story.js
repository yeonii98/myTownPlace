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


function toggleFavorite(obj, id, idx, name, img, detailUrl) {
    console.log(idx);
    const resultElement = document.getElementById('favoriteCnt-'+ idx);
    let number = resultElement.innerText;
    console.log(number);
    if ($(obj).text() === "즐겨찾기 해제") {
        number--;
        resultElement.innerText = number;
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
        number++;
        resultElement.innerText = number;
        $.ajax({
            type: "post",
            url: `/api/favorite/${id}`,
            data: {"name" : name, "img" : img, "detailUrl" : detailUrl},
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










