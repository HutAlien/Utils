package com.alien.kernel.service.bridge;

import lombok.Data;

/**
 * @Auther: FengYunJun
 * @Date: 2019/5/15 16:14
 * @Description: 定义桥 持有ISourceable实例
 */
@Data
public abstract class Bridge {

    private ISourceable sourceAble;

    private void method() {
        sourceAble.method();
    }

}
