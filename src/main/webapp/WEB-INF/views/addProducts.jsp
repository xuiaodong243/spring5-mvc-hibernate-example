<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
		<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Spring5 MVC Hibernate Demo</title>
		<style type="text/css">
			.error {
				color: red;
			}
			table {
				width: 50%;
				border-collapse: collapse;
				border-spacing: 0px;
			}
			table td {
				border: 1px solid #565454;
				padding: 20px;
			}
		</style>
	</head>
	<body>
		<h1>Testing Form</h1>
		<form:form action="addProduct" method="post" modelAttribute="product">
			<table>
				<tr>
					<td>Product Name: </td>
					<td>
						<form:input path="name" /> <br />
						<form:errors path="name" cssClass="error" />
					</td>
				</tr>
				<tr>
					<td>Product Desc</td>
					<td>
						<form:input path="desc" /> <br />
						<form:errors path="desc" cssClass="error" />
					</td>
				</tr>
				<tr>
					<td colspan="2"><button type="submit">Submit</button></td>
				</tr>
			</table>
		</form:form>
		
		<h2>Product List</h2>
		<table>
			<tr>
				<td><strong>id</strong></td>
				<td><strong>Name</strong></td>
				<td><strong>Desc</strong></td>
				<td><strong>create by</strong></td>
				<td><strong>create dt</strong></td>
				<td><strong>update by</strong></td>
				<td><strong>update dt</strong></td>
			</tr>
			<c:forEach items="${products}" var="product">
				<tr>
					<td><a href="getProduct?id=${product.id}">${product.id}</a></td>
					<td>${product.name}</td>
					<td>${product.desc}</td>
					<td>${product.createBy}</td>
					<td>${product.createDt}</td>
					<td>${product.updateBy}</td>
					<td>${product.updateDt}</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="3"><a href="listProducts?page=0">First Page</a></td>
				<td colspan="4"><a href="listProducts?page=${pageIndex+1}">Next Page</a></td>
			</tr>
		</table>
	</body>
</html>