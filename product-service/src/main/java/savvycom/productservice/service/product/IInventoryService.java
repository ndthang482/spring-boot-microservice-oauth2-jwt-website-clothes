package savvycom.productservice.service.product;

import savvycom.productservice.domain.dto.InventoryDTO;
import savvycom.productservice.domain.dto.ProductQuantityDTO;
import savvycom.productservice.domain.entity.Branch;
import savvycom.productservice.domain.entity.product.Inventory;

import java.util.List;

public interface IInventoryService {
    Inventory save(Inventory Inventory);

    Inventory findById(Long id);

    Inventory updateInventory(Inventory inventory);
    Inventory findByBranchIdAndProductId(Long branchId, Long productId);

    void updateQuantities(List<InventoryDTO> inventoryDTOs);
    List<Inventory> findByProductId(Long productId);

    List<Inventory> findAll();
}