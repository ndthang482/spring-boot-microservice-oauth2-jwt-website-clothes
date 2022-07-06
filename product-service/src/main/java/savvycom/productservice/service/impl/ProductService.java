package savvycom.productservice.service.impl;
//@Service hold the business handling code in it

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import savvycom.productservice.domain.dto.*;
import savvycom.productservice.domain.entity.product.Product;
import savvycom.productservice.repository.product.ProductRepository;
import savvycom.productservice.service.IDiscountService;
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
    @Autowired
    IDiscountService discountService;
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
    public ProductOutput findProductOutputById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        return ProductOutput.builder()
                .id(product.getId())
                .color(product.getColor())
                .size(product.getSize())
                .productLine(productLineService.findById(product.getProductLineId()))
                .price(product.getPrice())
                .discount(discountService.findById(product.getDiscountId()))
                .inventories(inventoryService.findInventoryOutputByProductId(product.getId()))
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
        productOutput.setProductLine(productLineService.findById(product.getProductLineId()));
        productOutput.setPrice(product.getPrice());
        productOutput.setImages(imageService.findByProductId(product.getProductLineId()));
        productOutput.setDiscount(discountService.findById(product.getDiscountId()));
        productOutput.setInventories(inventoryService.findInventoryOutputByProductId(product.getId()));
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
        productOutput.setInventories(inventoryService.findInventoryOutputByProductId(product.getId()));
        productOutput.setDiscount(discountService.findById(product.getDiscountId()));
        productOutput.setActive(product.getActive());
        productOutput.setCreatedAt(LocalDateTime.now());
        productOutput.setModifiedAt(LocalDateTime.now());
        return productOutput;
    }
    private ProductResponse mapToProductResponse(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setColor(product.getColor());
        productResponse.setSize(product.getSize());
        productResponse.setProductLine(productLineService.findById(product.getProductLineId()));
        productResponse.setPrice(product.getPrice());
        productResponse.setImages(imageService.findByProductId(product.getProductLineId()));
        productResponse.setDiscount(discountService.findById(product.getDiscountId()));
        productResponse.setActive(product.getActive());
        productResponse.setCreatedAt(LocalDateTime.now());
        productResponse.setModifiedAt(LocalDateTime.now());
        return productResponse;
    }

    private ProductDTO mapToDTONoProductLine(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setColor(product.getColor());
        productDTO.setSize(product.getSize());
        productDTO.setImages(imageService.findByProductId(product.getProductLineId()));
        productDTO.setPrice(product.getPrice());
        productDTO.setDiscount(discountService.findById(product.getDiscountId()));
        productDTO.setInventories(inventoryService.findInventoryOutputByProductId(product.getId()));
        productDTO.setDiscount(discountService.findById(product.getDiscountId()));
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
        List<ProductResponse> content = products.getContent()
                .stream()
                .map(product -> mapToProductResponse(product))
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
        List<ProductResponse> content = products.getContent()
                .stream()
                .map(product -> mapToProductResponse(product))
                .collect(Collectors.toList());
        return new PageImpl<>(content, PageRequest.of(pageNo, pageSize, sort), products.getTotalElements());
    }

    @Override
    public List<ProductDTO> findListProductDTOByProductLineId(Long productLineId) {
        return productRepository.findByProductLineId(productLineId)
                .stream()
                .map(product ->
                mapToDTONoProductLine(product))
                .collect(Collectors.toList());
    }

    @Override
    public PageImpl<?> findProductByColorAndPriceBetween(String color, Long priceFrom,
             Long priceTo, int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Page<Product> products = productRepository.findProductByColorAndPriceBetween(color, priceFrom, priceTo,
                PageRequest.of(pageNo, pageSize, sort));
        List<ProductResponse> content = products.getContent()
                .stream()
                .map(product -> mapToProductResponse(product))
                .collect(Collectors.toList());
        return new PageImpl<>(content, PageRequest.of(pageNo, pageSize, sort), products.getTotalElements());
    }
    @Override
    public PageImpl<?> findProductBySizeAndPriceBetween(String size, Long priceFrom, Long priceTo, int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Page<Product> products = productRepository.findProductBySizeAndPriceBetween(size, priceFrom, priceTo,
                PageRequest.of(pageNo, pageSize, sort));
        List<ProductResponse> content = products.getContent()
                .stream()
                .map(product -> mapToProductResponse(product))
                .collect(Collectors.toList());
        return new PageImpl<>(content, PageRequest.of(pageNo, pageSize, sort), products.getTotalElements());
    }

    @Override
    public PageImpl<?> findProductByDiscountIdAndPriceBetween(Long discountId,Long priceFrom, Long priceTo, int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Page<Product> products = productRepository.findProductByDiscountIdAndPriceBetween(discountId, priceFrom, priceTo
                , PageRequest.of(pageNo, pageSize, sort));
        List<ProductResponse> content = products.getContent()
                .stream()
                .map(product -> mapToProductResponse(product))
                .collect(Collectors.toList());
        return new PageImpl<>(content, PageRequest.of(pageNo, pageSize, sort), products.getTotalElements());
    }

    @Override
    public PageImpl<?>findByColorAndSizeAndPriceBetween(String color, String size, Long priceFrom, Long priceTo,
             int pageNo, int pageSize, String sortBy, String sortDir)
    {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Page<Product> products = productRepository.findByColorAndSizeAndPriceBetween(color, size, priceFrom, priceTo,
                 PageRequest.of(pageNo, pageSize, sort));
        List<ProductResponse> content = products.getContent()
                .stream()
                .map(product -> mapToProductResponse(product))
                .collect(Collectors.toList());
        return new PageImpl<>(content, PageRequest.of(pageNo, pageSize, sort), products.getTotalElements());
    }
    @Override
    public PageImpl<?>findByColorAndDiscountIdAndPriceBetween(String color, Long priceFrom, Long priceTo
            ,Long discountId, int pageNo, int pageSize, String sortBy, String sortDir)
    {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Page<Product> products = productRepository.findByColorAndDiscountIdAndPriceBetween(color, priceFrom, priceTo,
                 discountId, PageRequest.of(pageNo, pageSize, sort));
        List<ProductResponse> content = products.getContent()
                .stream()
                .map(product -> mapToProductResponse(product))
                .collect(Collectors.toList());
        return new PageImpl<>(content, PageRequest.of(pageNo, pageSize, sort), products.getTotalElements());
    }

    @Override
    public PageImpl<?> findBySizeAndDiscountIdAndPriceBetween(String size
            ,Long priceFrom, Long priceTo, Long discountId, int pageNo, int pageSize, String sortBy, String sortDir)
    {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Page<Product> products = productRepository.findBySizeAndDiscountIdAndPriceBetween(size, priceFrom, priceTo,
                discountId, PageRequest.of(pageNo, pageSize, sort));
        List<ProductResponse> content = products.getContent()
                .stream()
                .map(product -> mapToProductResponse(product))
                .collect(Collectors.toList());
        return new PageImpl<>(content, PageRequest.of(pageNo, pageSize, sort), products.getTotalElements());
    }

    @Override
    public List<ProductOutput> findProductOutput(Long id) {
        return productRepository.findAll().stream().map(product ->
                mapNewDTO(product)).collect(Collectors.toList());
    }

}