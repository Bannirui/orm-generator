<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${dao_package_name}.${class_name}${dao_class_name_suffix}">
    <#if columns?exists>
    <resultMap id="BaseResultMap" type="${model_package_name}.${class_name}">
        <!--@Table ${table_name}-->
        <#list columns as col>
            <#if col.primaryKey>
        <id column="${col.lowerUnderscoreName}" jdbcType="${col.dbType}" property="${col.lowerCamelName}" />
            <#else>
        <result column="${col.lowerUnderscoreName}" jdbcType="${col.dbType}" property="${col.lowerCamelName}" />
            </#if>
        </#list>
    </resultMap>

    <sql id="Base_Column_List">
        <#list columns as col>
        ${col.lowerUnderscoreName}<#if col_has_next>, </#if>
        </#list>
    </sql>

    <#if primary_key?exists>
    <select id="selectByPrimaryKey" parameterType="${primary_key.jdkLangType}" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List" />
        from ${table_name}
        where ${primary_key.lowerUnderscoreName} = <#noparse>#{</#noparse>${primary_key.lowerCamelName}, jdbcType=${primary_key.dbType}<#noparse>}</#noparse>
    </select>

    <update id="updateByPrimaryKey" parameterType="${model_package_name}.${class_name}">
        update ${table_name}
        set
        <#list columns as col>
        <if test="${col.lowerCamelName} != null">
            ${col.lowerUnderscoreName} = <#noparse>#{</#noparse>${col.lowerCamelName}, jdbcType=${col.dbType}<#noparse>}</#noparse>,
        </if>
        </#list>
        where ${primary_key.lowerUnderscoreName} = <#noparse>#{</#noparse>${primary_key.lowerCamelName}, jdbcType=${primary_key.dbType}<#noparse>}</#noparse>
    </update>

    <delete id="deleteByPrimaryKey" parameterType="${primary_key.jdkLangType}">
        delete from ${table_name}
        where ${primary_key.lowerUnderscoreName} = <#noparse>#{</#noparse>${primary_key.lowerCamelName}, jdbcType=${primary_key.dbType}<#noparse>}</#noparse>
    </delete>
    </#if>

    <insert id="insert" keyColumn="${primary_key.lowerUnderscoreName}" keyProperty="${primary_key.lowerCamelName}" parameterType="${model_package_name}.${class_name}" useGeneratedKeys="true">
        insert into ${table_name} (
        <#list columns as col>
        ${col.lowerUnderscoreName}<#if col_has_next>, </#if>
        </#list>
        ) values (
        <#list columns as col>
        <#noparse>#{</#noparse>${col.lowerCamelName}, jdbcType=${col.dbType}<#noparse>}</#noparse><#if col_has_next>, </#if>
        </#list>
        )
    </insert>

    <insert id="insertSelective" keyColumn="${primary_key.lowerUnderscoreName}" keyProperty="${primary_key.lowerCamelName}" parameterType="${model_package_name}.${class_name}" useGeneratedKeys="true">
        insert into ${table_name}
        <trim prefix="(" suffix=")" suffixOverrides=",">
            <#list columns as col>
            <if test="${col.lowerCamelName} != null">
                ${col.lowerUnderscoreName}<#if col_has_next>, </#if>
            </if>
            </#list>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides=",">
            <#list columns as col>
            <if test="${col.lowerCamelName} != null">
                <#noparse>#{</#noparse>${col.lowerCamelName}, jdbcType=${col.dbType}<#noparse>}</#noparse><#if col_has_next>, </#if>
            </if>
            </#list>
        </trim>
    </insert>

    <update id="updateByPrimaryKeySelective" parameterType="${model_package_name}.${class_name}">
        update ${table_name}
        <set>
            <#list columns as col>
            <if test="${col.lowerCamelName} != null">
                ${col.lowerUnderscoreName} = <#noparse>#{</#noparse>${col.lowerCamelName}, jdbcType=${col.dbType}<#noparse>}</#noparse><#if col_has_next>, </#if>
            </if>
            </#list>
        </set>
        where ${primary_key.lowerUnderscoreName} = <#noparse>#{</#noparse>${primary_key.lowerCamelName}, jdbcType=${primary_key.dbType}<#noparse>}</#noparse>
    </update>
    </#if>
</mapper>