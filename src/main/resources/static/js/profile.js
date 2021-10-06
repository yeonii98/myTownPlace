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
            console.log(u);
            let item = getFavoriteItem(u);
            $("#subscribeModalList").append(item);
        });

    }).fail(error => {
        console.log("구독정보 불러오기 오류", error);
    });
}

function getFavoriteItem(u) {
    let item = `<div class="subscribe__item">
    <div class="subscribe__img" onclick="location.href='${u.detailUrl}'" style="cursor: pointer">
        <img src="/upload/${u.img}" onerror="this.src='/images/foodImg.png'"/>
    </div>
    <div class="subscribe__text" onclick="location.href='${u.detailUrl}'" style="cursor: pointer">
        <div>${u.name}</div>
    </div>
    <div class="subscribe__btn">`;

    // if(!u.equalUserState && !promotionType){
    //     if(u.subscribeState){
    //         item += `<button class="modi" onclick="toggleSubscribeModal(this,${u.id})" style="margin-right: 1rem;"><i class="fas fa-star fa-2x" style="color: #0095f6"></i></button>`
    //     }
    //     else{
    //         item += `<button class="modi" onclick="toggleSubscribeModal(this,${u.id})" style="margin-right: 1rem;"><i class="far fa-star fa-2x" style="color: #0095f6"></i></button>`
    //     }
    // }

    item += `</div>
</div>`;

    return item;
}

// (2) 구독자 정보  모달 보기
// function subscribeInfoModalOpen(pageUserId, promotionType) {
//     $(".modal-subscribe").css("display", "flex");
//
//     $.ajax({
//         url: `/api/user/${pageUserId}/subscribe`,
//         dataType: "json"
//     }).done(res => {
//         res.data.forEach((u) => {
//             console.log(u);
//             let item = getSubscribeModalItem(u, promotionType);
//             $("#subscribeModalList").append(item);
//         });
//
//     }).fail(error => {
//         console.log("구독정보 불러오기 오류", error);
//     });
// }

// function getSubscribeModalItem(u, promotionType) {
//     let item = `<div class="subscribe__item" id="subscribeModalItem-${u.id}">
//     <div class="subscribe__img" onclick="location.href='/user/${u.id}'" style="cursor: pointer">
//         <img src="/upload/${u.profileImageUrl}" onerror="this.src='/images/Profile.png'"/>
//     </div>
//     <div class="subscribe__text" onclick="location.href='/user/${u.id}'" style="cursor: pointer">
//         <div>${u.username}</div>
//         <p>${u.name}</p>
//     </div>
//     <div class="subscribe__btn">`;
//
//     if(!u.equalUserState && !promotionType){
//         if(u.subscribeState){
//         item += `<button class="modi" onclick="toggleSubscribeModal(this,${u.id})" style="margin-right: 1rem;"><i class="fas fa-star fa-2x" style="color: #0095f6"></i></button>`
//         }
//         else{
//             item += `<button class="modi" onclick="toggleSubscribeModal(this,${u.id})" style="margin-right: 1rem;"><i class="far fa-star fa-2x" style="color: #0095f6"></i></button>`
//         }
//     }
//
//     item += `</div>
// </div>`;
//
//     return item;
// }


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
function profileImageUpload() {
    $("#userProfileImageInput").click();

    $("#userProfileImageInput").on("change", (e) => {
        let f = e.target.files[0];

        if (!f.type.match("image.*")) {
            alert("이미지를 등록해야 합니다.");
            return;
        }

        // 사진 전송 성공시 이미지 변경
        let reader = new FileReader();
        reader.onload = (e) => {
            $("#userProfileImage").attr("src", e.target.result);
        }
        reader.readAsDataURL(f); // 이 코드 실행시 reader.onload 실행됨.
    });
}


// (5) 사용자 정보 메뉴 열기 닫기
function popup(obj) {
    $(obj).css("display", "flex");
}

function closePopup(obj) {
    $(obj).css("display", "none");
}


// (6) 사용자 정보(회원정보, 로그아웃, 닫기) 모달
function modalInfo() {
    $(".modal-info").css("display", "none");
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






