package com.sh.cache.employee.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sh.cache.employee.entity.Employee;

/**
 * 员工服务
 *
 *
 * @author 盛浩
 * @date 2021/1/2 15:08
 */
public interface EmployeeService extends IService<Employee> {

    /**
     * 查询员工
     *
     * @param id 员工id
     * @return 员工
     */
    Employee queryEmployee(Integer id);

    /**
     * 修改员工
     *
     * @param employee 员工
     * @return 员工
     */
    Employee modifyEmployee(Employee employee);

    /**
     * 删除员工
     *
     * @param id 员工id
     * @return 是否删除成功
     */
    Boolean delEmployee(Integer id);
}
