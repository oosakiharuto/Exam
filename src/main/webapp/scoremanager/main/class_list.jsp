<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp">
    <c:param name="title">クラス管理</c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">クラス管理</h2>
            <div class="px-4">
                <%-- さっき作った登録画面へ移動するボタン --%>
                <a href="class_num_create.jsp" class="btn btn-primary mb-3">新規登録</a>
                
                <table class="table table-hover table-striped">
                    <thead>
                        <tr>
                            <th>クラス番号</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%-- データベースから取得したクラス一覧をループで表示 --%>
                        <c:forEach var="cNum" items="${class_list}">
                            <tr>
                                <td>${cNum}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </section>
    </c:param>
</c:import>