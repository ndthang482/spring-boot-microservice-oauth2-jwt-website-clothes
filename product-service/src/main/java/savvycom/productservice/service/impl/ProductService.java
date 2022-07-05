package savvycom.productservice.service.impl;
//@Service hold the business handling code in it

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import savvycom.productservice.domain.dto.*;
import savvycom.productservice.domain.entity.product.Product;
import savvycom.productservice.repository.product.ProductRepository;
import savvycom.productservice.service.IImageService;
import savvycom.productservice.service.IReviewService;
import savvycom.productservice.service.product.IInventoryService;
import savvycom.productservice.service.product.IProductLineService;
import savvycom.productservice.service.product.IProductService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private IImageService imageService;
    @Autowired
    private IReviewService reviewService;
    @Autowired
    private IProductLineService productLineService;
    @Autowired
    private IInventoryService inventoryService;
    public ProductService(ProductRepository productRepository, IImageService imageService) {
        this.productRepository = productRepository;
        this.imageService = imageService;
    }
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }
    @Override
    public void deleteById(Long id) {
        Product product= productRepository.findById(id).orElse(null);
        if(product != null){
            product.setActive(0L);
            save(product);
        }
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
                        .images(imageService.findByProductId(product.getProductLineId()))
                        .createdAt(LocalDateTime.now())
                        .modifiedAt(LocalDateTime.now())
                        .build())
                .collect(Collectors.toList());
    }


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
                .images(imageService.findByProductId(product.getProductLineId()))
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .build();
    }

    private ProductOutput mapToDTO(Product product) {
        ProductOutput productOutput = new ProductOutput();
        productOutput.setId(product.getId());
        productOutput.setColor(product.getColor());
        productOutput.setSize(product.getSize());
        productOutput.setProductLine(productLineService.findById(product.getId()));
        productOutput.setPrice(product.getPrice());
        productOutput.setImages(imageService.findByProductId(product.getProductLineId()));
        productOutput.setDiscountId(product.getDiscountId());
        productOutput.setActive(product.getActive());
        productOutput.setCreatedAt(LocalDateTime.now());
        productOutput.setModifiedAt(LocalDateTime.now());
        return productOutput;
    }
    private ProductOutput mapNewDTO(Product product) {
        ProductOutput productOutput = new ProductOutput();
        productOutput.setId(product.getId());
        productOutput.setColor(product.getColor());
        productOutput.setSize(product.getSize());
        productOutput.setProductLine(productLineService.findById(product.getId()));
        productOutput.setPrice(product.getPrice());
        productOutput.setImages(imageService.findByProductId(product.getProductLineId()));
        productOutput.setInventories(inventoryService.findByProductId(product.getProductLineId()));
        productOutput.setDiscountId(product.getDiscountId());
        productOutput.setActive(product.getActive());
        productOutput.setCreatedAt(LocalDateTime.now());
        productOutput.setModifiedAt(LocalDateTime.now());
        return productOutput;
    }

    private ProductDTO mapToDTONoProductLine(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setColor(product.getColor());
        productDTO.setSize(product.getSize());
        productDTO.setImages(imageService.findByProductId(product.getProductLineId()));
        productDTO.setPrice(product.getPrice());
        productDTO.setDiscountId(product.getDiscountId());
        productDTO.setReview(reviewService.findReviewByProductId(product.getId()));
        productDTO.setActive(product.getActive());
        productDTO.setCreatedAt(LocalDateTime.now());
        productDTO.setModifiedAt(LocalDateTime.now());

        return productDTO;
    }

    @Override
    public PageImpl<?> findAllProductResponse(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Product> products = productRepository.findAll(pageable);
        List<ProductOutput> content = products.getContent()
                .stream()
                .map(product -> mapToDTO(product))
                .collect(Collectors.toList());
        return new PageImpl<>(content, PageRequest.of(pageNo, pageSize, sort), products.getTotalElements());
    }
    @Override
    public PageImpl<?> findByProductLineId(List<Long> productLineId, int pageNo, int pageSize
            , String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Product> products = productRepository.findByProductLineIdIn(productLineId, pageable);
        List<ProductOutput> content = products.getContent()
                .stream()
                .map(product -> mapToDTO(product))
                .collect(Collectors.toList());
        return new PageImpl<>(content, PageRequest.of(pageNo, pageSize, sort), products.getTotalElements());
    }

    @Override
    public PageImpl<?> findByColorAndSizeAndPriceBetweenAndDiscountId(String color, String size, Long priceFrom
            , Long priceTo,Long discountId,int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Page<Product> products = productRepository.findByColorAndSizeAndPriceBetweenAndDiscountId(color, size
                , priceFrom, priceTo, discountId, PageRequest.of(pageNo, pageSize, sort));
        List<ProductOutput> content = products.getContent()
                .stream()
                .map(product -> mapToDTO(product))
                .collect(Collectors.toList());
        return new PageImpl<>(content, PageRequest.of(pageNo, pageSize, sort), products.getTotalElements());
    }

    @Override
    public List<ProductDTO> findListProductDTOByProductLineId(Long productLineId) {
        return productRepository.findByProductLineId(productLineId).stream().map(product ->
                mapToDTONoProductLine(product)).collect(Collectors.toList());
    }

    @Override
    public PageImpl<?> findProductByColor(String color, int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Page<Product> products = productRepository.findProductByColor(color, PageRequest.of(pageNo, pageSize, sort));
        List<ProductOutput> content = products.getContent()
                .stream()
                .map(product -> mapToDTO(product))
                .collect(Collectors.toList());
        return new PageImpl<>(content, PageRequest.of(pageNo, pageSize, sort), products.getTotalElements());
    }
    @Override
    public PageImpl<?> findProductBySize(String size, int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Page<Product> products = productRepository.findProductBySize(size, PageRequest.of(pageNo, pageSize, sort));
        List<ProductOutput> content = products.getContent()
                .stream()
                .map(product -> mapToDTO(product))
                .collect(Collectors.toList());
        return new PageImpl<>(content, PageRequest.of(pageNo, pageSize, sort), products.getTotalElements());
    }

    @Override
    public PageImpl<?> findProductByDiscountId(Long discountId, int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Page<Product> products = productRepository.findProductByDiscountId(discountId, PageRequest.of(pageNo, pageSize, sort));
        List<ProductOutput> content = products.getContent()
                .stream()
                .map(product -> mapToDTO(product))
                .collect(Collectors.toList());
        return new PageImpl<>(content, PageRequest.of(pageNo, pageSize, sort), products.getTotalElements());
    }

    @Override
    public PageImpl<?>findByColorAndSize(String color, String size, int pageNo, int pageSize, String sortBy, String sortDir)
    {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Page<Product> products = productRepository.findByColorAndSize(color, size, PageRequest.of(pageNo, pageSize, sort));
        List<ProductOutput> content = products.getContent()
                .stream()
                .map(product -> mapToDTO(product))
                .collect(Collectors.toList());
        return new PageImpl<>(content, PageRequest.of(pageNo, pageSize, sort), products.getTotalElements());
    }

    @Override
    public PageImpl<?> findByPriceBetween(Long priceFrom, Long priceTo, int pageNo, int pageSize, String sortBy, String sortDir)
    {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Page<Product> products = productRepository.findByPriceBetween(priceFrom, priceTo, PageRequest.of(pageNo, pageSize, sort));
        List<ProductOutput> content = products.getContent()
                .stream()
                .map(product -> mapToDTO(product))
                .collect(Collectors.toList());
        return new PageImpl<>(content, PageRequest.of(pageNo, pageSize, sort), products.getTotalElements());
    }

    @Override
    public List<ProductOutput> findProductOutput(Long id) {
        return productRepository.findAll().stream().map(product ->
                mapNewDTO(product)).collect(Collectors.toList());
    }


}
