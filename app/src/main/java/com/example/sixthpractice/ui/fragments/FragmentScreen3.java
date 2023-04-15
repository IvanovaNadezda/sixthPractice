package com.example.sixthpractice.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.sixthpractice.R;
import com.example.sixthpractice.databinding.Screen3Binding;


public class FragmentScreen3 extends Fragment {
    Screen3Binding binding;
    public FragmentScreen3()
    {
        super(R.layout.screen3);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = Screen3Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Выбираем рейтинг
        binding.ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                Bundle bundle = new Bundle();
                // Передаем данные из третьей активности в первую
                bundle.putFloat("rating3",  binding.ratingBar.getRating());
                Navigation.findNavController(view).navigate(R.id.action_third_fragment_to_first_fragment,bundle);
            }
        });

    }
}