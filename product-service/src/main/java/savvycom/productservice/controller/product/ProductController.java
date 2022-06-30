package savvycom.productservice.controller.product;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import savvycom.productservice.common.Const;
import savvycom.productservice.controller.BaseController;
import savvycom.productservice.domain.dto.ProductResponse;
import savvycom.productservice.domain.dto.ProductReviewResponse;
import savvycom.productservice.domain.entity.Review;
import savvycom.productservice.domain.entity.product.Category;
import savvycom.productservice.domain.entity.product.Product;
import savvycom.productservice.domain.entity.product.ProductLine;
import savvycom.productservice.domain.dto.ProductOutput;
import savvycom.productservice.domain.message.ResponseMessage;
import savvycom.productservice.service.IDiscountService;
import savvycom.productservice.service.IReviewService;
import savvycom.productservice.service.product.ICategoryService;
import savvycom.productservice.service.product.IProductLineService;
import savvycom.productservice.service.product.IProductService;
import savvycom.productservice.utils.AppConstants;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController extends BaseController {
    @Autowired
    private IProductService productService;
    @Autowired
    private IProductLineService productLineService;
    @Autowired
    private IReviewService reviewService;

    @Autowired
    public ProductController(IProductService ProductService) {
        this.productService = ProductService;
    }

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
     * Find all ProductOutput by productLineId
     * @Param Long id
     * @return successResponse with List<ProductOutput>
     */
    @GetMapping("/line/{id}")
    @Operation(summary = "Find product by productLine")
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_OK_STR, description = "Find Product by ProductLine completed",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMessage.class))})
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMessage.class))})
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMessage.class))})
    public ResponseEntity<?> findProductByProductLine(@PathVariable("id") Long id) {
        return successResponse(productService.findProductByProductLine(id));
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
     * Find all ProductResponse
     * @Param Long id
     * @return successResponse with List<ProductOutput>
     */
    @GetMapping("")
    @Operation(summary = "Find all ProductOutput")
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_OK_STR, description = "Find all ProductOutput completed",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMessage.class))})
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMessage.class))})
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMessage.class))})
    public ResponseEntity<?> findAllProductResponse(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
        return successResponse(productService.findAllProductResponse(pageNo, pageSize, sortBy, sortDir));
    }

    /**
     * Search ProductResponse by name in ProductLine
     * @Param String name
     * @return successResponse with ProductResponse
     */
    @GetMapping("/search")
    @Operation(summary = "Search name Product By ProductLine")
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_OK_STR, description = "Search name Product By ProductLine completed",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMessage.class))})
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMessage.class))})
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMessage.class))})
    public ResponseEntity<?> findByNameLike(
            @RequestParam String name,
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
        name = "%" + name + "%";
        List<ProductLine> productLines = productLineService.findByNameLike(name);
        List<Long> productLineIds = productLines.stream().map(productLine -> productLine.getId()).collect(Collectors.toList());
        ProductResponse productResponse = productService.findByProductLineId(productLineIds, pageNo, pageSize, sortBy, sortDir);
        return successResponse(productResponse);
    }
    /**
     * Filter feature ProductResponse by feature in product
     * @Param String name
     * @return successResponse with ProductResponse
     */
    @GetMapping("/filter")
    @Operation(summary = "Filter color, size, priceFrom, priceTo")
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_OK_STR, description = "Filter color, size, priceFrom, priceTo completed",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMessage.class))})
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMessage.class))})
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMessage.class))})
    public ResponseEntity<?> findByColorAndSizeAndPriceBetweenAndDiscountId(
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_SIZE, required = false) String size,
            @RequestParam(value = "color", defaultValue = AppConstants.DEFAULT_COLOR, required = false) String color,
            @RequestParam(value = "priceFrom", defaultValue = AppConstants.DEFAULT_PRICE_FROM, required = false) Long priceFrom,
            @RequestParam(value = "priceTo", defaultValue = AppConstants.DEFAULT_PRICE_TO, required = false) Long priceTo,
            @RequestParam(value = "discountId", defaultValue = AppConstants.DEFAULT_DISCOUNT_ID, required = false) Long discountId,
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
        ProductResponse productResponse = productService.findByColorAndSizeAndPriceBetweenAndDiscountId(color, size, priceFrom, priceTo, discountId, pageNo, pageSize, sortBy, sortDir);
        return successResponse(productResponse);
    }
    /**
     * Find all ProductResponse by categoryId from productLineId
     * @Param String name
     * @return successResponse with ProductResponse
     */
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<?> findCategoryByName(
    @PathVariable() Long categoryId,
    @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
    @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
    @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
    @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir)
    {
        List<ProductLine> productLines = productLineService.findByCategoryId(categoryId);
        List<Long> productLineIds = productLines.stream().map(productLine -> productLine.getId()).collect(Collectors.toList());
        ProductResponse productResponse = productService.findByProductLineId(productLineIds, pageNo, pageSize, sortBy, sortDir);
        return successResponse(productResponse);
    }
    @GetMapping("/review/{id}")
    public ResponseEntity<?> findReviewByProductId (
    @PathVariable() List<Long> id,
    @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
    @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
    @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
    @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir)
    {
        ProductReviewResponse productReviewResponse = productService.findProductDTOByReview(id, pageNo, pageSize, sortBy, sortDir);
        return successResponse(productReviewResponse);
    }

}
