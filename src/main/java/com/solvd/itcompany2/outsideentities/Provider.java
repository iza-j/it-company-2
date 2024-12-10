package com.solvd.itcompany2.outsideentities;

import com.solvd.itcompany2.exceptions.NegativeNumberException;
import com.solvd.itcompany2.exceptions.NumberEqualToZeroException;

import static com.solvd.itcompany2.helpers.Formatter.*;
import static com.solvd.itcompany2.helpers.GlobalVariable.LOGGER;

public abstract class Provider implements PayableEntity { // an abstract class cannot be instantiated on its own

    protected String name;
    protected String phoneNumber;

    public abstract void phoneCall();

    @Override
    public void pay(double amount) throws NumberEqualToZeroException, NegativeNumberException {
        String exceptionMessage = new StringBuilder()
                .append("You can't pay anybody ")
                .append(amount)
                .append(" pln.")
                .toString();

        if (amount == 0) {
            throw new NumberEqualToZeroException(exceptionMessage);

        } else if (amount < 0) {
            throw new NegativeNumberException(exceptionMessage);

        } else {
            LOGGER.info(new StringBuilder()
                    .append(ansiColor(yellowFG, blackBG))
                    .append(" *ka-ching!* ")
                    .append(ansiColor(reset))
                    .append(" You pay ")
                    .append(this.name)
                    .append(" ")
                    .append(amount)
                    .append(" pln for their services."));
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}