package ${package.Controller};

import ${package.Service}.${table.serviceName};
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import ${package.Parent}.utils.ResponseAPI;

/**
 * @Description ${entity}控制器
 * @ClassName ${table.controllerName}
 * @Author ${author}
 * @Date ${date}
 * @Version 1.0
 */
@Slf4j
@Api(tags = "${entity?uncap_first}模块")
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

    @Autowired
    public ${table.serviceName} ${entity?uncap_first}Service;

    /**
     * @Description 根据id查询
     * @Author ${author}
     * @Date ${date}
     * @Param [java.lang.Integer]
     * @Return ${package.Parent}.utils.ResponseAPI<?>
     **/
    @ApiOperation(value = "根据id查询${entity?uncap_first}", notes = "根据id查询${entity?uncap_first}")
    @GetMapping("/${entity?uncap_first}s/{id}")
    public ResponseAPI<?> get${entity}ById(
            @ApiParam(name = "id", value = "主键", required = true)
            @PathVariable("id")
                    Integer id) {
        return ResponseAPI.success(${entity?uncap_first}Service.getById(id));
    }

}
</#if>
