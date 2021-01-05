package com.sh.cache.employee.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sh.cache.employee.entity.Employee;
import com.sh.cache.employee.mapper.EmployeeMapper;
import com.sh.cache.employee.service.EmployeeService;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

/**
 * 员工业务
 *
 *
 * @author 盛浩
 * @date 2021/1/2 15:08
 */
@Service
@CacheConfig(cacheNames = "emp")
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

    /**
     * 查询员工
     *
     * Cacheable：查询缓存key是否存在，再调用查询数据库方法
     * 1、cacheNames/Value：缓存名称，将方法的缓存结果放入到那个缓存中，是数组方式，k可以指定多个缓存名
     * 2、key：缓存数据使用的key，可以使用它来指定，默认是使用方法参数的值
     * 3、keyGenerator：指定key的生成器，可以自己指定key的生成器组件id，key和keyGenerator二选一使用
     * 4、cacheManager：指定缓存管理器，或者cacheResolver指定获取解析器，二选一使用
     * 5、condition：符合条件下进行缓存
     * 6、unless：什么情况下不进行缓存
     * 7、sync：是否使用异步模式，默认是false，开启后不支持unless
     *
     * @param id 员工id
     * @return 员工
     */
    @Override
    @Cacheable(key = "#id", unless = "#result == null")
    public Employee queryEmployee(Integer id) {
        return this.getById(id);
    }

    /**
     * 修改员工
     *
     * CachePut：先调用更新数据库方法，再将更新的数据写入对应的key缓存中
     *
     * @param employee 员工
     * @return 是否修改成功
     */
    @Override
    @CachePut(key = "#employee.id", unless = "#result == null")
    public Employee modifyEmployee(Employee employee) {
        this.updateById(employee);
        return this.getById(employee.getId());
    }

    /**
     * 删除员工
     *
     * CacheEvict：
     * allEntries：默认false是否清楚缓存名称中的全部缓存
     * beforeInvocation：默认false方法执行完成进行清除缓存，true在方法前清除
     *
     * @param id 员工id
     * @return 是否删除成功
     */
    @Override
    @CacheEvict(key = "#id")
    public Boolean delEmployee(Integer id) {
        return this.removeById(id);
    }

    /**
     * 复杂缓存规则
     *
     * @param lastName 姓名
     * @return 员工
     */
    @Caching(
            cacheable = {
                    @Cacheable(key = "#lastName")
            },
            put = {
                    @CachePut(key = "#result.id")
            },
            evict = {
                    @CacheEvict(key = "#result.id")
            }
    )
    public Employee caching(String lastName) {
        return null;
    }
}
