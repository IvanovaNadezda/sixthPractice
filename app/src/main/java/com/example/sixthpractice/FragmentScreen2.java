package com.example.sixthpractice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.sixthpractice.databinding.Screen2Binding;


public class FragmentScreen2 extends Fragment {
    Screen2Binding binding;
    public FragmentScreen2()
    {
        super(R.layout.screen2);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = Screen2Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Выбираем рейтинг
        binding.ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            // Передаем данные обратно
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                // Передача данных из второй активности в первую
                Bundle bundle = new Bundle();
                bundle.putFloat("rating2",  binding.ratingBar.getRating());
                Navigation.findNavController(view).navigate(R.id.action_second_fragment_to_first_fragment,bundle);
            }
        });

    }
}