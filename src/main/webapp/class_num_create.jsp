<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>クラス登録</title>
</head>
<body>
    <%@ include file="../common/header.jsp" %>
    <%@ include file="../common/navigation.jsp" %>

    <h2>クラス登録</h2>
    <form action="ClassNumCreateExecute.action" method="post">
        <div>
            <label>クラス番号:</label>
            <input type="text" name="class_num" required maxlength="5">
        </div>
        <button type="submit">登録</button>
    </form>

    <%@ include file="../common/footer.jsp" %>
</body>
</html>