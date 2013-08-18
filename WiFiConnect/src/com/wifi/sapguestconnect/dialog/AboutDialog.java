package com.wifi.sapguestconnect.dialog;

import com.wifi.sapguestconnect.R;
import com.wifi.sapguestconnect.common.CommonFacade;
import com.wifi.sapguestconnect.log.LogManager;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AboutDialog 
{
	public static void show(Context context)
	{		
		LogManager.LogFunctionCall("AboutDialog", "show()");
		
		Resources resources = context.getResources();
		Dialog dialog = new Dialog(context);

		// Set Title
		dialog.setContentView(R.layout.about_dialog);
		dialog.setTitle(resources.getString(R.string.app_name)+" "+CommonFacade.getVersionName(context));

		// Set Text
		TextView text = (TextView) dialog.findViewById(R.id.text);
		text.setText(resources.getString(R.string.app_about_summary));
		
		// Set Credits Text
		TextView creditsTxt = (TextView) dialog.findViewById(R.id.creditsTxt);
		creditsTxt.setText(resources.getString(R.string.app_about_credits));
		
		// Set Image
		ImageView image = (ImageView) dialog.findViewById(R.id.image);
		image.setImageResource(R.drawable.hp_connect);
		
		Button okBtn = (Button) dialog.findViewById(R.id.ok_btn);
		okBtn.setOnClickListener(new OkBtnOnClickListener(dialog));
		
		// Make dialog cancellable on touch outside;
		dialog.setCanceledOnTouchOutside(true);
		
		dialog.show();
		
		Window window = dialog.getWindow();
		window.setLayout(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		 
	}

	static private class OkBtnOnClickListener implements OnClickListener
	{

		private Dialog mDialog;
		public OkBtnOnClickListener(Dialog dialog)
		{
			this.mDialog = dialog;
		}
		
		@Override
		public void onClick(View v) {
			mDialog.dismiss();
		}
		
	}
}
