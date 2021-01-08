<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>


<c:choose>
<c:when test="${currentUserId == null }">
Anonymous
</c:when>
<c:otherwise>
${currentUserName }
</c:otherwise>
</c:choose>


</title>
</head>
<body>
<div class="pravokutnik"></div>
<div class="header">
<h1>Blogovi:</h1>
</div>
<c:choose>
<c:when test="${blogEntries == null }">
Korisnik nema napisanih blogova! 
</c:when>

<c:otherwise>

<div class="sadrzaj">
<br>
<br>
<br>
<br>

<ol>
<c:forEach var="i" begin="0" end="${blogEntries.size()-1 }">
<li class="dnevnik"><a href="${blogEntries.get(i).getAutor() }/${blogEntries.get(i).getId()}" > ${blogEntries.get(i).getNaslov()}</a></li>
</c:forEach>
</ol>

</c:otherwise>
</c:choose>
<br>
<form action="main" method="post">
<input class="button" type="submit" value="Vrati se na početnu!">
</form>
<br>
<c:if test="${addNew !=null }">
<form action="${currentUserNick}/new" method="get">
<input class="button" type="submit" value="Dodaj novi blog"/>
</form>
</c:if>
<c:if test="${currentUserId !=null }">
<br><br>
<form action="logout" method="post">
<input class="button" type="submit" name="logout" value="Log out">
</form>

</c:if>
</div>

<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Indie+Flower" />

<style>
.header{
padding: 10px;
font-family: "Indie Flower";
font-size: 28px;
color: white;
position: absolute;
top: 0;
left:0;
height: 120px;
width:100vw;
background-color: #d3d3d3;

}

.pravokutnik{
position: absolute;
top:78px;
left:0;
height: 100vh;
width: 40px;
background-color: #a3a3a3;



}
.sadrzaj{
position:absolute;
top:50px;
left: 60px;
font-family: "Indie Flower";
font-size: 25px;
color: #000;}

a{
font-family: "Indie Flower";
font-size: 25px;
color: #000;
}

a:hover, a:visited{
opacity:0.7;
font-family: "Indie Flower";
font-size: 25px;
color: #000;
}

.dnevnik{
border-bottom: 1px solid #d3d3d3;
border-right: 1px solid #d3d3d3;
width:60vw;
}


.button{
  background-color: #a3a3a3;
  border: none;
  color: #000;
  font-family: "Indie Flower";
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  font-size: 20px;
}
.button:hover{
opacity:0.7;

}


</style>
</body>
</html>