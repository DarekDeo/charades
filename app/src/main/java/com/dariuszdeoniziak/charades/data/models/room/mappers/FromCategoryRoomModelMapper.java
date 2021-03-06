package com.dariuszdeoniziak.charades.data.models.room.mappers;

import com.dariuszdeoniziak.charades.data.models.Category;
import com.dariuszdeoniziak.charades.data.models.room.CategoryRoomModel;
import com.dariuszdeoniziak.charades.utils.Mapper;


public class FromCategoryRoomModelMapper implements Mapper<CategoryRoomModel, Category> {

    @Override
    public Category map(CategoryRoomModel categoryRoomModel) {
        Category category = new Category();
        category.id = categoryRoomModel.id;
        category.name = categoryRoomModel.name;
        category.description = categoryRoomModel.description;
        return category;
    }
}
