package com.alien.kernel.utils;

/**
 * @Auther: FengYunJun
 * @Date: 2019/4/23 09:31
 * @Description:
 */
public class ClassLoaderTest extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }
}
