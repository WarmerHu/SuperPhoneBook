package com.superphonebook.utils;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;

public class WidgetUtil {

    private static Builder makeSureBuilder;
    
    public static void getSureWidget(Context context, String title, String message, DialogInterface.OnClickListener dialog) {
	if (makeSureBuilder == null)
	    makeSureBuilder = new Builder(context)
		.setTitle(title)
		.setMessage(message)
		.setPositiveButton("确认",dialog)
		.setNegativeButton("取消",dialog);
	makeSureBuilder.show();
	}
    
}
