<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>档案管理</title>
</head>
<body>
<h2>员工档案</h2>
<a href="/dashboard">返回</a>
<h3>新增档案</h3>
<form action="/files" method="post">
    用户:
    <select name="userId">
        <c:forEach items="${users}" var="u">
            <option value="${u.id}">${u.fullName} (${u.username})</option>
        </c:forEach>
    </select>
    入职日期: <input type="date" name="hireDate" required/>
    备注: <input type="text" name="notes"/>
    <button type="submit">保存</button>
</form>

<h3>档案列表</h3>
<table border="1">
    <tr><th>用户</th><th>入职日期</th><th>备注</th><th>状态</th><th>操作</th></tr>
    <c:forEach items="${files}" var="f">
        <tr>
            <td>${f.user.fullName}</td>
            <td>${f.hireDate}</td>
            <td>${f.notes}</td>
            <td><c:choose><c:when test="${f.deleted}">已删除</c:when><c:otherwise>正常</c:otherwise></c:choose></td>
            <td>
                <c:choose>
                    <c:when test="${f.deleted}">
                        <form action="/files/restore/${f.id}" method="post" style="display:inline"><button type="submit">恢复</button></form>
                    </c:when>
                    <c:otherwise>
                        <form action="/files/delete/${f.id}" method="post" style="display:inline"><button type="submit">假删除</button></form>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
