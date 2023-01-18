package ${package.Parent}.pojo.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

<#if swagger>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>
<#if entityLombokModel>
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
    <#if chainModel>
import lombok.experimental.Accessors;
    </#if>
</#if>

import javax.validation.constraints.NotNull;

/**
 * @ClassName ${entity}VO
 * @Author ${author}
 * @Date ${date}
 * @Version 1.0
 */

<#if entityLombokModel>
@Data
@NoArgsConstructor
@AllArgsConstructor
    <#if chainModel>
@Accessors(chain = true)
    </#if>
</#if>
<#if swagger>
@ApiModel(value = "${entity}VO", description = "${table.comment!}")
</#if>
<#if superEntityClass??>
public class ${entity}VO extends ${superEntityClass}<#if activeRecord><${entity}></#if> {
<#elseif activeRecord>
public class ${entity}VO extends Model<${entity}> {
<#elseif entitySerialVersionUID>
public class ${entity}VO implements Serializable {
<#else>
public class ${entity}VO {
</#if>
<#if entitySerialVersionUID>

    private static final long serialVersionUID = 1L;
</#if>
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>

    <#if field.metaInfo.nullable||field.keyFlag>
    @NotNull(message = "${field.propertyName}不能为空")
    </#if>
    <#if field.comment!?length gt 0>
        <#if swagger>
    @ApiModelProperty(name = "${field.propertyName}", value = "${field.comment}", dataType = "${field.propertyType}")
        <#else>
    /**
     * ${field.comment}
     */
        </#if>
    </#if>
    private ${field.propertyType} ${field.propertyName};
</#list>
<#------------  END 字段循环遍历  ---------->

<#if !entityLombokModel>
    <#list table.fields as field>
        <#if field.propertyType == "boolean">
            <#assign getprefix="is"/>
        <#else>
            <#assign getprefix="get"/>
        </#if>
    public ${field.propertyType} ${getprefix}${field.capitalName}() {
        return ${field.propertyName};
    }

    <#if chainModel>
    public ${entity} set${field.capitalName}(${field.propertyType} ${field.propertyName}) {
    <#else>
    public void set${field.capitalName}(${field.propertyType} ${field.propertyName}) {
    </#if>
        this.${field.propertyName} = ${field.propertyName};
        <#if chainModel>
        return this;
        </#if>
    }
    </#list>
</#if>

<#if entityColumnConstant>
    <#list table.fields as field>
    public static final String ${field.name?upper_case} = "${field.name}";

    </#list>
</#if>
<#if activeRecord>
    @Override
    public Serializable pkVal() {
    <#if keyPropertyName??>
        return this.${keyPropertyName};
    <#else>
        return null;
    </#if>
    }

</#if>
<#if !entityLombokModel>
    @Override
    public String toString() {
        return "${entity}{" +
    <#list table.fields as field>
        <#if field_index==0>
            "${field.propertyName}=" + ${field.propertyName} +
        <#else>
            ", ${field.propertyName}=" + ${field.propertyName} +
        </#if>
    </#list>
        "}";
    }
</#if>
}
