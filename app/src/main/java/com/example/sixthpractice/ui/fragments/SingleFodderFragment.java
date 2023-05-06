package com.example.sixthpractice.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.sixthpractice.R;
import com.example.sixthpractice.databinding.FodderInfoBinding;


public class SingleFodderFragment extends Fragment {
    FodderInfoBinding binding;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            String name = getArguments().getString("Name");
            int image = getArguments().getInt("Photo");
            binding.textView15.setText(name);
            binding.imageView3.setImageResource(image);
        }
        binding.button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("Favorite", String.valueOf(binding.textView15.getText()));
                Navigation.findNavController(view)
                        .navigate(R.id.action_single_author_fragment_to_author_list_fragment, bundle);
            }
        });
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FodderInfoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}
