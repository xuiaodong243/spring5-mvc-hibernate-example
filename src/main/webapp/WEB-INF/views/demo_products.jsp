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
		<h1>Testing List Products</h1>
		<form:form action="addDemoProduct" method="post" modelAttribute="product">
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
					<td>Product price</td>
					<td>
						<form:input path="unitPrice" type="number" /> <br />
						<form:errors path="unitPrice" cssClass="error" />
					</td>
				</tr>
				<tr>
					<td>Product Supplier</td>
					<td>
						<form:select path="supplierId">
							<form:option value="-" label="--Please Select--"/>
							<form:options items="${suppliers}" />
						</form:select>

						<form:errors path="supplierId" cssClass="error" />
					</td>
				</tr>
				<tr>
					<td>Product Category</td>
					<td>
						<form:select path="categoryId">
							<form:option value="-" label="--Please Select--"/>
							<form:options items="${categories}" />
						</form:select>
						<form:errors path="categoryId" cssClass="error" />
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
				<td><strong>Unit Price</strong></td>
				<td><strong>Supplier</strong></td>
				<td><strong>Category</strong></td>
				<td><strong>create by</strong></td>
				<td><strong>create dt</strong></td>
				<td><strong>update by</strong></td>
				<td><strong>update dt</strong></td>
			</tr>
			<c:forEach items="${products}" var="product">
				<tr>
					<td><a href="../getDemoProduct?id=${product.id}">${product.id}</a></td>
					<td>${product.name}</td>
					<td>${product.desc}</td>
					<td>${product.unitPrice}</td>
					<td>
						<c:set var="supplierId">${product.supplierId}</c:set>
						<c:out value="${suppliers[supplierId]}"/>
					</td>
					<td>
						<c:set var="categoryId">${product.categoryId}</c:set>
						<c:out value="${categories[categoryId]}"/>
					</td>
					<td>${product.createBy}</td>
					<td>${product.createDt}</td>
					<td>${product.lastupdBy}</td>
					<td>${product.lastupdDt}</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="5"><a href="../listDemoProducts">First Page</a></td>
				<td colspan="5"><a href="../listDemoProducts/${pageIndex+1}">Next Page</a></td>
			</tr>
		</table>
	</body>
</html>