package com.jgduan.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.jgduan.utils.ModifySystemDialog;

public class MainActivity extends Activity implements OnClickListener {
	private Button myBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		// 根据id在布局中找到控件对象
		myBtn = (Button) findViewById(R.id.test);
		
		// 为控件绑定点击事件监听器
		myBtn.setOnClickListener((OnClickListener) this);



	}

	public void onClick(View v) {
        switch (v.getId()) {
        case R.id.test:
//            new ModifySystemDialog(this).setDate("退出扣扣第SAN次修改","您确定退出QQ吗？","左边是啥", "右边是啥", new ModifySystemDialog.OnDialogClickListener() {
//				@Override
//				public void onLeftClickListener() {
//					Toast.makeText(MainActivity.this, "点了←左边", Toast.LENGTH_SHORT).show();
//				}
//
//				@Override
//				public void onRightClickListener() {
//					Toast.makeText(MainActivity.this, "点了→右边", Toast.LENGTH_SHORT).show();
//
//				}
//			}).show();
			new ModifySystemDialog(this).setTitle("退出扣扣第SAN次修改").setMessage("您确定退出QQ吗？").setLeftText("左边是啥")
						.setRightText("右边是啥").setOnDialogClickListener(new ModifySystemDialog.OnDialogClickListener() {
				@Override
				public void onLeftClickListener() {
					Toast.makeText(MainActivity.this, "点了←左边", Toast.LENGTH_SHORT).show();
				}

				@Override
				public void onRightClickListener() {
					Toast.makeText(MainActivity.this, "点了→右边", Toast.LENGTH_SHORT).show();
				}
			}).show();


        default:
            break;
        }
    }

}
