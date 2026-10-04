package pl.kurs.response;

import java.math.BigDecimal;

public record BookResponse(Integer id, String title, String suthor, BigDecimal price, boolean isAvalible) {
}
