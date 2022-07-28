<%@page import="user.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="user" class="user.User" scope="page" />
<jsp:setProperty property="userID" name="user" />
<jsp:setProperty property="userPW" name="user" />
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>게시판</title>
</head>
<body>
	<%
	UserDAO userDAO = new UserDAO();
	int res = userDAO.login(user.getUserID(), user.getUserPW());
	out.print(res);
	switch (res) {
	case 1:
		session.setAttribute("userID", user.getUserID());
		response.sendRedirect("main.jsp");
		break;
	default:
		response.sendRedirect("login.jsp");
		break;
	}
	%>
</body>
</html>