package com.sh.redis.employee.controller;

import com.sh.redis.employee.entity.Employee;
import com.sh.redis.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 员工管理
 *
 *
 * @author 盛浩
 * @date 2021/1/2 14:55
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    /**
     * 查询员工
     *
     * @param id 员工id
     * @return 员工
     */
    @GetMapping(value = "/{id}")
    public Employee queryEmployee(@PathVariable Integer id) {
        return this.employeeService.queryEmployee(id);
    }


    /**
     * 新增员工
     *
     * @param employee 员工
     * @return 是否新增成功
     */
    @PostMapping
    public Boolean addEmployee(@RequestBody Employee employee) {
        return this.employeeService.save(employee);
    }

    /**
     * 修改员工
     *
     * @param employee 员工
     * @return 是否修改成功
     */
    @PutMapping
    public Boolean modifyEmployee(@RequestBody Employee employee) {
        return this.employeeService.modifyEmployee(employee);
    }

    /**
     * 删除员工
     *
     * @param id 员工id
     * @return 是否删除成功
     */
    @DeleteMapping(value = "/{id}")
    public Boolean delEmployee(@PathVariable Integer id) {
        return this.employeeService.delEmployee(id);
    }
}
