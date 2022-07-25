package com.example.gallery;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActRCVAdapter extends RecyclerView.Adapter<MainActRCVAdapter.Viewholder> {

    Context context;
    private ArrayList<Integer> arrayList;

    ItemOnClickListener itemOnClickListener;

    public MainActRCVAdapter(Context context, ArrayList<Integer> arrayList, ItemOnClickListener itemOnClickListener) {
        this.context = context;
        this.arrayList = arrayList;
        this.itemOnClickListener = itemOnClickListener;
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.ivRCV);

        }
    }

    @NonNull
    @Override
    public MainActRCVAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.photo_rcv_item, parent, false);
        Viewholder viewholder = new Viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainActRCVAdapter.Viewholder holder, @SuppressLint("RecyclerView") int position) {

        holder.imageView.setImageResource(arrayList.get(position));

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String clickedImage=arrayList.get(position).toString();
                itemOnClickListener.onImageClicked(clickedImage);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
