<%@ page contentType="text/html; charset=EUC-KR" %>
<%@page import="java.net.URLDecoder"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>

<title>��� ��ǰ ����</title>

</head>
<body>
	����� ��� ��ǰ�� �˰� �ִ�
<br>
<br>
<c:forEach var="i" items="${URLDecoder.decode(cookie.history.value,'euc_kr').split(',')}">
	<a href="/product/getProduct?prodNo=${i}&menu=search"	target="rightFrame">${i}</a><br/>
</c:forEach>
<button onclick="window.close();">�ݱ�</button>

</body>
</html>