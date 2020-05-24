/**
 * メイン画面
 */
$(function() {
	// フルカレンダー
	$('#calendar').fullCalendar({
		// ライセンスキー
		schedulerLicenseKey : 'GPL-My-Project-Is-Open-Source',
		// インジケーター
		nowIndicator : true,
		// 日付選択有無
		selectable : true,
		// イベント移動有無
		editable : false,
		// イベント上限有無
		eventLimit : true,
		// 日付境界時刻
		nextDayThreshold : '00:00:00',
		// タイムゾーン
		timezone : 'Asia/Tokyo',
		//aspectRatio : 1.8,
		// 初期表示時刻
		scrollTime : '00:00',
		// 最小表示時刻
		minTime : '6:00',
		// カレンダーの高さ
		contentHeight : 'auto',
		// 表示切替時にリソース再取得
		refetchResourcesOnNavigate : true,
		// リソースの表示形式
		resourceGroupField : 'building',
		// リソースのラベル
		resourceLabelText : '分類',
		//handleWindowResize: true,
		navLinks: true,
		resourceAreaWidth: '30%',
		filterResourcesWithEvents : true,
		noEventsMessage : '表示対象が存在しません',
		// ヘッダー
		header : {
			left : 'prev,next,today',
			center : 'title',
			right : 'month,agendaWeek,listDay,timelineYear'
		},
		// 時刻フォーマット
		smallTimeFormat:  'H:mm',
		views : {
			// 月表示
			month : {
				timeFormat: 'H:mm',
				titleFormat : 'YYYY.M'
			},
			// 週表示
			week : {
				columnFormat:'ddd (D)',
				titleFormat : 'YYYY.M.D'
			},
			// 日表示
			list : {
				titleFormat : 'YYYY.M.D',
				buttonText:'日'
			},
			// 年表示
			year : {
				columnFormat:'ddd (D)',
				titleFormat : 'YYYY',
				buttonText:'年'
			}
		},
		// イベント
		events : function(start, end, timezone, callback) {
			setCalendarList(start.format('YYYY-MM-DD'), end.format('YYYY-MM-DD'), callback);
		},
		// リソース
		resources : function(callback, start, end, timezone) {
			setResourceList(start.format('YYYY-MM-DD'), end.format('YYYY-MM-DD'), callback);
		},
		// イベント取得時再編集
		eventRender : function(event, element, view) {
			// クライアントイベント
			element.find('.fc-title').prepend('<span><i class="' + event.eventIconClass+ '"></i></span>');
			element.find('.fc-list-item-title').prepend('<span><i class="' + event.eventIconClass+ '"></i></span>');
			// 祝日の場合、背景色をピンクにする
			if (event.resourceId === '1:0:0') {
				$('.fc-day[data-date="' + event.start.format('YYYY-MM-DD') + '"]').css('background', '#ffeaea');
			}
			// 年表示以外の場合、枝番が付与されたイベントは表示しない
			if (event.idParam !== 0 && view.name !== 'timelineYear') {
				if (event.id !== event.idParam) {
					return false;
				}
			}

		},
		// 表示切替時に再編集
		viewRender : function(v, e) {
		},
		// イベントクリック時に発動
		eventClick: function(e, j, v) {
			if (e.idParam !== 0 && v.name !== 'month') {
				var eventList = [];
				eventList.push(e);
				if (e.taskFlag) {
					// タスク編集画面を表示
					showModal(eventList, '/taskedit');
				} else {
					// イベント編集画面を表示
					showModal(eventList, '/eventedit');
				}
			}
		},
		// 日付リンククリック時に発動
		navLinkDayClick: function(d, j) {
			selectDay = moment(d).format('YYYY-MM-DD');
			$('#calendar').fullCalendar('changeView', 'listDay', d);
		},
		// 日付クリック時に発動
		dayClick : function(d, a, j, v) {
			selectDay = moment(d).format('YYYY-MM-DD');
		}
	});

	// モーダルを閉じた時に発動
	$('#calendar-modal').on('hidden.bs.modal', function() {
		selectDay = null;
	});

	// ヘッダーメニューの開閉
	$('.menu-trigger').on('click',function() {
		if ($(this).hasClass('active')) {
		    $(this).removeClass('active');
		    $('nav').removeClass('open');
		    $('.overlay').removeClass('open');
		} else {
		    $(this).addClass('active');
		    $('nav').addClass('open');
		    $('.overlay').addClass('open');
		}
	});
	$('.overlay').on('click',function() {
		if ($(this).hasClass('open')) {
			// モーダルが開いない場合、ヘッダーを閉じる
			if (!document.getElementById('calendar-modal')) {
				$(this).removeClass('open');
				$('.menu-trigger').removeClass('active');
				$('nav').removeClass('open');
			}
		}
	});
});

/**
 * イベント情報取得
 */
function setCalendarList(startDate, endDate, callback) {
	var eventData = [];
	if (filterFlag) {
		eventData = {
			loginIdParam : user.userId,
			userIdParam : filterUserId,
			groupIdParam : filterGroupId,
			start : startDate,
			end : endDate
		};
	} else {
		eventData = {
			loginIdParam : user.userId,
			userIdParam : user.userId,
			groupIdParam : user.groupId,
			start : startDate,
			end : endDate
		};
	}

	$.ajax({
		type : 'post',
		dataType : 'text',
		async : true,
		cache : false,
		url : '/api/event',
		data : JSON.stringify(eventData),
		contentType : 'application/json; charset=utf-8'
	}).then(function(data) {
		try {
			var tmpId = 0;
			var idCnt = 1;
			var events = jQuery.parseJSON(data);
			for (var i = 0; i < events.length; i++) {
				// イベントIDが重複した場合、表示用にイベントIDに枝番をつける
				if (tmpId == events[i].id) {
					events[i].idParam = events[i].id;
					events[i].id = events[i].id + "-" + idCnt;
					idCnt += 1;
				} else {
					events[i].idParam = events[i].id;
					idCnt = 1;
				}
				// 終日フラグがTRUEの場合、表示用に終了日に１日加算する
				if (events[i].allDay) {
					events[i].endParam = events[i].end;
					events[i].end = moment(events[i].end).add(1, 'days');
				} else {
					events[i].endParam = events[i].end;
				}
				tmpId = events[i].idParam
			}
			callback(events);
		} catch (error) {
			$(function() {
				showMessage(null);
			})
		}
	}, function() {
		$(function() {
			showMessage(null);
		})
	});
	return;
}

/**
 * リソース情報取得
 */
function setResourceList(startDate, endDate, callback) {
	$.ajax({
		type : 'post',
		dataType : 'text',
		async : true,
		cache : false,
		url : '/api/resource',
		contentType : 'application/json; charset=utf-8'
	}).then(function(data) {
		try {
			callback(jQuery.parseJSON(data));
		} catch (error) {
			$(function() {
				showMessage(null);
			})
		}
	}, function() {
		$(function() {
			showMessage(null);
		})
	});
	return;
}