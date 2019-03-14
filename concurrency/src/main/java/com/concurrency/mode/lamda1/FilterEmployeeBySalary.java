package com.concurrency.mode.lamda1;

public class FilterEmployeeBySalary implements  MyPredicate<Employee> {

    @Override
    public boolean test(Employee employee) {
        return employee.getSalary() > 50000;
    }
}
