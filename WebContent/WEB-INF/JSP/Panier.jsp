<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<script src="https://kit.fontawesome.com/8ed12d656b.js" crossorigin="anonymous"></script>
</head>
<header class="p-3 mb-2 bg-secondary text-white">
<h1 class="text-center">COURSES</h1>
</header>
<body>
	<h1 class="text-center">Votre panier</h1>
	
	<div class= "text-center">
		<c:forEach var="RCL" items="${RecupLC.getListeArticle()}">
			<c:if test="${RCL.getCheckCase()=='false'}">
				<a  href="Check?idArticle=${RCL.getIdArticle()}&idListe=${RecupLC.getIdListeCourse()}&boolean=${RCL.getCheckCase()}"  class="btn text-dark"><i class="far fa-square fa-2x"></i></a>
			</c:if> 
			<c:if test="${RCL.getCheckCase()=='true'}">
				<a  href="Check?idArticle=${RCL.getIdArticle()}&idListe=${RecupLC.getIdListeCourse()}&boolean=${RCL.getCheckCase()}"  class="btn text-dark"><i class="far fa-check-square fa-2x"></i></a>
			</c:if> 
			${RCL.getNomArticle()} <br>
		</c:forEach>
	</div>
</body>

<footer class="p-3 mb-2 bg-secondary text-white" >
	<div class= "container d-flex justify-content-center">
		<a href="Accueil" class="btn text-light"><i class="fas fa-arrow-circle-left fa-3x"></i></a>
	</div>

</footer>
</html>