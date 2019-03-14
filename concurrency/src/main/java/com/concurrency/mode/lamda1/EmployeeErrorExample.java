package com.concurrency.mode.lamda1;

import com.concurrency.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.List;

/**
 * 这是一种很冗杂的作法 如果要加入年龄超过18的这个条件又需要写一套
 * 可以发现弊端：当需求有变更 新增过滤条件　又需要遍历一遍 if判断
 * 实际需要的代码只有一句if (employee.getAge() > 18)  却要写大量冗余代码 很恶心
 * 使用策略模式
 */
public class EmployeeErrorExample {

    public static void main(String[] args) {
        new EmployeeErrorExample().test();
    }

    // 获取工资大于5000的员工
    public List<Employee> filterEmployeeBySalary(List<Employee> employees) {
        List<Employee> list = new ArrayList<Employee>();
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getSalary() > 5000) {
                list.add(employees.get(i));
            }
        }
        return list;
    }

    // 1.优化方式 策略设计模式
    public List<Employee> filterEmployee(List<Employee> employees, MyPredicate<Employee> myPredicate) {
        List<Employee> list = new ArrayList<Employee>();
        for (Employee employee : employees) {
            if (myPredicate.test(employee)) {
                list.add(employee);
            }
        }
        return list;
    }


    public void test() {
        List<Employee> list = new ArrayList<Employee>();
        Employee employee = new Employee();
        employee.setAge(12);
        employee.setSalary(5001d);
        list.add(employee);
        List<Employee> list2 = filterEmployee(list, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return true;
            }

        });

        // List<Employee> list3 = filterEmployee(list, (e) -> e.getSalary() >= 5000);
        // List<Employee> list3 = filterEmployee(list, (e) -> e.getSalary() >= 5000);
        // System.out.println(list3);
    }

}
