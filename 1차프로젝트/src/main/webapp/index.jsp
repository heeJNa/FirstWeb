<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<title>MainPage</title>
	<!-- Index 페이지 StyleSheet -->
	<link rel="stylesheet" href="css/index.css">
</head>
<body>
	<c:import url="/Common.jsp"/>
	<!-- 메인 페이지 시작 -->
	<section>
		<!-- 이벤트 섹션 -->
		<article>
			<div class="container-fluid">
				<div class="row">
					<div class="col-xs-12 col-xs-offset-0 col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 event-area">
						<div class="event-picture">
							<!-- carousel -->
							<!-- Swiper -->
							<div class="eventSwiper">
						      <div class="swiper-wrapper">
						        <div class="swiper-slide" id="event-slide">
						          <img class="img-responsive" src="img/e1.jpg" />
						        </div>
						        <div class="swiper-slide" id="event-slide">
						          <img class="img-responsive" src="img/e2.jpg" />
						        </div>
						        <div class="swiper-slide" id="event-slide">
						          <img class="img-responsive" src="img/e3.jpg" />
						        </div>
						      </div>
						      <div class="swiper-pagination"></div>
						    </div>
						</div>					    
					</div>
				</div>
			</div>
		</article>
		<!-- 베스트 셀러 -->
		<article>
			<div class="bestseller-space" style="background-image: url(img/bg1.jpg);">
				<div class="container">
					<div class="row bestseller-title">
						<h1>베스트셀러</h1>
					</div>
					<div class="row">
						<div class="col-xs-6 col-xs-offset-3 col-sm-12 col-sm-offset-0" id="bestseller-slide-area">
							<!-- Slider main container -->
							<div class="swiper book-swiper">
							  <!-- Additional required wrapper -->
							  <div class="swiper-wrapper">
							  	<!-- Slides -->
							  	<c:forEach items="${bestBooks}" var="bestBook">
						        	<div class="bestseller-info swiper-slide" id="bestseller-slide">
										<img src="${bestBook.img}" alt="${bestBook.name}">
									</div>
						      	</c:forEach>							    
							  </div>
							  <!-- If we need navigation buttons -->
							  <div class="swiper-button-prev"></div>
							  <div class="swiper-button-next"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</article>
		<!-- 신간 도서 -->
		<article>
			<div class="newbook-space">
				<div class="container">
					<div class="row newbook-title">
						<h1>신간 도서</h1>
					</div>
					<div class="row">
						<div class="col-xs-6 col-xs-offset-3 col-sm-12 col-sm-offset-0" id="newbook-slide-area">
							<!-- Slider main container -->
							<div class="swiper book-swiper">
							  <!-- Additional required wrapper -->
							  <div class="swiper-wrapper">
							    <!-- Slides -->
							    <c:forEach items="${newBooks}" var="newBook">
						        	<div class="bestseller-info swiper-slide" id="bestseller-slide">
										<img src="${newBook.img}" alt="${newBook.name}">
									</div>
						      	</c:forEach>							    
							  </div>
							  <!-- If we need navigation buttons -->
							  <div class="swiper-button-prev"></div>
							  <div class="swiper-button-next"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</article>
		<!-- 유튜브 리뷰 섹션 -->
		<article>
			<div class="under-space">
				<div class="youtube-space">
					<div class="container-fluid">
						<div class="row">
							<div class="col-xs-8 col-xs-offset-2 col-md-4 col-md-offset-4 youtube-title"><h2>Youtube 리뷰</h2></div>
						</div>
						<div class="row">
							<div class="col-xs-10 col-xs-offset-1" id="youtube-area">
								<div class="row">
									<div class="col-xs-12 col-md-6" id="video-area">
										<div class="main-video">
											<c:forEach var="i" begin="0" end="2" step="1">
												<div class="mySlides">
												    <iframe id="video" src="https://www.youtube.com/embed/${youtubeBooks[i].contentId}?enablejsapi=1&version=3&playerapiid=ytplayer" frameborder="0"></iframe>
												 </div>
											</c:forEach>									
										</div>
										<div class="thumnail-bar" id="thumb-area">
											<c:forEach var="i" begin="0" end="2" step="1">
												<div class="column" id="thumb-element">
											      <img id="thumb" class="demo cursor" src="https://img.youtube.com/vi/${youtubeBooks[i].contentId}/0.jpg" style="width:100%" onmouseover="showSlides(${i+1})">
											    </div>
											</c:forEach>										    
										</div>
									</div>
									<div class="hidden-xs hidden-sm col-md-6">
										<c:forEach var="i" begin="0" end="2" step="1">
											<div class="myDescription book-descript-${i+1}">
												<div class="row">
											    	<div class="col-xs-4 youtube-book-img">
											    		<img alt="${youtubeBooks[i].name}" src="${youtubeBooks[i].img}">
											    	</div>
											    	<div class="col-xs-6 youtube-book-info">
											    		<ul class="list-unstyled">
											    			<li>${youtubeBooks[i].name}</li>
											    			<li>${youtubeBooks[i].price}</li>
											    			<li>${youtubeBooks[i].author} | ${youtubeBooks[i].publisher}</li>						
											    			<li class="youtube-book-tag">${youtubeBooks[i].tag}</li>
											    		</ul>
											    		<button class="btn btn-primary" style="float:right">구매하기</button>
											    	</div>
											    </div>
											    <div class="row">
											    	<div class="col-xs-11 youtube-book-description">
											    		${youtubeBooks[i].description}
											    	</div>
											    </div>
											</div>
										</c:forEach>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- SNS 리뷰 섹션 -->
					<div class="container-fluid">
						<div class="row">
							<div class="col-xs-8 col-xs-offset-2 col-md-4 col-md-offset-4 sns-title"><h2>SNS 게시판</h2></div>
						</div>
						<div class="row">
							<div class="col-xs-10 col-xs-offset-1" id="sns-area">
								<h1>준비 중 입니다...</h1>
							</div>
						</div>
					</div>
				</div>				
			</div>
		</article>
	</section>
	<script src="js/index.js"></script>
</body>
</html>