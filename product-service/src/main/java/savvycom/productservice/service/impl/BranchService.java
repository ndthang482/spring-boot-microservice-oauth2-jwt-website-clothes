package savvycom.productservice.service.impl;
//@Service hold the business handling code in it

import org.springframework.stereotype.Service;
import savvycom.productservice.domain.dto.ProductQuantityDTO;
import savvycom.productservice.domain.entity.Branch;
import savvycom.productservice.domain.entity.product.Inventory;
import savvycom.productservice.repository.BranchRepository;
import savvycom.productservice.service.IBranchService;
import savvycom.productservice.service.product.IInventoryService;

import java.util.List;

@Service
public class BranchService implements IBranchService {
    private BranchRepository branchRepository;

    private IInventoryService inventoryService;

    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    @Override
    public Branch save(Branch branch) {
        return branchRepository.save(branch);
    }

    @Override
    public void delete(Long id) {
        branchRepository.deleteById(id);
    }

    @Override
    public List<Branch> findAll() {
        return branchRepository.findAll();
    }

    @Override
    public Branch findById(Long id) {
        return branchRepository.findById(id).orElse(null);
    }


}
