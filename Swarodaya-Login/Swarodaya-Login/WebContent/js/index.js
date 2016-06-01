$(document).ready(function(){
	console.log('Dashboard initiated.');
//	makeAjaxCall("/workinteam/admin/user/userlist?draw=1", "GET", "application/json",null, "json", successGetUserList, ajaxErrorHadler);
});

function successGetUserList(data){
	$('#userCount').html(data.recordsTotal);
}

function ajaxErrorHadler(data){
	showNotification("Error while getting data<br/>Please contact your administrator","danger");
}
