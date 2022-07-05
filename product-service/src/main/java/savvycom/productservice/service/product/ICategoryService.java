package savvycom.productservice.service.product;
//handle bussiness
import org.springframework.data.domain.PageImpl;
import savvycom.productservice.domain.dto.CategoryDTO;
import savvycom.productservice.domain.entity.product.Category;

import java.util.List;

public interface ICategoryService {
    Category save(Category category);

    void delete(Long id);

    List<Category> findAll();

    Category findById(Long id);
    CategoryDTO findProductLineByCategory(Long id);

    PageImpl<?> findByCategory(int pageNo, int pageSize, String sortBy, String sortDir);
}
