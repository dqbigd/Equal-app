package com.example.equal.Fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.equal.LoginActivity;
import com.example.equal.Preferences;
import com.example.equal.R;
import com.example.equal.SaveJobActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {
    LinearLayout lyEditProfile, lyLokerSimpan, lyKeluar;
    TextView tvNamaAccount, tvNama, tvEmail;
    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        tvNamaAccount = view.findViewById(R.id.tvNamaAccount);
        tvNama = view.findViewById(R.id.tvNama);
        tvEmail = view.findViewById(R.id.tvEmail);
        lyEditProfile = view.findViewById(R.id.lyEditProfil);
        lyLokerSimpan = view.findViewById(R.id.lyLokerSimpan);
        lyKeluar = view.findViewById(R.id.lyKeluar);

        final Preferences preferences = new Preferences(getContext());
        tvNamaAccount.setText(preferences.getName());
        tvNama.setText(preferences.getName());
        tvEmail.setText(preferences.getEmail());

        lyLokerSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), SaveJobActivity.class);
                startActivity(i);
            }
        });

        lyKeluar.setOnClickListener(new View.OnClickListener() {
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
