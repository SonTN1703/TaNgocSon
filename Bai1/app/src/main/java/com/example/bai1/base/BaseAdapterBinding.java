package com.example.bai1.base;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class BaseAdapterBinding<T> extends RecyclerView.Adapter<BaseViewHolderBinding> {

    private LayoutInflater mLayoutInflater;

    /**
     * item layout
     *
     * @return resource layout.
     */
    @LayoutRes
    public abstract int itemLayoutResource();

    /**
     * base listener for itemView.
     */
    protected IBaseAdapterListener mListener;

    /**
     * viewModel for itemview.
     */
    protected ViewModel mViewModel;
    protected LifecycleOwner mLifeCycleOwner;
    /**
     * T is type of data
     * mlist is result list
     */
    protected List<T> mListData;

    @NonNull
    @Override
    public BaseViewHolderBinding onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mLayoutInflater == null) mLayoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(mLayoutInflater, itemLayoutResource(), parent, false);
        return new BaseViewHolderBinding(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolderBinding holder, int position) {
        onBind(holder, position);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolderBinding holder, int position, @NonNull List<Object> payloads) {
        if (payloads.isEmpty())
            super.onBindViewHolder(holder, position, payloads);
        else onBind(holder, position);
    }

    public abstract void onBind(BaseViewHolderBinding holder, int position);

    @Override
    public int getItemCount() {
        return mListData == null ? 0 : mListData.size();
    }


    /**
     * add new object to current list
     *
     * @param data is object
     */
    public void add(T data) {
        mListData.add(data);
        notifyDataSetChanged();
    }

    /**
     * set listener
     *
     * @param mListener
     */
    public void setItemListener(IBaseAdapterListener mListener) {
        this.mListener = mListener;
    }

    /**
     * add 1 object to the specified position in the list
     *
     * @param position need add
     * @param data     is object
     */
    public void add(int position, T data) {
        mListData.add(position, data);
        notifyDataSetChanged();
    }

    /**
     * set new list
     *
     * @param listData new list
     */
    public void set(List<T> listData) {
        mListData.clear();
        addAll(listData);
    }

    /**
     * add first list.
     *
     * @param listData first list.
     */
    public void addAll(List<T> listData) {
        mListData.addAll(listData);
        notifyDataSetChanged();

    }

}
