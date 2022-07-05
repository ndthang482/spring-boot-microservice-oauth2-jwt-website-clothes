package savvycom.productservice.domain.entity.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.security.Timestamp;
import java.sql.Date;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity

@Table(name="product")

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String color;

    private String size;

    private Long productLineId;
    
    private Long price;

    private Long discountId;

    private Long active;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

}