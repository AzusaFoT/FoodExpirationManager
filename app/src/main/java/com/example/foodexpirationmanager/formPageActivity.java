package com.example.foodexpirationmanager;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toolbar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class formPageActivity extends Activity {

    private LinearLayout menuLayout,formMainLayout;
    private boolean menu_Bool=false;
    private Button menuButton,sentButton;
    private TextView homeButton,totalButton,searchButton,formButton,errorTextView;
    private EditText userNameEditText,userEmailEditText,userQusContentEditText;
    private RadioButton questionRadioButton,suggestionRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_page);

        menuButton=findViewById(R.id.menu_Button);
        menuLayout=(LinearLayout)findViewById(R.id.menu_Layout);
        homeButton=findViewById(R.id.home_Button);
        totalButton=findViewById(R.id.total_Button);
        searchButton=findViewById(R.id.search_Button);
        formButton=findViewById(R.id.form_Button);
        sentButton=findViewById(R.id.sent_Button);
        userNameEditText=findViewById(R.id.userNameEditText);
        userEmailEditText=findViewById(R.id.userEmailEditText);
        questionRadioButton=findViewById(R.id.questionRadioButton);
        suggestionRadioButton=findViewById(R.id.suggestionRadioButton);
        userQusContentEditText=findViewById(R.id.userQusContentEditText);
        errorTextView=findViewById(R.id.errorTextView);
        formMainLayout=findViewById(R.id.form_MainLayout);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(formPageActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        totalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(formPageActivity.this,listPageActivity.class);
                i.putExtra("whichclass","a");
                i.putExtra("timelimit","-1");
                startActivity(i);
                finish();
            }
        });
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(formPageActivity.this,searchPageActivity.class);
                startActivity(i);
                finish();
            }
        });
        formButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast=Toast.makeText(getApplicationContext(),"?????????'????????????'??????",Toast.LENGTH_SHORT);
                toast.show();
            }
        });



        //????????????????????????
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(menu_Bool==false){
                    menuLayout.setTranslationX(0);
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) menuLayout.getLayoutParams();
                    params.width = 500;
                    menuLayout.setLayoutParams(params);
                    menu_Bool=true;
                }else{
                    menuLayout.setTranslationX(-1);
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) menuLayout.getLayoutParams();
                    params.width = 1;
                    menuLayout.setLayoutParams(params);
                    menu_Bool=false;
                    //????????????????????????????????????????????????
                }


            }
        });
        //?????????????????????????????????
        formMainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(menu_Bool==true) {
                    menuLayout.setTranslationX(-1);
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) menuLayout.getLayoutParams();
                    params.width = 1;
                    menuLayout.setLayoutParams(params);
                    menu_Bool = false;
                }
            }
        });

        //????????????????????????????????????????????????
        sentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!userNameEditText.getText().toString().isEmpty() && !userEmailEditText.getText().toString().isEmpty() && !userQusContentEditText.getText().toString().isEmpty()){
                    String nickname = userNameEditText.getText().toString();
                    String email = userEmailEditText.getText().toString();
                    String content = userQusContentEditText.getText().toString();
                    String fbClass = "??????";
                    if (suggestionRadioButton.isChecked() == true ){
                        fbClass = "??????";
                    }
                    errorTextView.setText("????????????????????????");
                    feedbackData(nickname,email,fbClass,content);

                    //????????????/????????????
                    userNameEditText.setText("");
                    userEmailEditText.setText("");
                    userQusContentEditText.setText("");
                    questionRadioButton.setChecked(true);

                }else{
                    errorTextView.setText("?????????");
                    if(userNameEditText.getText().toString().isEmpty()){
                        errorTextView.append("'??????' ");
                    }
                    if(userEmailEditText.getText().toString().isEmpty()){
                        errorTextView.append("'??????' ");
                    }
                    if(userQusContentEditText.getText().toString().isEmpty()){
                        errorTextView.append("'????????????'");
                    }
                }
            }
        });


    }

    private void feedbackData(String nickname, String email, String fbClass, String content) {
        //google app script ????????????
        String url = "https://script.google.com/macros/s/AKfycbwAvJ0gNfEFnnXrIM7qqAnSbEReZych_YlzE298MD1mRKtrF6rUYgq4rFaMFLfDnycUZQ/exec?";
        //??????get???????????????
        url = url + "action=create"
                   +"&nickname="+nickname
                   +"&email="+email
                   +"&fbClass="+fbClass
                   +"&content="+content;
        //?????????get
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(formPageActivity.this, response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(formPageActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }

    //??????onBackPressed????????????????????????????????????
    public void onBackPressed(){
        if(menu_Bool==true) {
            menuLayout.setTranslationX(-1);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) menuLayout.getLayoutParams();
            params.width = 1;
            menuLayout.setLayoutParams(params);
            menu_Bool = false;
        }else{
            Intent i=new Intent(formPageActivity.this,MainActivity.class);
            startActivity(i);
            this.finish();
        }

    }
}