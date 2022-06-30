package savvycom.productservice.controller.product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import savvycom.productservice.common.Const;
import savvycom.productservice.controller.BaseController;
import savvycom.productservice.domain.entity.product.ProductLine;
import savvycom.productservice.domain.message.ResponseMessage;
import savvycom.productservice.service.product.IProductLineService;
import savvycom.productservice.service.product.IProductService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/line")
public class ProductLineController extends BaseController {
    private IProductLineService productLineService;

    private IProductService productService;
    @Autowired
    public ProductLineController(IProductLineService ProductLineService){
        this.productLineService=ProductLineService;
    }

    @GetMapping("")
    @Operation(summary = "Find all product_Line")
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_OK_STR, description = "Find all product_Line completed",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMessage.class))})
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMessage.class))})
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMessage.class))})
    public ResponseEntity<?> findAll() {
        return successResponse(productLineService.findAll());
    }

    @PostMapping("")
//    @PreAuthorize("hasAuthority('admin')")
    @Operation(summary = "Create product_Line")
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_OK_STR, description = "Create product_Line completed",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMessage.class))})
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMessage.class))})
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMessage.class))})
    public ResponseEntity<?> newProductLine(@RequestBody ProductLine productLine){
        return successResponse(productLineService.save(productLine));
    }
    @GetMapping("/{id}")
    @Operation(summary = "Find product_Line by id")
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_OK_STR, description = "Find product_Line by id completed",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMessage.class))})
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMessage.class))})
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMessage.class))})
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return successResponse(productLineService.findById(id));
    }
    @PutMapping("{id}")
//    @PreAuthorize("hasAuthority('admin')")
    @Operation(summary = "Update product_Line")
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_OK_STR, description = "Update product_Line completed",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMessage.class))})
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMessage.class))})
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMessage.class))})
    public ResponseEntity<?> updateProductLine(@RequestBody ProductLine productLine){
        return successResponse(productLineService.save(productLine));
    }

    @GetMapping("/category/{id}")
    @Operation(summary = "Find product_Line by categoryId")
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_OK_STR, description = "Find product_Line by categoryId completed",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMessage.class))})
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_BAD_REQUEST_STR, description = "Input invalid",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMessage.class))})
    @ApiResponse(responseCode = Const.API_RESPONSE.API_STATUS_INTERNAL_SERVER_ERROR_STR, description = "Internal Server Error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMessage.class))})
    public ResponseEntity<?> findAllCategoryByLine(@PathVariable("id") Long id){
        return successResponse(productLineService.findCategoryByLine(id));
    }

}
