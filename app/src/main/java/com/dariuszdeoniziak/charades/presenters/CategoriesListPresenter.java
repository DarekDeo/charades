package com.dariuszdeoniziak.charades.presenters;

import com.dariuszdeoniziak.charades.models.Category;
import com.dariuszdeoniziak.charades.models.interactors.ModelInteractor;
import com.dariuszdeoniziak.charades.views.CategoriesListView;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CategoriesListPresenter implements Presenter<CategoriesListView> {

    CategoriesListView view;
    ModelInteractor modelInteractor;
    Single<List<Category>> categoriesSingle = Single.fromCallable(new Callable<List<Category>>() {

        @Override
        public List<Category> call() throws Exception {
            return modelInteractor.getCategories();
        }
    });

    @Inject
    public CategoriesListPresenter(ModelInteractor modelInteractor) {
        this.modelInteractor = modelInteractor;
    }

    @Override
    public void onSave() {

    }

    @Override
    public void onTakeView(CategoriesListView view) {
        this.view = view;
    }

    @Override
    public void onDropView() {
        view = null;
    }

    public void loadCategories() {
        view.showProgressIndicator();
        categoriesSingle
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Category>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<Category> value) {
                        if (value.isEmpty())
                            view.showEmptyList();
                        else
                            view.showCategories(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showEmptyList();
                    }
                });
    }
}