package com.finapp.finance_calculator.fd;

public enum FdPayoutType {

	MONTHLY(12),
    QUARTERLY(4),
    HALF_YEARLY(2),
    YEARLY(1),
    AT_MATURITY(0);;

    private final int periods;

    FdPayoutType(int periods) {
        this.periods = periods;
    }

    public int getPeriods() {
        return periods;
    }
}
