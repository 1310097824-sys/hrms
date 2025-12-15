<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>控制台</title>
</head>
<body>
<h2>欢迎，${user.fullName} (${user.role})</h2>
<p><a href="/logout">退出登录</a></p>
<ul>
    <li><a href="/attendance">打卡与记录</a></li>
    <li><a href="/payroll">薪酬管理/查看</a></li>
    <c:if test="${isArchiveAdmin}">
        <li><a href="/org">机构与职位管理</a></li>
        <li><a href="/files">档案管理</a></li>
    </c:if>
</ul>
</body>
</html>
