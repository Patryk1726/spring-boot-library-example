package pl.kurs.service.strategy;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@Service
public class StudentBookStrategy implements BookStrategy {
    @Override
    public BookPlan getBookPlan() {
        return BookPlan.STUDENT;
    }
    @Override
    public BigDecimal getBookPrice(BigDecimal baseDeposit) {
        BigDecimal discount = new BigDecimal("0.50");
        return baseDeposit.multiply(discount);
    }
}
