package luistorm.tasseladmin;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EditIngredient extends AppCompatActivity implements View.OnClickListener{

    private EditText eT;
    private Button b;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_ingredient);
        eT = (EditText) findViewById(R.id.editText);
        eT.setText(getIntent().getExtras().getString("name"));
        b = (Button) findViewById(R.id.button);
        b.setOnClickListener(this);
        requestQueue = Volley.newRequestQueue(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == b.getId()) {
            if(eT.getText().toString().isEmpty()) {
                Toast.makeText(this,getString(R.string.write),Toast.LENGTH_LONG).show();
            }
            if(getIntent().getExtras().getString("action").compareTo("update") == 0) {
                String url = utilities.serverAddress+"/controllers/ingredientController.php";
                Map<String, String> params = new HashMap<>();
                params.put("action", "Update");
                params.put("id",getIntent().getExtras().getString("id"));
                params.put("name",eT.getText().toString());
                final Context context = this;
                CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, url,
                        params, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String s = response.getString("response");
                            if(s.compareTo("true") == 0){
                                Toast.makeText(context,getString(R.string.saved),Toast.LENGTH_LONG).show();
                            }
                            else {
                                Toast.makeText(context,getString(R.string.not_saved),Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Intent intent = new Intent(context,MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError response) {
                        Log.d("Response: ", response.toString());
                    }
                });
                requestQueue.add(jsObjRequest);
            }
            if(getIntent().getExtras().getString("action").compareTo("insert") == 0) {
                String url = utilities.serverAddress+"/controllers/ingredientController.php";
                Map<String, String> params = new HashMap<>();
                params.put("action", "Insert");
                params.put("name",eT.getText().toString());
                final Context context = this;
                CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, url,
                        params, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String s = response.getString("response");
                            if(s.compareTo("true") == 0){
                                Toast.makeText(context,getString(R.string.saved),Toast.LENGTH_LONG).show();
                            }
                            else {
                                Toast.makeText(context,getString(R.string.not_saved),Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Intent intent = new Intent(context,MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError response) {
                        Log.d("Response: ", response.toString());
                    }
                });
                requestQueue.add(jsObjRequest);
            }
        }
    }
}
