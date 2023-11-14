package ${model_package_name};

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ${table_name}.
<#if table_comment?exists>
 * ${table_comment}.
</#if>
 *
 * @since ${date}
 * @author ${author}
*/
public class ${class_name} implements Serializable {

    private static final long serialVersionUID = 1L;

<#if columns?exists>
    <#list columns as column>
    /**
    * ${column.comment}.
    */
        <#if (column.dbType = 'BIGINT' || column.dbType = 'BIGINT UNSIGNED')>
    private Long ${column.lowerCamelName};
        <#elseif (column.dbType = 'INT' || column.dbType = 'INT UNSIGNED' || column.dbType = 'INTEGER' || column.dbType = 'INTEGER UNSIGNED' || column.dbType = 'MEDIUMINT' || column.dbType = 'MEDIUMINT UNSIGNED' || column.dbType = 'SMALLINT' || column.dbType = 'SMALLINT UNSIGNED' || column.dbType = 'TINYINT' || column.dbType = 'TINYINT UNSIGNED')>
    private Integer ${column.lowerCamelName};
        <#elseif (column.dbType = 'BIT')>
    private Boolean ${column.lowerCamelName};
        <#elseif (column.dbType = 'BLOB')>
    private byte[] ${column.lowerCamelName};
        <#elseif (column.dbType = 'DECIMAL')>
    private BigDecimal ${column.lowerCamelName};
        <#elseif (column.dbType = 'DOUBLE')>
    private Double ${column.lowerCamelName};
        <#elseif (column.dbType = 'FLOAT')>
    private Float ${column.lowerCamelName};
        <#elseif (column.dbType = 'VARCHAR' || column.dbType = 'TEXT' || column.dbType = 'CHAR')>
    private String ${column.lowerCamelName};
        <#elseif column.dbType = 'TIMESTAMP' || column.dbType = 'YEAR' || column.dbType = 'DATE' || column.dbType = 'DATETIME' >
    private LocalDateTime ${column.lowerCamelName};
        <#else>
    private MISS ${column.lowerCamelName};
        </#if>
    </#list>

    public ${class_name}(){}
    <#list columns as column>
        <#if (column.dbType = 'BIGINT' || column.dbType = 'BIGINT UNSIGNED')>

    public Long get${column.upperCamelName}(){
        return this.${column.lowerCamelName};
    }

    public void set${column.upperCamelName}(Long ${column.lowerCamelName}){
        this.${column.lowerCamelName}=${column.lowerCamelName};
    }
        <#elseif (column.dbType = 'INT' || column.dbType = 'INT UNSIGNED' || column.dbType = 'INTEGER' || column.dbType = 'INTEGER UNSIGNED' || column.dbType = 'MEDIUMINT' || column.dbType = 'MEDIUMINT UNSIGNED' || column.dbType = 'SMALLINT' || column.dbType = 'SMALLINT UNSIGNED' || column.dbType = 'TINYINT' || column.dbType = 'TINYINT UNSIGNED')>

    public Integer get${column.upperCamelName}(){
        return this.${column.lowerCamelName};
    }

    public void set${column.upperCamelName}(Integer ${column.lowerCamelName}){
        this.${column.lowerCamelName}=${column.lowerCamelName};
    }
        <#elseif (column.dbType = 'BIT')>

    public Boolean get${column.upperCamelName}(){
        return this.${column.lowerCamelName};
    }

    public void set${column.upperCamelName}(Boolean ${column.lowerCamelName}){
        this.${column.lowerCamelName}=${column.lowerCamelName};
    }
        <#elseif (column.dbType = 'BLOB')>

    public byte[] get${column.upperCamelName}(){
        return this.${column.lowerCamelName};
    }

    public void set${column.upperCamelName}(byte[] ${column.lowerCamelName}){
        int sz = 0;
        if(${column.lowerCamelName} != null && (sz=${column.lowerCamelName}.length) != 0){
            this.${column.lowerCamelName} = new byte[sz];
            for(int i=0; i<sz; i++){
                this.${column.lowerCamelName}[i] = ${column.lowerCamelName}[i];
            }
        }
    }
        <#elseif (column.dbType = 'DECIMAL')>

    public DECIMAL get${column.upperCamelName}(){
        return this.${column.lowerCamelName};
    }

    public void set${column.upperCamelName}(DECIMAL ${column.lowerCamelName}){
        this.${column.lowerCamelName}=${column.lowerCamelName};
    }
        <#elseif (column.dbType = 'DOUBLE')>

    public Double get${column.upperCamelName}(){
        return this.${column.lowerCamelName};
    }

    public void set${column.upperCamelName}(Double ${column.lowerCamelName}){
        this.${column.lowerCamelName}=${column.lowerCamelName};
        }
        <#elseif (column.dbType = 'FLOAT')>

    public Float get${column.upperCamelName}(){
        return this.${column.lowerCamelName};
    }

    public void set${column.upperCamelName}(Float ${column.lowerCamelName}){
        this.${column.lowerCamelName}=${column.lowerCamelName};
    }
        <#elseif (column.dbType = 'VARCHAR' || column.dbType = 'TEXT' || column.dbType = 'CHAR')>

    public String get${column.upperCamelName}(){
        return this.${column.lowerCamelName};
    }

    public void set${column.upperCamelName}(String ${column.lowerCamelName}){
        this.${column.lowerCamelName}=${column.lowerCamelName};
    }
        <#elseif (column.dbType = 'TIMESTAMP' || column.dbType = 'YEAR' || column.dbType = 'DATE' || column.dbType = 'DATETIME')>

    public LocalDateTime get${column.upperCamelName}(){
        return this.${column.lowerCamelName};
    }

    public void set${column.upperCamelName}(LocalDateTime ${column.lowerCamelName}){
        this.${column.lowerCamelName}=${column.lowerCamelName};
    }
        <#else>
        </#if>
    </#list>
</#if>
}