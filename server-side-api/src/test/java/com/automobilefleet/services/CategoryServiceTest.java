//package com.automobilefleet.services;
//
//import com.automobilefleet.entities.Category;
//import com.automobilefleet.repositories.CategoryRepository;
//import com.automobilefleet.utils.CategoryUtils;
//import com.automobilefleet.utils.Constants;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.modelmapper.ModelMapper;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.Arrays;
//import java.util.List;
//
//@ExtendWith(SpringExtension.class)
//class CategoryServiceTest {
//
//    @InjectMocks
//    private CategoryService categoryService;
//
//    @Mock
//    private CategoryRepository categoryRepository;
//
//    @Mock
//    private ModelMapper mapper;
//    private Category categoryUnderTest;
//
//
//    @BeforeEach
//    void setup() {
//        this.categoryUnderTest = CategoryUtils.ofCategory();
//    }
//
//    @Test
//    void shouldReturnListOfCategoryWhenCallingListBrand() {
//        List<Category> categoriesForTests = Arrays.asList(
//                new Category("Category Test 01", "Description 01"),
//                new Category("Category Test 02", "Description 02"),
//                new Category("Category Test 03", "Description 03")
//        );
//
//        Mockito.when(this.categoryRepository.findAll()).thenReturn(categoriesForTests);
//
//        String test = "string_%d";
//
//        System.out.println(test+2);
//
////        List<CategoryResponse> expected = Arrays.asList(
////          new CategoryResponse(1L, "")
////        );
//    }
//
//
//}
