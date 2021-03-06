package com.herotculb.qunhaichat.weixin.operationgame.item;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.herotculb.qunhaichat.util.QNPermissionApiImpl;

public class AddGuaGuaKaThreadItem extends Thread {
	private Context context;
	private Handler handler;

	public AddGuaGuaKaThreadItem(Context context, Handler handler) {
		super();
		this.context = context;
		this.handler = handler;
	}

	@Override
	public void run() {
		QNPermissionApiImpl qnpAPi=new QNPermissionApiImpl(context);
		List<Map<String, Object>> list= qnpAPi.getAwards();
		
		Map<String,Object> map=list.iterator().next();
		Message sendmsg = Message.obtain();  
        sendmsg.obj = map;   //利用Message.obj把子线程的handle传递给主线程。  
        handler.sendMessage(sendmsg);  
	}
}
