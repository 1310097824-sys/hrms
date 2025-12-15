<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<h2>人力资源管理系统登录</h2>
<form action="/login" method="post">
    <label>用户名: <input type="text" name="username"/></label><br/>
    <label>密码: <input type="password" name="password"/></label><br/>
    <button type="submit">登录</button>
</form>
<c:if test="${not empty error}">
    <p style="color:red;">${error}</p>
</c:if>
</body>
</html>
