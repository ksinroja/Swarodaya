<div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
    </button>     
    <a class="navbar-brand" href="<%=request.getContextPath()%>/admin/user?action=dashBoard" style="color:white;">Swarodaya</a>
</div>
<!-- /.navbar-header -->

<ul class="nav navbar-top-links navbar-right">
    <li>
        <a href="<%=request.getContextPath()%>/admin/user?action=dashBoard" style="color:white;">
            <i class="glyphicon glyphicon-home"></i>
        </a>        
    </li>
    <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#" style="color:white;">
            <i class="glyphicon glyphicon-user"></i>  <i class="fa fa-caret-down"></i>
        </a>
        <ul class="dropdown-menu dropdown-user">
        <%-- 	<li><a href="<%=request.getContextPath()%>/admin/user/myprofile-v"><i class="fa fa-user fa-fw"></i> User Profile</a>
            </li> --%>
            <!--
            <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
            </li>
            -->
            <!-- <li class="divider"></li> -->
            <li><a href="<%=request.getContextPath()%>/admin/user?action=logout"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
            </li>
        </ul>
        <!-- /.dropdown-user -->
    </li>
</ul>
<!-- /.navbar-top-links -->