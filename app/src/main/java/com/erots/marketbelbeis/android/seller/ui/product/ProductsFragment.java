package com.erots.marketbelbeis.android.seller.ui.product;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.erots.marketbelbeis.android.seller.R;
import com.erots.marketbelbeis.android.seller.databinding.ProductsFragmentBinding;
import com.erots.marketbelbeis.android.seller.viewmodels.ProductsViewModel;

import java.util.ArrayList;
import java.util.List;

public class ProductsFragment extends Fragment {

    ProductAdapter adapter;
    List<ProductModel> list;
    ProductsFragmentBinding binding;
    private ProductsViewModel mViewModel;

    public static ProductsFragment newInstance() {
        return new ProductsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = ProductsFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ProductsViewModel.class);
        // TODO: Use the ViewModel
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen._8sdp);
        binding.productsRecycler.addItemDecoration(new GridSpacingItemDecoration(2, spacingInPixels, true, 0));
        list = new ArrayList<>();

        list.add(new ProductModel("ملابس","Kataki","12/5/2021","fkghfdsgsd",R.drawable.ad_item_1,"jkdbvskdv","ساعه وخلاص","ساعه جامده كيكككك","500ج","Shipped","500ج","5/10/2021","20/10/2021","50 قطعه","20/10/2021","20/2016"));
        list.add(new ProductModel("ملابس","Kataki","12/5/2021","fkghfdsgsd",R.drawable.ad_item_2,"jkdbvskdv","ساعه وخلاص","ساعه جامده كيكككك","500ج","Shipped","500ج","5/10/2021","20/10/2021","50 قطعه","20/10/2021","20/2016"));
        list.add(new ProductModel("ملابس","Kataki","12/5/2021","fkghfdsgsd",R.drawable.ad_item_3,"jkdbvskdv","ساعه وخلاص","ساعه جامده كيكككك","500ج","Shipped","500ج","5/10/2021","20/10/2021","50 قطعه","20/10/2021","20/2016"));
        list.add(new ProductModel("ملابس","Kataki","12/5/2021","fkghfdsgsd",R.drawable.ad_item_4,"jkdbvskdv","ساعه وخلاص","ساعه جامده كيكككك","500ج","Shipped","500ج","5/10/2021","20/10/2021","50 قطعه","20/10/2021","20/2016"));
        list.add(new ProductModel("ملابس","Kataki","12/5/2021","fkghfdsgsd",R.drawable.ad_item_5,"jkdbvskdv","ساعه وخلاص","ساعه جامده كيكككك","500ج","Shipped","500ج","5/10/2021","20/10/2021","50 قطعه","20/10/2021","20/2016"));
        list.add(new ProductModel("ملابس","Kataki","12/5/2021","fkghfdsgsd",R.drawable.ad_item_6,"jkdbvskdv","ساعه وخلاص","ساعه جامده كيكككك","500ج","Shipped","500ج","5/10/2021","20/10/2021","50 قطعه","20/10/2021","20/2016"));
        list.add(new ProductModel("ملابس", "Kataki", "12/5/2021", "fkghfdsgsd", R.drawable.ad_item_1, "jkdbvskdv", "ساعه وخلاص", "ساعه جامده كيكككك", "500ج", "Shipped", "500ج", "5/10/2021", "20/10/2021", "50 قطعه", "20/10/2021", "20/2016"));

        adapter = new ProductAdapter(list,binding.getRoot().getContext());
        binding.productsRecycler.setClipToPadding(true);
        binding.productsRecycler.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}