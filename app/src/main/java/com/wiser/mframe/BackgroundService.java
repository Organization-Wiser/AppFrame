package com.wiser.mframe;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class BackgroundService {

    private static BackgroundService backgroundService;

    private Method method;

    public static BackgroundService instants() {
        synchronized (BackgroundService.class) {
            if (backgroundService == null) backgroundService = new BackgroundService();
            return backgroundService;
        }
    }

    public <T> void inject(final Object class1) {
        final Class clazz = class1.getClass();
        Object tClass = null;
        try {
            tClass = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Method[] methods = clazz.getMethods();
        for (final Method method : methods) {
            if (method.isAnnotationPresent(UpdateMessage.class)) {
                UpdateMessage message = method.getAnnotation(UpdateMessage.class);

                new WISERHttpExecutorService().execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            method.invoke(class1,"","");
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                });
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            method.invoke(class1,"","");
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }

//                    }
//                }).start();
                try {
//                    Thread.sleep(4000);
//                    method.invoke(class1, "11");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

}
