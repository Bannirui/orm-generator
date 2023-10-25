package ${dao_package_name};

import ${model_package_name}.${class_name};

/**
* ${table_name}.
<#if table_comment?exists>
* ${table_comment}.
</#if>
*
* @since ${date}
* @author ${author}
*/
public interface ${class_name}${dao_class_name_suffix} {

<#if primary_key?exists>
    ${class_name} selectByPrimaryKey(${primary_key.jdkType} ${primary_key.lowerCamelName});

    int updateByPrimaryKey(${class_name} record);

    int deleteByPrimaryKey(${primary_key.jdkType} ${primary_key.lowerCamelName});

</#if>
    int insert(${class_name} record);

    int insertSelective(${class_name} record);

    int updateByPrimaryKeySelective(${class_name} record);
}