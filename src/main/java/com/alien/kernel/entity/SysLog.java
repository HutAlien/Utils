package com.alien.kernel.entity;

import lombok.Data;
import org.nutz.dao.entity.annotation.*;

import java.util.Date;

/**
 * @Auther: FengYunJun
 * @Date: 2019/2/12 13:53
 * @Description:
 */
@Data
@Table("u_sys_log")
public class SysLog {
    @Id
    @Column
    private long id;

    @Column
    @Comment("操作类型")
    private String operateType;

    @Column
    @Comment("执行方法")
    @ColDefine(width = 255)
    private String method;

    @Column
    @Comment("日志描述")
    private String description;

    @Column("log_type")
    @Comment("日志类型 1 操作日志 2 异常日志")
    private String logType;

    @Column("request_ip")
    @Comment("请求IP")
    private String requestIp;

    @Column("exception_code")
    @Comment("异常代码")
    private String exceptionCode;

    @Column("exception_detail")
    @Comment("异常详情")
    private String exceptionDetail;

    @Column("request_params")
    @Comment("请求参数")
    private String requestParams;

    @Column("operate_user")
    @Comment("操作用户")
    private String operateUser;

    @Column("operate_time")
    @Comment("操作时间")
    private Date operateTime;
}
