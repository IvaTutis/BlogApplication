<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
		.error {
		   font-family: fantasy;
		   font-weight: bold;
		   font-size: 0.9em;
		   color: #FF0000;
		   padding-left: 110px;
		}
</style>
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
<h1>Login</h1>
</div>
<div class="sadrzaj">
<br>
<br>
<br>
<br>

<c:choose>

<c:when test="${currentUserId != null}">
<h1><c:out value="${currentUserName}"/></h1>
</c:when>


<c:otherwise>
<h3>Login</h3>
<form action="main" method="post">
<div>
Username:<input type="text" name="user" value="${usernameForm }"/>

</div>


<br>
<div>
Password:<input type="password" name="pass"/>

</div>

<input type="submit" class="button" value="Login"/>
</form>
<div class="error">
<c:if test="${loginError !=null}">
<br>
<c:out value="${loginError }"></c:out>
</c:if>
</div>


</c:otherwise>				
</c:choose>

<br><br>
<form action="register" method="get">
<input type="submit" class="button" value="Registriraj novog korisnika!"/><br>
</form>
<br>

<h4>Lista registriranih korisnika i linkovi koji vode na popis blogova istih:</h4>

<c:choose>
<c:when test="${users == null}">
<c:out value="Nema registriranih korisnika!"></c:out>
</c:when>
<c:otherwise>
<ol>
<c:forEach var="i" items="${users}">
<li class="dnevnik"><a href="author/${i.key}">${users.get(i.key)}</a></li>
</c:forEach>
</ol>
</c:otherwise>

</c:choose>


<br>
<c:if test="${currentUserId != null }">
<br><br>
<form action="logout" method="post">
<input type="submit" class="button" name="logout" value="Log out">
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