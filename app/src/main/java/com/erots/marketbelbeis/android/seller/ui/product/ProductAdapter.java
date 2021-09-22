package com.erots.marketbelbeis.android.seller.ui.product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.erots.marketbelbeis.android.seller.databinding.RowProductsItemBinding;

import java.text.MessageFormat;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {


    private final List<ProductModel> models;
    private final Context context;

    public ProductAdapter(List<ProductModel> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowProductsItemBinding binding = RowProductsItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        ProductModel model = models.get(position);
        holder.binding.tvProductContent.setText(model.getProductContent());
        holder.binding.tvProductDiscount.setText(MessageFormat.format("{0}\n{1}\n{2}", model.getProductDiscount(), model.getProductDiscountStartsAt(), model.getProductDiscountEndsAt()));
        holder.binding.tvProductPrice.setText(model.getProductPrice());
        holder.binding.tvProductPublishedAt.setText(model.getProductPublishedAt());
        holder.binding.tvProductQuantity.setText(model.getProductQuantity());
        holder.binding.tvProductTitle.setText(model.getProductTitle());
        holder.binding.tvProductStatues.setText(model.getProductStatues());
        holder.binding.tvProductStoreName.setText(model.getProductStoreName());
        holder.binding.tvProductType.setText(model.getProductType());
        Glide.with(context)
                .load(model.getProductImage())
                .into(holder.binding.rowProductImageView);

    }

    @Override
    public int getItemCount() {
        return models.size();
    }



    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        RowProductsItemBinding binding;

        public ProductViewHolder(RowProductsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}