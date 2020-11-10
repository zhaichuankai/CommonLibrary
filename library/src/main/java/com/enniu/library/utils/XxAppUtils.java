package com.enniu.library.utils;

import android.app.Activity;
import android.content.Context;

import java.util.HashMap;
import java.util.Stack;

/**
 * 应用程序Activity管理类：用于Activity管理和应用程序退出
 */
public class XxAppUtils {

	public static Stack<Activity> activityStack;
	private static XxAppUtils instance;
	private static HashMap<String, Integer> standMemory = new HashMap<String, Integer>();

	public void addMemory(Object o) {
		standMemory.put(o.getClass().getName() + o.hashCode(), 1);
	}

	public void finalize(Object o) {
		standMemory.remove(o.getClass().getName() + o.hashCode());
	}

	/**
	 * 单一实例
	 */
	public static XxAppUtils getInstance() {
		if (instance == null) {
			instance = new XxAppUtils();
		}
		return instance;
	}

	/**
	 * 添加Activity到堆栈
	 */
	public void addActivity(Activity activity) {
		if (activityStack == null) {
			activityStack = new Stack<Activity>();
		}
		activityStack.add(activity);
	}

	/**
	 * 获取当前Activity（堆栈中最后一个压入的）
	 */
	public Activity currentActivity() {
		if (activityStack == null) {
			return null;
		}
		Activity activity = activityStack.lastElement();
		return activity;
	}

	public String currentActivityName() {
		if (activityStack == null) {
			return null;
		}
		Activity activity = activityStack.lastElement();
		return activity.getClass().getSimpleName();
	}

	public boolean equalsActivity(String name) {
		return currentActivityName().equals(name);

	}


	/**
	 * 结束当前Activity（堆栈中最后一个压入的）
	 */
	public void finishActivity() {
		Activity activity = activityStack.lastElement();
		finishActivity(activity);
	}

	/**
	 * 结束指定的Activity
	 */
	public void finishActivity(Activity activity) {
		if (activity != null) {
			activityStack.remove(activity);
			activity.finish();
			activity = null;
		}
	}

	/**
	 * 结束指定类名的Activity
	 */
	public void finishActivity(Class<?> cls) {
		for (Activity activity : activityStack) {
			if (activity.getClass().equals(cls)) {
				finishActivity(activity);
				break;
			}
		}
	}

	/**
	 * 结束所有Activity
	 */
	public  void finishAllActivity() {
		if (activityStack == null) {
			return;
		}
		for (int i = 0, size = activityStack.size(); i < size; i++) {
			if (null != activityStack.get(i)) {
				activityStack.get(i).finish();
			}
		}
		activityStack.clear();
	}

	/**
	 * 退出应用程序
	 */
	public void AppExit(Context context) {
		try {
			finishAllActivity();
			/*
			 * Intent intent = new Intent(mContext, AddTextNewActivity.class);
			 * PendingIntent restartIntent = PendingIntent.getActivity( mContext,
			 * 0, intent, Intent.FLAG_ACTIVITY_NEW_TASK); //退出程序 AlarmManager
			 * mgr =
			 * (AlarmManager)mContext.getSystemService(Context.ALARM_SERVICE);
			 * mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 1000,
			 * restartIntent); // 1秒钟后重启应用
			 */
			// 杀死该应用进程
			android.os.Process.killProcess(android.os.Process.myPid());
			System.exit(0);

		} catch (Exception e) {

		}
	}
}