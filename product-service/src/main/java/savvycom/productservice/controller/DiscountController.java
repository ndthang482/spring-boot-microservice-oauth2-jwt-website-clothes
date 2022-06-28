package savvycom.productservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import savvycom.productservice.domain.entity.Discount;
import savvycom.productservice.service.IDiscountService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/discount")
public class DiscountController extends BaseController{
    private IDiscountService discountService;

    @Autowired
    public DiscountController(IDiscountService DiscountService) {
        this.discountService = DiscountService;
    }

    //find all discount
    @GetMapping("")
    public ResponseEntity<?> findAll() {
        return successResponse(discountService.findAll());
    }

    //pos: create new discount by admin
    @PostMapping("")
    public ResponseEntity<?> newDiscount(@RequestBody Discount discount) {
        return successResponse(discountService.save(discount));
    }

    //find id by discount
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return successResponse(discountService.findById(id));
    }

    //put: update discount
    @PutMapping("{id}")
    public ResponseEntity<?> updateDiscount(@RequestBody Discount discount) {
        return successResponse(discountService.save(discount));
    }
}
