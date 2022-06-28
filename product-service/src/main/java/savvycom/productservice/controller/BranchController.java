package savvycom.productservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import savvycom.productservice.domain.entity.Branch;
import savvycom.productservice.service.IBranchService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/branch")
public class BranchController extends BaseController{
    private IBranchService branchService;

    @Autowired
    public BranchController(IBranchService BranchService){
        this.branchService=BranchService;
    }

    //find all branch

    @GetMapping("")
    public ResponseEntity<?> findAll(){
        return successResponse(branchService.findAll());
    }

    //pos: create newbranch

    @PostMapping("")
    public ResponseEntity<?> newBranch(@RequestBody Branch branch){
        return successResponse(branchService.save(branch));
    }

    //find id by branch
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return successResponse(branchService.findById(id));
    }

    //put: update branch
    @PutMapping("{id}")
    public ResponseEntity<?> updateBranch(@RequestBody Branch branch){
        return successResponse(branchService.save(branch));
    }
}
