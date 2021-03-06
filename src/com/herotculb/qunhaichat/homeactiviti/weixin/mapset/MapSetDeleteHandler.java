package com.herotculb.qunhaichat.homeactiviti.weixin.mapset;

import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.widget.ListView;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.herotculb.qunhaichat.R;
import com.herotculb.qunhaichat.widget.LoadingDialog;

public class MapSetDeleteHandler extends Handler{
	Activity context;
	LoadingDialog dialog;
	public MapSetDeleteHandler(Activity context2,
			LoadingDialog dialog) {
		super();
		this.context = context2;
		this.dialog = dialog;
	}
	@SuppressLint("ResourceAsColor")
	@Override
	public void handleMessage(android.os.Message msg) {
		Map<String, Object> map = (Map<String, Object>) msg.obj;
		Boolean b=(Boolean) map.get("success");
		dialog.hide();
		dialog.dismiss();
		if (b) {
				new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
				.setTitleText("成功")
				.setContentText((String) map.get("info"))
				.setConfirmText("确定").showCancelButton(false)
				.setCancelClickListener(null).setConfirmClickListener(null)
				.show();
				ListView list=(ListView)context.findViewById(R.id.weixin_mapset_all_layout_list);
				LoadingDialog dialog = new LoadingDialog(context, "正在获取数据");
				dialog.show();
				MapSetItemHandler handler=new MapSetItemHandler(context, dialog, list);
				MapSetItemTread tread=new MapSetItemTread(context, handler);
				tread.start();
		} else {
			new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
					.setTitleText("失败")
					.setContentText((String) map.get("info"))
					.setConfirmText("确定").showCancelButton(false)
					.setCancelClickListener(null).setConfirmClickListener(null)
					.show();
		}
	}
}
