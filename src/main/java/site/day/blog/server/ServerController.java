package site.day.blog.server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import site.day.blog.utils.ResponseAPI;

/**
 * @Description Server监控
 * @ClassName ServerController
 * @Author 23DAY
 * @Date 2022/9/14 22:12
 * @Version 1.0
 */
@RestController
public class ServerController
{
    /**
     * @Description 获取Server信息
     * @Author 23DAY
     * @Date 2022/10/19 16:09
     * @Param []
     * @return site.day.blog.utils.ResponseAPI<?>
     **/
    @GetMapping("/admin/server")
    public ResponseAPI<?> getServerInfo() throws Exception
    {
        Server server = new Server();
        server.copyTo();
        return ResponseAPI.success(server);
    }
}
