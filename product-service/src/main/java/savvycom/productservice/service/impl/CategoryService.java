package savvycom.productservice.service.impl;
//@Service hold the business handling code in it

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import savvycom.productservice.domain.dto.CategoryDTO;
import savvycom.productservice.domain.entity.product.Category;
import savvycom.productservice.repository.product.CategoryRepository;
import savvycom.productservice.service.product.ICategoryService;
import savvycom.productservice.service.product.IInventoryService;
import savvycom.productservice.service.product.IProductService;

import java.util.List;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@Service
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepository;

    private final IProductService productService;

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }


    @Override
    public PageImpl<?> findByCategory(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Category> categories = categoryRepository.findAll(pageable);
        List<CategoryDTO> content = categories.getContent()
                .stream()
                .map(category -> mapToCategoryDTO(category))
                .collect(Collectors.toList());
        return new PageImpl<>(content, PageRequest.of(pageNo, pageSize, sort), categories.getTotalElements());
    }

    private CategoryDTO mapToCategoryDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setProductOutputs(productService.findProductOutput(category.getId()));
        return categoryDTO;
    }
}
