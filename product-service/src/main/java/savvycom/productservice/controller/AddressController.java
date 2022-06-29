//package savvycom.productservice.controller;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import savvycom.productservice.service.IAddressService;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/address")
//public class AddressController extends BaseController {
//    private IAddressService addressService;
//
//    @Autowired
//    public AddressController(IAddressService AddressService){
//        this.addressService=AddressService;
//    }
//
//    //find all address
//
//    @GetMapping("")
//    public ResponseEntity<?> findAll() {
//        return successResponse(addressService.findAll());
//    }
//
//    //find id by address
//
//    @GetMapping("/{id}")
//    public ResponseEntity<?> findById(@PathVariable Long id) {
//        return successResponse(addressService.findById(id));
//    }
//}
