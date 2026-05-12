<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp">
    <c:param name="title">科目管理</c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">科目管理</h2>
            <div class="px-4">
                <%-- 科目登録画面へ移動するボタン --%>
                <a href="subject_create.jsp" class="btn btn-primary mb-3">新規登録</a>
                
                <table class="table table-hover table-striped">
                    <thead>
                        <tr>
                            <th>科目コード</th>
                            <th>科目名</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="subject" items="${subjects}">
                            <tr>
                                <td>${subject.cd}</td>
                                <td>${subject.name}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </section>
    </c:param>
</c:import>