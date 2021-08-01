package com.example.postarticle;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.postarticle.Db.DbManager;
import com.example.postarticle.Model.LoginModel;
import com.example.postarticle.Retrofit.ApiService;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText eName, epassword;
    Button btn_login;
    Dialog dialog;

    String userName = "";
    String userPassword = "";

    /* Class to hold credentials */
    class Credentials
    {
        String name = "admin";
        String password = "123";
    }

    boolean isValid = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        eName = findViewById(R.id.username);
        epassword = findViewById(R.id.password);
        btn_login = findViewById(R.id.btnLogin);
        dialog = new Dialog(this);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userName = eName.getText().toString();
                userPassword = epassword.getText().toString();

                if (userName.isEmpty() || userPassword.isEmpty()){

                    Toast.makeText(LoginActivity.this, "Please enter name and password!", Toast.LENGTH_LONG).show();
                }else{
                    isValid = validate(userName, userPassword);

                    if (!isValid) {
                        dialog.setContentView(R.layout.logingagal_layout);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                        ImageView iV_clear = dialog.findViewById(R.id.iV_clear);
                        Button btn_ok =  dialog.findViewById(R.id.btn_ok);


                        iV_clear.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                Toast.makeText(LoginActivity.this, "Close Dialog", Toast.LENGTH_SHORT).show();
                            }
                        });

                        btn_ok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                Toast.makeText(LoginActivity.this, "Close Dialog btn", Toast.LENGTH_SHORT).show();
                            }
                        });

                        dialog.show();

                    }else{
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }
                }

            }
        });

    }

    private boolean validate(String userName, String userPassword)
    {
        /* Get the object of Credentials class */
        Credentials credentials = new Credentials();

        /* Check the credentials */
        if(userName.equals(credentials.name) && userPassword.equals(credentials.password))
        {
            return true;
        }

        return false;
    }

}


