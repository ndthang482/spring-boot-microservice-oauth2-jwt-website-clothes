package savvycom.productservice.controller.product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import savvycom.productservice.common.Const;
import savvycom.productservice.controller.BaseController;
import savvycom.productservice.domain.entity.product.Product;
import savvycom.productservice.domain.entity.product.ProductLine;
import savvycom.productservice.domain.message.ResponseMessage;
import savvycom.productservice.service.product.IProductLineService;
import savvycom.productservice.service.product.IProductService;
import savvycom.productservice.utils.AppConstants;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController extends BaseController {
    private final IProductService productService;
    private final IProductLineService productLineService;

    /**
     * Create new Product
     * @return successResponse
     */
    @PostMapping("")
    @PreAuthorize("hasAuthority('admin')")
    @Operation(summary = "create new product")
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_OK_STR, description = "create completed",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMessage.class))})
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMessage.class))})
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMessage.class))})
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        productService.save(product);
        return successResponse("create completed");
    }

    /**
     * Delete Product by admin
     * @Param Long id
     * @return successResponse
     */
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('admin')")
    @Operation(summary = "Delete product by admin")
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_OK_STR, description = "delete completed",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMessage.class))})
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMessage.class))})
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMessage.class))})
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        productService.deleteById(id);
        return successResponse("Deleted successfully!");
    }
    /**
     * Insert Product by admin
     * @Param Long id
     * @return successResponse
     */
    @PutMapping("{id}")
    @PreAuthorize("hasAuthority('admin')")
    @Operation(summary = "Update product")
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_OK_STR, description = "Update completed",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMessage.class))})
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMessage.class))})
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMessage.class))})
    public ResponseEntity<?> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        product.setId(id);
        productService.save(product);
        return successResponse("Update completed");
    }

    /**
     * Find all productOutput by id
     * @Param Long id
     * @return successResponse with List<ProductOutput>
     */
    @GetMapping("/{id}")
    @Operation(summary = "Find ProductOutput By id")
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_OK_STR, description = "Find ProductOutput by id completed",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMessage.class))})
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMessage.class))})
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMessage.class))})
    public ResponseEntity<?> findProductOutputById(@PathVariable("id") Long id) {
        return successResponse(productService.findProductOutputById(id));
    }
    /**
     * Filter,search product, search productLine by categoryId, find product detail
     * @Param detail, categoryId, name, color, size, price, discountId
     * @return successResponse with ProductResponse
     */
    @GetMapping("")
    @Operation(summary = "Filter,search product")
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_OK_STR, description = "Find all Product completed",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMessage.class))})
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMessage.class))})
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMessage.class))})
    public ResponseEntity<?> findByProductResponse(
    @RequestParam(value = "detail", required = false) Long detail,
    @RequestParam(value = "categoryId", required = false) Long categoryId,
    @RequestParam(value = "name", required = false) String name,
    @RequestParam(value = "size", required = false) String size,
    @RequestParam(value = "color", required = false) String color,
    @RequestParam(value = "priceFrom", defaultValue = AppConstants.DEFAULT_PRICE_FROM, required = false) Long priceFrom,
    @RequestParam(value = "priceTo", defaultValue = AppConstants.DEFAULT_PRICE_TO, required = false) Long priceTo,
    @RequestParam(value = "discountId", required = false) Long discountId,
    @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
    @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
    @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
    @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir)
    {
        // find all productOutput
        if (detail == null && categoryId == null && name == null && size == null && color == null && discountId == null) {
            PageImpl<?> productResponse = productService.findAllProductResponse(pageNo, pageSize, sortBy, sortDir);
            return successResponse(productResponse);
        }
        // find product detail
         else if(detail != null && categoryId == null && name == null && size == null && color == null && discountId == null){
            return successResponse(productLineService.findProductLineDTOById(detail));
        }
         // find productLine by categoryId
         else if(detail == null && categoryId != null && name == null && size == null && color == null && discountId == null) {
            List<ProductLine> productLines1 = productLineService.findByCategoryId(categoryId);
            List<Long> productLineIds1 = productLines1.stream().map(productLine -> productLine.getId()).collect(Collectors.toList());
            PageImpl<?> productResponse1 = productService.findByProductLineId(productLineIds1, pageNo, pageSize, sortBy, sortDir);
            return successResponse(productResponse1);
        }
         // search name productLine
         else if(detail == null && categoryId == null && name != null && size == null && color == null && discountId == null){
            name = "%" + name + "%";
            List<ProductLine> productLines = productLineService.findByNameLike(name);
            List<Long> productLineIds = productLines.stream().map(productLine -> productLine.getId()).collect(Collectors.toList());
            PageImpl<?> productResponse2 = productService.findByProductLineId(productLineIds, pageNo, pageSize, sortBy, sortDir);
            return successResponse(productResponse2);
        }
         //filter color, size, price, discount
         else if(detail == null && categoryId == null && name == null && size != null && color != null && discountId != null){
            PageImpl<?> productResponse3 = productService.findByColorAndSizeAndPriceBetweenAndDiscountId(color, size,
                        priceFrom, priceTo, discountId, pageNo, pageSize, sortBy, sortDir);
            return successResponse(productResponse3);
        }
        //filter size
        else if(detail == null && categoryId == null && name == null && size != null && color == null && discountId == null){
            PageImpl<?> productResponse4 = productService.findProductBySizeAndPriceBetween(size,
                    priceFrom, priceTo, pageNo, pageSize, sortBy, sortDir);
            return successResponse(productResponse4);
        }
        //filter color
        else if(detail == null && categoryId == null && name == null && size == null && color != null  && discountId == null){
            PageImpl<?> productResponse5 = productService.findProductByColorAndPriceBetween(color,
                    priceFrom, priceTo, pageNo, pageSize, sortBy, sortDir);
            return successResponse(productResponse5);
        }
        //filter discount
        else if(detail == null && categoryId == null && name == null && size == null && color == null && discountId != null)
         {
            PageImpl<?> productResponse6 = productService.findProductByDiscountIdAndPriceBetween
                    (priceFrom, priceTo, discountId, pageNo, pageSize, sortBy, sortDir);
            return successResponse(productResponse6);
        }
        //filter color and size
         else if(detail == null && categoryId == null && name == null && size != null && color != null && discountId == null){
            PageImpl<?> productResponse7 = productService.findByColorAndSizeAndPriceBetween
                    (color, size, priceFrom, priceTo, pageNo, pageSize, sortBy, sortDir);
            return successResponse(productResponse7);
        }
        //filter color and discount
        else if(detail == null && categoryId == null && name == null && size == null && color != null && discountId != null){
            PageImpl<?> productResponse8 = productService.findByColorAndDiscountIdAndPriceBetween
                    (color, priceFrom, priceTo, discountId, pageNo, pageSize, sortBy, sortDir);
            return successResponse(productResponse8);
        }
        //filter size and discount
        else if(detail == null && categoryId == null && name == null && size != null && color == null && discountId != null){
            PageImpl<?> productResponse9 = productService.findBySizeAndDiscountIdAndPriceBetween
                    (size, priceFrom, priceTo, discountId, pageNo, pageSize, sortBy, sortDir);
            return successResponse(productResponse9);
        }

        else {
            return successResponse("Bad Request");
        }
    }

}
