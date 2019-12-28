package com.dariuszdeoniziak.charades.views.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.dariuszdeoniziak.charades.R;
import com.dariuszdeoniziak.charades.data.models.Category;
import com.dariuszdeoniziak.charades.presenters.CategoriesFormPresenter;
import com.dariuszdeoniziak.charades.views.CategoriesFormContract;
import com.dariuszdeoniziak.charades.views.Layout;
import com.dariuszdeoniziak.charades.views.adapters.CharadesListAdapter;
import com.dariuszdeoniziak.charades.views.models.CharadeListItemModel;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import trikita.knork.Knork;


@SuppressLint("CheckResult")
@Layout(R.layout.fragment_categories_form)
public class CategoriesFormFragment extends BaseFragment implements CategoriesFormContract.View {

    @Knork.Id(R.id.form_category_title) EditText editTextCategoryTitle;
    @Knork.Id(R.id.form_charades_recycler) RecyclerView charadesRecyclerView;

    @Inject CategoriesFormPresenter presenter;
    @Inject CharadesListAdapter charadesListAdapter;

    long categoryId = 0;

    private final static String KEY_CATEGORY_ID = "key_category_id";

    private Disposable titleTextChangesDisposable = Disposables.empty();

    public static String TAG = CategoriesFormFragment.class.getSimpleName();

    public static CategoriesFormFragment newInstance() {
        return newInstance(0L);
    }

    public static CategoriesFormFragment newInstance(Long categoryId) {
        Bundle bundle = new Bundle();
        bundle.putLong(KEY_CATEGORY_ID, categoryId);

        CategoriesFormFragment fragment = new CategoriesFormFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            categoryId = getArguments().getLong(KEY_CATEGORY_ID, 0);
        }
    }

    @Override
    public void onViewCreated(@NonNull android.view.View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        charadesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        charadesRecyclerView.setAdapter(charadesListAdapter);
        charadesListAdapter.setPresenter(presenter);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.onTakeView(this);
        presenter.onLoadCategory(categoryId);
        setupViewActions();
    }

    private void setupViewActions() {
        editTextCategoryTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.onEditedCategoryTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        titleTextChangesDisposable.dispose();
        presenter.onDropView();
    }

    @Override
    public void showTextInfo(final String text) {
        componentsFacade.showToast(text, Toast.LENGTH_SHORT);
    }

    @Override
    public void showCategory(Category category) {
        editTextCategoryTitle.setText(category.name);
    }

    @Override
    public void showCharades(List<CharadeListItemModel> charades) {
        charadesListAdapter.adapt(charades);
    }
}
