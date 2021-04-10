/**
 * メニュー画面
 */
$(function() {
	// 日付選択
	(function() {
		var old_fn = $.datepicker._updateDatepicker;
		$.datepicker._updateDatepicker = function(inst) {
		old_fn.call(this, inst);
		var buttonPane = $(this).datepicker("widget").find(".ui-datepicker-buttonpane");
		var buttonHtml = "<button type='button' class='ui-datepicker-clean ui-state-default ui-priority-primary ui-corner-all'>削除</button>";
		// ヘッダーの日付選択以外は削除ボタン追加
		if (inst.id !== 'input-date'){
			$(buttonHtml).appendTo(buttonPane).click(
				function(ev) {
					$.datepicker._clearDate(inst.input);
				});
			}
		}
	})();
	$('#input-date').datepicker({
		dateFormat : 'yy-mm-dd',
		showOn : 'both',
		buttonText: '日付選択',
		autoclose: true,
		showButtonPanel: true,
		changeMonth: true,
		changeYear: true,
		todayHighlight: true,
		onSelect: function(dateText) {
			$('#calendar').fullCalendar('gotoDate', dateText);
		}
	});
	$('.ui-datepicker-trigger').addClass("btn btn-outline-light btn-lg btn-block");

	// 表示対象選択
	var teamData = [];
	for (var i = 0; i < groups.length; i++) {
		var memberData = [];
		for (var j = 0; j < members.length; j++) {
			var memberDataAttrs = [];
			if(groups[i].groupId === members[j].groupId ){
				// id
				memberDataAttrs.push({title:"dataattr1", data:"3:"+ members[j].userId});
				// json追加
				memberData.push({title:members[j].name, dataAttrs:memberDataAttrs});
			}
		}
		var teamDataAttrs = [];
		// id
		teamDataAttrs.push({title:"dataattr1",data:"2:"+ groups[i].groupId});
		// json追加
		teamData.push({title:groups[i].groupName, dataAttrs:teamDataAttrs, data:memberData});
	}
	var selectOptions = {
		    title : "表示対象を選択",
		    data: teamData,
		    maxHeight: 300,
		    selectChildren: false,
		    checkHandler: function(element) {
		        $("#selectEvent").SetTitle($(element).find("a").first().text());
		        selectEvent($('#selectEvent').GetSelected());
		    },
		    closedArrow: '<i class="fa fa-plus-square fa-lg" aria-hidden="true"></i>',
		    openedArrow: '<i class="fa fa-minus-square fa-lg" aria-hidden="true"></i>',
		    multiSelect: true
	}
	$("#selectEvent").DropDownTree(selectOptions);

	// 初期選択値
	var shareResource = [];
	shareResource.push("2:"+ user.groupId);
	shareResource.push("3:"+ user.userId);
	$("#selectEvent").find('li').each(function(index, element) {
		if ( $(this).data('dataattr1') === shareResource[0] || $(this).data('dataattr1') === shareResource[1]) {
			$(this).find('i').first().removeClass("fa-square-o");
			$(this).find('i').first().addClass("fa-check-square-o");
		}
	});

	// スケジュール追加ボタンクリック時に発動
	$('#new-show').click(function (e) {
		var eventList = [];
		eventList.push({
			idParam : 0,
			start:selectDay,
			endParam:selectDay
		});
		// スケジュール編集画面を表示
		showModal(eventList, '/api/edit');
	});

	// タスク一覧ボタンクリック時に発動
	$('#task-show').click(function (e) {
		var eventList = [];
		eventList.push({
			loginIdParam : user.userId,
			selectKubun : 1
		});
		// タスク編集画面を表示
		showModal(eventList, '/api/tasklist');
	});
});

/**
* 表示対象選択
*/
function selectEvent(value) {
	// メンバー
	filterUserId = null;
	// グループ
	filterGroupId = null;
	if (value.length !== 0) {
		var userIdPrmArry = [];
		var groupIdPrmArry= [];
		for (var i = 0; i < value.length; i++) {
			// グループの場合
			if ("2" === value[i].substr(0, 1)) {
				groupIdPrmArry.push(value[i].substr(2));
			// メンバーの場合
			} else if ("3" === value[i].substr(0, 1)) {
				userIdPrmArry.push(value[i].substr(2));
			}
		}
		if (userIdPrmArry.length !== 0) {
			filterUserId = userIdPrmArry.join(',');
		}
		if (groupIdPrmArry.length !== 0) {
			filterGroupId = groupIdPrmArry.join(',');
		}
	    filterFlag = true
	} else {
		filterFlag = false
	}
	refresh();
}

