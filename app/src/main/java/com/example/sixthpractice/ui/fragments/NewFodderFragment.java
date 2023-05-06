package com.example.sixthpractice.ui.fragments;

import static java.security.AccessController.getContext;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.room.RoomSQLiteQuery;

import com.example.sixthpractice.R;
import com.example.sixthpractice.data.database.Entity.OwlEntity;
import com.example.sixthpractice.data.repository.OwlRepository;
import com.example.sixthpractice.databinding.NewFodderFragmentBinding;

public class NewFodderFragment extends Fragment {
    private NewFodderFragmentBinding binding;
    private EditText mEditWordView;

    public NewFodderFragment() {
        super(R.layout.new_fodder_fragment);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = NewFodderFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        binding.buttonUpload.setOnClickListener(v ->{
            String authorName = binding.editTextAuthor.getText().toString();
            Bundle bundle = new Bundle();
            if (!TextUtils.isEmpty(authorName)){
                binding.editTextAuthor.setError(null);
                bundle.putString("RESULT_OK_NAME", authorName);
                bundle.putInt("RESULT_OK_IMG", R.drawable.beatle);
                Navigation.findNavController(view).navigate(R.id.action_fodder_list_fragment_to_new_fodder_fragment, bundle);
            }
            else {
                binding.editTextAuthor.setError("Пустая строка!");
            }
        });

    }

    
}
