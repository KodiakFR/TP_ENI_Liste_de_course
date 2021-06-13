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
	<h1 class="text-center">Nouvelle liste</h1>
	
		<form action="NouvelleListe" method="POST" class= "text-center">
			<input type = "number" name="id" id="id" value="${RecupLC.getIdListeCourse()}" style="visibility: hidden" >
			
				<c:if test="${!empty RecupLC}">	
				<p>
					<label for = liste>Nom: </label>
					<input type = "text" name="liste" id="liste" value="${RecupLC.getNomListeCourse()}" disabled> 
				</p>	
				</c:if>
				
				<c:if test="${empty RecupLC}">	
				<p>
					<label for = liste>Nom: </label>
					<input type = "text" name="liste" id="liste" required="required"> 
				</p>	
				</c:if>
			
			<p>
			<c:if test="${!empty RecupLC}">
				<c:forEach var="RCL" items="${RecupLC.getListeArticle()}">
					${RCL.getNomArticle()} 
					<a  href="Delete?idArticle=${RCL.getIdArticle()}&idListe=${RecupLC.getIdListeCourse()}"  class="btn text-dark"><i class="fas fa-times-circle fa-2x"></i></a> <br>
				</c:forEach>
			</c:if>
			
			<c:if test="${empty RecupLC}">
				<p>Aucun article </p>
			</c:if>
			
			</p>
			<p>
			<label for = article>Article: </label>
			<input type = "text" name="article" id="article"required="required"	>
			<button type="submit"  class="btn text-black" class="btn text-light "><i class="fas fa-plus-circle fa-2x"></i></button>
			</p>
		</form>
	
	</body>

<footer class="p-3 mb-2 bg-secondary text-white" >
	<div class= "container d-flex justify-content-center">
		<a  href="Accueil"  class="btn text-light"><i class="fas fa-arrow-alt-circle-right fa-3x"></i></a>
	</div>
</footer>
	
</html>