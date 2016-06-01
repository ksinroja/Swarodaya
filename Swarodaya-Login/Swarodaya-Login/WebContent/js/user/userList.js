var userTable;

$(document).ready(function(){
    var rows_selected = [];
    
    userTable = $('#userTable').DataTable({
        "processing" : true,
		"bServerSide" : false,
		"bSorting" : false,
		"sAjaxSource" : "/admin/user?action=userDetail&draw=1",
        columnDefs : [{
            'targets' : 0,
            'searchable' : false,
            'orderable' : false,
            'className' : 'dt-body-center',
            'render' : function(data, type, full, meta) {
                return '<input type="radio" name="userId">';
            },
            'width':'1%'
        }, {
            'targets' : 1,            
            'render' : function(data, type, row) {
                var newData = row.firstName;
                if (row.middleName != null)
                    newData = newData + " " + row.middleName;

                if (row.lastName != null)
                    newData = newData + " " + row.lastName;

                return "<a href='javascript:void(0)' userId="+row.userId+">"+newData+"</a>";
            }
        }, {
            'targets' : 2,
            'data' : "gender",
            'width':'10%'
        }, {
            'targets' : 3,
            'data' : "country",
            'width':'10%'
        }, {
            'targets' : 4,
            'data' : "mobileNo",
            'width':'15%'
        }, {
            'targets' : 5,
            'data' : "email",
            'width':'15%'
        }, {
            'targets' : 6,
            'data' : "paymentStatus",
            'width':'15%'
        },{
            'targets' : 7,
            'data' : "receiptSent",
            'width':'15%'
        } ],
        order: [] 
/*       'rowCallback': function(row, data, dataIndex){
        // Get row ID
        var rowId = data[0];

        // If row ID is in the list of selected row IDs
        if($.inArray(rowId, rows_selected) !== -1){
           $(row).find('input[type="checkbox"]').prop('checked', true);
           $(row).addClass('selected');
        }
     }*/
    });
    
    // Handle table draw event
    userTable.on('draw', function(){
      // Update state of "Select all" control
    
     // updateDataTableSelectAllCtrl(userTable);
     // bindUserNameClickEvent();
   });
    

    $("#btnAddUser").click(function(){
        $("#page-wrapper").load("/workinteam/user/adduser-v",function(response,textStatus,jqXHR) {
	    	  if(jqXHR.getResponseHeader("is_login_page")){
	    		  location.reload();
	    	  }
	      });
    });
    
    // Handle click on checkbox
    $('#userTable tbody').on('click', 'input[type="radio"]', function(e){       
        
        $("#userTable tbody input:radio").each(function(){
      	   //do something here
      	  var $row = $(this).closest('tr');

            if(this.checked){
               $row.addClass('selected');
            } else {
               $row.removeClass('selected');
            }
      	})
        
        // Update state of "Select all" control
      //  updateDataTableSelectAllCtrl(userTable);

        // Prevent click event from propagating to parent
        e.stopPropagation();
     });

   // Handle click on table cells with checkboxes
    $('#userTable').on('click', 'tbody td, thead th:first-child', function(e){
 	   console.log("Enter");
       $(this).parent().find('input[type="radio"]').trigger('click');
    });

   // Handle click on "Select all" control
   $('thead input[name="select_all"]', userTable.table().container()).on('click', function(e){
      if(this.checked){
         $('#userTable tbody input[type="checkbox"]:not(:checked)').trigger('click');
      } else {
         $('#userTable tbody input[type="checkbox"]:checked').trigger('click');
      }

      // Prevent click event from propagating to parent
      e.stopPropagation();
   });
   
   $("#btnPayment").click(function(){
	   	//alert('a'+userTable.rows('.selected').data().userId);
	   /*	var ids = $.map(userTable.rows('.selected').data(), function (item) {
	   		
	           return item.userId;
	       });
	   	$("#userId").val(ids);*/
	  // 	alert(ids);
	   });
	   
   $("#btnUpdate").click(function(){
	   	//alert('a'+userTable.rows('.selected').data().userId);
	   //var user 
	 	var ids = $.map(userTable.rows('.selected').data(), function (item) {
	   		
	           return item.userId;
	       });
	  
	   var userId = ids;
	   var amount = $("#amount").val();
	   var pdate = $("#pdate").val();
	   var mode = $("#mode").val();
	   var details = $("#details").val();
	   $("#paymentModal").modal('hide');
	   makeAjaxCall("/admin/payment?action=addPayment&userId="+userId+"&amount="+amount+"&pdate="+pdate+"&mode="+mode+"&details="+details, "POST", "application/json; charset=utf-8", null, "json", paymentOkHandler, errorHandler);
	   });
   
   $("#btnSendReceipt").click(function(){
	   var ids = $.map(userTable.rows('.selected').data(), function (item) {
           return item.userId;
       });
	  
	   var userId = ids;
	   makeAjaxCall("/admin/payment?action=sendReceipt&userId="+userId, "POST", "application/json; charset=utf-8", null, "json", paymentOkHandler, errorHandler);
	   
   });
   
});

function updateDataTableSelectAllCtrl(table){
   var $table             = table.table().node();
   var $chkbox_all        = $('tbody input[type="checkbox"]', $table);
   var $chkbox_checked    = $('tbody input[type="checkbox"]:checked', $table);
   var chkbox_select_all  = $('thead input[name="select_all"]', $table).get(0);

   // If none of the checkboxes are checked
   if($chkbox_checked.length === 0){
      chkbox_select_all.checked = false;
      if('indeterminate' in chkbox_select_all){
         chkbox_select_all.indeterminate = false;
      }

   // If all of the checkboxes are checked
   } else if ($chkbox_checked.length === $chkbox_all.length){
      chkbox_select_all.checked = true;
      if('indeterminate' in chkbox_select_all){
         chkbox_select_all.indeterminate = false;
      }

   // If some of the checkboxes are checked
   } else {
      chkbox_select_all.checked = true;
      if('indeterminate' in chkbox_select_all){
         chkbox_select_all.indeterminate = true;
      }
   }
}



function paymentOkHandler(data){
    if(data.success == "true"){
        showNotification("Payment details added successfully","success");
        //$("#clientTable tbody tr").removeClass('selected');
        userTable.clear();
        userTable.ajax.reload();
    }else{
        showNotification("Error while adding paymen details<br/>Please contact your administrator","danger");
    }
}
function errorHandler(){
    showNotification("Something went wrong.<br/>Please contact your administrator","danger");
}



