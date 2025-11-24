<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Work Hours Report</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2 class="mb-4">Work Hours Report</h2>
    <form class="row g-3 mb-4" method="get" action="${pageContext.request.contextPath}/report">
        <div class="col-md-4">
            <label for="startDate" class="form-label">Start Date</label>
            <input type="datetime-local" class="form-control" id="startDate" name="startDate"
                   value="${param.startDate}">
        </div>
        <div class="col-md-4">
            <label for="endDate" class="form-label">End Date</label>
            <input type="datetime-local" class="form-control" id="endDate" name="endDate"
                   value="${param.endDate}">
        </div>
        <div class="col-md-4 align-self-end">
            <button type="submit" class="btn btn-primary">Filter</button>
        </div>
    </form>

    <c:choose>
        <c:when test="${not empty reportData}">
            <table class="table table-bordered table-striped">
                <thead class="table-light">
                <tr>
                    <th>Employee Name</th>
                    <th>Project Name</th>
                    <th>Total Hours</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="row" items="${reportData}">
                    <tr>
                        <td>${row.employeeName}</td>
                        <td>${row.projectName}</td>
                        <td>
                            <fmt:formatNumber value="${row.totalHours}" type="number" minFractionDigits="2" maxFractionDigits="2"/>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <div class="alert alert-info">No data found for the selected date range.</div>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
