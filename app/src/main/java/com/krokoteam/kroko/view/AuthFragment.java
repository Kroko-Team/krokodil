package com.krokoteam.kroko.view;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.krokoteam.kroko.R;

public class AuthFragment extends Fragment {

    private AuthViewModel mAuthViewModel;

    public static AuthFragment newInstance() {
        return new AuthFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.auth_fragment, container, false);
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        mViewModel = new ViewModelProvider(this).get(AuthViewModel.class);
//        // TODO: Use the ViewModel
//    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAuthViewModel = new ViewModelProvider(getActivity()).get(AuthViewModel.class);
        final Button loginBtn = view.findViewById(R.id.auth_login_btn);

        mAuthViewModel.getProgress().observe(getViewLifecycleOwner(), new Observer<AuthViewModel.LoginState>() {
            @Override
            public void onChanged(AuthViewModel.LoginState loginState) {
                if (loginState == AuthViewModel.LoginState.FAILED) {
                    loginBtn.setEnabled(true);
                    loginBtn.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
                } else if (loginState == AuthViewModel.LoginState.ERROR) {
                    loginBtn.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
                    loginBtn.setEnabled(true);
                } else if (loginState == AuthViewModel.LoginState.IN_PROGRESS) {
                    loginBtn.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
                    loginBtn.setEnabled(false);
                } else if (loginState == AuthViewModel.LoginState.SUCCESS) {
                    Toast.makeText(getContext(), "Success login", Toast.LENGTH_LONG).show();
                    loginBtn.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                    loginBtn.setEnabled(false);
                } else {
                    loginBtn.setBackground(getContext().getDrawable(android.R.drawable.btn_default));
                    loginBtn.setEnabled(true);
                }
            }
        });

        final EditText login = view.findViewById(R.id.auth_login);
        final EditText password = view.findViewById(R.id.auth_password);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuthViewModel.login(login.getText().toString(), password.getText().toString());
            }
        });
    }
}
