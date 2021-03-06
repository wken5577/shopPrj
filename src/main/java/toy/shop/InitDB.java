package toy.shop;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import toy.shop.entity.Category;
import toy.shop.repository.CategoryRepository;

import javax.annotation.PostConstruct;
import java.util.List;


@RequiredArgsConstructor
//@Component
public class InitDB {

    private final CategoryRepository categoryRepository;

    //@PostConstruct
    void init() {
        Category category1 = new Category("TOP");
        Category category2 = new Category("OUTER");
        Category category3 = new Category("KNIT");
        Category category4 = new Category("SHIRTS");
        Category category5 = new Category("PANTS");
        Category category6 = new Category("SHOES");

        categoryRepository.saveAll(List.of(category1, category2, category3, category4, category5, category6));
    }


}
