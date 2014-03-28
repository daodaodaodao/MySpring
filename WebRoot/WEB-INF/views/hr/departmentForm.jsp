<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>

<html>
	<head>
    
		<title>addDept</title>

	</head>
  
	<body>
		<form action="${ctx}/daodao/hr/dept/update" method="post">
			<input id="id" name="id" type="hidden" value="${dept.id}" />
			<table>
				<tr>
					<td>部门编号：</td>
					<td>
						<input id="deptNo" name="deptNo"  style="width:150px"
									class="form-control" value="${dept.deptNo}"/>
					</td>
				</tr>
				<tr>
					<td>部门名称：</td>
					<td>
						<input id="deptName" name="deptName"  style="width:150px"
									class="form-control" value="${dept.deptName}"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" class="btn btn-primary btn-lg" value="确定"/>
						<a class="btn btn-primary btn-lg" href="${ctx}/daodao/hr/dept/list">返回</a>
					</td>
				</tr>
			</table>
			
			<c:forEach var="param" items="${searchParams}">
				<input id="<c:out value="${tmp.key}" />" name="<c:out value="${tmp.key}" />" type="hidden" value="${param.value}" />
			</c:forEach> 
		</form>
  
	</body>
</html>
