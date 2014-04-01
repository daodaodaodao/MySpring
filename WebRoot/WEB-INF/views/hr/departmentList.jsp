<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>

<html>
  <head>
  
    <title>部门列表</title>
    
    <script type="text/javascript">
    
    	$(function(){
    		
    		var paginator = {
    				currentPage: ${depts.number}+1,
    				totalPages: ${depts.totalPages},
    				numberOfPages:5,
    	            onPageClicked: function(e,originalEvent,type,page){
    	    			$("#search_page").attr("value",page);
    	    	    	inputForm.submit();
    	            }
    		}
    		
    		$("#paginator").bootstrapPaginator(paginator);
    	});
    
    	function searchSubmit(){
			$("#search_page").attr("value",1);
			$("#inputForm").submit();
    	}
    
    	function clearSearch(){
			$("#inputForm input:text,select").val("");
			searchSubmit();
    	}
    	
    	function updateForm(id){
    		$("#inputForm").attr("action","${ctx}/daodao/hr/dept/updateForm/"+id);
			$("#inputForm").submit();
    	}
    	
    	function del(id){
    		$("#inputForm").attr("action","${ctx}/daodao/hr/dept/delete/"+id);
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
		<button type="button" class="btn btn-primary btn-lg" onclick="searchSubmit()">查询</button>
		<button type="button" class="btn btn-primary btn-lg" onclick="clearSearch()">重置</button>
		<input type="hidden" id="search_page" name="search_page" value="${param['search_page']}" />
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
						<a href="javascript:void(0)" onclick="del('${dept.id}')">删除</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<div id="paginator" name="paginator"></div>
	一共${depts.totalPages}页
	<a class="btn btn-primary btn-lg" href="${ctx}/daodao/hr/dept/addForm">添加</a>
  
  </body>
</html>
