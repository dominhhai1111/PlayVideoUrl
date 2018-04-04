package com.example.admin.playvideourl;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class RegisterActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final TextView email = findViewById(R.id.email);
        final TextView password = findViewById(R.id.password);

        final Button registerButton = findViewById(R.id.email_sign_up_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                APIService apiService = APIService.retrofit_sign_up.create(APIService.class);
                final Call<RegisterResult> call = apiService.checkRegisterUserAccount(email.getText().toString(), password.getText().toString());
                RegisterUserTask registerUserTask = new RegisterUserTask();
                registerUserTask.execute(call);
            }
        });
    }

    public class RegisterUserTask extends AsyncTask<Call, RegisterResult, RegisterResult>{

        @Override
        protected RegisterResult doInBackground(Call[] calls) {
            Call<RegisterResult> call = calls[0];
            try {
                Response<RegisterResult> response = call.execute();
                Log.d(TAG, response.body().getMessage());
                return response.body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new RegisterResult(false, "Neetwork error");
        }

        @Override
        protected void onPostExecute(RegisterResult registerResult) {
            super.onPostExecute(registerResult);
            Toast.makeText(RegisterActivity.this, registerResult.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
