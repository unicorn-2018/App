/**
 * タスクリスト画面
 */
function showTaskList() {
	var taskTable = $('#task-table').DataTable({
		pageLength: 5,
		info: false,
		lengthChange: false,
		searching: false,
		language: {
			url: "http://cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Japanese.json"
		},
		columnDefs: [
		      {targets:[4,5,6,7,8,9,10,11,12,13,14], visible:false}
		]
	});
	// 開閉のマークをクリックしたとき
	$('#task-table tbody').on('click', 'td.details-control', function () {
		var tr = $(this).closest('tr');
		var row = taskTable.row(tr);
		// 行の開閉イベント
		if (row.child.isShown()) {
			row.child.hide();
			tr.removeClass('shown');
		} else {
			taskTable.rows().every(function() {
				if(this.child.isShown()) {
					this.child.hide();
					$(this.node()).removeClass('shown');
				}
			});
			var events = [];
			var d = row.data();
			events.push({
					id : d[4],
					title : d[5],
					start : d[6],
					description : d[7],
					taskStatusId : d[8],
					taskStatusName : d[9],
					taskKigenFlag : d[10],
					editAuthFlag : d[11],
					eventTypeId : d[12],
					eventTypeColor : d[13],
					version : d[14]
			});
			$.ajax({
				type : 'post',
				url : '/api/taskedit',
				cache : false,
				async : true,
				dataType : 'text',
				data :JSON.stringify(events),
				contentType : 'application/json; charset=utf-8'
			}).then(function(data) {
				// html出力
			    row.child(data).show();
			    tr.addClass('shown');
			    // タスク編集画面を表示
			    showTaskEdit();
			},function() {
				$(function() {
					showMessage(null);
				})
			});
	  	}
	});
}