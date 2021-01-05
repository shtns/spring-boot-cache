package com.sh.redis.employee.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sh.redis.employee.entity.Employee;
import com.sh.redis.employee.mapper.EmployeeMapper;
import com.sh.redis.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 员工业务
 *
 *
 * @author 盛浩
 * @date 2021/1/2 15:08
 */
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

    private final RedisTemplate<String, Employee> redisTemplate;

    /**
     * 查询员工
     *
     * @param id 员工id
     * @return 员工
     */
    @Override
    public Employee queryEmployee(Integer id) {

        Employee employee = null;

        String keyName =  "employee：".concat(id.toString());
        if (this.redisTemplate.hasKey(keyName)) {
            employee = this.redisTemplate.opsForValue().get(keyName);
        } else {
             employee = this.getById(id);
            if (employee != null) {
                this.redisTemplate.opsForValue().set(keyName, employee);
            }
        }

        return employee;
    }

    /**
     * 修改员工
     *
     * @param employee 员工
     * @return 是否修改成功
     */
    @Override
    public Boolean modifyEmployee(Employee employee) {
        String keyName =  "employee：".concat(employee.getId().toString());
        boolean flag = this.updateById(employee);
        this.redisTemplate.opsForValue().set(keyName, this.getById(employee.getId()));
        this.updateById(employee);
        return flag;
    }

    /**
     * 删除员工
     *
     * @param id 员工id
     * @return 是否删除成功
     */
    @Override
    public Boolean delEmployee(Integer id) {
        String keyName =  "employee：".concat(id.toString());
        this.redisTemplate.delete(keyName);
        return this.removeById(id);
    }
}
