package com.example.rvclicknew;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class recycleAdapter extends RecyclerView.Adapter<recycleAdapter.MyViewHolder>{
    private final ArrayList<Radio> usersList;
    private final RecycleViewClickListener listener;
    private final RecycleViewOnLongClickListener listenerLong;
    public recycleAdapter(ArrayList<Radio> usersList, RecycleViewClickListener listener, RecycleViewOnLongClickListener listenerLong){
        this.usersList=usersList;
        this.listener=listener;
        this.listenerLong=listenerLong;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {
        private final TextView nameText;
        private final ImageView SmallRadioPic;
        public MyViewHolder(final View view){
        super(view);
        nameText=view.findViewById(R.id.textView2);
        SmallRadioPic=view.findViewById(R.id.imageRadioSmall);
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);

        }

        @Override
        public void onClick(View view) {
            listener.onClick(view,getAdapterPosition());

        }

        @Override
        public boolean onLongClick(View view) {
            listenerLong.onLongClick(view,getAdapterPosition());
            return true;
        }

    }
    @NonNull
    @Override
    public recycleAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview;
        itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items,parent,false);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull recycleAdapter.MyViewHolder holder, int position) {
    String name = usersList.get(position).getRadioName();
    String image=usersList.get(position).getRadioPic();
    holder.nameText.setText(name);
    Picasso.get().load(image).into(holder.SmallRadioPic);

    }

    @Override
    public int getItemCount() {
        return  usersList.size();
    }

    public interface RecycleViewOnLongClickListener {
        void onLongClick (View v,int position);
    }
    public interface RecycleViewClickListener{
        void onClick(View v,int position);
    }
}