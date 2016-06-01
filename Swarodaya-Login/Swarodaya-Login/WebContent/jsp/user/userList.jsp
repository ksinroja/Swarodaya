
<%-- <link href="${dataTableCss}/jquery.dataTables.min.css" rel="stylesheet" /> --%>
<link href="<%=request.getContextPath() %>/css/datatable/dataTables.bootstrap.css" rel="stylesheet" />
<link href="<%=request.getContextPath() %>/css/sb-admin-2.css" rel="stylesheet" />


<script src="<%=request.getContextPath() %>/js/bootstrap-notify.min.js" type="text/javascript" ></script>
<script src="<%=request.getContextPath() %>/js/datatable/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/js/datatable/dataTables.bootstrap.min.js" type="text/javascript" ></script>
<script src="<%=request.getContextPath() %>/js/user/userList.js"></script>

<div class="row toolbar">

    <div class="col-lg-6">
        
    </div>
    
    <div class="col-lg-6" style="text-align:right;">
    	<button type="button" class="btn btn-default btn-circle" id="btnPayment" title="Add Payment Detail" data-toggle="modal" data-target="#paymentModal"><i class="fa fa-rupee"></i>
        </button>
        <button type="button" id="btnSendReceipt" class="btn btn-default btn-circle" title="Send Payment Receipt"><i class="fa fa-send-o"></i>
        </button>
    </div>
    <!-- /.col-lg-12 -->
</div>
<div class="row" style="padding-top:15px;">
    <div class="col-lg-12">
        <div class="dataTable_wrapper">
            <table class="table table-striped table-bordered table-hover" id="userTable">
                <thead>
                    <tr>
                        <th></th>
                        <th>Full Name</th>
                        <th>Gender</th>
                        <th>Country</th>
                        <th>Mobile Phone</th>
                        <th>Email</th>
                        <th>Payment Status</th>
                        <th>Receipt Sent</th>
                    </tr>
                </thead>
            </table>
        </div>
        <!-- /.table-responsive -->
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div id="paymentModal" class="modal fade" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title" style="color: black">Payment Details</h4>
			</div>
			<form class="form-horizontal" role="form" data-toggle="validator">
			<div class="modal-body">
				<input type="hidden" id="userId" />
					<div class="form-group">
						<div class="col-sm-3 text-right">
							<label class="detail-lable control-label">Amount:
								</label>
						</div>
						<div class="col-sm-6 text-left">
							 <input type="text" class="form-control" id="amount" required
								placeholder="Amount">
						</div>
						<div class="col-sm-3 text-left">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-3 text-right">
							<label class="detail-lable control-label">Date:
								</label>
						</div>
						<div class="col-sm-6 text-left">
							 <input type="text" class="form-control" id="pdate" required
								placeholder="Payment Date">
						</div>
						<div class="col-sm-3 text-left">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-3 text-right">
							<label class="detail-lable control-label">Payment Mode:
								</label>
						</div>
						<div class="col-sm-6 text-left">
							 <select class="form-control" id="mode" required>
							 	<option value="-1">--Please Select--</option>
							    <option value="Cash">Cash</option>
							    <option value="Cheque">Cheque</option>
							    <option value="Credit Card">Credit Card</option>
							    <option value="Net banking">Net banking</option>
							  </select>
						</div>
						<div class="col-sm-3 text-left">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-3 text-right">
							<label class="detail-lable control-label">Payment Detail</label>
						</div>
						<div class="col-sm-6 text-left">
							<input type="text" class="form-control" id="details" required
								placeholder="Payment Detail">
						</div>
						<div class="col-sm-3 text-left">
						</div>
					</div>
				
			</div>
			<div class="modal-footer">
				<button type="button" id="btnUpdate" class="btn btn-primary"
					>Update</button>
				<button type="button" class="btn btn-secondary"
					data-dismiss="modal">Close</button>
			</div>
			</form>
		</div>

	</div>
</div>
<script src="<%=request.getContextPath() %>/js/swarodaya-util.js" ></script>
<script src="<%=request.getContextPath() %>/js/bootbox.min.js" ></script>

