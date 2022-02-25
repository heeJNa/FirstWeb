<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%--
    		반복문
    		do~while : 사용빈도가 거의 없다
    		초기값 -------------- 1
    		do
    		{
    			반복문장 ----2
    			증가식 ---- 3
    		}while(조건문) ---4
    		
    		while : 무한루프 (반복횟수가 없는경우) => 선조건 (수행을 못할 수도 있다.)
    		
    		초기값 --------1
    		while(조건문) ------2
    		{
    			반복문장 -------3
    			증가식 --------4
    		}
    		
    		for : 웹사이트 제작 => 반복 횟수가 있다
    		      1        2       3
    		for(초기값 ; 조건식; 증가식)
    			반복수행문장 3	
    
    		
    
    
     --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload=function(){
	let i=1;
	document.write("<h1>do~while</h1>")
	do{
		document.write("i="+i+"<br>")
		i++
	}while(i<=10);
	document.write("<hr>")
	
	i=1;
	document.write("<h1>while</h1>")
	while(i<=10){
		document.write("i="+i+"<br>")
		i++
	}
	document.write("<hr>")
	i=1;
	document.write("<h1>for</h1>")
	for(i=1;i<=10;i++){
		document.write("i="+i+"<br>")
	}
	document.write("<hr>")
	document.write("<h1>for2=continue</h1>")
	for(i=1;i<=10;i++){
		if(i===5){
			continue;	
		}
		document.write("i="+i+"<br>")
	}
	document.write("<hr>")
	document.write("<h1>for2=break</h1>")
	for(i=1;i<=10;i++){
		if(i===5){
			break;	
		}
		document.write("i="+i+"<br>")
	}
}


</script>
</head>
<body>

</body>
</html>










