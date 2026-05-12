<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%-- 共通のデザイン（base.jsp）を読み込みます --%>
<c:import url="/common/base.jsp">
    <c:param name="title">クラス登録完了</c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">クラス登録完了</h2>
            <div class="px-4 text-center mt-5">
                <p class="fs-4">クラスの登録が完了しました。</p>
                <%-- 登録が終わったらメニュー画面（Menu.action）に戻るボタン --%>
                <a href="Menu.action" class="btn btn-secondary mt-3">メニューへ戻る</a>
            </div>
        </section>
    </c:param>
</c:import>