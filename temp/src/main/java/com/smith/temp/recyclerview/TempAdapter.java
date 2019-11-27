package com.smith.temp.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.smith.temp.R;
import com.smith.temp.dagger.Food;

import java.util.List;

public class TempAdapter extends RecyclerView.Adapter<ViewHolder> {
    public static final int LOADING_MORE = 1;
    public static final int LOADING_NO_MORE = 2;
    private int footer_state = LOADING_NO_MORE;

    private List<String> list;
    private Context context;
    private static final int CONTENT_TYPE = 1;
    private static final int FOOT_TYPE = 2;
    private View footView;

    public TempAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setFooter_state(int footer_state) {
        this.footer_state = footer_state;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == CONTENT_TYPE) {
            View view = LayoutInflater.from(context).inflate(R.layout.temp_item_layout, parent, false);
            return new TempViewHolder(view);
        } else  {
            footView = LayoutInflater.from(context).inflate(R.layout.temp_foot_layout, parent, false);
            return new FootViewHolder(footView);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (getItemViewType(position) == CONTENT_TYPE) {
            TextView textView = holder.itemView.findViewById(R.id.temp_item_text);
            textView.setText(list.get(position));
        } else {
            if (position == 0) {
                holder.itemView.setVisibility(View.GONE);
                return;
            }

            switch (footer_state) {
                case LOADING_MORE:
                    holder.itemView.setVisibility(View.VISIBLE);
                    break;
                case LOADING_NO_MORE:
                    holder.itemView.setVisibility(View.GONE);
                    break;
                default:
                    break;
            }

        }


    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == list.size()) {
            return FOOT_TYPE;
        } else {
            return CONTENT_TYPE;
        }
    }

    public void setList(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    static class TempViewHolder extends RecyclerView.ViewHolder {

        public TempViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    static class FootViewHolder extends RecyclerView.ViewHolder {

        public FootViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
