<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>

<html>
 	<head>
	    <title>My JSP starting page</title>
	</head>
  
	<body>

		<div class="list-group">
			<a href="${ctx}/daodao/hr/dept/list" class="list-group-item active">
				部门列表
			</a>
			<a href="javascript:void(0)" class="list-group-item">
				员工列表
			</a>
		</div>

	</body>
</html>
