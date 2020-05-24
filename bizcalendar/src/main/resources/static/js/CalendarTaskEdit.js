/**
 * タスク編集画面
 */
function showTaskEdit() {
	// 期限日
	$('#taskKigenYmd').datepicker({
		dateFormat : 'yy-mm-dd',
	    showButtonPanel: true,
	    changeMonth: true,
	    changeYear: true,
	    todayHighlight: true
	}).on('dp.error', function(e) {
		  $(e.target).val("");
	});

	// 期限日
	$('#taskKigenYmd')
	// フォーカス時、読み取り専用
	.focusin(function(e) {
		$(this).attr('readonly', true);
	})
	// フォーカスアウト時、読み取り専用解除
	.focusout(function(e) {
		$(this).attr('readonly', false);
	})

	// 状況の設定
	const statusData = [];
	var tmpStatusTitle = null;
	for (var i = 0; i < taskStatus.length; i++) {
		var statusDataAttrs = [];
	    //id
		statusDataAttrs.push({title:'dataattr1', data:taskStatus[i].code});
	    //json追加
		statusData.push({title:taskStatus[i].naiyo1, dataAttrs:statusDataAttrs});
		// 変更の場合
		if ($('#taskStatusId').val() != "") {
			if (taskStatus[i].code == $('#taskStatusId').val()) {
				tmpStatusTitle = taskStatus[i].naiyo1;
			}
		// 追加の場合または変更時ステータスが未設定の場合
		} else {
			if (taskStatus[i].code == 99) {
				tmpStatusTitle = taskStatus[i].naiyo1;
			}
		}
	}
	var statusOptions = {
	    title : tmpStatusTitle,
	    data: statusData,
	    maxHeight: 200,
	    selectChildren: false,
	    multiSelect : false,
	    clickHandler: function(element){
	    	var status = $(element).data('dataattr1');
		    var text = $(element).find('a').first().text();
		    $('#status').SetTitle(text);
		    $('#taskStatusId').val(status);
	    }
	}
	$('#status').DropDownTree(statusOptions);

	// 種別の設定
	const taskTypeData = [];
	var taskTypeTitle = null;
	for (var i = 0; i < taskTypes.length; i++) {
		var typeDataAttrs = [];
	    // id
		typeDataAttrs.push({title:'dataattr1', data:taskTypes[i].code});
		// 種別色
		typeDataAttrs.push({title:'dataattr2', data:taskTypes[i].naiyo1});
		// 種別名称
		typeDataAttrs.push({title:'dataattr3', data:taskTypes[i].naiyo2});
	    // json追加
		var typeTitle = '<span style="color:' + taskTypes[i].naiyo1 + '"><i class="fa fa-square" aria-hidden="true"></i></span>' + ' ' + taskTypes[i].naiyo2;
		taskTypeData.push({title:typeTitle, dataAttrs:typeDataAttrs});
		// 変更の場合
		if ($('#taskTypeId').val() != "") {
			if (taskTypes[i].code == $('#taskTypeId').val()) {
				taskTypeTitle = typeTitle;
			}
		// 追加の場合または変更時ステータスが未設定の場合
		} else {
			if (taskTypes[i].code == 99) {
				taskTypeTitle = typeTitle;
			}
		}
	}
	var typeOptions = {
	    title : taskTypeTitle,
	    data: taskTypeData,
	    maxHeight: 200,
	    selectChildren: false,
	    multiSelect : false,
	    clickHandler: function(element) {
		    var typeId = $(element).data('dataattr1');
		    var typeColor = $(element).data('dataattr2');
		    var typeName = $(element).data('dataattr3');
		    $('#taskType').SetTitle('<span style="color:' + typeColor + '"><i class="fa fa-square" aria-hidden="true"></i></span>' + ' ' + typeName);
		    $('#taskTypeId').val(typeId);
	    }
	}
	$('#taskType').DropDownTree(typeOptions);

	// 追加の場合
	if ($('#taskId').val() == 0) {
		// タスク編集画面を表示
		document.getElementById('task-card-confirm-body').style.display ='none';
		document.getElementById('task-card-body').style.display ='block';

		// 保存（追加）ボタン
		document.querySelector('#taskAddButton').addEventListener('click', function(event) {
			var form = $('#task-validation')
			if (form[0].checkValidity() === false) {
				event.preventDefault();
				event.stopPropagation();
				form.addClass('was-validated');
			} else {
				addTask();
			}
		}, false);
	// 変更の場合
	} else {
		// タスク参照画面を表示
		document.getElementById('task-card-confirm-body').style.display ='block';
		document.getElementById('task-card-body').style.display ='none';

		// 編集ボタン
		$('#taskEditButton')
		.click(function() {
			document.getElementById('task-card-confirm-body').style.display ='none';
			document.getElementById('task-card-body').style.display ='block';
		 });

		// 削除ボタン
		$('#taskDeleteButton')
		.click(function() {
			swal({
				title: '削除しますか?',
				icon: 'warning',
		        buttons: {
		            cancel: 'いいえ',
		            ok: 'はい'
		        },
			})
			.then((willDelete) => {
				  if (willDelete) {
					  deleteTask();
				  } else {
					  swal('キャンセルされました');
				  }
			});
		});

		// 保存（変更）ボタン
		document.querySelector('#taskUpdateButton').addEventListener('click', function(event) {
			var form = $('#task-validation')
			if (form[0].checkValidity() === false) {
				event.preventDefault();
				event.stopPropagation();
				form.addClass('was-validated');
			} else {
				updateTask();
			}
		}, false);
	}
}

/**
 * タスク削除
 */
function deleteTask() {
	$.ajax({
		url : '/api/delete',
		type : 'post',
		dataType : 'text',
		data : $('#taskId').val(),
		contentType : 'application/json; charset=utf-8'
	}).then(function(data) {
		$(function() {
			try {
				var message = jQuery.parseJSON(data);
				$(function() {
					showMessage(message);
					$('#calendar-modal').modal('hide');
					refresh();
				});
			} catch (error) {
				$(function() {
					showMessage(null);
					$('#calendar-modal').modal('hide');
					refresh();
				})
			}
		})
	}, function() {
		$(function() {
			showMessage(null);
		})
	});
	$('#calendar-modal').modal('hide');
	refresh();
}

/**
 * タスク追加
 */
function addTask() {
	// 状況
	var tmpStatusId = null;
	if ($('#taskStatusId').val() !== 99 && $('#taskStatusId').val() !== "") {
		tmpStatusId = $('#taskStatusId').val();
	}
	// 種別
	var tmpTypeId = null;
	if ($('#taskTypeId').val() !== 99 && $('#taskTypeId').val() !== "") {
		tmpTypeId = $('#taskTypeId').val();
	}
	// リソース設定
	var resourceData = [{
		eventId : $('#taskId').val(),
		buildingTypeId : 3,
		buildingId : user.userId,
		parentResourceTypeId : 2,
		childResourceTypeId : tmpTypeId
	}];
	var eventData = {
			id : $('#taskId').val(),
			title : $('#taskTitle').val(),
			start : $('#taskKigenYmd').val() + 'T' + '00:00',
			end : $('#taskKigenYmd').val() + 'T' + '00:00',
			description : $('#taskContent').val(),
			allDay : true,
			editAuthId : 1,
			taskFlag : true,
			taskStatusId : tmpStatusId,
			eventVaildF : 1,
			version : 1,
			createUserId : user.userId,
			updateUserId : user.userId,
			resources : resourceData,
		};
	$.ajax({
		url : '/api/save',
		type : 'post',
		dataType : 'text',
		data : JSON.stringify(eventData),
		contentType : 'application/json; charset=utf-8'
	}).then(function(data) {
		$(function() {
			try {
				var message = jQuery.parseJSON(data);
				$(function() {
					showMessage(message);
					$('#calendar-modal').modal('hide');
					refresh();
				});
			} catch (error) {
				$(function() {
					showMessage(null);
					$('#calendar-modal').modal('hide');
					refresh();
				})
			}
		})
	}, function() {
		$(function() {
			$(function() {
				showMessage(null);
				$('#calendar-modal').modal('hide');
				refresh();
			})
		})
	});
	$('#calendar-modal').modal('hide');
	refresh();
}

/**
 * タスク変更
 */
function updateTask() {
	// 状況
	var tmpStatusId = null;
	if ($('#taskStatusId').val() != 99 && $('#taskStatusId').val() != "") {
		tmpStatusId = $('#taskStatusId').val()
	}
	// 種別
	var tmpTypeId = null;
	if ($('#taskTypeId').val() != 99 && $('#taskTypeId').val() !== "") {
		tmpTypeId = $('#taskTypeId').val();
	}
	// リソース設定
	var resourceData = [{
		eventId : $('#taskId').val(),
		buildingTypeId : 3,
		buildingId : user.userId,
		parentResourceTypeId : 2,
		childResourceTypeId : tmpTypeId
	}];
	var eventData = {
		id : $('#taskId').val(),
		title : $('#taskTitle').val(),
		start : $('#taskKigenYmd').val() + 'T' + '00:00',
		end : $('#taskKigenYmd').val() + 'T' + '00:00',
		description : $('#taskContent').val(),
		allDay : true,
		editAuthId : 1,
		taskFlag : true,
		taskStatusId : tmpStatusId,
		eventVaildF : 1,
		version : $('#taskVersion').val(),
		updateUserId : user.userId,
		resources : resourceData
	};
	$.ajax({
		url : '/api/update',
		type : 'post',
		dataType : 'text',
		data : JSON.stringify(eventData),
		contentType : 'application/json; charset=utf-8'
	}).then(function(data) {
		$(function() {
			try {
				var message = jQuery.parseJSON(data);
				$(function() {
					showMessage(message);
					$('#calendar-modal').modal('hide');
					refresh();
				});
			} catch (error) {
				$(function() {
					showMessage(null);
					$('#calendar-modal').modal('hide');
					refresh();
				})
			}
		})
	}, function() {
		$(function() {
			$(function() {
				showMessage(null);
				$('#calendar-modal').modal('hide');
				refresh();
			})
		})
	});
}