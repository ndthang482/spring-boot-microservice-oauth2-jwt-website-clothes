package savvycom.productservice.controller.product;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import savvycom.productservice.controller.BaseController;
import savvycom.productservice.domain.entity.product.ProductLine;
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

    // find all productline

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        return successResponse(productLineService.findAll());
    }

    //pos: create newline by admin

    @PostMapping("")
    public ResponseEntity<?> newProductLine(@RequestBody ProductLine productLine){
        return successResponse(productLineService.save(productLine));
    }

    //find id by line

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return successResponse(productLineService.findById(id));
    }

    //put: update line

    @PutMapping("{id}")
    public ResponseEntity<?> updateProductLine(@RequestBody ProductLine productLine){
        return successResponse(productLineService.save(productLine));
    }

    //find all discount by line

//    @GetMapping("/discount/{id}")
//    public ResponseEntity<?> findAllDiscountByLine(@PathVariable("id") Long id){
//        return successResponse(productLineService.findAllDiscountByLine(id));
//    }
    //find all category by line

    @GetMapping("/category/{id}")
    public ResponseEntity<?> findAllCategoryByLine(@PathVariable("id") Long id){
        return successResponse(productLineService.findAllCategoryByLine(id));
    }

}
