package savvycom.productservice.controller.product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import savvycom.productservice.controller.BaseController;
import savvycom.productservice.domain.entity.product.Category;
import savvycom.productservice.service.product.ICategoryService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController extends BaseController {
    private ICategoryService categoryService;

    @Autowired
    public CategoryController(ICategoryService ProductCategoryService){
        this.categoryService=ProductCategoryService;
    }

    // find all category

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        return successResponse(categoryService.findAll());
    }

    //pos: create category

    @PostMapping("")
    public ResponseEntity<?> newCategory(@RequestBody Category category){
        return successResponse(categoryService.save(category));
    }

    //find id by category

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return successResponse(categoryService.findById(id));
    }

    //put: update category

    @PutMapping("{id}")
    public ResponseEntity<?> updateCategory(@RequestBody Category category){
        return successResponse(categoryService.save(category));
    }

}
