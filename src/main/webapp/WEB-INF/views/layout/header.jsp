<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>우리 동네 맛집</title>

	<!-- 제이쿼리 -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	
	<!-- Style -->
	<link rel="stylesheet" href="/css/style.css">
	<link rel="stylesheet" href="/css/story.css">
	<link rel="stylesheet" href="/css/popular.css">
	<link rel="stylesheet" href="/css/profile.css">
	<link rel="stylesheet" href="/css/upload.css">
	<link rel="stylesheet" href="/css/update.css">
	<link rel="shortcut icon" href="/images/insta.svg">
	
	<!-- Fontawesome -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />

	<!-- Fonts -->
	<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100;200;300;400;500;600;700&display=swap" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
</head>

<body>
	
	<header class="header">
		<div class="container">
			<a href="/image/story" class="logo">
				우리 동네 맛집<i class="fas fa-utensils header_icon"></i>
			</a>
			<div class="input-box">
				<input class="header__search" type="text" placeholder="동네를 검색하세요">
							<i class="fas fa-search"></i>
			</div>
			<nav class="navi">
				<ul class="navi-list">
					<li class="navi-item"><a href="/image/story">
							<i class="fas fa-home"></i>
						</a></li>
<%--					<li class="navi-item"><a href="/image/popular">--%>
<%--							<i class="fas fa-utensils"></i>--%>
<%--						</a></li>--%>
					<li class="navi-item"><a href="/user/profile">
							<i class="fas fa-user"></i>
						</a></li>
				</ul>
			</nav>
		</div>
	</header>
