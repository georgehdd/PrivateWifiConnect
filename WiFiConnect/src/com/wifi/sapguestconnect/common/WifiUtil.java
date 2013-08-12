package com.wifi.sapguestconnect.common;

import android.net.wifi.WifiManager;

public class WifiUtil 
{
	public static String getSSID(WifiManager wifiManager) 
	{
		if (wifiManager == null)
			return null;
		
		String ssid = wifiManager.getConnectionInfo().getSSID();
		if (ssid.startsWith("\"") && ssid.endsWith("\""))
		{
            ssid = ssid.substring(1, ssid.length()-1);
		}
		
		return ssid;
	}
}
