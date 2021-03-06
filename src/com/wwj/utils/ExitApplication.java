package com.wwj.utils;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.app.Application;

/*
 *退出程序工具类
 * */

public class ExitApplication extends Application {
	private static ExitApplication instance;
	private List<Activity> activityList = new LinkedList<Activity>();

	private ExitApplication() {
	}

	public synchronized static ExitApplication getInstance() {
		if (null == instance) {
			instance = new ExitApplication();
		}
		return instance;
	}

	public void addActivity(Activity activity) {
		activityList.add(activity);
	}

	public void exit() {
		for (Activity activity : activityList) {
			if (activity != null) {
				activity.finish();
			}
		}
		System.exit(0);
	}

}
