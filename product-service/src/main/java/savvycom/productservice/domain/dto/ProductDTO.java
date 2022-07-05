package savvycom.productservice.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import savvycom.productservice.domain.entity.Image;
import savvycom.productservice.domain.entity.Review;
import savvycom.productservice.domain.entity.product.ProductLine;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    private Long id;

    private String color;

    private String size;

    private ProductLine productLine;

    private Long price;

    private List<Image> images;

    private Long discountId;

    private List<Review> review;

    private Long active;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;
}
