<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>

<html>
	<head>
    
		<title>addDept</title>
		<script type="text/javascript">
		
		$(function(){
				
		        //设置页面验证
				$("#inputForm").validate(
				{
					rules: {
						deptNo : {required:true},
						deptName: {required:true}
					},
					messages: {
						deptNo : {required:"部门编号不能为空！"},
						deptName: {required:"部门名称不能为空！"}
					},
					errorLabelContainer: $("#container"),
					wrapper: "li"
				});
				
			});
		
			function returnBack(){
				$("#inputForm").attr("action","${ctx}/daodao/hr/dept/list");
				$("#inputForm").submit();
			}
			
			function reset(){
				$.ajax({
			       url:"${ctx}/daodao/hr/dept/test.json", 
			       type:"post", 
			       dataType:"json",
			       success:function(data){
			    	   alert(data.deptNo);
			       }
				});
			}
		</script>

	</head>
  
	<body>
		<form action="${ctx}/daodao/hr/dept/update" method="post" name="inputForm" id="inputForm">
			<input id="id" name="id" type="hidden" value="${dept.id}" />
			<div id="container">
			    <spring:hasBindErrors name="dept">
			        <c:if test="${errors.fieldErrorCount > 0}">  
			            字段错误：<br/>  
			            <c:forEach items="${errors.fieldErrors}" var="error">  
			                <spring:message var="message" code="${error.code}" arguments="${error.arguments}" text="${error.defaultMessage}"/>  
			                ${error.field}------${message}
			            </c:forEach>  
			        </c:if>  
			      
			        <c:if test="${errors.globalErrorCount > 0}">  
			            全局错误：<br/>  
			            <c:forEach items="${errors.globalErrors}" var="error">  
			                <spring:message var="message" code="${error.code}" arguments="${error.arguments}" text="${error.defaultMessage}"/>  
			                <c:if test="${not empty message}">  
			                    ${message}<br/>  
			                </c:if>  
			            </c:forEach>  
			        </c:if>  
			    </spring:hasBindErrors>  
			</div>
			<table>
				<tr>
					<td>部门编号：</td>
					<td>
						<input id="deptNo" name="deptNo"  style="width:150px"
									class="form-control" value="${dept.deptNo}"/>
						<a onclick="reset()">重置</a>
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
						<a class="btn btn-primary btn-lg" href="javascript:void(0)" onclick="returnBack()">返回</a>
					</td>
				</tr>
			</table>
			
			<c:forEach items="${searchParams}"  var="sparam" >
				<input id="${sparam.key}" name="${sparam.key}" 
					type="hidden" value="${sparam.value}" />
			</c:forEach> 
		</form>
  
	</body>
</html>
