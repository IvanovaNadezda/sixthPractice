package com.example.sixthpractice.ui.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.sixthpractice.R;
import com.example.sixthpractice.databinding.NewOwlFragmentBinding;

public class NewOwlFragment extends Fragment {
    private NewOwlFragmentBinding binding;

    public NewOwlFragment() {
        super(R.layout.new_owl_fragment);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = NewOwlFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonUpload.setOnClickListener(v -> {
            String owlName = binding.editTextName.getText().toString();
            String owlType = binding.editTextAuthor.getText().toString();
            Bundle bundle = new Bundle();
            if (!TextUtils.isEmpty(owlName) && !TextUtils.isEmpty(owlType)) {
                binding.editTextName.setError(null);
                bundle.putString("RESULT_OK_NAME", owlName);
                bundle.putString("RESULT_OK_TYPE", owlType);
                bundle.putInt("RESULT_OK_IMG", R.drawable.owl);
                Navigation.findNavController(view).navigate(R.id.action_new_owl_fragment_to_owl_list_fragment, bundle);
            }
            else if (TextUtils.isEmpty(owlName)) {
                binding.editTextName.setError("Пустая строка!");
            }
            else if (TextUtils.isEmpty(owlType))
            {
                binding.editTextAuthor.setError("Пустая строка!");
            }
            else
            {
                binding.editTextName.setError("Пустая строка!");
                binding.editTextAuthor.setError("Пустая строка!");
            }
        });
    }
}
