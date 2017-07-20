package luistorm.tasseladmin;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

public class Comments extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;
    private RequestQueue requestQueue;
    private Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        final List items = new ArrayList();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView2);
        recyclerView.setHasFixedSize(true);

        lManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(lManager);
        final Context context = this;

        requestQueue = Volley.newRequestQueue(this);
        String url = utilities.serverAddress+"/controllers/commentController.php?action=GetAll";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.compareTo("Something is wrong with the comments query") != 0) {
                            String[] comments = response.split(";");
                            for (int i = 0; i < comments.length; i++) {
                                String[] info = comments[i].split(":");
                                items.add(new comment(Integer.parseInt(info[0]),info[1],info[2]
                                        ,Integer.parseInt(info[3]),Integer.parseInt(info[4])));
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

    }
}
