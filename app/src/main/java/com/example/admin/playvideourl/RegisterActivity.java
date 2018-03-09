package com.example.admin.playvideourl;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

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

    public class RegisterUserTask extends AsyncTask<Call, RegisterResult, Response<RegisterResult>>{

        @Override
        protected Response<RegisterResult> doInBackground(Call[] calls) {
            Call<RegisterResult> call = calls[0];
            try {
                Response<RegisterResult> response = call.execute();
                return response;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new Response<RegisterResult>();
        }

        @Override
        protected void onPostExecute(Response response) {
            super.onPostExecute(response);
            Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
