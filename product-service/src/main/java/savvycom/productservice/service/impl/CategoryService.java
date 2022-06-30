package savvycom.productservice.service.impl;
//@Service hold the business handling code in it

import org.springframework.stereotype.Service;
import savvycom.productservice.domain.entity.product.Category;
import savvycom.productservice.repository.product.CategoryRepository;
import savvycom.productservice.service.product.ICategoryService;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

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

}
