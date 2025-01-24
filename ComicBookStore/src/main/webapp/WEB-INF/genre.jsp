<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome <c:out value="${ user.firstName}" /></title>
<!-- for Bootstrap CSS -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<!-- YOUR own local CSS -->
<link rel="stylesheet" href="/css/homepage.css" />
<!-- For any Bootstrap that uses JS -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<!-- YOUR own local JavaScript -->
<script type="text/javascript" src="/js/app.js"></script>
</head>
<body>
<c:set var="isAdmin" value="#{user.isAdmin == true}" />
<c:set var="isNotAdmin" value="#{user.isAdmin == null or user.isAdmin == false}"/>
	<nav class="navbar navbar-expand-lg border border-secondary mb-3" style="background-color: #e1ecfd">
		<div class="container-fluid">
			<a class="navbar-brand" href="/Home">Comic Book Store</a>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item">
          				<a class="nav-link active" aria-current="page" href="/Home">Home</a>
        			</li>
        			<li class="nav-item">
          				<a class="nav-link" aria-current="page" href="#">Add/View Genres</a>
        			</li>
        			<li class="nav-item">
          				<a class="nav-link" aria-current="page" href="/comics/search">Search Library</a>
        			</li>
        			<c:if test="${isAdmin}">
	        			<li class="nav-item">
    	      				<a class="nav-link" aria-current="page" href="/comics/new">Add a New Comic!</a>
        				</li>
        			</c:if>
        			<li class="nav-item">
          				<a href="/logout" class="nav-link">Logout</a>
        			</li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container" style="width: 50%">
    <h2>Create Genre</h2>
	    <form:form action="/genre" method="POST" modelAttribute="genre">
	        <form:label class="form-label" path="name">Name: </form:label>
	        <form:input class="form-control" type="text" path="name"/>
	        <form:errors class="fw-lighter fst-italic text-danger" path="name"/>
	
	        <form:button class="btn btn-primary btn-sm mt-2" type="submit" value="submit">Create</form:button>
	    </form:form>
	    <div class="container" style="width: 50%">
		    <h2>Genres</h2>
		    <table class="table table-hover">
		        <thead>
		            <tr>
		                <th>Name</th>
		                <th></th>
		            </tr>
		        </thead>
		
		        <tbody>
		            <c:forEach var="genre" items="${genres}">
		                <tr>
		                    <td><c:out value="${genre.name}"></c:out></td>
		                <td>
		                    <form action="/genre/delete/${genre.id}" method="post">
		                        <input type="hidden" name="_method" value="delete">
		                        <button class="btn btn-danger btn-sm" type="submit" value="Delete">Delete</button>
		                    </form>
		                </td>
		                </tr>
		            </c:forEach>
		        </tbody>
		    </table>
	    </div>
	</div>
</body>
</html>