package net.androidwing.aspectj;

import android.app.Activity;
import android.content.Context;


import net.androidwing.aspectj.App;

import java.util.Iterator;
import java.util.Stack;

/**
 * Description: 该类用栈来管理所有该应用的activity，进栈退栈等
 * <ol>
 * <li>{@linkplain #finishActivity()} 关闭栈中第一个activity</li>
 * <li>{@linkplain #finishAllActivity(Class)} 关闭栈中所有该类名的activity</li>
 * <li>{@linkplain #finishAfterActivity(Class)} 关闭栈中该类名的activity之上的activity</li>
 * <li>{@linkplain #finishLastActivity(Class)} 关闭栈中第一个与该类名匹配的activity</li>
 * <li>{@linkplain #finishAllActivityAndClose()} 关闭应用退出</li>
 * </ol>
 * <p>
 * <strong>注意所有的函数都要进行判空的操作，因为可能因为内存不足导致activity被回收而导致空指针的错误</strong>
 *
 * @author zzp(zhao_zepeng@hotmail.com)
 * @since 2015-07-07
 */
public final class ActivityManager {
    private static volatile ActivityManager instance = null;
    private Stack<Activity> mStack = null;

    private ActivityManager() {
        mStack = new Stack<>();
    }

    public static ActivityManager getInstance() {
        if (instance == null) {
            synchronized (ActivityManager.class) {
                if (instance == null)
                    instance = new ActivityManager();
            }
        }
        return instance;
    }

    /**
     * 获取栈的信息
     */
    public String getStackInfo() {
        StringBuilder sb = new StringBuilder();
        for (Activity temp : mStack) {
            if (temp != null)
                sb.append(temp.toString()).append("\n");
        }
        return sb.toString();
    }

    /**
     * 将activity加入到栈中
     *
     * @param activity 需要加入到栈中的activity
     */
    public void addActivity(Activity activity) {
        mStack.push(activity);
    }

    /**
     * 删除栈中activity
     */
    public void removeActivity(Activity activity) {
        mStack.remove(activity);
    }

    /**
     * @return 栈顶的activity
     */
    public Activity getActivity() {
        if (!mStack.isEmpty())
            return mStack.peek();
        return null;
    }

    /**
     * 关闭并删除掉最上面一个的activity
     */
    public void finishActivity() {
        if (!mStack.isEmpty()) {
            Activity temp = mStack.pop();
            if (temp != null)
                temp.finish();
            return;
        }
    }

    /***
     * 关闭并删除指定 activity
     */
    public void finishActivity(Activity activity) {
        if (mStack.isEmpty()) {
            return;
        }
        try {
            mStack.remove(activity);
        } catch (Exception e) {
        } finally {
            if (activity != null)
                activity.finish();
        }
    }

    /**
     * 删除并关闭栈中该class对应的所有的该activity
     */
    public void finishAllActivity(Class<?> clazz) {
        Iterator<Activity> iterator = mStack.iterator();
        while (iterator.hasNext()) {
            Activity activity = iterator.next();
            if (activity != null && activity.getClass().equals(clazz)) {
                //注意应该通过iterator操作stack，要不然回报ConcurrentModificationException
                iterator.remove();
                activity.finish();
            }
        }
    }

    /**
     * 删除并关闭栈中该class对应的第一个该activity,从栈顶开始
     */
    public void finishLastActivity(Class<?> clazz) {
        Activity activity = null;
        Iterator<Activity> iterator = mStack.iterator();
        while (iterator.hasNext()) {
            Activity temp = iterator.next();
            if (temp != null && temp.getClass().equals(clazz))
                activity = temp;
        }
        if (activity != null)
            finishActivity(activity);
    }

    /**
     * 删除栈上该activity之上的所有activity
     */
    public void finishAfterActivity(Activity activity) {
        if (activity != null && mStack.search(activity) == -1) {
            return;
        }
        while (mStack.peek() != null) {
            Activity temp = mStack.pop();
            if (temp != null && temp.equals(activity)) {
                mStack.push(temp);
                break;
            }
            if (temp != null)
                temp.finish();
        }
    }

    /**
     * 删除栈上该class之上的所有activity
     */
    public void finishAfterActivity(Class<?> clazz) {
        boolean flag = true;
        Activity activity = null;
        Iterator<Activity> iterator = mStack.iterator();
        while (iterator.hasNext()) {
            activity = iterator.next();
            if (activity != null && activity.getClass().equals(clazz)) {
                flag = false;
                break;
            }
        }
        if (flag) {
            return;
        }
        finishAfterActivity(activity);
    }

    /**
     * 弹出关闭所有activity并关闭应用所有进程
     */
    public void finishAllActivityAndClose() {
        while (mStack.size() > 0) {
            Activity temp = mStack.pop();
            if (temp != null)
                temp.finish();
        }
        //调用finish()之后不会立马调用onDestroy()
//        App.checkApplicationDestroy();
        try {
            android.app.ActivityManager activityManager = (android.app.ActivityManager)
                    App.getInstance().getSystemService(Context.ACTIVITY_SERVICE);
            activityManager.killBackgroundProcesses(App.getInstance().getPackageName());
        } catch (SecurityException e) {
        }
        System.exit(0);
    }

    /**
     * 弹出关闭所有activity并保留应用后台进程
     */
    public void finishAllActivityWithoutClose() {
        while (mStack.size() > 0) {
            Activity temp = mStack.pop();
            if (temp != null)
                temp.finish();
        }
        //调用finish()之后不会立马调用onDestroy()
//        com.android.libcore.application.App.checkApplicationDestroy();
        System.exit(0);
    }
}