package com.android.tiagorodrigues.moneykeeper;
 import android.app.Activity;
 import android.app.AlertDialog;
 import android.content.Context;
 import android.os.AsyncTask;
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.io.OutputStream;
 import java.io.OutputStreamWriter;
 import java.net.HttpURLConnection;
 import java.net.MalformedURLException;
 import java.net.URL;
 import java.net.URLEncoder;
 import android.content.Intent;
 import android.widget.Toast;

/**
 * Created by tiagorodrigues on 26/10/2017.
 */

public class Connect  extends AsyncTask<String,Void,String> {

    String usergb,passwordgb;
    Context context;
    AlertDialog alertDialog;
    Connect (Context ctx){
        context = ctx;
    }
    @Override
    protected String doInBackground(String... params){

        String type= params[0];
        String login_url = "http://moneykeeper.000webhostapp.com/login.php";
        if(type.equals("login"))
        {
            try
            {
                String user_name = params[1];
                String password = params[2];
                usergb = params[1];
                passwordgb = params[2];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data  = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result="";
                String line="";
                while ((line = bufferedReader.readLine())!=null){
                    result+=line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return result;
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return null;

    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");

    }

    @Override
    protected void onPostExecute(String result) {
        //alertDialog.setMessage(result);
        //alertDialog.show();
        if(result.equals("1")){

            String type = "objectivos";
            ConnectObjectivos connect = new ConnectObjectivos(context);
            connect.execute(type,usergb,passwordgb);

           //Intent intent = new Intent(context,objectivos.class);
           //context.startActivity(intent);
            //alertDialog.setMessage("login conseguido!");
            //alertDialog.show();

        }else{
            alertDialog.setMessage("User ou Password errado!");
            alertDialog.show();
        }
    }
}
