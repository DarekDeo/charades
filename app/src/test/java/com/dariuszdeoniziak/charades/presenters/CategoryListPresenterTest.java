package com.dariuszdeoniziak.charades.presenters;

import com.dariuszdeoniziak.charades.models.Category;
import com.dariuszdeoniziak.charades.models.interactors.ModelInteractor;
import com.dariuszdeoniziak.charades.views.CategoryListView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

public class CategoryListPresenterTest {

    @Mock List<Category> categories;
    @Mock CategoryListView view;
    @Mock ModelInteractor modelInteractor;
    CategoryListPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new CategoryListPresenter(modelInteractor);
        presenter.onTakeView(view);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void loadCategoriesCallsShowCategories() {
        when(presenter.modelInteractor.getCategories())
                .thenReturn(categories);
        presenter.loadCategories();
        verify(modelInteractor).getCategories();
        verify(view).showProgressIndicator();
        verify(view).showCategories(categories);
    }

    @Test
    public void loadCategoriesCallsShowEmptyList() {
        when(presenter.modelInteractor.getCategories())
                .thenReturn(Collections.<Category>emptyList());
        presenter.loadCategories();
        verify(modelInteractor).getCategories();
        verify(view).showProgressIndicator();
        verify(view).showEmptyList();
    }
}