package com.example.sixthpractice.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sixthpractice.R;
import com.example.sixthpractice.data.models.Owl;
import com.example.sixthpractice.databinding.OwlListBinding;
import com.example.sixthpractice.ui.adapters.MyCustomListAdapter;
import com.example.sixthpractice.ui.viewmodels.OwlListViewModel;

public class OwlListFragment extends Fragment {
    RecyclerView recyclerView;
    MyCustomListAdapter myCustomListAdapter;

    OwlListBinding binding;
    OwlListViewModel owlListViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        owlListViewModel = new ViewModelProvider(this).get(OwlListViewModel.class);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = OwlListBinding.inflate(inflater, container, false);
        myCustomListAdapter = new MyCustomListAdapter();// создание адаптера
        Bundle args = getArguments();
        if (args != null && args.containsKey("RESULT_OK_NAME") && args.containsKey("RESULT_OK_IMG")&& args.containsKey("RESULT_OK_TYPE")) {
            Owl type = new Owl(args.getString("RESULT_OK_NAME"), args.getString("RESULT_OK_TYPE"),args.getInt("RESULT_OK_IMG"));
            owlListViewModel.insert(type);
        }
        if (args != null && args.containsKey("Rating"))
        {
            Toast.makeText(getContext(), "Вы оценили сову на "+getArguments().getFloat("Rating"), Toast.LENGTH_SHORT).show();
        }

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_book_list_fragment_to_profile_fragment);
            }
        });
        binding.button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_book_list_fragment_to_new_book_fragment);
            }
        });
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(myCustomListAdapter);// установка адаптера
        owlListViewModel.getAllOwls().observe(getViewLifecycleOwner(), owlsList ->
                myCustomListAdapter.updateOwls(owlsList));
    }
}
