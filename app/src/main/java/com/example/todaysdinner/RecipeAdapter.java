package com.example.todaysdinner;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.CustomViewHolder> {
    private List<ListItem> recipeItemList;
    private Context mContext;
    private OnItemClickListener onItemClickListener;

    public RecipeAdapter(Context context, List<ListItem> feedItemList) {
        this.recipeItemList = feedItemList;
        this.mContext = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Log.d("onCreateView", "onCreateView");
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder customViewHolder, final int i) {
        final ListItem listItem = recipeItemList.get(i);
                //Render image using Picasso library
                if (!TextUtils.isEmpty(listItem.getImage())) {
                    Picasso.with(mContext).load(listItem.getImage())
                            .error(R.drawable.placeholder)
                            .placeholder(R.drawable.placeholder)
                            .into(customViewHolder.imageView);
                    //Setting text view title
                customViewHolder.textView.setText(Html.fromHtml(listItem.getTitle()));
                    View.OnClickListener listener = new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onItemClickListener.onItemClick(listItem,i);
                        }
                    };
              //      customViewHolder.imageView.setOnClickListener(listener);
                //    customViewHolder.textView.setOnClickListener(listener);
                }
    }

    @Override
    public int getItemCount() {
        return (null != recipeItemList ? recipeItemList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected ImageView imageView;
        protected TextView textView;
        protected TextView invitextView;
        protected RecyclerView myrecyclerView;

        public CustomViewHolder(View view) {
            super(view);
            this.imageView = (ImageView) view.findViewById(R.id.iv);
            this.textView = (TextView) view.findViewById(R.id.resulttext);
            this.invitextView = (TextView)view.findViewById(R.id.invi);
            this.myrecyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        }
    }
 public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
