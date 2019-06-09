package jp.co.bizrefine.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ajd4jp.AJD;
import ajd4jp.AJDException;
import ajd4jp.Holiday;
import ajd4jp.Month;
import jp.co.bizrefine.domain.model.Event;

public class MakeCalendarUtil {

	public static List<Event> getHoliDay(String date) throws AJDException {
		List<Date> monthStartDate = getStartDate(date);
		// 年月を設定
		List<Month> month1 = new ArrayList<Month>();
		for (Date date1 : monthStartDate) {
			String stringStartDate = new SimpleDateFormat("yyyyMMdd").format(date1);
			int year = Integer.parseInt(stringStartDate.substring(0, 4));
			int month = Integer.parseInt(stringStartDate.substring(4, 6));
			month1.add(new Month(year, month));
		}

		List<Event> events = new ArrayList<Event>();
		// 1ヶ月分を取得
		for (Month month : month1) {
			for (AJD ajd : month.getDays()) {
				Event event = new Event();
				int day = ajd.getDay();
				Holiday holiday = Holiday.getHoliday(ajd);
				String offName = "";
				if (holiday != null) {
					offName = holiday.getName(ajd);
					String offYmd = String.valueOf(month.getYear()) + "-" + String.format("%02d", month.getMonth())
							+ "-" + String.format("%02d", day);
					event.setTitle(offName);
					event.setStart(offYmd);
					event.setEventVaildF(0);
					event.setName("祝日");
					events.add(event);
				}
			}
		}
		return events;
	}

	public static List<Date> getStartDate(String startDate) {
		Date date1 = getDateFormat("yyyyMMdd", startDate);
		List<Date> date2 = new ArrayList<Date>();

		// Date型の日時をCalendar型に変換
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(date1);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(date1);

		// 日時を加算する
		calendar1.add(Calendar.MONTH, -1);
		calendar2.add(Calendar.MONTH, 1);
		// Calendar型の日時をDate型に戻す
		date2.add(calendar1.getTime());
		date2.add(calendar2.getTime());

		return date2;
	}

	public static Date getDateFormat(String format, String date) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date dateFormat = new Date();
		try {
			dateFormat = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateFormat;
	}

}
