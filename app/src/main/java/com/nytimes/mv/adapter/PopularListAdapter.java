package com.nytimes.mv.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.nytimes.mv.R;
import com.nytimes.mv.model.Popular;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PopularListAdapter extends RecyclerView.Adapter<PopularListAdapter.ViewHolder> {

    private final List<Popular> items = new ArrayList<>();

    private final LayoutInflater inflater;
    private final RequestManager imageLoader;
    @Nullable
    private final OnItemClickListener clickListener;

    public PopularListAdapter(Context context, @Nullable OnItemClickListener clickListener) {
        this.inflater = LayoutInflater.from(context);
        this.clickListener = clickListener;

        final RequestOptions imageOption = new RequestOptions()
                .placeholder(R.drawable.ic_image_placeholder)
                .fallback(R.drawable.ic_image_placeholder)
                .centerCrop();
        this.imageLoader = Glide.with(context).applyDefaultRequestOptions(imageOption);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_popular, parent, false), clickListener);
    }

    @Override
    @Nullable
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void replaceItems(@NonNull List<Popular> newItems) {
        items.clear();
        items.addAll(newItems);
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(@NonNull Popular newsItem);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textTitle)
        TextView textTitle;

        @BindView(R.id.textView)
        TextView textViewBy;

        @BindView(R.id.textSection)
        TextView textSection;

        @BindView(R.id.textDate)
        TextView textDate;

        @BindView(R.id.imageCover)
        ImageView imageCover;

        ViewHolder(@NonNull View itemView, @Nullable final OnItemClickListener listener) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(items.get(position));
                }
            });
        }

        void bind(Popular popular) {
            imageLoader.load(popular.getMedia().get(0).getMediaMetaData().get(0).getUrl())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .apply(RequestOptions.circleCropTransform())
                    .into(imageCover);
            textTitle.setText(popular.getTitle());
            textViewBy.setText(popular.getByline());
            textSection.setText(popular.getSection());
            textDate.setText(popular.getPublishedDate());
        }
    }

}