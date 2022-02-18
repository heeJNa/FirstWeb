showSlides();
function showSlides(n = 1) {
  var i;
  var slides = document.getElementsByClassName("mySlides");
  var descriptions = document.getElementsByClassName("myDescription");
  var dots = document.getElementsByClassName("demo");

  for (i = 0; i < slides.length; i++) {
      slides[i].style.display = "none";
  }
  for (i = 0; i < descriptions.length; i++) {
      descriptions[i].style.display = "none";
  }
  for (i = 0; i < dots.length; i++) {
      dots[i].style.opacity = "0.6";
  }
  slides[n-1].style.display = "block";
  descriptions[n-1].style.display = "block";
  dots[n-1].style.opacity = "1";
}
// ================== 메인페이지 파트 ==========================
const swiper = new Swiper('.book-swiper', {
  autoHeight: true,
  // Optional parameters
  loop: true,
  slidesPerView: 1,
  spaceBetween: 50,
  autoplay: {
      delay: 5000,
      disableOnInteraction: false,
    },
  // Navigation arrows
  navigation: {
    nextEl: '.swiper-button-next',
    prevEl: '.swiper-button-prev',
  },
  // Responsive breakpoints
  breakpoints: {
    // when window width is >= 320px
    768: {
      slidesPerView: 2,
      spaceBetween: 120
    },
    // when window width is >= 480px
    992: {
      slidesPerView: 3,
      spaceBetween: 50
    },
    // when window width is >= 640px
    1200: {
      slidesPerView: 4,
      spaceBetween: 40
    }
  }
});
const eventSwiper = new Swiper(".eventSwiper", {
	autoHeight: true,
    spaceBetween: 30,
    effect: "fade",
    loop: true,
    autoplay: {
      delay: 10000,
      disableOnInteraction: false,
    },
    pagination: {
      el: ".swiper-pagination",
      clickable: true,
    },
  });