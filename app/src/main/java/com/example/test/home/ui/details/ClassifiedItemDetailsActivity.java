package com.example.test.home.ui.details;



import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test.R;
import com.example.test.data.models.ClassifiedItem;
import com.example.test.utils.ExtensionsKt;
import com.squareup.picasso.Picasso;

public class ClassifiedItemDetailsActivity extends AppCompatActivity {

    TextView tvItemName, tvItemPrice, tvTime;
    ImageView ivItemImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_classified_item_detail);
        initViews();
        readExtras();
    }

    private void initViews() {
        tvItemName = findViewById(R.id.tvItemName);
        tvItemPrice = findViewById(R.id.tvItemPrice);
        tvTime = findViewById(R.id.tvTime);
        ivItemImage = findViewById(R.id.ivItemImage);
    }

    private void readExtras() {
        ClassifiedItem classifiedItem = getIntent().getExtras().getParcelable("classifiedItem");
        if(classifiedItem != null) {
            initData(classifiedItem);
        }
    }

    private void initData(ClassifiedItem item) {
        tvItemName.setText(item.getName());
        tvItemPrice.setText(item.getPrice());
        Picasso.with(this).load(item.getImagesList().get(0)).fit().placeholder(R.color.shimmerBackground).into(ivItemImage);
        tvTime.setText(ExtensionsKt.getFormattedDate(item));
    }
}
