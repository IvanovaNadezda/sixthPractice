package com.example.sixthpractice;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.sixthpractice.databinding.Screen1Binding;


public class FragmentScreen1 extends Fragment {
    Screen1Binding binding;
    float ratingOwl;
    float ratingFodder;
    public FragmentScreen1()
    {
        super(R.layout.screen1);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = Screen1Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            if (bundle.containsKey("rating2"))
            {
                // Устанавливаем данные из второго фрагмента
                ratingOwl = bundle.getFloat("rating2");
                binding.textView14.setText("Вы выбрали сову с рейтингом "+ ratingOwl);
            }
            else
            {
                // Устанавливаем данные из третьего фрагмента
                ratingFodder = bundle.getFloat("rating3");
                binding.textView6.setText("Вы выбрали корм с рейтингом "+ ratingFodder);
            }

        }

        // Переход на второй фрагмент из первого
        binding.button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_first_fragment_to_second_fragment);
            }
        });
        // Переход на третий фрагмент из первого
        binding.button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_first_fragment_to_third_fragment);
            }
        });

    }
}