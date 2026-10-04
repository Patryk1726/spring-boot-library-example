package pl.kurs.service.strategy;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@Service
public class StandardBookStrategy implements BookStrategy {
    @Override
    public BookPlan getBookPlan() {
        return BookPlan.STANDARD;
    }

    @Override
    public BigDecimal getBookPrice(BigDecimal baseDeposit) {
        BigDecimal discount = new BigDecimal("0.10");
        return baseDeposit.multiply(discount);
    }
}
