package com.example.bai1.base;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public class BaseViewHolderBinding<T extends ViewDataBinding> extends RecyclerView.ViewHolder {
    /**
     * T is type of view.
     */
    private T mBinding;

    public BaseViewHolderBinding(@NonNull T itemView) {
        super(itemView.getRoot());
        mBinding = itemView;
    }

    public T getBinding() {
        return mBinding;
    }
}
