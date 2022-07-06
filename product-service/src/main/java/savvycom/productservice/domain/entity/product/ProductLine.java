package savvycom.productservice.domain.entity.product;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.security.Timestamp;
import java.sql.Date;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name="product_line")
public class ProductLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String desc;

    private Long categoryId;

    private Long discountId;

    private Long active;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;


}
