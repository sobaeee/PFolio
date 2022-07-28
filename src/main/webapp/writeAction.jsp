<%@page import="udata.DataDAO"%>
<%@page import="domain.DataVO"%>
<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
request.setCharacterEncoding("UTF-8");
String userID = null;
if (session.getAttribute("userID") != null) {
	userID = (String) session.getAttribute("userID");
}
%>
<jsp:useBean id="vo" class="domain.DataVO" scope="page" />
<jsp:setProperty property="*" name="vo" />
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.css">
<title>게시판</title>
</head>
<body>
	<%
	//DataVO vo = new DataVO();
	DataDAO dataDAO = new DataDAO();
	
	System.out.println("dd"+ vo);

	if (userID == null) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('로그인하세요.')");
		script.println("location.href = 'login.jsp'");
		script.println("</script>");
		System.out.println("dd"+ userID);
		
	} else {
		if (vo.getDataTitle() == null || vo.getDataContent() == null) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('모든 문항을 입력해주세요.')");
			script.println("history.back()"); // 이전 페이지로 사용자를 보냄
			script.println("</script>");
			
			
		} else {
			int result = dataDAO.write(vo);
			if (result == -1) { // 글쓰기 실패시
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('글쓰기에 실패했습니다.')");
		script.println("history.back()"); // 이전 페이지로 사용자를 보냄
		script.println("</script>");
			} else { // 글쓰기 성공시
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("location.href = 'list.jsp'"); // 메인 페이지로 이동
		script.println("</script>");
			}
		}
	}
	%>

</body>
</html>