<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>

<html>
  <head>
  
    <title>部门列表</title>

  </head>
  
  <body>
	<legend><small>部门列表</small></legend>
	
	<div>
		<form id="inputForm"  class="form-search" action="${ctx}/daodao/hr/dept/list" method="post">
			<label>部门编号:</label>
			<input id="deptNo" name="deptNo"  style="width:50px"
						class="form-control" value="${param['deptNo']}"/>
			<label>部门名称:</label>
			<input id="deptName" name="deptName" 
						class="form-control" value="${param['deptName']}"/>
			<button type="button" class="btn btn-primary btn-lg" onclick="">查询</button>
			<button type="button" class="btn btn-primary btn-lg" onclick="">重置</button>
		
		</form>
	</div>
  
	<table id="contentTable"
		class="table table-striped table-bordered table-hover table-condensed">
		
		<thead>
			<tr>
				<th>部门编号</th>
				<th>部门名称</th>
				<th>操作</th>
			</tr>
		</thead>
		
		<tbody>
			<c:forEach items="${depts.content}" var="dept">
				<tr>${dept.deptNo}</tr>
				<tr>${dept.deptName}</tr>
				<tr></tr>
			</c:forEach>
		</tbody>
	</table>
	
	<button type="button" class="btn btn-primary btn-lg" onclick="">添加</button>
  
  </body>
</html>
