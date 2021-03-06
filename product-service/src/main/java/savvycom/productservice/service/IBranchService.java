package savvycom.productservice.service;

import savvycom.productservice.domain.entity.Branch;

import java.util.List;

public interface IBranchService {
    Branch save(Branch branch);

    void delete(Long id);

    List<Branch> findAll();

    Branch findById(Long id);
}
