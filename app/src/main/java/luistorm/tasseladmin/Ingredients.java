package luistorm.tasseladmin;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

public class Ingredients extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;
    private RequestQueue requestQueue;
    private FloatingActionButton fAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);
        final List items = new ArrayList();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        lManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(lManager);
        final Context context = this;

        requestQueue = Volley.newRequestQueue(this);
        String url = utilities.serverAddress+"/controllers/ingredientController.php?action=GetAll";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.compareTo("Something is wrong with the ingredients query") != 0) {
                            String[] ingredients = response.split(";");
                            for (int i = 0; i < ingredients.length; i++) {
                                String[] info = ingredients[i].split(":");
                                items.add(new ingredient(Integer.parseInt(info[0]),info[1]));
                                adapter = new ingredientAdapter(items);
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

        fAB = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        fAB.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == fAB.getId()) {
            Intent intent = new Intent(this, EditIngredient.class);
            intent.putExtra("id","");
            intent.putExtra("name","");
            intent.putExtra("action","insert");
            startActivity(intent);
        }
    }
}
