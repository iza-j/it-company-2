package com.solvd.itcompany2.corporatestructure;

import java.util.HashSet;

public interface CorporateUnit {

    HashSet<Employee> getAllEmployees();

    String getName();
}