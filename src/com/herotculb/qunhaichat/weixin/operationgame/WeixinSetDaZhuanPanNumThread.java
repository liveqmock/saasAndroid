package com.herotculb.qunhaichat.weixin.operationgame;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.herotculb.qunhaichat.R;
import com.herotculb.qunhaichat.util.QNPermissionApiImpl;

public class WeixinSetDaZhuanPanNumThread extends Thread {
	private Activity context;
	private Handler handler;
	
	public WeixinSetDaZhuanPanNumThread(Activity context, Handler handler) {
		super();
		this.context = context;
		this.handler = handler;
	}

	@Override
	public void run() {
		super.run();
		Looper.prepare();
		
		QNPermissionApiImpl qnpAPi=new QNPermissionApiImpl(context);
		BootstrapEditText num = (BootstrapEditText)
				context.findViewById(R.id.game_dazhuanpan_num_edit);
		String value = num.getText().toString();
		List<Map<String, Object>> list= qnpAPi.setDazhuanpanNum(value);
		Map<String,Object> map=list.iterator().next();
		//获取数据成功
		Message sendmsg = Message.obtain();  
        sendmsg.obj = map;   //利用Message.obj把子线程的handle传递给主线程。  
        handler.sendMessage(sendmsg); 
	}
	
}
