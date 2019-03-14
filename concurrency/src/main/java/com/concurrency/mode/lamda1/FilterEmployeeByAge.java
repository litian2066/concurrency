package com.concurrency.mode.lamda1;

public class FilterEmployeeByAge implements MyPredicate<Employee> {

    public boolean test(Employee employee) {
        return employee.getAge() > 18;
    }
}
