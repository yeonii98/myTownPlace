/**
 1. 유저 프로파일 페이지
 (1) 유저 프로파일 페이지 구독하기, 구독취소
 (2) 구독자 정보 모달 보기
 (3) 구독자 정보 모달에서 구독하기, 구독취소
 (4) 유저 프로필 사진 변경
 (5) 사용자 정보 메뉴 열기 닫기
 (6) 사용자 정보(회원정보, 로그아웃, 닫기) 모달
 (7) 사용자 프로파일 이미지 메뉴(사진업로드, 취소) 모달
 (8) 구독자 정보 모달 닫기
 */

// (1) 유저 프로파일 페이지 구독하기, 구독취소
function toggleSubscribe(obj, pageUserId) {
    if ($(obj).html() === '<i class="fas fa-star" style="color: #0095f6"></i>') {
        $.ajax({
            type: "delete",
            url: `/api/subscribe/${pageUserId}`,
            dataType: "json"
        }).done(res => {
                $(obj).html('<i class="far fa-star" style="color: #0095f6"></i>');
                // $(obj).toggleClass("blue");
            }
        ).fail(error => {
            console.log("즐겨찾기 취소에 실패했습니다.", error);
        });

    } else {
        $.ajax({
            type: "post",
            url: `/api/subscribe/${pageUserId}`,
            dataType: "json"
        }).done(res => {
                $(obj).html('<i class="fas fa-star" style="color: #0095f6"></i>');
                // $(obj).toggleClass("blue");
            }
        ).fail(error => {
            console.log("즐겨찾기 추가에 실패했습니다.", error);
        });

    }
}

// (2) 즐겨찾기  모달 보기
function favoriteModalOpen(pageUserId) {
    $(".modal-subscribe").css("display", "flex");

    $.ajax({
        url: `/api/user/${pageUserId}/favorite`,
        dataType: "json"
    }).done(res => {
        res.data.forEach((u) => {
            let item = getFavoriteItem(u);
            $("#subscribeModalList").append(item);
        });

    }).fail(error => {
        console.log("구독정보 불러오기 오류", error);
    });
}

function getFavoriteItem(u) {
    let item = `<div class="subscribe__item">
        <a href="${u.detailUrl}">
            <div class="subscribe__img" style="cursor: pointer">
                <img src=${u.img} onerror="this.src='/images/foodImg.png'"/>
            </div>
        </a>
    <div class="subscribe__text">
        <div>${u.name}</div>
    </div>
    <div class="subscribe__btn">`;


        if(u.favoriteState){
            item += `<button class="cta" onclick="toggleFavoriteModal(this, ${u.toPlaceId})">즐겨찾기 해제</button>`
        }
        else{
            item += `<button class="cta blue" onclick="toggleFavoriteModal(this, ${u.toPlaceId}, '${u.name}', '${u.img}', '${u.detailUrl}')">즐겨찾기 추가</button>`
        }


    item += `</div>
</div>`;

    return item;
}

// (2) 구독자 정보  모달 보기
function subscribeInfoModalOpen(pageUserId) {
    $(".modal-subscribe").css("display", "flex");

    $.ajax({
        url: `/api/user/${pageUserId}/subscribe`,
        dataType: "json"
    }).done(res => {
        res.data.forEach((u) => {
            console.log(u);
            let item = getSubscribeModalItem(u);
            $("#subscribeModalList").append(item);
        });

    }).fail(error => {
        console.log("구독정보 불러오기 오류", error);
    });
}

function getSubscribeModalItem(u) {
    let item = `<div class="subscribe__item" id="subscribeModalItem-${u.id}">
    <div class="subscribe__img" onclick="location.href='/user/${u.id}'" style="cursor: pointer">
        <img src="/upload/${u.profileImageUrl}" onerror="this.src='/images/Profile.png'"/>
    </div>
    <div class="subscribe__text" onclick="location.href='/user/${u.id}'" style="cursor: pointer">
        <div>${u.username}</div>
        <p>${u.name}</p>
    </div>
    <div class="subscribe__btn">`;

    if(!u.equalUserState){
        if(u.subscribeState){
        item += `<button class="modi" onclick="toggleSubscribeModal(this,${u.id})" style="margin-right: 1rem;"><i class="fas fa-star fa-2x" style="color: #0095f6"></i></button>`
        }
        else{
            item += `<button class="modi" onclick="toggleSubscribeModal(this,${u.id})" style="margin-right: 1rem;"><i class="far fa-star fa-2x" style="color: #0095f6"></i></button>`
        }
    }

    item += `</div>
</div>`;

    return item;
}

function toggleFavoriteModal(obj, id, name, img, detailUrl) {
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

// (3) 구독자 정보 모달에서 구독하기, 구독취소
function toggleSubscribeModal(obj, pageUserId) {
    if ($(obj).html() === '<i class="fas fa-star fa-2x" style="color: #0095f6"></i>') {
        $.ajax({
            type: "delete",
            url: `/api/subscribe/${pageUserId}`,
            dataType: "json"
        }).done(res => {
                $(obj).html('<i class="far fa-star fa-2x" style="color: #0095f6"></i>');
            }
        ).fail(error => {
            console.log("즐겨찾기 취소에 실패했습니다.", error);
        });

    } else {
        $.ajax({
            type: "post",
            url: `/api/subscribe/${pageUserId}`,
            dataType: "json"
        }).done(res => {
                $(obj).html('<i class="fas fa-star fa-2x" style="color: #0095f6"></i>');
            }
        ).fail(error => {
            console.log("즐겨찾기 추가에 실패했습니다.", error);
        });

    }
}

// (4) 유저 프로파일 사진 변경 (완)
function profileImageUpload(principalId, pageUserId) {
    if(principalId === pageUserId)
        $("#userProfileImageInput").click();

    $("#userProfileImageInput").on("change", (e) => {
        let f = e.target.files[0];

        if (!f.type.match("image.*")) {
            alert("이미지를 등록해야 합니다.");
            return;
        }

        //사진 서버 전송
        let profileImageForm = $("#userProfileImageForm")[0];

        //form 데이터 객체를 이용하면 form 태그의 필드와 그 값을 나타내는 일련의 key/value 쌍을 담을 수 있다.
        let formData = new FormData(profileImageForm);
        console.log(formData);

        $.ajax({
            type: "put",
            url: `/api/user/${principalId}/profileImageUrl`,
            data: formData,
            contentType: false, // 필수 : x-www-form-urlencoded로 파싱되는 것을 방지
            processData: false, // 필수 : contentType을 false로 줬을 때 QueryString 자동 설정되므로 해제
            enctype:"multipart/form-data",
            dataType: "json"
        }).done(res=>{
            // 사진 전송 성공시 이미지 변경
            $("#profileImg").attr("onclick", `popup('.modal-image', ${pageUserId}, ${principalId})`);
            let reader = new FileReader();
            reader.onload = (e) => {
                $("#userProfileImage").attr("src", e.target.result);
            }
            reader.readAsDataURL(f); // 이 코드 실행시 reader.onload 실행됨.
        }).fail(error=>{
            console.log("오류", error);
        })
    });
}

function profileImageDelete(principalId, pageUserId){
    $.ajax({
        url: `/api/user/${principalId}/profileImageUrl`,
        type: "delete",
        dataType: "json"
    }).done(res => {
        console.log(res);
        $("#userProfileImage").attr("src",'/images/Profile.png');
        $("#profileImg").attr("onclick", `profileImageUpload(${principalId}, ${pageUserId})`);
    }).fail(error => {
        console.log("오류", error);
    });
}

function deleteReview(reviewId) {
    const resultElement = document.getElementById('reviewCnt');
    let number = resultElement.innerText;
    if(confirm("게시글을 삭제하겠습니까?")){
        $.ajax({
            url: `review/${reviewId}`,
            type: "delete",
            dataType: "json"
        }).done(res => {
            console.log(res);
            number--;
            resultElement.innerText = number;
            $(`#review-${reviewId}`).remove();
        }).fail(error => {
            console.log("오류", error);
        });
    }
}


// (5) 사용자 정보 메뉴 열기 닫기
function popup(obj, pageUserId, principalId) {
    if(pageUserId === principalId)
        $(obj).css("display", "flex");
}

function popup2(obj, pageUserId, principalId, reviewId) {
    $("#reviewModal").empty();
    let item = `<button onclick="location.href='/review/update/${reviewId}'">수정</button>
        <button onclick="deleteReview(${reviewId})">삭제</button>
        <button onclick="closePopup('.modal-review')">취소</button>`
    $("#reviewModal").prepend(item)

    if(pageUserId === principalId)
        $(obj).css("display", "flex");
}

function closePopup(obj) {
    $(obj).css("display", "none");
}


// (6) 사용자 정보(회원정보, 로그아웃, 닫기) 모달
function modalInfo() {
    $(".modal-info").css("display", "none");
}

function modalReview() {
    $(".modal-review").css("display", "none");
}

// (7) 사용자 프로파일 이미지 메뉴(사진업로드, 취소) 모달
function modalImage() {
    $(".modal-image").css("display", "none");
}

// (8) 구독자 정보 모달 닫기
function modalClose() {
    $(".modal-subscribe").css("display", "none");
    location.reload();
}






