<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp">
    <c:param name="title">成績参照</c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績管理（参照）</h2>
            <div class="px-4">
                <form action="TestList.action" method="get" class="row border-bottom pb-3 mb-4">
                    <div class="col-3">
                        <label class="form-label">科目</label>
                        <select name="subject_cd" class="form-select">
                            <c:forEach var="subject" items="${subjects}">
                                <option value="${subject.cd}" <c:if test="${subject.cd == param.subject_cd}">selected</c:if>>${subject.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-3">
                        <label class="form-label">クラス</label>
                        <select name="class_num" class="form-select">
                            <c:forEach var="cNum" items="${class_list}">
                                <option value="${cNum}" <c:if test="${cNum == param.class_num}">selected</c:if>>${cNum}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-3">
                        <label class="form-label">回数</label>
                        <select name="no" class="form-select">
                            <option value="1" <c:if test="${param.no == '1'}">selected</c:if>>1</option>
                            <option value="2" <c:if test="${param.no == '2'}">selected</c:if>>2</option>
                        </select>
                    </div>
                    <div class="col-3 d-flex align-items-end">
                        <button type="submit" class="btn btn-primary">検索</button>
                    </div>
                </form>

                <c:if test="${not empty tests}">
                    <table class="table table-hover table-striped">
                        <thead>
                            <tr>
                                <th>学生番号</th>
                                <th>氏名</th>
                                <th>点数</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="test" items="${tests}">
                                <tr>
                                    <td>${test.student.no}</td>
                                    <td>${test.student.name}</td>
                                    <td>${test.point}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </div>
        </section>
    </c:param>
</c:import>