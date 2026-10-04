package pl.kurs.request;

import pl.kurs.service.strategy.BookPlan;

public record BorrowBookRequest(BookPlan bookPlan) {
}
