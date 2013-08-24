package com.wifi.sapguestconnect.common;

import android.app.Activity;

import com.devspark.appmsg.AppMsg;

public class ToastUtil
{
	public enum Style
	{
		Info,
		Confirm,
		Alert
	};

    // Display Toast-Message
	public static void Display(Activity activity, String message, Style style) 
	{
		if ((message == null) || (message.trim().length() == 0))
		{
			return;
		}
		
		com.devspark.appmsg.AppMsg.Style appMsgStyle;
		switch(style)
		{
			case Alert:
				appMsgStyle = AppMsg.STYLE_ALERT;
				break;
			case Confirm:
				appMsgStyle = AppMsg.STYLE_CONFIRM;
				break;
			case Info:
				appMsgStyle = AppMsg.STYLE_INFO;
				break;
			default:
				appMsgStyle = AppMsg.STYLE_INFO;
		}
		
		AppMsg.makeText(activity, message, appMsgStyle).show();
		//Toast toastMsg = Toast.makeText(this, message, Toast.LENGTH_SHORT);
		//toastMsg.show();
	}
}
