<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp">
    <c:param name="title">クラス登録</c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">クラス登録</h2>
            <div class="px-4">
                <form action="ClassNumCreateExecute.action" method="post">
                    <div class="mb-3">
                        <label class="form-label">クラス番号:</label>
                        <input type="text" name="class_num" class="form-control" required maxlength="5">
                    </div>
                    <button type="submit" class="btn btn-primary">登録</button>
                </form>
            </div>
        </section>
    </c:param>
</c:import>