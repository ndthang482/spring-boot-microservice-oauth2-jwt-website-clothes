package savvycom.productservice.controller.product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import savvycom.productservice.common.Const;
import savvycom.productservice.controller.BaseController;
import savvycom.productservice.domain.entity.product.Category;
import savvycom.productservice.domain.message.ResponseMessage;
import savvycom.productservice.service.product.ICategoryService;
import savvycom.productservice.utils.AppConstants;


@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController extends BaseController {
    @Autowired
    private ICategoryService categoryService;

    /**
     * Create new Product category
     * @return successResponse
     */
    @PostMapping("")
    @PreAuthorize("hasAuthority('admin')")
    @Operation(summary = "Create new product category")
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_OK_STR, description = "Create new category completed",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMessage.class))})
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMessage.class))})
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMessage.class))})
    public ResponseEntity<?> newCategory(@RequestBody Category category){
        return successResponse(categoryService.save(category));
    }
    /**
     * Update new Product category
     * @return successResponse
     */
    @PutMapping("{id}")
    @PreAuthorize("hasAuthority('admin')")
    @Operation(summary = "Update product category by id")
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_OK_STR, description = "Update category by id completed",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMessage.class))})
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMessage.class))})
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMessage.class))})
    public ResponseEntity<?> updateCategory(@RequestBody Category category){
        return successResponse(categoryService.save(category));
    }
    /**
     * Find all Product category
     * @Param pageNo, pageSize, sortBy, sortDir
     * @return successResponse
     */
    @GetMapping("")
    @Operation(summary = "Find all product by category")
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_OK_STR, description = "Update category by id completed",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMessage.class))})
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMessage.class))})
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMessage.class))})
    public ResponseEntity<?> findByCategory(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir)
    {
        return successResponse(categoryService.findByCategory(pageNo, pageSize , sortBy, sortDir));
    }

}
