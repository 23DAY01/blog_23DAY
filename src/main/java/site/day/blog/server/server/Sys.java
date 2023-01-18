package site.day.blog.server.server;

import lombok.Data;

/**
 * @Description Sys
 * @ClassName Sys
 * @Author 23DAY
 * @Date 2022/9/14 22:12
 * @Version 1.0
 */
@Data
public class Sys
{
    /**
     * 服务器名称
     */
    private String computerName;

    /**
     * 服务器Ip
     */
    private String computerIp;

    /**
     * 项目路径
     */
    private String userDir;

    /**
     * 操作系统
     */
    private String osName;

    /**
     * 系统架构
     */
    private String osArch;

}
