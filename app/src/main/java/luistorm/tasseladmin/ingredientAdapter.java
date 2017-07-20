package luistorm.tasseladmin;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by luis on 14/07/17.
 */

public class ingredientAdapter extends RecyclerView.Adapter<ingredientAdapter.ingredientViewHolder> {
    private List<ingredient> items;
    private static Context context;

    public static class ingredientViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView name;
        public CardView card;

        public ingredientViewHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.textView5);
            card = (CardView) v.findViewById(R.id.cardIngredient);
            context = v.getContext();
        }
    }

    public ingredientAdapter(List<ingredient> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public ingredientAdapter.ingredientViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.ingredient_item, viewGroup, false);
        return new ingredientAdapter.ingredientViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ingredientAdapter.ingredientViewHolder viewHolder, int i) {
        viewHolder.name.setText(items.get(i).getName());
        final int id = items.get(i).getId();
        final String name = items.get(i).getName();
        viewHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,EditIngredient.class);
                intent.putExtra("id",Integer.toString(id));
                intent.putExtra("name",name);
                intent.putExtra("action","update");
                context.startActivity(intent);
            }
        });
    }
}
