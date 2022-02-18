// ================ 네비게이션 바 파트 ======================
// Mobile 네비게이션
function toggleMobileMainList() {
  var mobMainlist = document.getElementById("mobile-mainlist"),
  mobDomesticlist = document.getElementById("mobile-domesticlist"),
  mobForeignlist = document.getElementById("mobile-foreignlist"),
  mobEbooklist = document.getElementById("mobile-ebooklist");
  if (mobMainlist.style.width === "50%") {
      if (mobDomesticlist.style.width === "50%" || mobForeignlist.style.width === "50%" || mobEbooklist.style.width === "50%") {
        mobDomesticlist.style.width = '0%'; mobForeignlist.style.width = '0%'; mobEbooklist.style.width = '0%';
        setTimeout(function(){
          mobMainlist.style.fontSize = '0px';
          mobMainlist.style.width = '0%';
        }, 100)
      } else {
        mobMainlist.style.fontSize = '0px';
        mobMainlist.style.width = '0%';
      }
  } else {
    mobMainlist.style.width = '50%';
    mobMainlist.style.fontSize = '30px';
  }
}
function toggleMobileDomesticList() {
  var mobDomesticlist = document.getElementById("mobile-domesticlist"),
  mobForeignlist = document.getElementById("mobile-foreignlist"),
  mobEbooklist = document.getElementById("mobile-ebooklist");
  if (mobDomesticlist.style.width = "0%") {
    if (mobForeignlist.style.width === "50%" || mobEbooklist.style.width === "50%") {
      mobForeignlist.style.width = '0%';
      mobEbooklist.style.width = '0%';
      setTimeout(function(){
          mobDomesticlist.style.width = '50%';
        }, 100)
    } else {
      mobDomesticlist.style.width = '50%';
    }
  } else {
    mobDomesticlist.style.width = '0%';
  }
}
function toggleMobileForeignList() {
  var mobDomesticlist = document.getElementById("mobile-domesticlist"),
  mobForeignlist = document.getElementById("mobile-foreignlist"),
  mobEbooklist = document.getElementById("mobile-ebooklist");
  if (mobForeignlist.style.width = "0%") {
    if (mobDomesticlist.style.width === "50%" || mobEbooklist.style.width === "50%") {
      mobDomesticlist.style.width = '0%';
      mobEbooklist.style.width = '0%';
      setTimeout(function(){
          mobForeignlist.style.width = '50%';
        }, 100)
    } else {
      mobForeignlist.style.width = '50%';
    }
  } else {
    mobForeignlist.style.width = '0%';
  }
}
function toggleMobileEbookList() {
  var mobDomesticlist = document.getElementById("mobile-domesticlist"),
  mobForeignlist = document.getElementById("mobile-foreignlist"),
  mobEbooklist = document.getElementById("mobile-ebooklist");
  if (mobEbooklist.style.width = "0%") {
    if (mobForeignlist.style.width === "50%" || mobDomesticlist.style.width === "50%") {
      mobDomesticlist.style.width = '0%';
      mobForeignlist.style.width = '0%';
      setTimeout(function(){
          mobEbooklist.style.width = '50%';
        }, 100)
    } else {
      mobEbooklist.style.width = '50%';
    }
  } else {
    mobEbooklist.style.width = '0%';
  }
}
// PC 네비게이션
function toggleMainList() {
  var navlistarea = document.getElementById("navListArea"),
  mainlist = document.getElementById("mainList"),
  domesticlist = document.getElementById("domesticList"),
  foreignlist = document.getElementById("foreignList"),
  ebooklist = document.getElementById("ebookList");
  if (mainlist.style.height === "100%") {
      if (domesticlist.style.width === "20%" || foreignlist.style.width === "20%" || ebooklist.style.width === "20%") {
      	domesticlist.style.width = '0%'; foreignlist.style.width = '0%'; ebooklist.style.width = '0%';
      	setTimeout(function(){
          mainlist.style.height = '0%';
          navlistarea.style.height = '0%';
        }, 100)
      } else {
      	mainlist.style.height = '0%';
        navlistarea.style.height = '0%';
      }
  } else {
    navlistarea.style.height = '76vh';
    mainlist.style.height = '100%';
  }
}
function toggleDomesticList() {
  var domesticlist = document.getElementById("domesticList"),
  foreignlist = document.getElementById("foreignList"),
  ebooklist = document.getElementById("ebookList");
  if (domesticlist.style.width = "0%") {
  	if (foreignlist.style.width === "20%" || ebooklist.style.width === "20%") {
  		foreignlist.style.width = '0%';
  		ebooklist.style.width = '0%';
  		setTimeout(function(){
          domesticlist.style.width = '20%';
        }, 100)
  	} else {
  		domesticlist.style.width = '20%';
  	}
  } else {
  	domesticlist.style.width = '0%';
  }
}
function toggleForeignList() {
  var domesticlist = document.getElementById("domesticList"),
  foreignlist = document.getElementById("foreignList"),
  ebooklist = document.getElementById("ebookList");
  if (foreignlist.style.width = "0%") {
  	if (domesticlist.style.width === "20%" || ebooklist.style.width === "20%") {
  		domesticlist.style.width = '0%';
  		ebooklist.style.width = '0%';
  		setTimeout(function(){
          foreignlist.style.width = '20%';
        }, 100)
  	} else {
  		foreignlist.style.width = '20%';
  	}
  } else {
  	foreignlist.style.width = '0%';
  }
}
function toggleEbookList() {
  var domesticlist = document.getElementById("domesticList"),
  foreignlist = document.getElementById("foreignList"),
  ebooklist = document.getElementById("ebookList");
  if (ebooklist.style.width = "0%") {
  	if (foreignlist.style.width === "20%" || domesticlist.style.width === "20%") {
  		domesticlist.style.width = '0%';
  		foreignlist.style.width = '0%';
  		setTimeout(function(){
          ebooklist.style.width = '20%';
        }, 100)
  	} else {
  		ebooklist.style.width = '20%';
  	}
  } else {
  	ebooklist.style.width = '0%';
  }
}
var navSwiper = new Swiper(".navSwiper", {
  slidesPerView: 3,
  spaceBetween: 30,
  freeMode: true,
  loop: true,
  autoplay: {
    delay: 1500,
    disableOnInteraction: false,
  },
});