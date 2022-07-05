package savvycom.productservice.service.product;

import savvycom.productservice.domain.dto.InventoryDTO;
import savvycom.productservice.domain.entity.product.Inventory;

import java.util.List;

public interface IInventoryService {
    Inventory save(Inventory Inventory);

    List<Inventory> findAll();

    Inventory findById(Long id);

    List<Inventory> findByBranchId(Long branchId);

    Inventory updateInventory(Inventory inventory);
    Inventory findByBranchIdAndProductId(Long branchId, Long productId);

    void updateQuantities(List<InventoryDTO> inventoryDTOs);
    List<Inventory> findByProductId(Long productId);
    List<Inventory> findByQuantity(Long quantity);

}