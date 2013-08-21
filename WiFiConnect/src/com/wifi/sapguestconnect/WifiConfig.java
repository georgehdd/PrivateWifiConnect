package com.wifi.sapguestconnect;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;
import com.wifi.sapguestconnect.dialog.IDialogResult;
import com.wifi.sapguestconnect.dialog.SelectNetworkListener;
import com.wifi.sapguestconnect.log.LogManager;

public class WifiConfig extends SherlockActivity 
{
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wifi_config);
		
	    LogManager.LogFunctionCall("WifiConfig", "onCreate()");
	    
		initUILayout();
	}

	private void initUILayout() 
	{
	    LogManager.LogFunctionCall("WifiConfig", "initUILayout()");
	    
	    
	    initPasswordLayout();
	    //
	    initSSIDEntryLayout();
	    
	    // User: https://www.iconfinder.com/icons/118589/user_icon#size=128
	    // Pass: https://www.iconfinder.com/icons/111044/closed_lock_secure_icon#size=128
	    // Wifi: https://www.iconfinder.com/icons/174752/wifi_icon#size=128
	}
	
	private void initPasswordLayout()
	{
		EditText password = (EditText) findViewById(R.id.password_input);
		password.setTypeface(Typeface.DEFAULT);
		password.setTransformationMethod(new PasswordTransformationMethod());
	}
	
	private void initSSIDEntryLayout() 
	{
		EditText wifiPicker = (EditText)findViewById(R.id.wifi_input);
		
		wifiPicker.setOnClickListener(new SelectNetworkListener(this, new IDialogResult() {
					
					@Override
					public void OnFinish(Object result) {
						LogManager.LogFunctionCall("WifiSettings", "initPasswordEntryLayout().SelectNetworkListener().OnFinish()");
						setWifiSSID((String)result);
					}
				}
		));
	}
	
	private void setWifiSSID(String wifiSSID) 
	{
		LogManager.LogFunctionCall("WifiSettings", "setWifiSSID()");
		
		//validateLoginDataMember();
		
		if (wifiSSID != null)
		{
			//mLoginData.setSSID(wifiSSID);
		}
		else
		{
			//mLoginData.setSSID("");
		}
		
		// Commit to DB
		//persistLoginData();
		
		// Update UI
		TextView ssidText = (TextView)findViewById(R.id.wifi_input);
		ssidText.setText(wifiSSID);
	}
}
