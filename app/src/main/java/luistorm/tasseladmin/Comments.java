package luistorm.tasseladmin;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Comments extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;
    private RequestQueue requestQueue;
    private Button b;
    private List items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        items = new ArrayList();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView2);
        recyclerView.setHasFixedSize(true);

        lManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(lManager);
        final Context context = this;

        requestQueue = Volley.newRequestQueue(this);
        String url = utilities.serverAddress+"/controllers/commentController.php?action=GetAllAdmin";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.compareTo("Something is wrong with the comments query") != 0) {
                            String[] comments = response.split(";");
                            for (int i = 0; i < comments.length; i++) {
                                String[] info = comments[i].split(":");
                                items.add(new comment(Integer.parseInt(info[0]),info[1],info[3]
                                        ,Integer.parseInt(info[2]),Integer.parseInt(info[4])));
                                adapter = new commentAdapter(items);
                                recyclerView.setAdapter(adapter);
                            }

                        }
                        else{
                            Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error: \n" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequest);
        b = (Button) findViewById(R.id.button2);
        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(b.getId() == view.getId()) {
            for (int i = 0; i < items.size(); i++) {
                Map<String, String> params = new HashMap<>();
                params.put("action", "SetVisibility");
                params.put("id",Integer.toString(((comment)items.get(i)).getId()));
                params.put("val",Integer.toString(((comment)items.get(i)).getVisible()));
                final Context context = this;
                String url = utilities.serverAddress+"/controllers/commentController.php";
                CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, url,
                        params, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String s = response.getString("response");
                            int id = response.getInt("id");
                            if(s.compareTo("true") == 0 &&
                                    ((comment)items.get(items.size()-1)).getId() == id){
                                Toast.makeText(context,getString(R.string.saved),Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(context,MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }
                            else if(((comment)items.get(items.size()-1)).getId() == id){
                                Toast.makeText(context,getString(R.string.not_saved),Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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
