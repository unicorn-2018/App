package jp.co.bizrefine.domain.model;

/**
 * メッセージ
 */
public class Message {

	// タイトル
	private String title;

	// テキスト
	private String text;

	// アイコン
	private String icon;

	// タイマー
	private int timer;

	// ボタン
	private boolean buttons;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getTimer() {
		return timer;
	}

	public void setTimer(int timer) {
		this.timer = timer;
	}

	public boolean isButtons() {
		return buttons;
	}

	public void setButtons(boolean buttons) {
		this.buttons = buttons;
	}

}