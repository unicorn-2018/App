/**
 * イベント編集画面
 */
function showEventEdit() {
	// アイコンの設定
	const iconData = [];
	var tmpIconTitle = null;
	for (var i = 0; i < icons.length; i++) {
		var iconDataAttrs = [];
	    // id
		iconDataAttrs.push({title:'dataattr1', data:icons[i].code});
		// アイコンクラス
		iconDataAttrs.push({title:'dataattr2', data:icons[i].naiyo1});
		// アイコン名称
		iconDataAttrs.push({title:'dataattr3', data:icons[i].naiyo2});
	    // json追加
		var iconTitle = '<span><i class="' + icons[i].naiyo1 + '"></i></span>' + ' '  + icons[i].naiyo2;
		var jsonIconData = {dataAttrs:iconDataAttrs, title:iconTitle};
		iconData.push(jsonIconData);
		// 変更の場合
		if ($('#eventIconId').val() != "") {
			if (icons[i].code == $('#eventIconId').val()) {
				tmpIconTitle = iconTitle;
			}
		// 追加の場合または、変更時ステータスが未着手の場合
		} else {
			if (icons[i].code == 99) {
				tmpIconTitle = icons[i].naiyo2;
			}
		}
	}
	var iconOptions = {
	    title : tmpIconTitle,
	    data: iconData,
	    maxHeight: 200,
	    selectChildren: false,
	    multiSelect : false,
	    clickHandler: function(element) {
		    var iconId = $(element).data('dataattr1');
		    var iconClass = $(element).data('dataattr2');
		    var iconName = $(element).data('dataattr3');
		    $('#icon').SetTitle('<span><i class="' + iconClass + '"></i></span>' + ' '  + iconName);
		    $('#eventIconId').val(iconId);
	    }
	}
	$('#icon').DropDownTree(iconOptions);

	// 種別の設定
	const eventTypeData = [];
	var tmpEventTypeTitle = null;
	for (var i = 0; i < eventTypes.length; i++) {
		var typeDataAttrs = [];
	    // id
		typeDataAttrs.push({title:'dataattr1', data:eventTypes[i].code});
		// 種別色
		typeDataAttrs.push({title:'dataattr2', data:eventTypes[i].naiyo1});
		// 種別名称
		typeDataAttrs.push({title:'dataattr3', data:eventTypes[i].naiyo2});
	    // json追加
		var typeTitle = '<span style="color:' + eventTypes[i].naiyo1 + '"><i class="fa fa-square" aria-hidden="true"></i></span>' + ' ' + eventTypes[i].naiyo2;
		var jsonTypeData = {dataAttrs:typeDataAttrs, title:typeTitle};
		eventTypeData.push(jsonTypeData);
		// 変更の場合
		if ($('#eventTypeId').val() != "") {
			if (eventTypes[i].code == $('#eventTypeId').val()) {
				tmpEventTypeTitle = typeTitle;
				$('#eventTypeColor').val(eventTypes[i].naiyo2);
			}
		// 追加の場合または変更時ステータスが未着手の場合
		} else {
			if (eventTypes[i].code == 99) {
				tmpEventTypeTitle = typeTitle;
			}
		}
	}
	var typeOptions = {
	    title : tmpEventTypeTitle,
	    data: eventTypeData,
	    maxHeight: 200,
	    selectChildren: false,
	    multiSelect : false,
	    clickHandler: function(element) {
		    var typeId = $(element).data('dataattr1');
		    var typeColor = $(element).data('dataattr2');
		    var typeName = $(element).data('dataattr3');
		    $('#eventType').SetTitle('<span style="color:' + typeColor + '"><i class="fa fa-square" aria-hidden="true"></i></span>' + ' ' + typeName);
		    $('#eventTypeId').val(typeId);
	    }
	}
	$('#eventType').DropDownTree(typeOptions);

	// 開始日
	(function() {
		$('#inputYmdFrom')
		.datepicker({
			dateFormat : 'yy-mm-dd',
			showButtonPanel: true,
			changeMonth: true,
			changeYear: true,
			todayHighlight: true,
			onSelect: function(selected) {
				$('#inputYmdTo').datepicker('option','minDate', selected);
				if ($('#inputYmdFrom').val() == $('#inputYmdTo').val()) {
					if ($('#inputHmTo').val() !== "" && $('#inputHmFrom').val() > $('#inputHmTo').val()) {
						$('#inputHmFrom').val($('#inputHmTo').val());
					}
					if ($('#inputHmFrom').val() !== "" && $('#inputHmFrom').val() > $('#inputHmTo').val()) {
						$('#inputHmTo').val($('#inputHmFrom').val());
					}
				}
			}
		});
		// 終了日
		$('#inputYmdTo')
		.datepicker({
			dateFormat : 'yy-mm-dd',
			showButtonPanel: true,
			changeMonth: true,
			changeYear: true,
			todayHighlight: true,
			onSelect: function(selected) {
				$('#inputYmdFrom').datepicker('option','maxDate', selected);
				if ($('#inputYmdFrom').val() == $('#inputYmdTo').val()) {
					if ($('#inputHmTo').val() !== "" && $('#inputHmFrom').val() > $('#inputHmTo').val()) {
						$('#inputHmFrom').val($('#inputHmTo').val());
					}
					if ($('#inputHmFrom').val() !== "" && $('#inputHmFrom').val() > $('#inputHmTo').val()) {
						$('#inputHmTo').val($('#inputHmFrom').val());
					}
				}
			}
		});
	})();

	// 開始日・終了日
	$('#inputYmdFrom, #inputYmdTo')
	// フォーカス時、読み取り専用
	.focusin(function(e) {
		$(this).attr('readonly', true);
	})
	// フォーカスアウト時、読み取り専用解除
	.focusout(function(e) {
		$(this).attr('readonly', false);
	});

	// 開始時刻・終了時刻
	$('#inputHmFrom, #inputHmTo')
	// フォーカスアウト時、時刻判定
	.focusout(function(e) {
		if ($('#inputYmdFrom').val() == $('#inputYmdTo').val()) {
			if ($('#inputHmTo').val() !== "" && $('#inputHmFrom').val() > $('#inputHmTo').val()) {
				$('#inputHmFrom').val($('#inputHmTo').val());
			}
			if ($('#inputHmFrom').val() !== "" && $('#inputHmFrom').val() > $('#inputHmTo').val()) {
				$('#inputHmTo').val($('#inputHmFrom').val());
			}
		}
	});

	var inputHmFrom = null;
	var inputHmTo = null;
	// 終日チェックボックス
	$('#alldayCheck')
	.click(function() {
		// 終日チェックボックスがOFFの場合
		if (!document.getElementById('alldayCheck').checked) {
			// 入力可能
			$('#inputHmFrom').prop('disabled', false);
			$('#inputHmFrom').val(inputHmFrom);
			$('#inputHmTo').prop('disabled', false);
			$('#inputHmTo').val(inputHmTo);
		// 終日チェックボックスがONの場合
		} else {
			if ($('#inputHmFrom').val() != null) {
				inputHmFrom = $('#inputHmFrom').val();
			}
			if ($('#inputHmTo').val() != null) {
				inputHmTo = $('#inputHmTo').val();
			}
			// 入力不可能
			$('#inputHmFrom').prop('disabled', true);
			$('#inputHmFrom').val("");
			$('#inputHmTo').prop('disabled', true);
			$('#inputHmTo').val("");
		}
	});

	var shareResource = [];
	// 新規の場合
	if ($('#eventId').val() == 0) {
		// イベント編集画面を表示
		document.getElementById('event-card-confirm-body').style.display ='none';
		document.getElementById('event-card-body').style.display ='block';

		// 共有先の初期値
		shareResource = ['3:'+ user.userId];

		// 追加ボタン
		document.querySelector('#eventAddButton').addEventListener('click', function(event) {
			var form = $('#event-validation')
			if (form[0].checkValidity() === false) {
				event.preventDefault();
				event.stopPropagation();
				form.addClass('was-validated');
			} else {
				addEvent();
			}
		}, false);
	// 変更の場合
	} else {
		// イベント参照画面を表示
		document.getElementById('event-card-confirm-body').style.display ='block';
		document.getElementById('event-card-body').style.display ='none';

		// 共有先の初期値
		shareResource = document.getElementById('shareResource').value.split(',');

		// 編集ボタン
		$('#eventEditButton')
		.click(function() {
			document.getElementById('event-card-confirm-body').style.display ='none';
			document.getElementById('event-card-body').style.display ='block';
		 });

		// 削除ボタン
		$('#eventDeleteButton')
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
					deleteEvent();
				} else {
				    swal('キャンセルされました');
				}
			});
		 });

		// 変更ボタン
		document.querySelector('#eventUpdateButton').addEventListener('click', function(event) {
			var form = $('#event-validation')
			if (form[0].checkValidity() === false) {
				event.preventDefault();
				event.stopPropagation();
				form.addClass('was-validated');
			} else {
				updateEvent();
			}
		}, false);
	}

	// 共有先の設定
	var teamData = [];
	for (var i = 0; i < groups.length; i++) {
		var memberData = [];
		for (var j = 0; j < members.length; j++) {
		    var memberDataAttrs = [];
		    if (groups[i].groupId === members[j].groupId ) {
		        //id
		        memberDataAttrs.push({title:'dataattr1', data:'3:'+ members[j].userId});
		        memberDataAttrs.push({title:'dataattr2', data:members[j].name});
		        //json追加
		        memberData.push({title:members[j].name, dataAttrs:memberDataAttrs});
		    }
		}
		var teamDataAttrs = [];
		// id
		teamDataAttrs.push({title:'dataattr1', data:'2:'+ groups[i].groupId});
		teamDataAttrs.push({title:'dataattr2', data:groups[i].groupName});
		// json追加
		teamData.push({title:groups[i].groupName, dataAttrs:teamDataAttrs, data:memberData});
	}
	var publicDataAttrs = [];
	publicDataAttrs.push({title:'dataattr1', data:'1:1'});
	publicDataAttrs.push({title:'dataattr2', data:'全体'});
	const shareData = [
		{title:'全体', dataAttrs:publicDataAttrs, data:teamData}
	]
	var shareOptions = {
		title : '共有先を選択',
		data: shareData,
		maxHeight: 200,
		selectChildren: false,
		multiSelect : true,
		closedArrow: '<i class="fa fa-plus-square fa-lg" aria-hidden="true"></i>',
		openedArrow: '<i class="fa fa-minus-square fa-lg" aria-hidden="true"></i>',
		checkHandler: function(element) {
			$('#share').SetTitle($(element).find('a').first().text());
		}
	}
	$('#share').DropDownTree(shareOptions);
	var shareTitle = "";
	var shareResourceName = "";
	$('#share').find('li').each(function(index, element) {
		if (shareResource.length > 0) {
			for (i = 0; i < shareResource.length; i++) {
				if ( $(this).data('dataattr1') === shareResource[i]) {
					$(this).find('i').first().removeClass('fa-square-o');
					$(this).find('i').first().addClass('fa-check-square-o');
					if (shareTitle === "") {
						shareTitle = $(this).data('dataattr2');
					}
					shareResourceName += '<span class="badge badge-secondary">' + $(this).data('dataattr2') + '</span> ';
				}
			}
		}
	});
	if (shareResource.length > 1) {
		shareTitle += ' 他';
		shareTitle += shareResource.length -1;
		shareTitle += '件選択';
	}
	$('#share').SetTitle(shareTitle);
	$('#shareResourceName').html(shareResourceName);
}

/**
 * イベント追加
 */
function addEvent() {
	// 開始日
	var tmpStart = null;
	if ($('#inputHmFrom').val() == "") {
		tmpStart = $('#inputYmdFrom').val() + 'T' + '00:00';
	} else {
		tmpStart = $('#inputYmdFrom').val() + 'T' + $('#inputHmFrom').val();
	}
	// 終了日
	var tmpEnd = null;
	if ($('#inputYmdTo').val() == "") {
		$('#inputYmdTo').val($('#inputYmdFrom').val());
	}
	if ($('#inputHmTo').val() == "") {
		tmpEnd = $('#inputYmdTo').val() + 'T' + '00:00';
	} else {
		tmpEnd = $('#inputYmdTo').val() + 'T' + $('#inputHmTo').val();
	}
	// 終日
	if ($('#inputHmFrom').val() == "" && $('#inputHmTo').val() == "") {
		$('#alldayCheck').val(true);
	} else {
		$('#alldayCheck').val(false);
	}
	// アイコン
	var tmpIconId = null;
	if ($('#eventIconId').val() != 99 && $('#eventIconId').val() != "") {
		tmpIconId = $('#eventIconId').val();
	}
	// 種別
	var tmpTypeId = null;
	if ($('#eventTypeId').val() != 99 && $('#eventTypeId').val() != "") {
		tmpTypeId = $('#eventTypeId').val();
	}
	// 共有先
	var shareSelectArry = $('#share').GetSelected();
	var resourceData = [];
	if (shareSelectArry.length !== 0) {
		for (var i = 0; i < shareSelectArry.length; i++) {
			resourceData.push({
				buildingTypeId : shareSelectArry[i].substr(0, 1),
				buildingId : shareSelectArry[i].substr(2),
				parentResourceTypeId : '1',
				childResourceTypeId : tmpTypeId
			});
		}
	} else {
		resourceData.push({
			buildingTypeId : 3,
			buildingId : user.userId,
			parentResourceTypeId : '1',
			childResourceTypeId : $('#eventTypeId').val()
		});
	}
	var eventData = {
		title : $('#inputTitle').val(),
		start : tmpStart,
		end : tmpEnd,
		description : $('#inputContent').val(),
		allDay : $('#alldayCheck').val(),
		editAuthId : $('[name="editAuthId"]:checked').val(),
		eventIconId : tmpIconId,
		eventVaildF : 1,
		version : 1,
		createUserId : user.userId,
		updateUserId : user.userId,
		resources : resourceData
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
			$('#calendar-modal').modal('hide');
			refresh();
		})
	}, function() {
		$(function() {
			showMessage(message);
			$('#calendar-modal').modal('hide');
			refresh();
		})
	});
}

/**
 * イベント変更
 */
function updateEvent() {
	// 開始日
	var tmpStart = null;
	if ($('#inputHmFrom').val() == "") {
		tmpStart = $('#inputYmdFrom').val() + 'T' + '00:00';
	} else {
		tmpStart = $('#inputYmdFrom').val() + 'T' + $('#inputHmFrom').val();
	}
	// 終了日
	var tmpEnd = null;
	if ($('#inputYmdTo').val() == "") {
		$('#inputYmdTo').val($('#inputYmdFrom').val());
	}
	if ($('#inputHmTo').val() == "") {
		tmpEnd = $('#inputYmdTo').val() + 'T' + '00:00';
	} else {
		tmpEnd = $('#inputYmdTo').val() + 'T' + $('#inputHmTo').val();
	}
	// 終日
	if ($('#inputHmFrom').val() == "" && $('#inputHmTo').val() == "") {
		$('#alldayCheck').val(true);
	} else {
		$('#alldayCheck').val(false);
	}
	// アイコン
	var tmpIconId = null;
	if ($('#eventIconId').val() != 99 && $('#eventIconId').val() != "") {
		tmpIconId = $('#eventIconId').val();
	}
	// 種別
	var tmpTypeId = null;
	if ($('#eventTypeId').val() != 99 && $('#eventTypeId').val() != "") {
		tmpTypeId = $('#eventTypeId').val();
	}
	// 共有先取得
	var shareSelectArry = $('#share').GetSelected();
	var resourceData = [];
	if (shareSelectArry.length !== 0) {
		for (var i = 0; i < shareSelectArry.length; i++) {
			resourceData.push({
				eventId : $('#eventId').val(),
				buildingTypeId : shareSelectArry[i].substr(0, 1),
				buildingId : shareSelectArry[i].substr(2),
				parentResourceTypeId : 1,
				childResourceTypeId : tmpTypeId
			});
		}
	};
	var eventData = {
			id : $('#eventId').val(),
			title : $('#inputTitle').val(),
			start : tmpStart,
			end : tmpEnd,
			description : $('#inputContent').val(),
			allDay : $('#alldayCheck').val(),
			editAuthId : $('[name="editAuthId"]:checked').val(),
			eventIconId : tmpIconId,
			eventVaildF : 1,
			version : $('#eventVersion').val(),
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
	}, function() {
		$(function() {
			showMessage(message);
			$('#calendar-modal').modal('hide');
			refresh();
		})
	});
}

/**
 * イベント削除
 */
function deleteEvent() {
	$.ajax({
		url : '/api/delete',
		type : 'post',
		dataType : 'text',
		data : $('#eventId').val(),
		contentType : 'application/json; charset=utf-8',
	}).then(function(data) {
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
	}, function() {
		$(function() {
			showMessage(null);
			$('#calendar-modal').modal('hide');
			refresh();
		})
	});
}