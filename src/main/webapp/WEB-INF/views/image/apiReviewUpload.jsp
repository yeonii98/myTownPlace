<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	
	<%@ include file="../layout/header.jsp" %>

    <!--사진 업로드페이지 중앙배치-->
        <main class="uploadContainer">
           <!--사진업로드 박스-->
            <section class="upload">
               
               <!--사진업로드 로고-->
                <div class="upload-top">
                    <a href="home.html" class="logo upload__logo">
                        우리 동네 맛집<i class="fas fa-utensils header_icon"></i>
                    </a>
                    <p>리뷰 쓰기</p>
                </div>
                <!--사진업로드 로고 end-->
                
                <!--사진업로드 Form-->
                <form class="upload-form" id="myform" action="/apiReview" method="post" enctype="multipart/form-data">
                    <input type="hidden" value="${apiId}" name="apiId"/>
                    <input type="file" name="file"  onchange="imageChoose(this)" id='profile_img_upload'/>
                    <label for='profile_img_upload'><i class="far fa-file-image"></i>&nbsp;이미지 선택</label>
                    <div class="upload-img">
                        <img src="/images/default-img.jpg" alt="" id="imageUploadPreview" />
                    </div>
                    <fieldset>
                        <input type="radio" name="rating" value="5" id="rate1"><label for="rate1">⭐</label>
                        <input type="radio" name="rating" value="4" id="rate2"><label for="rate2">⭐</label>
                        <input type="radio" name="rating" value="3" id="rate3"><label for="rate3">⭐</label>
                        <input type="radio" name="rating" value="2" id="rate4"><label for="rate4">⭐</label>
                        <input type="radio" name="rating" value="1" id="rate5"><label for="rate5">⭐</label>
                    </fieldset>
                    <!--사진설명 + 업로드버튼-->
                    <div class="upload-form-detail">
                         <input type="text" value="${location}" name="town" readonly>
                         <input type="text" value="${placeName}" name="place" readonly>
                        <textarea type="text" placeholder="리뷰를 적어주세요" rows="3" name="caption" required class="upload__review"></textarea>
                        <button class="cta blue">업로드</button>
                    </div>
                    <!--사진설명end-->
                </form>
                <!--사진업로드 Form-->
            </section>
            <!--사진업로드 박스 end-->
        </main>
        <br/><br/>
	
	<script src="/js/upload.js" ></script>
    <%@ include file="../layout/footer.jsp" %>