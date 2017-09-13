package com.jgduan.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jgduan.activity.R;

/**
 * xiu
 */
public class ModifySystemDialog extends Dialog implements android.view.View.OnClickListener {
	private String rightText;
	public interface OnDialogClickListener{
		void  onLeftClickListener();
		void  onRightClickListener();
	}
	public OnDialogClickListener listener;

	int layoutRes = R.layout.customdialog;// 布局文件
	Context context;
	/** 确定按钮 **/
	private Button confirmBtn;
	/** 取消按钮 **/
	private Button cancelBtn;
	private String title;
	private String leftText;

	public ModifySystemDialog(Context context) {
		super(context, R.style.mystyle);
		this.context = context;
	}


	public ModifySystemDialog setLeftAndRightAndListener(String title, String leftText, String rightText, OnDialogClickListener listener){
		this.title = title;
		this.leftText = leftText;
		this.rightText = rightText;
		this.listener = listener;
		return this;
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(layoutRes);
		// 根据id在布局中找到控件对象
		confirmBtn = (Button) findViewById(R.id.confirm_btn);
		cancelBtn = (Button) findViewById(R.id.cancel_btn);
		TextView tv_title = (TextView) findViewById(R.id.tv_title);
		tv_title.setText(title);
		confirmBtn.setText(rightText);
		cancelBtn.setText(leftText);

		// 设置按钮的文本颜色
		confirmBtn.setTextColor(0xff1E90FF);
		cancelBtn.setTextColor(0xff1E90FF);
		// 为按钮绑定点击事件监听器
		confirmBtn.setOnClickListener(this);
		cancelBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.confirm_btn:
			listener.onRightClickListener();
			break;
		case R.id.cancel_btn:
			listener.onLeftClickListener();
			break;
		}
	}
}