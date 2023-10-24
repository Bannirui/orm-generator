package ${model_package_name};

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ${table_comment}.
 *
 * @since ${date}
 * @author ${author}
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ${class_name} implements Serializable {

    private static final long serialVersionUID = 1L;

<#if columns?exists>
    <#list columns as column>
    /**
    * ${column.comment}.
    */
        <#if (column.dbType = 'BIGINT')>
    private Long ${column.lowerCamelName};
        </#if>
        <#if (column.dbType = 'INT' || column.dbType = 'INT UNSIGNED' || column.dbType = 'TINYINT' || column.dbType = 'TINYINT UNSIGNED')>
    private Integer ${column.lowerCamelName};
        </#if>
        <#if (column.dbType = 'DECIMAL')>
    private BigDecimal ${column.lowerCamelName};
        </#if>
        <#if (column.dbType = 'VARCHAR' || column.dbType = 'TEXT' || column.dbType = 'CHAR')>
    private String ${column.lowerCamelName};
        </#if>
        <#if column.dbType = 'TIMESTAMP' || column.dbType = 'YEAR' || column.dbType = 'DATE' || column.dbType = 'DATETIME' >
    private LocalDateTime ${column.lowerCamelName};
        </#if>
        <#if (column.dbType != 'BIGINT'
        && column.dbType != 'INT'
        && column.dbType != 'DECIMAL'
        && column.dbType != 'VARCHAR'
        && column.dbType != 'TEXT'
        && column.dbType != 'CHAR'
        && column.dbType != 'TIMESTAMP'
        && column.dbType != 'YEAR'
        && column.dbType != 'DATE'
        && column.dbType != 'DATETIME'
        && column.dbType != 'INT UNSIGNED'
        && column.dbType != 'TINYINT'
        && column.dbType != 'TINYINT UNSIGNED')>
    private MISS ${column.lowerCamelName};
        </#if>

    </#list>
</#if>
}