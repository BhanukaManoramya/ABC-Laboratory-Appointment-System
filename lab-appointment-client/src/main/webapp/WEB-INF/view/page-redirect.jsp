<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
	<title>User Authority Configuration</title>
	</head>
	<body>
	
	
		<!-- Redirecting user based on authority level  -->	
		
		<security:authorize access="hasRole('ADMIN')">
			<% response.sendRedirect("admin/home"); %>
		</security:authorize>
		
		<security:authorize access="hasRole('MANAGER')">
			<% response.sendRedirect("manager/home"); %>
		</security:authorize>
		
		<security:authorize access="hasRole('PATIENT')">
			<% response.sendRedirect("patient/home"); %>
		</security:authorize>
	</body>
</html>