package com.dariuszdeoniziak.charades.views;

import com.dariuszdeoniziak.charades.models.Category;

import java.util.List;

public interface CategoryListView {

    void showProgressIndicator();
    void showCategories(List<Category> categories);
    void showEmptyList();
}