package savvycom.productservice.service.impl;
//@Service hold the business handling code in it

import org.springframework.stereotype.Service;
import savvycom.productservice.domain.entity.product.ProductLine;
import savvycom.productservice.repository.product.ProductLineRepository;
import savvycom.productservice.service.product.IProductLineService;

import java.util.List;

@Service
public class ProductLineService implements IProductLineService {
    private ProductLineRepository productLineRepository;

    public ProductLineService(ProductLineRepository productLineRepository) {
        this.productLineRepository = productLineRepository;
    }
    @Override
    public ProductLine save(ProductLine productLine) {
        return productLineRepository.save(productLine);
    }

    @Override
    public void delete(Long id) {
        productLineRepository.deleteById(id);
    }

    @Override
    public List<ProductLine> findAll() {
        return productLineRepository.findAll();
    }

    @Override
    public ProductLine findById(Long id) {
        return productLineRepository.findById(id).orElse(null);
    }

    @Override
    public List<ProductLine> findByCategoryId(Long categoryId) {
        return productLineRepository.findByCategoryId(categoryId);
    }

    @Override
    public List<ProductLine> findByNameLike(String name) {

        return productLineRepository.findByNameLike(name);
    }

}
