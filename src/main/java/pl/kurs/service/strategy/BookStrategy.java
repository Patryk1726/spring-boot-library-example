package pl.kurs.service.strategy;

import java.math.BigDecimal;

public interface BookStrategy {
    BookPlan getBookPlan();
    BigDecimal getBookPrice(BigDecimal baseDeposit);
}
