package com.wifi.sapguestconnect.preferences.location;

import com.wifi.sapguestconnect.R;
import com.wifi.sapguestconnect.log.LogManager;

import android.content.Context;
import android.content.res.Resources;

class LocationDE implements ILocation 
{
	private final int codeResource = R.string.germany_code;
	private final int hostNameResource = R.string.host_name_aruba_networks;
	
	private Context mContext;
	private Resources mResources;
	
	public LocationDE(Context context)
	{
		mContext = context;
		
		LogManager.LogFunctionCall("LocationDE", "C'tor()");

		mResources = mContext.getResources();
	}
	
	@Override
	public String getConnectionHostName() 
	{
		LogManager.LogFunctionCall("LocationDE", "getConnectionHostName()");
		
		return mResources.getString(hostNameResource);
	}

	@Override
	public String getLocationCode() 
	{
		LogManager.LogFunctionCall("LocationDE", "getLocationCode()");
		
		return mResources.getString(codeResource);
	}
	
}
