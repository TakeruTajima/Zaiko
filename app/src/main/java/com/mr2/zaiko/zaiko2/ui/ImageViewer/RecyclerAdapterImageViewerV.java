package com.mr2.zaiko.zaiko2.ui.ImageViewer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.mr2.zaiko.R;

class RecyclerAdapterImageViewerV extends RecyclerView.Adapter<RecyclerAdapterImageViewerV.ViewHolder> {
    private Context context;
    private ImageViewerResource resource;
    private ImageViewerFragment.Crop crop;
    private int defaultPosition;
    private OnClickListener onClickListener;
    private OnPageChangedListener onPageChangedListener;
    private ViewPager2 currentPager;

    RecyclerAdapterImageViewerV(@NonNull Context context, ImageViewerResource resource, ImageViewerFragment.Crop crop, int defaultPosition) {
        this.context = context;
        this.resource = resource;
        this.crop = crop;
        this.defaultPosition = defaultPosition;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_image_viewer_horizontal, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (1 != position) { holder.pager = null; return; }
        //setResource, createAdapter, setAdapter, setListener
        RecyclerAdapterImageViewerH adapter = new RecyclerAdapterImageViewerH(context, resource, crop);
        currentPager = holder.pager;
        holder.pager.setAdapter(adapter);
        holder.pager.setCurrentItem(defaultPosition);
        adapter.setOnClickListener(this::onClick);
        holder.pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                if (null != onPageChangedListener && 0 == state){
                    onPageChangedListener.onPageChanged(holder.pager.getCurrentItem());
                }
            }
        });
        System.out.println("horizontal adapter set");
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    private void onClick(ImageView view, int position1) {
        if (null != onClickListener) onClickListener.onClick(view, position1);
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private ViewPager2 pager;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pager = itemView.findViewById(R.id.imageViewerViewPager2Horizontal);
        }
    }

    public void setCurrentItem(int itemIndex){
        currentPager.setCurrentItem(itemIndex);
    }

    public void setOnClickListener(OnClickListener listener){
        this.onClickListener = listener;
    }

    public void setOnPageChangedListener(OnPageChangedListener listener){
        this.onPageChangedListener = listener;
    }

    public interface OnClickListener{
        void onClick(View v, int position);
    }

    public interface OnPageChangedListener{
        void onPageChanged(int position);
    }
}
