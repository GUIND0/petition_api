package ml.springsecurity.springsecurity.service;

import ml.springsecurity.springsecurity.dto.request.CategorieRequest;
import ml.springsecurity.springsecurity.dto.response.CategoryResponse;
import ml.springsecurity.springsecurity.entity.Category;
import ml.springsecurity.springsecurity.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    public CategoryResponse saveCategory(CategorieRequest categorieRequest){
        Category category = new Category();
        category.setTitle(categorieRequest.getTitle());
        categoryRepository.save(category);
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setTitle(category.getTitle());
        return  categoryResponse;
    }


}
