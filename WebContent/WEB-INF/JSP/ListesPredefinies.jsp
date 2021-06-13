<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestionnaire d'une liste de course</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<script src="https://kit.fontawesome.com/8ed12d656b.js" crossorigin="anonymous"></script>
</head>
<header class="p-3 mb-2 bg-secondary text-white">
<h1 class="text-center">COURSES</h1>
</header>
<body>
	<h1 class="text-center">Listes prédéfinies</h1>
	
	<div class = "text-center">
		<c:forEach var="LC" items="${ListeCourse}">
		${LC.getNomListeCourse()} 
		<a  href="Panier?idListe=${LC.getIdListeCourse()}"  class="btn text-dark"><i class="fas fa-shopping-cart fa-2x"></i></a>
		<a  href="DeleteListe?idListe=${LC.getIdListeCourse()}"  class="btn text-dark"><i class="fas fa-times-circle fa-2x"></i></a> <br>
		</c:forEach>
	</div>
</body>

<footer class="p-3 mb-2 bg-secondary text-white" >
	<div class= "container d-flex justify-content-center">
		<a href="NouvelleListe" class="btn text-light "><i class="fas fa-plus-circle fa-3x"></i></a>
	</div>

</footer>
</html>