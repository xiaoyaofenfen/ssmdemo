<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>SSM Demo</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  <body>
   <h1>user table</h1>   
    <div>
        <c:forEach var="user" items="${users}" varStatus="status" step="1">
            <p>id = ${user.id}, name = ${user.userName}, password = ${user.password}</p>
        </c:forEach>
    </div>
    <fmt:setBundle var="bundle"  basename="com.liangfen.resource.Strings" scope="page"/>
    <form action="">
        <fmt:message key="register.username" bundle="${bundle}"/><input type="text" name="username"><br/>
        <fmt:message key="register.password" bundle="${bundle}"/><input type="password" name="password"><br/>
    </form>
  </body>
</html>
