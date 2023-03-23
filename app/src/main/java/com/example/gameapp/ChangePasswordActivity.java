package com.example.gameapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.View;


import com.example.gameapp.databinding.ActivityChangePasswordBinding;

public class ChangePasswordActivity extends AppCompatActivity {
   private ActivityChangePasswordBinding binding;
   public SharedPreferences sp;
   public SharedPreferences.Editor edit;

    // sp keywords//
    public static final String sp_context_name="register_activity";
    public static final String sp_pass="password";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityChangePasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sp=getSharedPreferences(sp_context_name, MODE_PRIVATE);
        edit=sp.edit();


        binding.changePassBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newPass=binding.changePassEtNewPass.getText().toString();
                String oldPassword=binding.changePassEtOldPassword.getText().toString();
                String newPass2=binding.changePassEtReEnterNewPass.getText().toString();

                String oldpass=sp.getString(sp_pass,null);

                if (oldPassword.equals(oldpass)) {
                    if (methods.pass_strength(newPass)) {
                        if (newPass.equals(newPass2)){
                            AlertDialog.Builder confirmation_dialogue= new AlertDialog.Builder(ChangePasswordActivity.this);
                                           confirmation_dialogue.setTitle(getString(R.string.ChangePass_Dialog_title))
                                                   .setMessage(getString(R.string.ChangePass_Dialog_message))
                                                   .setPositiveButton(getString(R.string.Dialoge_postive_btn), new DialogInterface.OnClickListener() {
                                                       @Override
                                                       public void onClick(DialogInterface dialogInterface, int i) {
                                                           edit.putString(sp_pass,newPass);
                                                           edit.apply();


                                                           startActivity(new Intent(ChangePasswordActivity.this,game.class));
                                                           finish();

                                                       }
                                                   })
                                                   .setNegativeButton(getString(R.string.Dialoge_negative_btn), new DialogInterface.OnClickListener() {
                                                       @Override
                                                       public void onClick(DialogInterface dialogInterface, int i) {

                                                           dialogInterface.cancel();
                                                       }
                                                   });
                            AlertDialog dialog=confirmation_dialogue.create();
                            dialog.show();


                        } else {
                            binding.changePassEtReEnterNewPass.setError(getString(R.string.passMatch_error));
                        }

                    } else {
                        binding.changePassEtNewPass.setError(getString(R.string.password_strength_error));
                    }
                } else {
                   binding.changePassEtOldPassword.setError(getString(R.string.password_error));
                }
            }
        });
    }
}