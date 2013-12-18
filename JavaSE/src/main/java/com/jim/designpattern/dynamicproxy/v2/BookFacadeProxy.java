package com.jim.designpattern.dynamicproxy.v2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 12/13/13
 * Time: 3:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class BookFacadeProxy implements InvocationHandler {
    private Object target;

    /**
     * 绑定委托对象并返回一个代理类
     *
     * @param target
     * @return
     */
    public <T> T bind(T target) {
        this.target = target;
        //取得代理对象
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);   //要绑定接口(这是一个缺陷，cglib弥补了这一缺陷)
    }

    /**
     * 调用方法
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //proxy 这里指 BookFacadeImpl 继承 Proxy 的一个对象
        Object result = null;
        TransactionType type = TransactionType.getInstance(method);
        try {
            before(type);
            result = method.invoke(target, args);
            return result;
        } finally {
            after(type);
        }
    }

    private void before(TransactionType type) {
        if (type == TransactionType.COMMIT) {
            System.out.println("打开事物");

        }
    }

    private void after(TransactionType type) {
        if (type == TransactionType.COMMIT) {
            System.out.println("提交事物");
        }
    }

    enum TransactionType {
        READ_ONLY, COMMIT;
        public static final String[] commitMethodPrefixSource = {"add", "save"};

        public static TransactionType getInstance(Method method) {
            for (String commitMethodPrefix : commitMethodPrefixSource) {
                if (method.getName().startsWith(commitMethodPrefix)) {
                    return COMMIT;
                }
            }
            return READ_ONLY;
        }
    }
}

