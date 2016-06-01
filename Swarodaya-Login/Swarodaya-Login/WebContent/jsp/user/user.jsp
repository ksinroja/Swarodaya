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
	<link rel="shortcut icon" type="image/x-icon" href="../images/logo.png" />

	<!-- Bootstrap Core CSS -->
    <link href="../../css/bootstrap.min.css" rel="stylesheet" />
    <link href="../../css/sb-admin-2.css" rel="stylesheet" />
     <link href="../../css/metisMenu.min.css" rel="stylesheet" />
    <link href="../../css/font-awesome.min.css" rel="stylesheet" />
    <link href="../../css/index.css" rel="stylesheet" />

   <script src="../../js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="../../js/bootstrap.min.js"></script>
    <script src="../../js/metisMenu.min.js"></script>

    <script src="../../js/sb-admin-2.js"></script>
    
    <script src="../../js/index.js"></script>
    <script src="../../js/bootstrap-notify.min.js" ></script>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0;background-color:#337ab7">
        	<jsp:include page="/jsp/common/top.jsp" />
        	<jsp:include page="/jsp/common/left-nav.jsp" />
        </nav>

        <div id="page-wrapper">
        	<jsp:include page="/jsp/user/userList.jsp" />
        </div>

    </div>

    
</body>
</html>