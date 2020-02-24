package com.example.equal.Fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.equal.LoginActivity;
import com.example.equal.Preferences;
import com.example.equal.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {
    Button btnMetu;
    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        btnMetu = view.findViewById(R.id.btnMetu);

        btnMetu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Preferences preferences = new Preferences(getContext());

                preferences.setLoggedIn(false, 0);
                preferences.setProfile("", "");
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

}
