package com.herotculb.qunhaichat.weixin.operationgame;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.herotculb.qunhaichat.util.QNPermissionApiImpl;

public class WeixinDeleteGuaGuaKaThread extends Thread {
	private Activity context;
	private long id;
	private Handler handler;
	
	public WeixinDeleteGuaGuaKaThread(Activity context, long id,
			Handler handler) {
		super();
		this.context = context;
		this.id = id;
		this.handler = handler;
	}

	@Override
	public void run() {
		super.run();
		Looper.prepare();
		
		QNPermissionApiImpl qnpAPi=new QNPermissionApiImpl(context);
		List<Map<String, Object>> list= qnpAPi.deleteGuaguaka(String.valueOf(id));
		Map<String,Object> map=list.iterator().next();
		Message sendmsg = Message.obtain();  
        sendmsg.obj = map;   //利用Message.obj把子线程的handle传递给主线程。  
        handler.sendMessage(sendmsg); 
	}
	
}