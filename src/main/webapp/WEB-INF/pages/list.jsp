<%--
  Created by IntelliJ IDEA.
  User: ponking
  Date: 2019/9/15
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%
    String APP_PATH = request.getContextPath();
    request.setAttribute("APP_PATH", APP_PATH);
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>员工列表</title>
<%--    web路径:
不以/开始的相对路径,找资源,以当前资源的路径为基准,经常容易出问题.
以/开始的相对路径,找资源,以服务器为标准(https://localhost:3306):
需要添加上项目名,如http://localhost:3306/crud
--%>
    <link href="${APP_PATH}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="${APP_PATH}/staic/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${APP_PATH}/static/js/jquery-3.3.1.min.js"></script>
</head>
<body>
<%--搭建显示页面--%>
<div class="container">
<%--    标题--%>
    <div class="row">
        <div class="col-lg-12">
            <h1>SSM-CRUD</h1>
        </div>
    </div>

<%--   新增, 删除按钮--%>
    <div class="row">
        <div class="col-md-4 col-md-offset-8">
            <button class="btn btn-primary">新增</button>
            <button class="btn btn-danger">删除</button>
        </div>
    </div>
<%--    表格--%>

    <div class="row">
        <div class="col-md-12">

            <table class="table table-hover">
                <tr>
                    <th>#</th>
                    <th>empName</th>
                    <th>gender</th>
                    <th>email</th>
                    <th>deptName</th>
                    <th>操作</th>
                </tr>

                <c:forEach items="${pageInfo.list}" var="emp">
                    <tr>
                    <th>${emp.empId}</th>
                    <th>${emp.empName}</th>
                    <th>${emp.gender=="M"?"男":"女"}</th>
                    <th>${emp.email}</th>
                    <th>${emp.department.deptName}</th>
                    <th>
                        <button class="btn btn-primary">
                            <span class="glyphicon glyphicon-pencil">编辑</span>
                        </button>
                        <button class="btn btn-danger">
                            <span class="glyphicon glyphicon-trash">删除</span>
                        </button>
                    </th>
                    </tr>
                </c:forEach>

            </table>
        </div>
    </div>
<%--    分页信息--%>
    <div class="row">
        <div class="col-lg-4">
            第${pageInfo.pageNum}页,共${pageInfo.pages},共${pageInfo.total}条记录
        </div>

        <div class="col-lg-8">
            <nav aria-label="Page navigation">
                <ul class="pagination">

                    <li><a href="${APP_PATH}/emps/?pn=1">首页</a></li>
                    <c:if test="${pageInfo.hasPreviousPage}">
                        <li>
                            <a href="${APP_PATH}/emps?pn=${pageInfo.pageNum-1}"
                               aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                    </c:if>

                    <c:forEach items="${pageInfo.navigatepageNums}" var="num" begin="0" end="4">
                        <c:if test="${num == pageInfo.pageNum}">
                            <li class="active"><a href="${APP_PATH}/emps?pn=${num}">${num}</a></li>
                        </c:if>
                        <c:if test="${num != pageInfo.pageNum}">
                            <li><a href="${APP_PATH}/emps?pn=${num}">${num}</a></li>
                        </c:if>
                    </c:forEach>

                    <c:if test="${pageInfo.hasNextPage}">
                        <li>
                            <a href="${APP_PATH}/emps?pn=${pageInfo.pageNum+1}"
                               aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </c:if>

                    <li><a href="${APP_PATH}/emps?pn=${pageInfo.pages}">尾页</a></li>
                </ul>
            </nav>
        </div>
    </div>
</div>

</body>
</html>
