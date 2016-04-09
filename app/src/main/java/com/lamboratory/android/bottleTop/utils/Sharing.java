package com.lamboratory.android.bottleTop.utils;

import com.lamboratory.android.bottleTop.race.R;

import android.app.Activity;
import android.content.Intent;

public class Sharing {

	public static void share(Activity activity) {
		final Intent intent = new Intent(Intent.ACTION_SEND);

		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_SUBJECT, activity.getResources().getString(R.string.app_name));
		intent.putExtra(Intent.EXTRA_TEXT, activity.getResources().getString(R.string.share_text));

		activity.startActivity(Intent.createChooser(intent, activity.getResources().getString(R.string.share)));
	}
}
