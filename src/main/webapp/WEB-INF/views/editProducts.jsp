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
		<form:form action="editProduct" method="post" modelAttribute="product">
			<table>
				<tr>
					<td>Product Id: </td>
					<td>
						${product.id}
						<form:hidden path="id"/>
					</td>
				</tr>
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
					<td>Product create by: </td>
					<td>
						<form:input path="createBy"/>
					</td>
				</tr>
				<tr>
					<td>Product create dt: </td>
					<td>
						${product.createDt}
						<form:hidden path="createDt" />
					</td>
				</tr>
				<tr>
					<td>Product update by: </td>
					<td>
						<form:input path="updateBy" />
					</td>
				</tr>
				<tr>
					<td>Product update dt: </td>
					<td>
						${product.updateDt}
						<form:hidden path="updateDt" />
					</td>
				</tr>

				<tr>
					<td colspan="2"><button type="submit">Submit</button></td>
				</tr>
			</table>
		</form:form>
		
		<h2>Product history</h2>
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
			<c:forEach items="${pList}" var="product">
				<tr>
					<td>${product.id}</td>
					<td>${product.name}</td>
					<td>${product.desc}</td>
					<td>${product.createBy}</td>
					<td>${product.createDt}</td>
					<td>${product.updateBy}</td>
					<td>${product.updateDt}</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>