package net.androidwing.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by wing on 5/9/17.
 */
@Aspect
public class ActivityTrace {
    private long timemillis;

 /* @Before("execution(* android.app.Activity.onCreate(..))")
  public void beforeMethod(JoinPoint joinPoint) {

    String key = joinPoint.getSignature().toString();
    if (!key.contains("net.androidwing.aspectj")) {
      return;
    }
    timemillis = System.currentTimeMillis();
  }*/

    /*@After("execution(* android.app.Activity.onResume(..))")
    public void afterMethod(JoinPoint joinPoint) {
      String key = joinPoint.getSignature().toString();
      if (!key.contains("net.androidwing.aspectj")) {
        return;
      }
      Log.e("wing",
          joinPoint.getThis().getClass() + "Activity started in :" + (System.currentTimeMillis()
              - timemillis) + "ms");
    }
  */
    @Pointcut("execution(@net.androidwing.aspectj.CheckLogin * *(..))")
    public void checkLogin() {
    }

    @Pointcut("execution(@net.androidwing.aspectj.CheckAuth * *(..))")
    public void checkAuth() {
    }

    /* @Before("checkLogin()")
     public void onDebugToolMethodBefore(JoinPoint joinPoint)
             throws Throwable {

         if (App.isLogin()) {
             Log.e("wing", "login");
         } else {
             Log.e("wing", "notLogin");
         }
     }*/
    @Around("checkLogin()")
    public void checkLogin(ProceedingJoinPoint proceedingJoinPoint) {
        CheckPermission checkPermission = new CheckPermission.Builder(App.getInstance()).isLogin(AppConfig.getInstance().isLogin()).build();
        if (checkPermission.check()) {
            try {
                proceedingJoinPoint.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }

    }

    @Around("checkAuth()")
    public void checkAuth(ProceedingJoinPoint proceedingJoinPoint) {
        CheckPermission checkPermission = new CheckPermission.Builder(App.getInstance()).isAuth(AppConfig.getInstance().isAuth()).build();
        if (checkPermission.check()) {
            try {
                proceedingJoinPoint.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }

}
