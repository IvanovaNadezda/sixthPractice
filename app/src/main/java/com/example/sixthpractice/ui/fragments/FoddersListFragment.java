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
import com.example.sixthpractice.data.models.Fodder;
import com.example.sixthpractice.databinding.FodderListBinding;
import com.example.sixthpractice.ui.adapters.MyCustomFodderListAdapter;
import com.example.sixthpractice.ui.viewmodels.FodderListViewModel;

public class FoddersListFragment extends Fragment{

        RecyclerView recyclerView;
         MyCustomFodderListAdapter myCustomListAdapter;
        FodderListBinding binding;
        FodderListViewModel fodderListViewModel;

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            fodderListViewModel = new ViewModelProvider(this).get(FodderListViewModel.class);
        }

        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState) {
            binding = FodderListBinding.inflate(inflater, container, false);
            myCustomListAdapter = new MyCustomFodderListAdapter();// создание адаптера
            Bundle args = getArguments();
            if (args != null && args.containsKey("RESULT_OK_NAME") && args.containsKey("RESULT_OK_IMG")) {
                Fodder fodder = new Fodder(args.getString("RESULT_OK_NAME"), args.getInt("RESULT_OK_IMG"));
                fodderListViewModel.insert(fodder);
            }
            if (args != null && args.containsKey("Favorite"))
            {
                Toast.makeText(getContext(), "Вы добавили " +args.getString("Favorite")+" в избранное", Toast.LENGTH_SHORT).show();
            }
            return binding.getRoot();
        }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            binding.button8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Navigation.findNavController(view).navigate(R.id.action_fodder_list_fragment_to_profile_fragment);
                }
            });
            binding.button9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Navigation.findNavController(view).navigate(R.id.action_fodder_list_fragment_to_new_fodder_fragment);
                }
            });

            recyclerView = view.findViewById(R.id.recyclerView);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(myCustomListAdapter);// установка адаптера
            fodderListViewModel.getAllFodders().observe(getViewLifecycleOwner(), fodderList ->
                    myCustomListAdapter.updateFodders(fodderList));
        }
}


