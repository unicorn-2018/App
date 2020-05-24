/*function showEventList() {

var eventTable = $('#event-table').DataTable({
	pageLength: 5,
	info: false,
	lengthChange: false,
	searching: false,
	language: {
		url: "http://cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Japanese.json"
	},
	columnDefs: [
	      {targets:[4],visible: false}
	]
});

// 開閉のマークをクリックしたとき
$('#event-table tbody').on('click', 'td.details-control', function () {
	var tr = $(this).closest('tr');
	var row = eventTable.row(tr);
	// 行の開閉イベント
	if (row.child.isShown()) {
		row.child.hide();
		tr.removeClass('shown');
	} else {
		eventTable.rows().every(function() {
			if(this.child.isShown()) {
				this.child.hide();
				$(this.node()).removeClass('shown');
			}
		});
		var events = [];
		var d = row.data();
		e = $('#calendar').fullCalendar('clientEvents',d[4]);
		$.each(e, function(index, value){
			events.push({
				id : value['id'],
				resourceId : value['resourceId'],
				title : value['title'],
				start : value['start'],
				end : value['end'],
				description : value['description'],
				allDay : value['allDay'],
				editAuthId : value['editAuthId'],
				taskFlag : value['taskFlag'],
				eventIconId : value['eventIconId'],
				eventIconName : value['eventIconName'],
				eventTypeId : value['eventTypeId'],
				eventTypeName : value['eventTypeName'],
				taskStatusId : value['taskStatusId'],
				taskStatusName : value['taskStatusName'],
				createName : value['createName'],
				updateName : value['updateName'],
				buildingIdParam : value['buildingIdParam']
			});
		});
		$.ajax({
			type : 'post',
			url : '/eventedit',
			cache : false,
			async : true,
			dataType : 'text',
			data :JSON.stringify(events),
			contentType : 'application/json; charset=utf-8'

		}).then(function(data) {
			//html出力
	    	row.child(data).show();
	    	tr.addClass('shown');
	    	showEventEdit();
		},function() {
			 alert("読み込み失敗");
		});
  	}
});
}*/