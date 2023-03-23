package com.example.gameapp;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;


import com.example.gameapp.databinding.ActivityRegisterBinding;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;


public class RegisterActivity extends AppCompatActivity {


    private ActivityRegisterBinding binding;
    public SharedPreferences sp;
    public SharedPreferences.Editor edit;
    public String age1;
    public String profile_img="";
    public Uri profile_img_uri;

    //Shared preferences keys//
    public static final String sp_name="fullname";
    public static final String sp_email="email";
    public static final String sp_user_name="username";
    public static final String sp_pass="password";
    public static final String sp_country="country";
    public static final String sp_DB= "birthdate";
    public static final String sp_gender="gender";
    public static final String sp_context_name="register_activity";
    public static final String sp_age="age";
    public static final String sp_image="image";

    //Intent data keys//
    public static final String intent_pass="pass";
    public static final String intent_username="username";

    private boolean accessing_result;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());





        ActivityResultLauncher<String> accessing_storage=registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                new ActivityResultCallback<Boolean>() {
                    @Override
                    public void onActivityResult(Boolean result) {

                        accessing_result=result;
                    }
                });



        sp = getSharedPreferences(sp_context_name, MODE_PRIVATE);
        edit = sp.edit();

        // Bring pic from gallery
        ActivityResultLauncher<String> pic_gal=registerForActivityResult(new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {

                        binding.regImgUserimage.setImageURI(result);
                        profile_img_uri=result;
                        profile_img=profile_img_uri.toString();
                    }
                });


        binding.regImgUserimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accessing_storage.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE);

                if (accessing_result){
                pic_gal.launch("image/*");}

            }
        });

        // Date Picker section
        binding.regEtDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // getting the date
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                                binding.regEtDB.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);

                                age1=String.valueOf(now.get(Calendar.YEAR)-year);



                            }
                        },
                        now.get(Calendar.YEAR), // Initial year selection
                        now.get(Calendar.MONTH), // Initial month selection
                        now.get(Calendar.DAY_OF_MONTH) // Initial day selection
                );

                dpd.show(getSupportFragmentManager(), "Datepickerdialog");

            }
        });


          //Save button to save all the info
        binding.regBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email=binding.regEtEmail.getText().toString();
                String pass=binding.regEtPassword.getText().toString();
                String re_pass=binding.regEtRepass.getText().toString();
                String birthdate=binding.regEtDB.getText().toString();
                String Full_Name=binding.regEtFullname.getText().toString();
                String User_Name=binding.regEtUsername.getText().toString();


                // Checking all fields are full and correct before storing in SP and moving

                if (methods.input_correct(email,Full_Name,User_Name,pass,re_pass,birthdate,binding.regSpCountry,profile_img)){

                    edit.putString(sp_name, Full_Name);
                    edit.putString(sp_email, email);
                    edit.putString(sp_user_name, User_Name);
                    edit.putString(sp_pass, pass);
                    edit.putString(sp_country,binding.regSpCountry.getSelectedItem().toString());
                    edit.putString(sp_DB,birthdate);
                    edit.putString(sp_image,profile_img);
                    edit.putString(sp_age,age1);



                    if(binding.regRbFemale.isChecked()){
                        edit.putString(sp_gender,"Female");
                    } else if (binding.regRbMale.isChecked()){
                        edit.putString(sp_gender,"Male");
                    }
                    edit.apply();

                    Intent intent=new Intent();
                    intent.putExtra(intent_username,User_Name);
                    intent.putExtra(intent_pass,pass);
                    setResult(RESULT_OK,intent);
                    finish();



                } else {
                    // Name checking
                    if (!methods.valid_string(Full_Name)){
                        binding.regEtFullname.setError(getString(R.string.character_error));}

                    if (binding.regSpCountry.getSelectedItemPosition()<1){
                        Toast.makeText(RegisterActivity.this, getString(R.string.country_error), Toast.LENGTH_SHORT).show();
                    }


                    //Email checking
                    if (!(methods.email_validation(email)) ){
                        binding.regEtEmail.setError(getString(R.string.email_error));}

                    if (birthdate.isEmpty()){
                        binding.regEtDB.setError(getString(R.string.BD_error));
                    }


                    // User name checking
                    if (!methods.valid_string(User_Name) ){

                        binding.regEtUsername.setError(getString(R.string.character_error));
                    }


                    // Checking Password strength
                    if (!methods.pass_strength(pass)){
                        binding.regEtPassword.setError(getString(R.string.password_strength_error));}


                    if (!re_pass.equals(pass) ) {
                        binding.regEtRepass.setError(getString(R.string.passMatch_error));}

                    // checking if the user choose pic
                    if(TextUtils.isEmpty(profile_img)){
                        Toast.makeText(RegisterActivity.this, getString(R.string.pic_error), Toast.LENGTH_SHORT).show();
                    }

                    //checking if the user picked or enterd a valid date

                    if (!methods.date_validation(birthdate)){
                        binding.regEtDB.setError(getString(R.string.date_error));
                    }

                }
            }


        });
    }
}