<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>

<link rel="stylesheet" type="text/css"
	href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

<c:url value="/css/main.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet" />

</head>
<body>

	<nav class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Spring Boot</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">Home</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container">

		<div class="starter-template">
			<div>
				<h2>${message}</h2>
			</div>

			<div>
				<form method="POST" enctype="multipart/form-data" action="/">
					<table>
						<tr>
							<td>File to upload:</td>
							<td><input type="file" name="file" /></td>
						</tr>
						<tr>
							<td></td>
							<td><input type="submit" value="Upload" /></td>
						</tr>
					</table>
				</form>
			</div>
			<br /> <br />
			<div>
				<ul>
					<table id="myTable">
						<tr>
							<th>File</th>
							<th>Size In Bytes</th>
							<th>Content Type</th>
						</tr>
						<c:forEach var="listValue" items="${files}">
							<tr>
								<td><a href="file/${listValue.name}${listValue.extension}">${listValue.name}${listValue.extension}</a></td>
								<td>${listValue.size}</td>
								<td>${listValue.contentType}</td>
							</tr>
						</c:forEach>
					</table>

				</ul>
			</div>
		</div>

	</div>
	<!-- /.container -->

	<script type="text/javascript"
		src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>