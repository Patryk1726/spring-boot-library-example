package pl.kurs.service.strategy;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@Service
public class VipBookStrategy implements BookStrategy {
    @Override
    public BookPlan getBookPlan() {
        return BookPlan.VIP;
    }

    @Override
    public BigDecimal getBookPrice(BigDecimal baseDeposit) {
        BigDecimal discount = new BigDecimal("0.80");
        return baseDeposit.multiply(discount);
    }
}
