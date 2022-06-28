package savvycom.productservice.service.product;
//handle bussiness
import savvycom.productservice.domain.entity.product.Category;

import java.util.List;

public interface ICategoryService {
    Category save(Category category);

    void delete(Long id);

    List<Category> findAll();

    Category findById(Long id);
}
