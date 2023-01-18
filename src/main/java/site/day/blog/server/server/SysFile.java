package site.day.blog.server.server;

import lombok.Data;

/**
 * @Description SysFile
 * @ClassName SysFile
 * @Author 23DAY
 * @Date 2022/9/14 22:12
 * @Version 1.0
 */
@Data
public class SysFile
{
    /**
     * 盘符路径
     */
    private String dirName;

    /**
     * 盘符类型
     */
    private String sysTypeName;

    /**
     * 文件类型
     */
    private String typeName;

    /**
     * 总大小
     */
    private String total;

    /**
     * 剩余大小
     */
    private String free;

    /**
     * 已经使用量
     */
    private String used;

    /**
     * 资源的使用率
     */
    private double usage;
}
