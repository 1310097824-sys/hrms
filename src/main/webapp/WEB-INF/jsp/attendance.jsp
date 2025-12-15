<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>打卡</title>
</head>
<body>
<h2>考勤</h2>
<a href="/dashboard">返回</a>
<form action="/attendance/checkin" method="post">
    <button type="submit">立即打卡</button>
</form>
<h3>打卡记录</h3>
<ul>
    <c:forEach items="${records}" var="r">
        <li>${r.checkInTime}</li>
    </c:forEach>
</ul>
</body>
</html>
