package com.bawei.zhangyaojun1231.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.zhangyaojun1231.R;
import com.bawei.zhangyaojun1231.entity.Bean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author 张耀军
 * @createTime 2019/12/31 9:18
 * @description
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.Holder> {

    private Context context;
    private List<Bean.RankingBean> list;

    public MyAdapter(Context context, List<Bean.RankingBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(View.inflate(context, R.layout.item_recycler, null));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {
        holder.text_id.setText(list.get(position).getRank());
        holder.text_name.setText(list.get(position).getName());
        Glide.with(context)
                .load(list.get(position).getAvatar())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.drawable.ic_launcher_background)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onClick(list.get(position).getName());
            }
        });
    }

    OnItemClick onItemClick;

    public interface OnItemClick {
        void onClick(String s);
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_id)
        TextView text_id;
        @BindView(R.id.text_name)
        TextView text_name;
        @BindView(R.id.imageView)
        ImageView imageView;

        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
