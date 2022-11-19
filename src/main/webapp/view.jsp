<%--
  Created by IntelliJ IDEA.
  User: choejunhyeog
  Date: 2022/11/15
  Time: 4:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page import="com.example.dao.BoardDAO, com.example.bean.BoardVO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원정보 - 실전프로젝트 회원관리 V1</title>
    <link rel="stylesheet" href="member.css">
</head>
<body>
<%
    BoardDAO boardDAO = new BoardDAO();
    String id = request.getParameter("id");
    BoardVO u = boardDAO.getBoard(Integer.parseInt(id));
    request.setAttribute("vo", u);
%>
<h1>회원 정보 보기</h1>
<table id="edit">
    <tr>
        <td>Id</td> <td>${vo.getSeq()}</td>
    </tr>
    <tr>
        <td>Category</td> <td>${vo.getCategory()}</td>
    </tr>
    <tr>
        <td>Title</td><td>${vo.getTitle()}</td>
    </tr>
    <tr>
        <td>Photo</td><td><c:if test="${vo.getFile() ne ''}"><br />
        <img src="${pageContext.request.contextPath }/upload/${vo.getFile()}" class="file"></c:if></td>
    </tr>
    <tr>
        <td>Writer</td><td>${vo.getWriter()}</td>
    </tr>
</table>
<button type="button" onclick="history.back()">뒤로 가기</button>
<button type="button" onclick="location.href='editform.jsp?id=${vo.getSeq()}'">수정하기</button>
</body>
</html>
