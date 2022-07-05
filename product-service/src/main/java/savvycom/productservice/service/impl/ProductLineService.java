package savvycom.productservice.service.impl;
//@Service hold the business handling code in it

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import savvycom.productservice.domain.dto.CategoryDTO;
import savvycom.productservice.domain.dto.ProductLineDTO;
import savvycom.productservice.domain.dto.ProductOutput;
import savvycom.productservice.domain.entity.product.ProductLine;
import savvycom.productservice.repository.product.ProductLineRepository;
import savvycom.productservice.service.product.IProductLineService;
import savvycom.productservice.service.product.IProductService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductLineService implements IProductLineService {
    private final ProductLineRepository productLineRepository;

    private final IProductService productService;

    @Override
    public ProductLine save(ProductLine productLine) {
        return productLineRepository.save(productLine);
    }

    @Override
    public void deleteById(Long id) {
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
    public ProductLineDTO findDetailById(Long id) {
       ProductLine productLine = productLineRepository.findById(id).orElse(null);
       if(productLine != null){
           return ProductLineDTO.builder()
                   .id(productLine.getId())
                   .name(productLine.getName())
                   .desc(productLine.getDesc())
                   .categoryId(productLine.getCategoryId())
                   .productDTOs(productService.findListProductDTOByProductLineId(productLine.getId()))
                   .build();
       }
       return null;
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
