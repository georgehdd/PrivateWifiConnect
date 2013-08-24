package com.wifi.sapguestconnect;

import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.wifi.sapguestconnect.common.CommonConsts;
import com.wifi.sapguestconnect.common.ToastUtil;
import com.wifi.sapguestconnect.data.DataFacade;
import com.wifi.sapguestconnect.dialog.IDialogResult;
import com.wifi.sapguestconnect.dialog.SelectNetworkListener;
import com.wifi.sapguestconnect.log.LogManager;
import com.wifi.sapguestconnect.preferences.PreferencesFacade;

public class WifiConfig extends SherlockActivity 
{
	private boolean mIsFirstRun;
	private Resources mResources;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wifi_config);
		
	    LogManager.LogFunctionCall("WifiConfig", "onCreate()");
	    
	    Bundle extras = getIntent().getExtras();
	    Boolean isFirstRun = extras.getBoolean(CommonConsts.IS_FIRST_RUN);
	    this.mIsFirstRun = isFirstRun != null ? isFirstRun.booleanValue() : false;
	    
		// Init Resources
		this.mResources = this.getResources();
	    
		initUILayout();
	    loadLoginData();
	}
	
	private void loadLoginData()
	{
		LogManager.LogFunctionCall("WifiConfig", "loadLoginData()");
		
	    LoginData loadedLoginData = DataFacade.LoadLoginData(this);
	    setUserName(loadedLoginData.getUser());
	    setPassword(loadedLoginData.getPass());
	    setWifiSSID(loadedLoginData.getSSID());
	}

	private void initUILayout() 
	{
	    LogManager.LogFunctionCall("WifiConfig", "initUILayout()");

	    initPasswordLayout();
	    initSSIDEntryLayout();
	    initSaveBtnLayout();
	    
		if (this.mIsFirstRun == false && Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) 
		{
			ActionBar actionBar = getSupportActionBar();
			actionBar.setDisplayHomeAsUpEnabled(true);
		}
	}
	
	private void initPasswordLayout()
	{
		LogManager.LogFunctionCall("WifiConfig", "initPasswordLayout()");
		EditText password = (EditText) findViewById(R.id.password_input);
		password.setTypeface(Typeface.DEFAULT);
		password.setTransformationMethod(new PasswordTransformationMethod());
	}
	
	private void initSSIDEntryLayout() 
	{
		LogManager.LogFunctionCall("WifiConfig", "initSSIDEntryLayout()");
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
	
	private void initSaveBtnLayout()
	{
		LogManager.LogFunctionCall("WifiConfig", "initSaveBtnLayout()");
		Button storeDataBtn = (Button)findViewById(R.id.storeDataBtn);
		storeDataBtn.setOnClickListener( new OnClickListener() {
			@Override
			public void onClick(View v) 
			{
				LogManager.LogFunctionCall("WifiConfig", "initSaveBtnLayout().onClick()");
				LoginData loginData = new LoginData();
				loginData.setPass(WifiConfig.this.getPassword());
				loginData.setUser(WifiConfig.this.getUserName());
				loginData.setSSID(WifiConfig.this.getWifiSSID());
				
				if (!WifiConfig.this.validateLoginData(loginData))
					return;
				
				DataFacade.PersistLoginData(WifiConfig.this, loginData);
				
				PreferencesFacade.refreshRunAsService(WifiConfig.this);
				
				if (mIsFirstRun == true)
				{
					finish();
				}
				else
				{
					displayToastMessage(WifiConfig.this.mResources.getString(R.string.saved_data) , ToastUtil.Style.Info);
				}
			}
		});
	}
	
	private boolean validateLoginData(LoginData loginData)
	{
		int errorStringId = 0;
		
		// Validate
		if (loginData.getUser().trim().equals(""))
		{
			errorStringId = R.string.save_failed_username_missing; 
		}
		else if (loginData.getPass().trim().equals(""))
		{
			errorStringId = R.string.save_failed_password_missing; 
		}
		else if (loginData.getSSID().trim().equals(""))
		{
			errorStringId = R.string.save_failed_ssid_missing; 
		}
		
		// Return Correct Data
		if (errorStringId != 0)
		{
			ToastUtil.Display(WifiConfig.this, WifiConfig.this.mResources.getString(errorStringId) , ToastUtil.Style.Alert);
			return false;
		}
		
		return true;
	}
	
	private void setUserName(String username) 
	{
		LogManager.LogFunctionCall("WifiConfig", "setUserName()");
		EditText userNameInput = (EditText)findViewById(R.id.username_input);
		userNameInput.setText(username);
	}
	
	private String getUserName() 
	{
		LogManager.LogFunctionCall("WifiConfig", "getUserName()");
		EditText userNameInput = (EditText)findViewById(R.id.username_input);
		return userNameInput.getText().toString();
	}

	private void setPassword(String password) 
	{
		LogManager.LogFunctionCall("WifiConfig", "setPassword()");
		EditText passwordInput = (EditText)findViewById(R.id.password_input);
		passwordInput.setText(password);
	}
	
	private String getPassword() 
	{
		LogManager.LogFunctionCall("WifiConfig", "getPassword()");
		EditText passwordInput = (EditText)findViewById(R.id.password_input);
		return passwordInput.getText().toString();
	}
	
	private void setWifiSSID(String wifiSSID) 
	{
		LogManager.LogFunctionCall("WifiConfig", "setWifiSSID()");
		
		// Update UI
		TextView ssidText = (TextView)findViewById(R.id.wifi_input);
		ssidText.setText(wifiSSID);
	}
	
	private String getWifiSSID() 
	{
		LogManager.LogFunctionCall("WifiConfig", "getWifiSSID()");
		
		// Update UI
		TextView ssidText = (TextView)findViewById(R.id.wifi_input);
		return ssidText.getText().toString();
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		if (this.mIsFirstRun == true)
			return super.onOptionsItemSelected(item);
		
	    switch (item.getItemId()) 
	    {
		    case android.R.id.home:
		    	finish();
		        return true;
		    default: 
		    	return super.onOptionsItemSelected(item);  
	    }
	}
	
    // Display Toast-Message
	private void displayToastMessage(String message, ToastUtil.Style style) 
	{
		ToastUtil.Display(this, message, style);
	}
}
