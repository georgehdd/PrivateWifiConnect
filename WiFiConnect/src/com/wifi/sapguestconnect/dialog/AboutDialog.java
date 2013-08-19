package com.wifi.sapguestconnect.dialog;

import com.wifi.sapguestconnect.R;
import com.wifi.sapguestconnect.common.CommonFacade;
import com.wifi.sapguestconnect.log.LogManager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AboutDialog 
{
	public static void show(Context context)
	{		
		LogManager.LogFunctionCall("AboutDialog", "show()");
		
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		Resources resources = context.getResources();
		Activity activity = (Activity) context;
		LayoutInflater inflater = activity.getLayoutInflater();
		

		builder.setTitle(resources.getString(R.string.app_name) + " " + CommonFacade.getVersionName(context));

		builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	               
	           }
	    });
		
		
		View dialogView = inflater.inflate(R.layout.about_dialog, null);
		
		// Set Text
		TextView text = (TextView) dialogView.findViewById(R.id.text);
		text.setText(resources.getString(R.string.app_about_summary));
		
		// Set Credits Text
		TextView creditsTxt = (TextView) dialogView.findViewById(R.id.creditsTxt);
		creditsTxt.setText(resources.getString(R.string.app_about_credits));
		
		// Set Image
		ImageView image = (ImageView) dialogView.findViewById(R.id.image);
		image.setImageResource(R.drawable.hp_connect);
		
		// Make dialog cancellable on touch outside;
		//dialog.setCanceledOnTouchOutside(true);
		
		builder.setView(dialogView);
		AlertDialog dialog = builder.create();
		dialog.show();
		
		//Window window = dialog.getWindow();
		//window.setLayout(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		 
	}

}
