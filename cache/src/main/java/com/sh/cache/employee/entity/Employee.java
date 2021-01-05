package com.sh.cache.employee.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;

/**
 * 员工
 *
 *
 * @author 盛浩
 * @date 2021/1/2 14:46
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Employee extends Model<Employee> implements Serializable {

    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 名字
     */
    @TableField(value = "lastName")
    private String lastName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 性别（0男    1女）
     */
    private Integer gender;

    /**
     * 部门id
     */
    private Integer dId;
}
