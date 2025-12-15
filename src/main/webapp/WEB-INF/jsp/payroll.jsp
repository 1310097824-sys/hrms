<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>薪酬管理</title>
</head>
<body>
<h2>薪酬</h2>
<a href="/dashboard">返回</a>
<c:if test="${canEdit}">
    <h3>新增/调整薪酬</h3>
    <form action="/payroll" method="post">
        员工:
        <select name="userId">
            <c:forEach items="${users}" var="u">
                <option value="${u.id}">${u.fullName} (${u.username})</option>
            </c:forEach>
        </select>
        月份 (YYYY-MM): <input type="text" name="month" placeholder="2024-06" required/>
        金额: <input type="number" step="0.01" name="amount" required/>
        备注: <input type="text" name="notes"/>
        <button type="submit">保存</button>
    </form>
</c:if>
<h3>薪酬记录</h3>
<table border="1">
    <tr><th>员工</th><th>月份</th><th>金额</th><th>备注</th></tr>
    <c:forEach items="${records}" var="r">
        <tr>
            <td>${r.user.fullName}</td>
            <td>${r.month}</td>
            <td>${r.amount}</td>
            <td>${r.notes}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
