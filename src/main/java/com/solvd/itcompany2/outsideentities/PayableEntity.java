package com.solvd.itcompany2.outsideentities;

import com.solvd.itcompany2.exceptions.NegativeNumberException;
import com.solvd.itcompany2.exceptions.NumberEqualToZeroException;

public interface PayableEntity {

    void pay(double amount) throws NumberEqualToZeroException, NegativeNumberException;
}