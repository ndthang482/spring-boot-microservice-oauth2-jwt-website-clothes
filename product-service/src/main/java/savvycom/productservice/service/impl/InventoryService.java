package savvycom.productservice.service.impl;
//@Service hold the business handling code in it

import org.springframework.stereotype.Service;
import savvycom.productservice.domain.dto.InventoryDTO;
import savvycom.productservice.domain.entity.product.Inventory;
import savvycom.productservice.repository.product.InventoryRepository;
import savvycom.productservice.service.product.IInventoryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryService implements IInventoryService {
    private InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public Inventory save(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    @Override
    public List<Inventory> findAll() {
        return inventoryRepository.findAll();
    }

    @Override
    public Inventory findById(Long id) {
        return inventoryRepository.findById(id).orElse(null);
    }

//    @Override
//    public List<Inventory> fineBranchByInventory(Long branchId) {
//        return inventoryRepository.findAll().stream()
//                .filter(inventory -> inventory.getBranchId() == branchId)
//                .collect(Collectors.toList());
//    }

    @Override
    public List<Inventory> findByBranchId(Long branchId) {
        return inventoryRepository.findByBranchId(branchId);
    }



    @Override
    public Inventory updateInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    @Override
    public Inventory findByBranchIdAndProductId(Long branchId, Long productId) {
        return inventoryRepository.findByBranchIdAndProductId(branchId, productId);
    }

    @Override
    public void updateQuantities(List<InventoryDTO> inventoryDTOs) {
        List<Inventory> inventories = inventoryDTOs.stream()
                .map(inventoryDTO -> {
                    Inventory inventory = inventoryRepository
                            .findByBranchIdAndProductId(inventoryDTO.getBranchId(), inventoryDTO.getProductId());
                    inventory.setQuantity(inventory.getQuantity() - inventoryDTO.getQuantity());
                    return inventory;
                })
                .collect(Collectors.toList());
        inventoryRepository.saveAll(inventories);
    }

    @Override
    public List<Inventory> findByProductId(Long productId) {
        return inventoryRepository.findByProductId(productId);
    }

    @Override
    public List<Inventory> findByQuantity(Long quantity) {
        return inventoryRepository.findByQuantity(quantity);
    }


}