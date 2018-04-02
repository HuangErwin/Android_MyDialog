package com.bfmehuang.mylibrary;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bfmehuang.mylibrary.R;


/**
 * xiu
 */
public class ModifySystemDialog extends Dialog implements android.view.View.OnClickListener {
	private String rightText;
	private String message;
    private String title;
    private String leftText;
	public interface OnDialogClickListener{
		void  onLeftClickListener(ModifySystemDialog dialog);
		void  onRightClickListener(ModifySystemDialog dialog);
	}
	public OnDialogClickListener listener;

	int layoutRes = R.layout.customdialog;// 布局文件
	Context context;
	/** 确定按钮 **/
	private Button confirmBtn;
	/** 取消按钮 **/
	private Button cancelBtn;

	public ModifySystemDialog(Context context) {
		super(context, R.style.mystyle);
		this.context = context;
	}

	public ModifySystemDialog setDate(String title, String  message,String leftText, String rightText, OnDialogClickListener listener){
		this.title = title;
		this.leftText = leftText;
		this.rightText = rightText;
		this.listener = listener;
		this.message = message;
		return this;
	}

    public ModifySystemDialog setTitle(String title){
        this.title = title;
        return this;
    }
    public ModifySystemDialog setMessage(String message){
        this.message = message;
        return this;
    }
    public ModifySystemDialog setLeftText(String leftText){
        this.leftText = leftText;
        return this;
    }
    public ModifySystemDialog setRightText(String rightText){
        this.rightText = rightText;
        return this;
    }

    public ModifySystemDialog setOnDialogClickListener(OnDialogClickListener listener){
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
        TextView tv_message = (TextView) findViewById(R.id.tv_message);
        tv_message.setText(message);
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
		int i = v.getId();
		if (i == R.id.confirm_btn) {
			listener.onRightClickListener(this);

		} else if (i == R.id.cancel_btn) {
			listener.onLeftClickListener(this);

		}
	}
}