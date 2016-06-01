<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html>
	<head>
		<% pageContext.setAttribute( "contextPath", request.getContextPath()+"/"); %>
		
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <meta name="description" content="">
	    <meta name="author" content="">
	
		<%
			response.setHeader("is_login_page","true");
		%>
		
	    <title>Swarodaya Admin Login</title>
	
	    
	    
	     
	   <link rel="shortcut icon" href="<%=request.getContextPath() %>/images/logo.png" />
	    
	    <!-- Bootstrap Core CSS -->
	    <link href="<%=request.getContextPath() %>/css/bootstrap.min.css" rel="stylesheet" />
		
	    <!-- Custom Fonts -->
	    <link href="<%=request.getContextPath() %>/css/font-awesome.min.css" rel="stylesheet" />
	
	    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	    <!--[if lt IE 9]>
	        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
	    <![endif]-->
	

<style>
.form-signin {
  max-width: 400px; 
  display:block;
  background-color: #f7f7f7;
  -moz-box-shadow: 0 0 3px 3px #ddd;
  -webkit-box-shadow: 0 0 3px 3px #ddd;
  box-shadow: 0 0 3px 3px #ddd;
  margin: 0 auto;
}
.main{
	padding-right: 38px;
	padding-left: 38px;
	padding-bottom: 38px;
	padding-top: 20px;
}
.social-box{
  margin: 0 auto;
  padding: 38px;
  border-bottom:1px #ccc solid;
}
.social-box a{
  font-weight:bold;
  font-size:18px;
  padding:8px;
}
.social-box a i{
  font-weight:bold;
  font-size:20px;
}
.heading-desc{
	font-size:20px;
	font-weight:bold;
	padding:38px 38px 0px 38px;
	
}
.form-signin .form-signin-heading,
.form-signin .checkbox {
  margin-bottom: 10px;
}
.form-signin .checkbox {
  font-weight: normal;
}
.form-signin .form-control {
  position: relative;
  font-size: 16px;
  height: 40px;
  /* padding: 20px; */
  padding-top: 5px;
  padding-bottom: 5px;
  -webkit-box-sizing: border-box;
     -moz-box-sizing: border-box;
          box-sizing: border-box;
}
.form-signin .form-control:focus {
  z-index: 2;
}
.form-signin input[type="text"] {
  margin-bottom: 0px;
  border-radius: 5px;
}
.form-signin input[type="password"] {
  margin-bottom: 0px;
  border-radius: 5px;
}
.input-group {
    margin-bottom: 15px;
}
.login-footer{
	background:#f0f0f0;
	margin: 0 auto;
	border-top: 1px solid #dadada;
	padding:10px;
}
.login-footer .left-section a{
	font-size:12px;
	line-height:19px;
}
.mg-btm{
	margin-bottom:20px;
	margin-top:20px;
}
.row img {
    display: block;
    margin-left: auto;
    margin-right: auto;
}

html, body, .container-table {
    height: 100%;
    background-color: #eee;
}
.container-table {
    display: table;
}
.vertical-center-row {
    display: table-cell;
    vertical-align: middle;
}
</style>
</head>

<body>
<script>
function forgetPassword(userId){
	var userId = $('#fp-userId').val();
	makeAjaxCall("/workinteam/admin/forgetPassword?userId="+userId, "GET","text/plain", null, "json", successHandler, errorHandler);
}

function successHandler(data){
	console.log(data);
	if(data.success == "true"){
		$('#message').html(data.message);
	}else{
		$('#message').html(data.message);
	}
}

function errorHandler(){
	console.log(data);
}
</script>

	<div id="forgotPassword" class="modal fade" role="dialog">
		<div class="modal-dialog" style="width: 30%; ">

	    	<!-- Modal content-->
    		<div class="modal-content">
      			<div class="modal-header">
        			<button type="button" class="close" data-dismiss="modal">&times;</button>
        			<h4 class="modal-title">Forgot your password?</h4>
      			</div>
      				<div class="modal-body">
        				<label>Enter User Id</label>    
            			<div class="input-group">
                			<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                			<input type="text" id="fp-userId" name="fp-userId" class="form-control" placeholder="UserId" autofocus>
            			</div>
      				</div>
      				<div class="modal-footer">
        				<button type="button" onclick="javascript:forgetPassword();" class="btn btn-primary">Send</button>
      				</div>
      		</div>
    	</div>
  	</div>

    <div class="container container-table">
        <div class="row vertical-center-row">
		<form class="form-signin mg-btm" action="<%=request.getContextPath()%>/admin/login" method="post">
    	<img src="<%=request.getContextPath()%>/images/bg.png" alt="Swarodaya">
		<div class="main">	
			
            <label>User Id</label>    
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
            	<input type="text" name="userId" id="userId" class="form-control" placeholder="UserId" autofocus>
            </div>
            <label>Password</label>
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                <input type="password" name="password" class="form-control" placeholder="Password">
            </div>
    		<div class="row">
            	<div class="col-xs-12 col-md-12" style="color:red;">
            	    <strong><%=request.getParameter("message") != null ? request.getParameter("message"):"" %></strong> 
                </div>
    		</div>
        	<div class="row">
                <div class="col-xs-6 col-md-6">
                     
                </div>
                <div class="col-xs-6 col-md-6 pull-right">
                    <button type="submit" class="btn btn-primary pull-right">Login</button>
                </div>
            </div>
		</div>
        
        <span class="clearfix"></span>	

		<div class="login-footer">
    		<div class="row">
                <div class="col-xs-6 col-md-6">
                    <div class="left-section">
                        <a href="javascript:void(0);" data-toggle="modal" data-target="#forgotPassword">Forgot your password?</a>
    				</div>
                </div>
                <div class="col-xs-6 col-md-6 pull-right">
                </div>
            </div>
		</div>
	  </form>
	</div>	
    </div>

    
    <!-- jQuery -->
    <script src="<%=request.getContextPath() %>/js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="<%=request.getContextPath() %>/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript 
    <script src="${metisMenuJs}"></script>
	-->
	
    <!-- Custom Theme JavaScript 
    <script src="${sbAdminJs}"></script>
    -->
<script>
$(document).ready(function(){
    $("#userId").focus();
});
</script>
</body>

</html>