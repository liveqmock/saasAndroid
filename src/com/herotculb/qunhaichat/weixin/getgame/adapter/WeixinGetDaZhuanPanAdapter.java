package com.herotculb.qunhaichat.weixin.getgame.adapter;

import java.util.Date;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TableLayout;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.herotculb.qunhaichat.HomeActivity;
import com.herotculb.qunhaichat.R;
import com.herotculb.qunhaichat.dto.GamesAwardsListDto;
import com.herotculb.qunhaichat.util.DateUtil;
import com.herotculb.qunhaichat.widget.DateActivity;
/**
 * 大转盘 Adapter
 * @author lkx
 *
 */
public class WeixinGetDaZhuanPanAdapter extends BaseAdapter {
	private HomeActivity  context;
	private List<GamesAwardsListDto> list;
	BootstrapButton enter;
	BootstrapButton contentBut;
	BootstrapButton startButton;
	BootstrapButton endButton;
	TextView startDate;
	TextView endDate;
		
	public WeixinGetDaZhuanPanAdapter(HomeActivity context, List<GamesAwardsListDto> list) {
		super();
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO 自动生成的方法存根
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO 自动生成的方法存根
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO 自动生成的方法存根
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO 自动生成的方法存根
		final GamesAwardsListDto dto = (GamesAwardsListDto) getItem(position);
		LayoutInflater inflater = LayoutInflater.from(context);
		TableLayout gridLayout = (TableLayout) inflater.inflate(
				R.layout.game_dazhuanpan_list, null);
		TextView text  = (TextView) gridLayout.findViewById(R.id.game_dazhuanpan_content1);
		TextView text1 = (TextView) gridLayout.findViewById(R.id.game_dazhuanpan_num1);
		TextView text2 = (TextView) gridLayout.findViewById(R.id.game_dazhuanpan_start_date1);
		TextView text3 = (TextView) gridLayout.findViewById(R.id.game_dazhuanpan_end_date1);
		enter =(BootstrapButton) gridLayout.findViewById(R.id.game_dazhuanpan_enter1);
		contentBut =(BootstrapButton) gridLayout.findViewById(R.id.game_dazhuanpan_content_button1);
		startButton =(BootstrapButton) gridLayout.findViewById(R.id.game_dazhuanpan_start_date_button1);
		endButton =(BootstrapButton) gridLayout.findViewById(R.id.game_dazhuanpan_end_date_button1);
		startDate =(TextView)gridLayout.findViewById(R.id.game_dazhuanpan_start_date1);
		endDate =(TextView)gridLayout.findViewById(R.id.game_dazhuanpan_end_date1);
		
		enter.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 确认设置
				
			}
		});
		contentBut.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 奖品选择
				
			}
		});
		startButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 开始日期选择
				Intent i = new Intent(context, DateActivity.class);
				Bundle b = new Bundle(); 
				b.putString("type", "startDate");
                i.putExtras(b);
                context.startActivity(i);
                context.overridePendingTransition(R.anim.modal_in, R.anim.modal_out);
			}
		});
		endButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 结束日期选择
				Intent i = new Intent(context, DateActivity.class);
				Bundle b = new Bundle(); 
				b.putString("type", "endDate");
                i.putExtras(b);
                context.startActivity(i);
                context.overridePendingTransition(R.anim.modal_in, R.anim.modal_out);
			}
		});
		text.setText(dto.getContent());
		text1.setText(String.valueOf(dto.getNum()));
		Date date = new Date();
		Date date1 = new Date();
		Long sdate = Long.parseLong(dto.getStartDate());
		Long edate = Long.parseLong(dto.getEndDate());
		date.setTime(sdate);
		date1.setTime(edate);
		String startDate = DateUtil.formatDateYYYY_MM_DD(date);
		String endDate = DateUtil.formatDateYYYY_MM_DD(date1);
		text2.setText(startDate);
		text3.setText(endDate);
		return gridLayout;
	}
	
}
