<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>科目登録</title>
</head>
<body>
    <%@ include file="../common/header.jsp" %>
    <%@ include file="../common/navigation.jsp" %>

    <h2>科目登録</h2>
    <form action="SubjectCreateExecute.action" method="post">
        <div>
            <label>科目コード:</label>
            <input type="text" name="cd" required maxlength="3">
        </div>
        <div>
            <label>科目名:</label>
            <input type="text" name="name" required>
        </div>
        <button type="submit">登録して終了</button>
    </form>

    <%@ include file="../common/footer.jsp" %>
</body>
</html>