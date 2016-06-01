<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<% pageContext.setAttribute( "contextPath", request.getContextPath()+"/"); %>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
	
	<title>Swarodaya Home</title>
	<link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath() %>/images/logo.png" />

	<!-- Bootstrap Core CSS -->
    <link href="<%=request.getContextPath() %>/css/bootstrap.min.css" rel="stylesheet" />
    <link href="<%=request.getContextPath() %>/css/metisMenu.min.css" rel="stylesheet" />
    <link href="<%=request.getContextPath() %>/css/sb-admin-2.css" rel="stylesheet" />
    <link href="<%=request.getContextPath() %>/css/font-awesome.min.css" rel="stylesheet" />
    <link href="<%=request.getContextPath() %>/css/index.css" rel="stylesheet" />

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<input type="hidden" id="hiddenUserId" value="${LOGGEDIN_USER.userId}"/>
	<div id="wrapper">

        <!-- Navigation -->

        <nav class="navbar navbar-default navbar-static-top top-navbar" role="navigation" style="margin-bottom: 0;">
        	<jsp:include page="/jsp/common/top.jsp" />
        	<jsp:include page="/jsp/common/left-nav.jsp" />
        </nav>

        <div id="page-wrapper">
            <!-- <div class="row">
                <div class="col-lg-12">
                    <h1 class="h3">Dashboard</h1>
                </div>
            </div> -->
            <div class="row" style="padding-top:10px;">
                <div class="col-lg-3 col-md-6">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-users fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge" id="userCount">10&nbsp;</div>
                                    <div>Users registered!</div>
                                </div>
                            </div>
                        </div>
                        <a href="<%=request.getContextPath() %>/admin/user?action=userHome">
                            <div class="panel-footer">
                                <span class="pull-left">View Details</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6">
                    <div class="panel panel-green">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-sitemap fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge" id="clientCount">&nbsp;</div>
                                    <div>Clients registered!</div>
                                </div>
                            </div>
                        </div>
                        <a href="/workinteam/admin/client/client-main-v">
                            <div class="panel-footer">
                                <span class="pull-left">View Details</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
                
            </div>
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->
    
    <!-- jQuery -->
    <script src="<%=request.getContextPath() %>/js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="<%=request.getContextPath() %>/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath() %>/js/metisMenu.min.js"></script>

    <script src="<%=request.getContextPath() %>/js/sb-admin-2.js"></script>
    <script src="<%=request.getContextPath() %>/js/swarodaya-util.js"></script>
    <script src="<%=request.getContextPath() %>/js/index.js"></script>
    <script src="<%=request.getContextPath() %>/js/bootstrap-notify.min.js" ></script>
    
</body>
</html>