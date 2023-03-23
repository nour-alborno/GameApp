package com.example.gameapp;



import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;


import com.example.gameapp.databinding.ActivityLoginactivityBinding;
import com.example.gameapp.databinding.ActivityLoginactivityBinding;




public class LoginActivity extends AppCompatActivity {

    private ActivityLoginactivityBinding binding;
    public int reg_reqcode = 1;
    public SharedPreferences sp;
    public SharedPreferences.Editor edit;

    //Intent data keys//
    public static final String intent_pass="pass";
    public static final String intent_username="username";
    public static final String ChangedPass_intent="new password";

    // shared preferences keys//
    public static final String sp_context_name="register_activity";
    public static final String sp_remember_box="Remember";
    public static final String sp_user_name="username";
    public static final String sp_pass="password";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginactivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sp=getSharedPreferences(sp_context_name, MODE_PRIVATE);
        edit=sp.edit();

        String remember_me=sp.getString(sp_remember_box,"");

                if (remember_me.equals("True")) {
                    startActivity(new Intent(getBaseContext(), game.class));
                    finish();
                }




        // Going to the registration activity
        binding.logBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), RegisterActivity.class);
                startActivityForResult(intent, reg_reqcode);
            }
        });





        // Login btn to go to game activity
        binding.logBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String sp_username=sp.getString(sp_user_name," ");
                String sp_password=sp.getString(sp_pass," ");

                String password = binding.logEtPassword.getText().toString();
                String user = binding.logEtUsername.getText().toString();

                if (!TextUtils.isEmpty(password) && !TextUtils.isEmpty(user)){
                   if (sp_username.equals(user)  && sp_password.equals(password)  ){
                              startActivity(new Intent(getBaseContext(), game.class));
                              finish();
                              
                }
                   else  {
                       Toast.makeText(LoginActivity.this, getString(R.string.ivalid_info_error), Toast.LENGTH_SHORT).show();
                   }
                }
                else  {
                    Toast.makeText(LoginActivity.this, getString(R.string.ivalid_info_error), Toast.LENGTH_SHORT).show();
                }



            }
        });

        // Remember me section
        binding.logCbRemeberME.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                String password=binding.logEtPassword.getText().toString();
                String user=binding.logEtUsername.getText().toString();

                if ((!password.isEmpty() && !user.isEmpty())){
                 if (compoundButton.isChecked()){
                    edit.putString(sp_remember_box,"True");
                    edit.apply();
                 }
                else if (!compoundButton.isChecked()) {
                    edit.putString(sp_remember_box,"False");
                    edit.apply();}

                }


            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == reg_reqcode){

            binding.logEtPassword.setText(data.getStringExtra(intent_pass));
            binding.logEtUsername.setText(data.getStringExtra(intent_username));

        }
    }
}




















