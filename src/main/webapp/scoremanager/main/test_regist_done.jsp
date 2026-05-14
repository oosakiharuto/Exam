<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp">
    <c:param name="title">成績登録完了</c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績登録完了</h2>
            <div class="px-4 text-center mt-5">
                <p class="fs-4">成績の登録が完了しました。</p>
                <a href="TestRegist.action" class="btn btn-primary mt-3">続けて登録する</a>
                <a href="Menu.action" class="btn btn-secondary mt-3 ms-2">メニューへ戻る</a>
            </div>
        </section>
    </c:param>
</c:import>