package com.example.sixthpractice.ui.adapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sixthpractice.R;
import com.example.sixthpractice.data.models.Fodder;

import java.util.ArrayList;
import java.util.List;

public class MyCustomFodderListAdapter extends RecyclerView.Adapter<MyCustomFodderListAdapter.MyAuthorViewHolder> {
    private List<Fodder> fodders;
    public MyCustomFodderListAdapter() {
        this.fodders =  new ArrayList<>();
    }


    public MyAuthorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fodder_list_item, parent, false);
        return new MyAuthorViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull MyAuthorViewHolder holder, int position) {
        Fodder fodder = fodders.get(position);
        holder.name.setText(fodder.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("Name", fodder.getName());
                bundle.putInt("Photo", fodder.getPhoto());
                Navigation.findNavController(view).navigate(R.id.action_fodder_list_fragment_to_single_fodder_fragment, bundle);
            }
        });

    }
    @Override
    public int getItemCount() {
        return fodders.size();
    }

    public static class MyAuthorViewHolder extends RecyclerView.ViewHolder {
        public TextView name;

        public MyAuthorViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView16);
        }
    }
    public void updateFodders(List<Fodder> fodders) {
        this.fodders.clear();
        this.fodders = fodders;
        notifyDataSetChanged();
    }
}
