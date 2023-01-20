package ml.springsecurity.springsecurity.controller;

import ml.springsecurity.springsecurity.dto.request.CategorieRequest;
import ml.springsecurity.springsecurity.dto.response.CategoryResponse;
import ml.springsecurity.springsecurity.entity.Category;
import ml.springsecurity.springsecurity.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategorieController {

    @Autowired
    private CategoryService categoryService;


    @PostMapping("/category")
    public ResponseEntity<CategoryResponse> saveCategory(@RequestBody CategorieRequest categorieRequest){
        CategoryResponse category = categoryService.saveCategory(categorieRequest);
        if(category == null){
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<CategoryResponse>(category,HttpStatus.OK);
    }

}
