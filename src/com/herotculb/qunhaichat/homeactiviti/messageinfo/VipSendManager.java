package com.herotculb.qunhaichat.homeactiviti.messageinfo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.herotculb.qunhaichat.HomeActivity;
import com.herotculb.qunhaichat.R;
import com.herotculb.qunhaichat.messageinfo.SendVipMessageHandler;
import com.herotculb.qunhaichat.messageinfo.SendVipMessageThread;
import com.herotculb.qunhaichat.messageinfo.window.MessageVipDuiHuanWindow;
import com.herotculb.qunhaichat.messageinfo.window.MessageVipWindow;
import com.herotculb.qunhaichat.widget.LoadingDialog;

public class VipSendManager {
	public HomeActivity home;
	public LinearLayout linearLayout;
	BootstrapEditText marks;
	
	public VipSendManager(HomeActivity home, LinearLayout linearLayout) {
		this.home = home;
		this.linearLayout = linearLayout;
		//会员发信
		LinearLayout gridLayout = (LinearLayout) home.inflater.inflate(
				R.layout.message_vip_send_layout, null)
				.findViewById(R.id.message_vip_send_main);
		linearLayout.removeAllViews();
		linearLayout.addView(gridLayout);
		BootstrapButton duiHuan = (BootstrapButton) linearLayout.findViewById(R.id.message_vip_send_duihuan);
		BootstrapButton ordinary = (BootstrapButton) linearLayout.findViewById(R.id.message_vip_send_ordinary);
		duiHuan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 发送兑换券
				LinearLayout gridLayout = (LinearLayout) VipSendManager.this.home.inflater.inflate(
						R.layout.message_vip_send_duihuan, null)
						.findViewById(R.id.message_vip_send_duihuan_main);
				LinearLayout linearLayout = (LinearLayout)
						VipSendManager.this.home.findViewById(R.id.message_vip_send_list);
				linearLayout.removeAllViews();
				linearLayout.addView(gridLayout);
				TextView text = (TextView)gridLayout
						.findViewById(R.id.message_vip_send_duihuan_text);
				text.setText("模板说明 ： 直接把兑换券储存至会员的账户，<我的兑换券> 通过积分兑换进行确认序列号，"
						+ "最多300字，60字一条短信。请预先在会员管理模块-》会员积分兑换页面进行奖品添加\n"
						+ "注意：在发送时不减少兑换物的数量，\n"
						+ "群发信息 最长30分钟内到达，运营商接口有延迟，\n"
						+ "如果发送条数填写的数字超过当前会员数，则想当前所有会员发送。");
				BootstrapButton enter = (BootstrapButton)gridLayout
						.findViewById(R.id.message_vip_send_duihuan_enter);
				BootstrapButton send = (BootstrapButton)gridLayout
						.findViewById(R.id.message_vip_send_duihuan);
				BootstrapButton select = (BootstrapButton)gridLayout
						.findViewById(R.id.message_vip_send_duihuan_jiangpin);
				marks = (BootstrapEditText)gridLayout
						.findViewById(R.id.message_vip_send_duihuan_marks);
				
				enter.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO 确认
						LoadingDialog dialog = new LoadingDialog(VipSendManager
								.this.home,"正在保存数据！");
						dialog.show();
						SendVipMessageHandler handler = 
								new SendVipMessageHandler(VipSendManager.this.home, dialog);
						SendVipMessageThread thread = 
								new SendVipMessageThread(VipSendManager.this.home, "1", handler);
						thread.start();
					}
				});
				send.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO 用本机发送短信
						Intent i = new Intent(VipSendManager.this.home,MessageVipWindow.class);
						Bundle b = new Bundle();  
						b.putString("marks", marks.getText().toString());
		                i.putExtras(b);  
		                VipSendManager.this.home.startActivityForResult(i, 0);
		                VipSendManager.this.home.overridePendingTransition(R.anim.modal_in, R.anim.modal_out);
					}
				});
				select.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO 选择奖品
						Intent i = new Intent(VipSendManager.this.home,MessageVipDuiHuanWindow.class);
						Bundle b = new Bundle();  
		                i.putExtras(b);  
		                VipSendManager.this.home.startActivityForResult(i, 0);
		                VipSendManager.this.home.overridePendingTransition(R.anim.modal_in, R.anim.modal_out);
					}
				});
			}
		});
		ordinary.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO 发送普通短信
				LinearLayout linearLayout = (LinearLayout)
						VipSendManager.this.home.findViewById(R.id.message_vip_send_list);
				LinearLayout gridLayout = (LinearLayout) VipSendManager.this.home.inflater.inflate(
						R.layout.message_vip_send_ordinary, null)
						.findViewById(R.id.message_vip_send_ordinary_main);
				linearLayout.removeAllViews();
				linearLayout.addView(gridLayout);
				TextView text = (TextView)gridLayout
						.findViewById(R.id.message_vip_send_ordinary_text);
				text.setText("模板说明 ： 普通群发信息发送,最多300字，60字一条短信，如果发送条数填写的数字"
						+ "超过当前会员数，则想当前所有会员发送。\n群发信息 最长30分钟内到达，"
						+ "运营商接口有延迟，");
				BootstrapButton enter = (BootstrapButton)gridLayout
						.findViewById(R.id.message_vip_send_ordinary_enter);
				enter.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO 确认
						LoadingDialog dialog = new LoadingDialog(VipSendManager
								.this.home,"正在保存数据！");
						dialog.show();
						SendVipMessageHandler handler = 
								new SendVipMessageHandler(VipSendManager.this.home, dialog);
						SendVipMessageThread thread = 
								new SendVipMessageThread(VipSendManager.this.home, "2", handler);
						thread.start();
					}
				});
			}
		});
	}
	
}
