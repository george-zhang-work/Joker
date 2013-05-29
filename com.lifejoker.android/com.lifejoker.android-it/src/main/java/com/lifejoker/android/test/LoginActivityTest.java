package com.lifejoker.android.test;

import android.test.ActivityInstrumentationTestCase2;
import com.lifejoker.android.LoginActivity;

public class LoginActivityTest extends
		ActivityInstrumentationTestCase2<LoginActivity> {

	public LoginActivityTest() {
		super(LoginActivity.class);
	}

	public void testActivity() {
		LoginActivity activity = getActivity();
		assertNotNull(activity);
	}
}
