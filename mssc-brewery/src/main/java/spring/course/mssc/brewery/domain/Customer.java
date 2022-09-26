package spring.course.mssc.brewery.domain;

import static lombok.AccessLevel.PRIVATE;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = PRIVATE)
public class Customer {
    UUID id;
    String name;
}
