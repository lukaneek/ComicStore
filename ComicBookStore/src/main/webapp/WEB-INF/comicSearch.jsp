<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- For JSTL Forms -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title></title>
<!-- for Bootstrap CSS -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<!-- YOUR own local CSS -->
<link rel="stylesheet" href="/css/main.css" />
<!-- For any Bootstrap that uses JS -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<!-- YOUR own local JavaScript -->
<script type="text/javascript" src="/js/app.js"></script>

</head>
<body>
<c:set var="isAdmin" value="#{user.isAdmin == true}" />
<c:set var="isNotAdmin" value="#{user.isAdmin == null or user.isAdmin == false}"/>
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
          				<a class="nav-link" aria-current="page" href="/genre">Add/View Genres</a>
        			</li>
        			<li class="nav-item">
          				<a class="nav-link" aria-current="page" href="#">Search Library</a>
        			</li>
        			<c:if test="${isAdmin}">
	        			<li class="nav-item">
    	      				<a class="nav-link" aria-current="page" href="comics/new">Add a New Comic!</a>
        				</li>
        			</c:if>
        			<li class="nav-item">
          				<a href="/logout" class="nav-link">Logout</a>
        			</li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container bg-primary-subtle" style="width: 75%">
		<div>
		<h1>Search Comic Titles</h1>
		<form:form action="/comics/results" method="Get">
			<input type="text" name="search" />
			<button type="submit">search</button>
		</form:form>
		</div>
			<table class="table table-hover table-bordered">
				<thead>
					<tr>
						<th>Title</th>
						<th>Cover Image</th>
						<th>Rent / Purchase</th>
					</tr>
				</thead>
				<tbody class="table-group-divider">
					<c:forEach var="comic" items="${searchedComics}">
						<tr>
							<td><a href="/comic/details/${comic.id}"> <c:out value="${comic.title}" /></a></td>
							<td><a href="/comic/details/${comic.id}"> <img src="/uploads/cover_pictures/${comic.coverImage}" alt="${comic.title}'s Cover Image" style="width:150px;"></a></td>
		                	<td>
							    <c:choose>
			                        <c:when test="${rentedComicIds.contains(comic.id)}">
			                            <button class="btn btn-secondary" disabled>Currently Unavailable</button>
			                        </c:when>
			                        <c:otherwise>
			                            <form action="/comics/rent/${comic.id}" method="post" style="display:inline;">
			                                <button type="submit" class="btn btn-primary">Rent Comic</button>
			                            </form>
			                            <form action="/comics/destroy/${comic.id}" method="post" style="display:inline;">
							                <input type="hidden" name="_method" value="delete" />
							                <button type="submit" class="btn btn-danger" onclick="return confirm('All sales are final! Continue with purchase?');">Purchase Comic</button>
							            </form>
			                        </c:otherwise>
			                    </c:choose>
							</td>
		                </tr>
		            </c:forEach>
		        </tbody>
		    </table>
		</div>
		<c:if test="${not empty rentedComics}">
			<div class="container mt-3" style="width: 60%">
			    <h2>My Rented Comics</h2>
			    <table class="table table-hover table-bordered">
			        <thead>
			            <tr>
			                <th>Title</th>
			                <th>Cover Image</th>
			                <th>Rented Date</th>
			                <th>Return / Purchase</th>
			            </tr>
			        </thead>
			        <tbody class="table-group-divider">
			            <c:forEach var="rental" items="${rentedComics}">
			                <tr>
			                    <td><a href="/comic/details/${rental.comic.id}"> <c:out value="${rental.comic.title}" /></a></td>
			                    <td><a href="/comic/details/${rental.comic.id}"> <img src="/uploads/cover_pictures/${rental.comic.coverImage}" alt="${rental.comic.title} Cover" style="width:150px;"></a></td>
			                    <td><fmt:formatDate value="${rental.createdAt}" pattern="MM/dd/yyyy" /></td>
			                    <td>
								    <form action="/comics/return/${rental.id}" method="post" style="display:inline;">
								        <button type="submit" class="btn btn-primary mb-3">Return Comic</button>
								    </form>
								    <form action="/comics/destroy/${rental.comic.id}" method="post" style="display:inline;">
						                <input type="hidden" name="_method" value="delete" />
						                <button type="submit" class="btn btn-danger" onclick="return confirm('All sales are final! Continue with purchase?');">Purchase Comic</button>
						            </form>
								</td>
			                </tr>
			            </c:forEach>
			        </tbody>
			    </table>
			</div>
		</c:if>
	</div>
</body>
</html>