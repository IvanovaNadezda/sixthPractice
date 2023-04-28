package com.example.sixthpractice.ui.adapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sixthpractice.R;
import com.example.sixthpractice.data.models.Owl;

import java.util.ArrayList;
import java.util.List;

public class MyCustomListAdapter extends RecyclerView.Adapter<MyCustomListAdapter.MyViewHolder> {

    private List<Owl> owls;
    public MyCustomListAdapter() {
        this.owls =  new ArrayList<>();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Owl owl = owls.get(position);
        holder.name.setText(owl.getName());
        holder.author.setText(owl.getAuthor());
        holder.imageView.setImageResource(owl.getImage());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("Name", owl.getName());
                bundle.putString("Fodder", owl.getAuthor());
                bundle.putInt("Image", owl.getImage());
                Navigation.findNavController(view).navigate(R.id.action_book_list_fragment_to_single_book_fragment, bundle);
            }
        });

    }

    @Override
    public int getItemCount() {
        return owls.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView author;
        public TextView name;
        public ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.item_author);
            imageView = itemView.findViewById(R.id.item_image);
            name = itemView.findViewById(R.id.item_name);
        }
    }
    public void updateOwls(List<Owl> owls) {
        this.owls.clear();
        this.owls = owls;
        notifyDataSetChanged();
    }
}

