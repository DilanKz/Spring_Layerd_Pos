package lk.ijse.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

/**
 * `@authority Tharindu Dilan`
 * 8:01 PM
 * 2023-10-10 - 10 - 2023
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class SavedOrdersDTO {
    private String oid;
    private LocalDate date;
    private String name;
}
