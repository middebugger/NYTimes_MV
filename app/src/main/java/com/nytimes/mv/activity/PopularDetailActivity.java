package com.nytimes.mv.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.browser.customtabs.CustomTabsIntent;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.nytimes.mv.R;
import com.nytimes.mv.base.BaseActivity;
import com.nytimes.mv.model.Popular;

public class PopularDetailActivity extends BaseActivity {

    private static final String EXTRA_NEWS_ITEM = "extra:popularObj";
    private CustomTabsIntent.Builder builder;
    private CustomTabsIntent customTabsIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        final Popular mostPopular = (Popular) getIntent().getSerializableExtra(EXTRA_NEWS_ITEM);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(mostPopular.getSection());

        final ImageView imageCover = findViewById(R.id.imageCover);
        final TextView textTitle = findViewById(R.id.textTitle);
        final TextView textDate = findViewById(R.id.textDate);
        final TextView textDescription = findViewById(R.id.textDescription);
        final TextView textView = findViewById(R.id.textView);
        final TextView textMore = findViewById(R.id.textMore);

        Glide.with(this)
                .load(mostPopular.getMedia().get(0).getMediaMetaData().get(0).getUrl())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageCover);

        textTitle.setText(mostPopular.getTitle());
        textView.setText(mostPopular.getByline());
        textDate.setText(mostPopular.getPublishedDate());
        textDescription.setText(mostPopular.getAbstract());

        textMore.setOnClickListener(v -> {
            if (builder == null) {
                builder = new CustomTabsIntent.Builder();
            }
            builder.setToolbarColor(getResources().getColor(R.color.colorPrimary));
            if (customTabsIntent == null) {
                customTabsIntent = builder.build();
            }
            customTabsIntent.launchUrl(PopularDetailActivity.this, Uri.parse(mostPopular.getUrl()));
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public static void start(@NonNull Context context, @NonNull Popular mostPopular) {

        context.startActivity(new Intent(context, PopularDetailActivity.class).putExtra(EXTRA_NEWS_ITEM, mostPopular));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}