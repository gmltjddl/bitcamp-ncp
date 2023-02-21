<%@page import="bitcamp.myapp.vo.Student"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%!
  private static String getLevelText(int level) {
    switch (level) {
      case 0: return "비전공자";
      case 1: return "준전공자";
      default: return "전공자";
    }
  }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>비트캠프 - NCP 1기</title>
</head>
<body>
<h1>학생(JSP + MVC2)</h1>

<div><a href='form'>새 학생</a></div>

<table border='1'>
<tr>
  <th>번호</th> <th>이름</th> <th>전화</th> <th>재직</th> <th>전공</th>
</tr>
<c:forEach items="${students}" var="s">
  <tr>
      <td>${s.no}</td> 
      <td><a href='view?no=${s.no}'>${s.name}</a></td> 
      <td>${s.tel}</td> 
      <td>${s.working ? "예" : "아니오"}</td> 
      <td>${s.level}</td>
  </tr>
</c:forEach>
</table>
<form action='list' method='get'>
  <input type='text' name='keyword' value='${param.keyword}'>
  <button>검색</button>
</form>

</body>
</html>


