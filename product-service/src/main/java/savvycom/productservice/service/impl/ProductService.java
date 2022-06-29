package savvycom.productservice.service.impl;
//@Service hold the business handling code in it

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import savvycom.productservice.domain.dto.ProductResponse;
import savvycom.productservice.domain.entity.product.Product;
import savvycom.productservice.domain.dto.ProductOutput;
import savvycom.productservice.domain.entity.product.ProductLine;
import savvycom.productservice.repository.product.ProductRepository;
import savvycom.productservice.service.IImageService;
import savvycom.productservice.service.product.IProductLineService;
import savvycom.productservice.service.product.IProductService;

import java.util.List;
import java.util.stream.Collectors;
//class ProductService implements to interface IProductService
//bắt buộc rằng productService phải override all interface IProductService

@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;

    //nâng cấp của bean(khi không biết nó có chứa cái kia hay cái kia chứa nó)
    @Autowired
    private IImageService imageService;

    @Autowired
    private IProductLineService productLineService;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }
    //delete set(active)
    @Override
    public void delete(Long id) {
        Product product= productRepository.findById(id).orElse(null);
        if(product != null){
            product.setActive(0L);
            save(product);
        }
    }
    //find id by product
    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<ProductOutput> findProductByProductLine(Long productLineId) {
        return productRepository.findAll().stream()
                .filter(product -> product.getProductLineId() == productLineId)
                .map(product -> ProductOutput.builder()
                        .id(product.getId())
                        .color(product.getColor())
                        .size(product.getSize())
                        .productLine(productLineService.findById(product.getProductLineId()))
                        .price(product.getPrice())
                        .discountId(product.getDiscountId())
                        .active(product.getActive())
                        .images(imageService.findByProductId(product.getId()))
                        .createdAt(product.getCreatedAt())
                        .modifiedAt(product.getModifiedAt())
                        .build())
                .collect(Collectors.toList());
    }



    //productOutput được dựng lên và trả về Id của từng productOutput
    @Override
    public ProductOutput findProductOutputById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        return ProductOutput.builder()
                .id(product.getId())
                .color(product.getColor())
                .size(product.getSize())
                .productLine(productLineService.findById(product.getProductLineId()))
                .price(product.getPrice())
                .discountId(product.getDiscountId())
                .active(product.getActive())
                .images(imageService.findByProductId(product.getId()))
                .createdAt(product.getCreatedAt())
                .modifiedAt(product.getModifiedAt())
                .build();
    }


    private ProductOutput mapToDTO(Product product){
        ProductOutput productOutput = new ProductOutput();
        productOutput.setId(product.getId());
        productOutput.setProductLine(productLineService.findById(product.getProductLineId()));
        productOutput.setColor(product.getColor());
        productOutput.setSize(product.getSize());
        productOutput.setImages(imageService.findByProductId(product.getId()));
        productOutput.setPrice(product.getPrice());
        productOutput.setActive(product.getActive());
        productOutput.setDiscountId(product.getDiscountId());
        productOutput.setCreatedAt(product.getCreatedAt());
        productOutput.setModifiedAt(product.getModifiedAt());
        return productOutput;
    }

    private Product mapToEntity(ProductOutput productOutput){
        Product product = new Product();
        product.setColor(productOutput.getColor());
        product.setSize(productOutput.getSize());
        product.setProductLineId(productOutput.getProductLine().getId());
        product.setPrice(productOutput.getPrice());
        product.setDiscountId(productOutput.getDiscountId());
        product.setActive(productOutput.getActive());
        product.setCreatedAt(productOutput.getCreatedAt());
        product.setModifiedAt(productOutput.getModifiedAt());
        return product;
    }
    //map productOutputById find all productOutput (ProductImage)
    @Override
    public ProductResponse findAllProductResponse(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Product> products = productRepository.findAll(pageable);

        List<Product> ListOfProducts = products.getContent();

        List<ProductOutput> content = ListOfProducts.stream().map(product -> mapToDTO(product))
                .collect(Collectors.toList());

        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(content);
        productResponse.setPageNo(products.getNumber());
        productResponse.setPageSize(products.getSize());
        productResponse.setTotalElements(products.getTotalElements());
        productResponse.setTotalPages(products.getTotalPages());
        productResponse.setLast(products.isLast());

        return productResponse;
    }

    @Override
    public ProductResponse findAllByProductLineIds(List<Long> productLineIds, int pageNo, int pageSize
            , String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Product> products = productRepository.findByProductLineIdIn(productLineIds, pageable);
        List<ProductOutput> content = products.getContent().stream()
                .map(product -> mapToDTO(product)).collect(Collectors.toList());
        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(content);
        productResponse.setPageNo(products.getNumber());
        productResponse.setPageSize(products.getSize());
        productResponse.setTotalElements(products.getTotalElements());
        productResponse.setTotalPages(products.getTotalPages());
        productResponse.setLast(products.isLast());

        return productResponse;
    }

    @Override
    public ProductResponse findByColorAndSizeAndPriceBetweenAndDiscountId(String color, String size, Long priceFrom
            , Long priceTo,Long discountId,int pageNo, int pageSize
            , String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Page<Product> products = productRepository.findByColorAndSizeAndPriceBetweenAndDiscountId(color, size, priceFrom, priceTo, discountId,
                PageRequest.of(pageNo, pageSize, sort));
        List<ProductOutput> content = products.getContent().stream()
                .map(product -> mapToDTO(product)).collect(Collectors.toList());

        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(content);
        productResponse.setPageNo(products.getNumber());
        productResponse.setPageSize(products.getSize());
        productResponse.setTotalElements(products.getTotalElements());
        productResponse.setTotalPages(products.getTotalPages());
        productResponse.setLast(products.isLast());

        return productResponse;
    }

}
