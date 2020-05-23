/*★★★★★★★★ 共通メソッド ★★★★★★★★*/
/**
 * イベントの再取得
 */
function refresh() {
	$('#calendar').fullCalendar('refetchEvents');
	$('#calendar').fullCalendar('refetchResources');
}

/**
 * モーダルを表示.
 */
function showModal(e, url) {
	var events = [];
	$.each(e, function(index, value) {
		events.push({
			// イベントID（画面表示用から置き換え）
			id : value['idParam'],
			// リソースID
			resourceId : value['resourceId'],
			// タイトル（画面表示用から置き換え）
			title : value['title'],
			// 開始日（タスクの場合期限日）
			start : value['start'],
			// 終了日（画面表示用から置き換え）
			end : value['endParam'],
			// コメント
			description : value['description'],
			// 終日フラグ（0:終日でない（時間表示） 1:終日（時間非表示））
			allDay : value['allDay'],
			// 編集権限ID（0:全ユーザー 1:作成者のみ）
			editAuthId : value['editAuthId'],
			// 編集権限フラグ（0:編集権限ID＝1 かつ ログインID≠作成者ID 1:0の条件以外）
			editAuthFlag : value['editAuthFlag'],
			// タスクフラグ（0:イベント 1:タスク）
			taskFlag : value['taskFlag'],
			// タスク期限フラグ（0:システム日付≦期限日 1:システム日付＞期限日）
			taskKigenFlag : value['taskKigenFlag'],
			// イベントアイコンID（E_002．コード）
			eventIconId : value['eventIconId'],
			// イベントアイコンクラス（E_002．内容１）
			eventIconClass : value['eventIconClass'],
			// イベント種別ID（イベントの場合:E_001．コード タスクの場合:T_002．コード）
			eventTypeId : value['eventTypeId'],
			// イベント種別色（イベントの場合:E_001．内容１ タスクの場合:T_002．内容１）
			eventTypeColor : value['eventTypeColor'],
			// タスク状況ID（T_001．コード）
			taskStatusId : value['taskStatusId'],
			// タスク状況名（T_001．内容１）
			taskStatusName : value['taskStatusName'],
			// 作成者名
			createName : value['createName'],
			// 更新者名
			updateName : value['updateName'],
			// 選択区分（1:タスク一覧 null:タスク一覧以外）
			selectKubun : value['selectKubun'],
			// ビルディングIDパラメータ（イベントIDと紐づくリソース．ビルディング種別ID：リソース．ビルディングID）
			// ※イベントの共有先のIDと紐づく
			buildingIdParam : value['buildingIdParam'],
			// バージョン
			version : value['version'],
			// ログインIDパラメータ
			loginIdParam : user.userId
		});
	});
	$.ajax({
		type : 'post',
		url : url,
		cache : false,
		async : true,
		dataType : 'text',
		data :JSON.stringify(events),
		contentType : 'application/json; charset=utf-8'
	}).then(function(data) {
		// html出力
		$('#modalBody').html(data);
		// モーダルを表示
		$('#calendar-modal').modal();

/*		if (document.getElementById('event-table')) {
			showEventList();
		}*/

		// タスクリスト画面を表示する場合
		if (document.getElementById('task-table')) {
			showTaskList();
		}

		// イベント編集画面を表示を表示する場合
		if (document.getElementById('event-card-body')) {
			showEventEdit();
		}

		// タスク編集画面を表示を表示する場合
		if (document.getElementById('task-card-body')) {
			showTaskEdit();
		}
	},function() {
		$(function() {
			showMessage(null);
		})
	});
}

/**
 * メッセージ表示
 */
function showMessage(message) {
	if (message != null) {
		swal({
			title: message.title,
			text: message.text,
			icon: message.icon,
			timer: message.timer,
			buttons: message.buttons
		});
	} else {
		swal({
			title: "読み込みが失敗しました",
			text: "３秒後にアラートが自動的に閉じます",
			icon: "error",
			timer: 3000,
			buttons: false
		});
	}
}