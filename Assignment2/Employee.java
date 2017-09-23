package com.company;
import java.util.*;

public class Employee {

    public static void main(String[] args) {

        double hours,a;
        System.out.println("Enter the number of hours worked");
        Scanner in=new Scanner(System.in);
        hours=in.nextDouble();
        Employee e=new Employee();
        a=e.employeeSalary(hours);
        System.out.print(a);

    }

    public double employeeSalary( double hours){
        double salary=0;
            if(hours<=36)
            {
                salary=hours*15.0;

            }
            else if(hours>36 && hours<=42 )
            {
                salary=hours*15*1.5;
            }
            else if(hours>42 && hours<=48)
            {
                salary=hours*15*2;
            }

            return salary;
    }
}
