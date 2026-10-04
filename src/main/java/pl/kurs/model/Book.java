package pl.kurs.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@AllArgsConstructor
@Getter
@Setter
public class Book {
    private Integer id;
    private String title;
    private String author;
    private BigDecimal deposit;
    private boolean isAvalible;

}
