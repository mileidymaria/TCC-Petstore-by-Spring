package org.csu.mypetstore.repository.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.csu.mypetstore.domain.Category;
import org.springframework.stereotype.Repository;
import java.util.List;

@Mapper
public interface CategoryMapper {
    List<Category> getCategoryList();

    Category getCategory(String categoryId);
}
