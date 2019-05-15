package com.alien.kernel.service.bridge;

import com.alien.kernel.service.bridge.impl.SourceSub1;
import com.alien.kernel.service.bridge.impl.SourceSub2;

/**
 * @Auther: FengYunJun
 * @Date: 2019/5/15 16:18
 * @Description:
 */
public class MyBridge extends Bridge {

    public void method() {
        getSourceAble().method();
    }


    public static void main(String[] args) {
        Bridge bridge = new MyBridge();
        //
        ISourceable sourceable = new SourceSub1();
        bridge.setSourceAble(sourceable);
        ((MyBridge) bridge).method();
        //
        ISourceable sourceable1 = new SourceSub2();
        bridge.setSourceAble(sourceable1);
        ((MyBridge) bridge).method();
    }
}
