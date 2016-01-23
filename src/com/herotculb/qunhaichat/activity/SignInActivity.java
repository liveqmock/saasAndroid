package com.herotculb.qunhaichat.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.herotculb.qunhaichat.R;
import com.herotculb.qunhaichat.activity.register.SignRegisterActivity;
import com.herotculb.qunhaichat.crm.querychance.window.AddLinkmanSexWindow;
import com.herotculb.qunhaichat.employee.AddCustomerWindow;
import com.herotculb.qunhaichat.widget.LoadingDialog;
import com.herotculb.qunhaichat.widget.RoundedCornerImageView;
import com.herotculb.qunhaichat.widget.TipsToast;

/**
 * 
 * @author Administrator
 * 
 */
public class SignInActivity extends Activity implements OnClickListener {

	private EditText mUsername; // 帐号编辑框
	private EditText mPassword; // 密码编辑框

	private Button mLoginBtn;
	private Button mRegisterBtn;

	private String username;
	private String password;


	private static TipsToast tipsToast;
	

	private long mExitTime;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			
			if ((System.currentTimeMillis() - mExitTime) > 2000) {
				Object mHelperUtils;
				showTips(R.drawable.tips_smile, "再按一次返回桌面");
				mExitTime = System.currentTimeMillis();

			} else {
				finish();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏头
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_sign_in);
		Intent intent = getIntent();

		String keyStr = intent.getStringExtra("key");

		
		LinearLayout inout=(LinearLayout)findViewById(R.id.login_main_in);//淡入动画
		Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.push_in);
		inout.startAnimation(anim);
		RoundedCornerImageView logo=(RoundedCornerImageView)findViewById(R.id.image_icon);//旋转动画
		Animation anim2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.logo	);
		logo.startAnimation(anim2);
		mUsername = (EditText) findViewById(R.id.login_user_edit);
		mPassword = (EditText) findViewById(R.id.login_passwd_edit);
		mLoginBtn = (Button) findViewById(R.id.login_login_btn);
		mRegisterBtn = (Button) findViewById(R.id.login_register);
		mLoginBtn.setOnClickListener(this);
		if ("1".equals(keyStr)) {
			mLoginBtn.setEnabled(false);
			mUsername.setText(intent.getStringExtra("username"));
			mPassword.setText(intent.getStringExtra("password"));
			login();
		
		}
		mRegisterBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO 跳转到注册页面
				Intent i = new Intent(SignInActivity.this,SignRegisterActivity.class);
				Bundle b = new Bundle();
				i.putExtras(b);
				SignInActivity.this.startActivity(i);
				SignInActivity.this.finish();
			}
		});
	}




	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.login_login_btn:
			login();
			break;
		
		default:
			break;
		}
	}

	public void login(){
		username = mUsername.getText().toString();
		password = mPassword.getText().toString();

		if ("".equals(username) || "".equals(password))// 判断 帐号和密码
		{
			showTips(R.drawable.tips_warning, "帐号或者密码不能为空，\n请输入后再登录！");
		} else {
			
			mLoginBtn.setEnabled(false);
			LoadingDialog dialog = new LoadingDialog(this, "正在登录...");
			dialog.show();
			SignInHandler handler=new SignInHandler(SignInActivity.this, dialog, tipsToast);
			SignInActivityLoginThread thread=new SignInActivityLoginThread(handler, username, password, SignInActivity.this);
			thread.start();
		}
	}
	/**
	 * 自定义toast
	 * 
	 * @param iconResId
	 *            图片
	 * @param msgResId
	 *            提示文字
	 */
	private void showTips(int iconResId, String tips) {
		if (tipsToast != null) {
			if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
				tipsToast.cancel();
			}
		} else {
			tipsToast = TipsToast.makeText(getApplication().getBaseContext(),
					tips, TipsToast.LENGTH_SHORT);
		}
		tipsToast.show();
		tipsToast.setIcon(iconResId);
		tipsToast.setText(tips);
	}



}