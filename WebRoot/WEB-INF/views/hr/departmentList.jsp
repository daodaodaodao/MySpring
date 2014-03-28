<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>

<html>
  <head>
  
    <title>部门列表</title>
    
    <script type="text/javascript">
    	function clearSearch(){
			$("#inputForm input:text,select").val("");
			$("#inputForm").submit();
    	}
    	
    	function updateForm(id){
    		$("#inputForm").attr("action","${ctx}/daodao/hr/dept/updateForm/"+id);
			$("#inputForm").submit();
    	}
    </script>

  </head>
  
  <body>
	<legend><small>部门列表</small></legend>
	<form id="inputForm"  class="form-search" action="${ctx}/daodao/hr/dept/list" method="post">
		<label>部门编号:</label>
		<input id="search_deptNo" name="search_deptNo"  style="width:150px"
					class="form-control" value="${param['search_deptNo']}"/>
		<label>部门名称:</label>
		<input id="search_deptName" name="search_deptName"  style="width:150px"
					class="form-control" value="${param['search_deptName']}"/>
		<button type="submit" class="btn btn-primary btn-lg" >查询</button>
		<button type="button" class="btn btn-primary btn-lg" onclick="clearSearch()">重置</button>
	
	</form>
  
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
				<tr>
					<td>${dept.deptNo}</td>
					<td>${dept.deptName}</td>
					<td>
						<a href="javascript:void(0)" onclick="updateForm('${dept.id}')">更新</a>
						<a href="${ctx}/daodao/hr/dept/delete/${dept.id}">删除</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<a class="btn btn-primary btn-lg" href="${ctx}/daodao/hr/dept/addForm">添加</a>
  
  </body>
</html>
