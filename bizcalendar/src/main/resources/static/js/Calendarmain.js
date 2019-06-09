// 選択日付
var day;
// 選択種別
var selectType = "0";

$(function() {$('#calendar').fullCalendar({
	schedulerLicenseKey : 'GPL-My-Project-Is-Open-Source',
	selectable : true,
	editable : false,
	navLinks : true,
	eventLimit : true,
	nextDayThreshold : '00:00:00',
	timezone : 'Asia/Tokyo',
	locale : 'ja',
	aspectRatio : 1.8,
	scrollTime : '00:00',
	minTime : '6:00',
	contentHeight : 900,
	refetchResourcesOnNavigate : true,
	resourceGroupField : 'building',
	header : {
		left : 'prev,next, today',
		center : 'title',
		right : 'month,agendaWeek,timelineDay,timelineYear'
	},
	views : {
		month : {
			titleFormat : 'YYYY.M'
		},
		week : {
			titleFormat : 'YYYY.M.D'
		},
		day : {
			titleFormat : 'YYYY.M.D'
		},
		year : {
			titleFormat : 'YYYY'
		}
	},
	resources : function(callback, start, end, timezone) {
		setResourceList(start.format('YYYY-MM-DD'), end.format('YYYY-MM-DD'), callback);
	},
	// イベントデータを読み込んだ時に発動
	eventDataTransform : function(event) {
		if (event.allDay) {
			event.end = moment(event.end).add(1, 'days')
		}
		return event;
	},
	events : function(start, end, timezone, callback) {
		setCalendarList(start.format('YYYY-MM-DD'), end.format('YYYY-MM-DD'), callback);
	},
	eventRender : function(event, element, view) {
		element.find('.fc-title').prepend('<span><i class="' + event.statusIcon+ '"></i></span>');
	},
	dayClick : function(d, a, j, v) {
		if (day === moment(d).format("YYYY-MM-DD")) {
			return false;
		} else {
			$("#calendar").fullCalendar("clientEvents",function(e) {
				if ((moment(d).format("YYYY-MM-DD") === moment(e.start).format("YYYY-MM-DD") ||
					moment(d).format("YYYY-MM-DD") === moment(e.end).format("YYYY-MM-DD") ||
					moment(d).isBetween(moment(e.start),moment(e.end)) === true) && e.eventVaildF === 1) {
					if ($('#' + e.id + '').length === 0) {
						$('#accordion').append(
							'<div class="'+ e.className+ '">'+ '<div class="card" id="'+ e.id+ '">'
								+ '<div class="card-header" role="tab">'
								+ '<h5 class="mb-0">'
									+ '<a class="collapsed text-body" data-toggle="collapse" href="#collapse'+ e.id+ '" role="button" aria-expanded="false">'
									+ '<span><i class="'+ e.statusIcon+ '"></i></span>'+ e.title+ '</a>'
								+ '</h5>'
								+ '</div>'
							+ '<div id="collapse'+ e.id+ '" class="collapse" role="tabpanel" data-parent="#accordion">'
								+ '<div class="card-body" id="card-body'+ e.id+ '">'

								/* イベント内容 */
								+ '<div class="container-fluid">'
									+ '<div class="calendarconfirmwidth">'
										+ '<div class="calendarconfirmcontainer">'
											+ '<input id ="scheduleId" type ="hidden">'
											+ '<label class="font-weight-bold calendarconfirmlabel">&nbsp;&nbsp;名前 :</label>'
											+ e.name
											+ '<hr>'
											+ '<label class="font-weight-bold calendarconfirmlabel calendarconfirmlabel_top">&nbsp;&nbsp;タイトル :</label>'
											+ e.title
											+ '<hr>'
											+ '<label class="font-weight-bold calendarconfirmlabel calendarconfirmlabel_top">&nbsp;&nbsp;日付 :</label>'
											+ moment(e.start).format("YYYY-MM-DD")+ "～"+ moment(e.end).format("YYYY-MM-DD")
											+ '<hr>'
											+ '<label class="font-weight-bold calendarconfirmlabel calendarconfirmlabel_top">&nbsp;&nbsp;時間 :</label>'
											+ moment(e.start).format("HH:mm")+ "～"+ moment(e.end).format("HH:mm")
											+ '<hr>'
											+ '<label class="font-weight-bold calendarconfirmlabel calendarconfirmlabel_top">&nbsp;&nbsp;コメント :</label>'
											+ e.description
											+ '<hr>'
											+ '<label class="font-weight-bold calendarconfirmlabel calendarconfirmlabel_top">&nbsp;&nbsp;状況 :</label>'
											+ e.eventStatus
											+ '<hr>'
											+ '<label class="font-weight-bold calendarconfirmlabel calendarconfirmlabel_top">&nbsp;&nbsp;種類 :</label>'
											+ e.eventType
											+ '<hr>'
											+ '<br>'
											+ '<button type="button" class="btn btn-outline btn-lg font-weight-bold calendaradd_applybutton calendaraddsquare_btn" data-toggle="modal" data-target="#addModal" data-whatever="@mdo" data-type="update" data-id="'+ e.id
											+ '" data-name="'+ e.name
											+ '" data-title="'+ e.title
											+ '" data-startymd="'+ moment(e.start).format("YYYY-MM-DD")
											+ '" data-endymd="'+ moment(e.end).format("YYYY-MM-DD")
											+ '" data-starthm="'+ moment(e.start).format("HH:mm")
											+ '" data-endhm="'+ moment(e.end).format("HH:mm")
											+ '" data-description="'+ e.description
											+ '">Update</button>'
											+ '&nbsp;&nbsp;'
											+ '<input type="button" class="btn btn-outline btn-lg font-weight-bold calendaradd_cancelbutton calendaraddsquare_btn" onclick="deleteSchedule();" value="Delete">'
										+ '</div>'
									+ '</div>'
								+ '</div>'
								/* イベント内容 */

								+ '</div>'
							+ '</div>'
							+ '</div>'
							+ '</div>')
							$('#scheduleId').val(e.id);
					}
				} else {
					$('#' + e.id + '').remove();
				}
			});
		}
			day = moment(d).format("YYYY-MM-DD");
		}
	});
});

/**
 * モーダル(編集画面)にイベントを渡す。
 */
$(function() {
	$('#addModal').on('shown.bs.modal', function(event) {
		var button = $(event.relatedTarget) // モーダルを呼び出すときに使われたボタンを取得
		var type = button.data('type') // data-whatever の値を取得
		$('#sampleForm').removeClass("was-validated");
		// modal.find('#operation').val(type);
		$(".alert").text("").removeAttr('style');

		if (type === 'update') {

			$(".alert").text("イベント編集画面");
			$('#registButton').hide();
			$('#updateButton').show();

			var id = button.data('id');
			var name = button.data('name');
			var title = button.data('title');
			var startymd = button.data('startymd');
			var endymd = button.data('endymd');
			var starthm = button.data('starthm');
			var endhm = button.data('endhm');
			var description = button.data('description');
			var modal = $(this); // モーダルを取得

			modal.find('#operation').val(type);
			modal.find('#eventId').val(id);
			modal.find('#inputName').val(name);
			modal.find('#inputTitle').val(title);
			modal.find('#inputYmdFrom').val(startymd);
			modal.find('#inputYmdTo').val(endymd);
			modal.find('#inputHmFrom').val(starthm);
			modal.find('#inputHmTo').val(endhm);
			modal.find('#inputContent').val(description);

		} else if (type === 'new') {

			$('#updateButton').hide();
			$('#registButton').show();
			$(".alert").text("イベント登録画面");
			$('#msg1').hide();
			$('#operation').val("");
			$('#ieventId').val("");
			$('#inputName').val("");
			$('#inputTitle').val("");
			$('#inputYmdFrom').val("");
			$('#inputYmdTo').val("");
			$('#inputHmFrom').val("");
			$('#inputHmTo').val("");
			$('#inputContent').val("");

		}
	});
});

/**
 * イベント情報取得
 */
function setCalendarList(startDate, endDate, callback) {
	var userId = document.getElementById("userId").value;
	var eventData = [];
	eventData = {
		userId : userId,
		start : startDate,
		className : selectType
	};
	$.ajax({
		type : 'post',
		dataType : 'text',
		async : true,
		cache : false,
		url : '/api/event',
		data : JSON.stringify(eventData),
		contentType : "application/json; charset=utf-8"
	}).then(function(data) {
		var obj = jQuery.parseJSON(data);
		var events = [];
		$.each(obj, function(index, value) {
			events.push({
				id : value['id'],
				resourceId : value['resourceId'],
				title : value['title'],
				start : value['start'],
				end : value['end'],
				description : value['description'],
				className : value['className'],
				eventVaildF : value['eventVaildF'],
				statusIcon : value['statusIcon'],
				name : value['name'],
				eventType : value['eventType'],
				eventStatus : value['eventStatus'],
				allday : false,
			});
		});
		callback(events);
	}, function() {
		alert("読み込み失敗");
	});
	return;
}

/**
 * リソース情報取得
 */
function setResourceList(startDate, endDate, callback) {
	var userId = document.getElementById("userId").value;
	var eventData = [];
	eventData = {
		userId : userId,
		start : startDate,
		className : selectType
	};
	$.ajax({
		type : 'post',
		dataType : 'text',
		async : true,
		cache : false,
		url : '/api/resource',
		data : JSON.stringify(eventData),
		contentType : "application/json; charset=utf-8"
	}).then(function(data) {
		var obj = jQuery.parseJSON(data);
		var resources = [];
		$.each(obj, function(index, value) {
			resources.push({
				id : value['id'],
				building : value['building'],
				title : value['title'],
				eventColor : value['eventColor']
			});
		});
		callback(resources);
	}, function() {
		alert("読み込み失敗");
	});
	return;
}

/**
 * 予定入力フォームの登録ボタンクリックイベント.
 */
function registSchedule() {
	var status = $('[name="customRadioInline1"]:checked').val(); // 状況
	var eventData = [];
	eventData = {
		title : $('#inputTitle').val(),
		start : $('#inputYmdFrom').val() + "T" + $('#inputHmFrom').val(),
		end : $('#inputYmdTo').val() + "T" + $('#inputHmTo').val(),
		description : $('#inputContent').val(),
		className : $('[name="customRadio"]:checked').val(), // 種類
	};
	$.ajax({
		url : '/api/save',
		type : "post",
		dataType : "json",
		data : JSON.stringify(eventData),
		contentType : "application/json; charset=utf-8",
	}).then(function(data) {
		// 再描画
		refresh();
		alert("予定を更新しました。");
	}, function() {
		alert("読み込み失敗");
	});
	return;
}

/**
 * 予定更新ボタンクリックイベント.
 */
function updateSchedule() {
	// var type = $('[name="type"]:checked').val(); //種類
	// var status = $('[name="status"]:checked').val(); //状況
	// var checkResourceId = checkResource(type,status);
	// alert($('[name="customRadio"]:checked').val());
	var eventData = [];
	eventData = {
		id : $('#eventId').val(),
		title : $('#inputTitle').val(),
		start : $('#inputYmdFrom').val() + "T" + $('#inputHmFrom').val(),
		end : $('#inputYmdTo').val() + "T" + $('#inputHmTo').val(),
		description : $('#inputContent').val(),
		className : $('[name="customRadio"]:checked').val(),
	// resourceId: checkResourceId,
	};
	$.ajax({
		url : '/api/update',
		type : "post",
		dataType : "json",
		data : JSON.stringify(eventData),
		contentType : "application/json; charset=utf-8",
	}).then(function(data) {
		// 再描画
		refresh();
		alert("予定を更新しました。");
	}, function() {
		alert("読み込み失敗");
	});
	return;
}

/**
 * 予定削除ボタンクリックイベント.
 */
function deleteSchedule() {
	var eventData = [];
	eventData = {
		id : $('#scheduleId').val()
	};
	$.ajax({
		url : '/api/delete',
		type : "post",
		dataType : 'text',
		data : eventData["id"],
		contentType : "application/json; charset=utf-8",
	}).then(function() {
		// 再描画
		refresh();
		alert("予定を更新しました。");
	}, function() {
		alert("読み込み失敗");
	});
}

/**
 * 種別ボタンクリックイベント
 */
function typeSelect(value) {
	selectType = String(value);
	refresh();
}

/**
 * イベントの再取得
 */
function refresh() {
	$('#calendar').fullCalendar('refetchEvents');
	$('#calendar').fullCalendar('refetchResources');
	$('.card').remove();
	day = "";
}

/**
 * searchボタンを押下後、日付検索
 */
$(function() {
	$("#search-button").on("click", function() {
		var date = $("#input-date").val();
		$('#calendar').fullCalendar('gotoDate', date);
	});
});
