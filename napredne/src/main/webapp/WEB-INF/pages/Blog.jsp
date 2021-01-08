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
<div class="header"><h1> Blog</h1></div>

<h1> ${blogEntry.getNaslov()}</h1>
<div class="sadrzaj">
<br><br><br><br>
<p>
${blogEntry.getText() }
</p>

<c:if test="${edit!=null }">
<form action="edit?blogID=${blogEntry.getId()}" method="post">
<input type="submit" class="button" value="Uredi"/>
</form>
</c:if>
<br><br>
<h3>Komentari:</h3>
<c:choose>
<c:when test="${comments == null }">
<c:out value="Nema komentara"></c:out>
</c:when>
<c:otherwise>
<c:forEach var="i" begin="0" end="${ comments.size()-1}">
<h5>${comments.get(i).getUsername() }</h5>
<p>${comments.get(i).getText() }</p>
<p>--------------------------------------</p><br>
</c:forEach>
</c:otherwise>
</c:choose>


<br>
<form action="main" method="post">
<input type="submit" class="button" value="Vrati se na početnu!">
</form>
<br>

<form action="comment?blogID=${blogEntry.getId()}" method="post">
<input type="submit" class="button"value="Dodaj novi komentar!">
</form>

<c:if test="${currentUserId!=null }">
<br><br>
<form action="logout" method="post">
<input type="submit" class="button" name="logout" value="Log out">
</form>

</c:if>
</div>
<br><br>
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
position: fixed;
top:0;
left:0;
bottom: 0;
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
margin: 28px;
border-bottom: 1px solid #d3d3d3;
border-right: 1px solid #d3d3d3;
width:60vw;
}


input[type="text"]
{
    font-size:16px;
    font-family: "Indie Flower";
    color: #a3a3a3;
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

}</stlye>



</body>
</html>