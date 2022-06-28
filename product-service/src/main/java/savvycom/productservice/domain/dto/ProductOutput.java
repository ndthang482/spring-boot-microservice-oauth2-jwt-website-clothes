package savvycom.productservice.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import savvycom.productservice.domain.entity.Image;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductOutput {
    private Long id;
    @NotBlank(message = "color must not be null or empty")
    @Size(max = 256, message = "color only from 0 to 256")
    @Schema(description = "color of the product")
    private String color;
    @NotBlank(message = "Size must not be null or empty")
    @Size(max = 256, message = "Size only from 0 to 256")
    @Schema(description = "Size of the product")
    private String size;
    @NotNull(message = "ProductLineId must not be null")
    @Schema(description = "ProductLineId of the product")
    private Long productLineId;
    @NotNull(message = "Price must not be null")
    @Schema(description = "Price of the product")
    private Long price;
    @NotNull(message = "images must not be null")
    @Schema(description = "images of the product")
    private List<Image> images;
    @NotNull(message = "DiscountId must not be null")
    @Schema(description = "DiscountId of the product")
    private Long discountId;
    @NotNull(message = "Active must not be null")
    @Schema(description = "Active of the product")
    private Long active;

    private Date createdAt;

    private Date modifiedAt;
}
