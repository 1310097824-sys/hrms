<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>机构与职位管理</title>
</head>
<body>
<h2>机构管理</h2>
<a href="/dashboard">返回</a>
<h3>新增机构</h3>
<form action="/org/unit" method="post">
    名称: <input type="text" name="name" required/>
    级别:
    <select name="level">
        <option value="1">一级机构</option>
        <option value="2">二级机构</option>
        <option value="3">三级机构</option>
    </select>
    父机构:
    <select name="parentId">
        <option value="">无</option>
        <c:forEach items="${units}" var="u">
            <option value="${u.id}">${u.name} (Level ${u.level})</option>
        </c:forEach>
    </select>
    <button type="submit">保存</button>
</form>

<h3>现有机构</h3>
<ul>
    <c:forEach items="${units}" var="u">
        <li>${u.name} - 等级${u.level} <c:if test="${not empty u.parent}">(父级: ${u.parent.name})</c:if></li>
    </c:forEach>
</ul>

<h3>新增职位 (关联三级机构)</h3>
<form action="/org/position" method="post">
    职位名称: <input type="text" name="name" required/>
    所属三级机构:
    <select name="orgUnitId" required>
        <c:forEach items="${level3}" var="lv3">
            <option value="${lv3.id}">${lv3.name}</option>
        </c:forEach>
    </select>
    <button type="submit">保存</button>
</form>

<h3>职位列表</h3>
<ul>
    <c:forEach items="${positions}" var="p">
        <li>${p.name} - ${p.organizationUnit.name}</li>
    </c:forEach>
</ul>
</body>
</html>
