package com.herotculb.qunhaichat.weixin.operationvip;

import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.herotculb.qunhaichat.HomeActivity;
import com.herotculb.qunhaichat.R;
import com.herotculb.qunhaichat.weixin.getvip.window.VipJianMoneyWindowActivity;
import com.herotculb.qunhaichat.widget.LoadingDialog;

public class WeixinJianPriceUserHandler extends Handler {
	VipJianMoneyWindowActivity context;
	ViewGroup view;
	LoadingDialog dialog;
		
	public WeixinJianPriceUserHandler(VipJianMoneyWindowActivity context, LoadingDialog dialog) {
		super();
		this.context = context;
		this.dialog = dialog;
	}

	@Override
	public void handleMessage(android.os.Message msg) {
		super.handleMessage(msg);
		Map<String, Object> map = (Map<String, Object>)msg.obj;
		boolean b=(Boolean) map.get("success");
		if(b){
			Intent i = new Intent(context, HomeActivity.class);  
	        Bundle b2 = new Bundle();
	        b2.putString("tyep", "jian_price_user");
	        i.putExtras(b2);
//	        context.setResult(context.RESULT_OK, i); 
	        context.startActivityForResult(i, 0);
	        context.overridePendingTransition(R.anim.modal_in, R.anim.modal_out);
	        context.finish();  
		}else{
			new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
            .setTitleText("出错了！")
            .setContentText((String)map.get("info"))
            .show();
		}
	}
}