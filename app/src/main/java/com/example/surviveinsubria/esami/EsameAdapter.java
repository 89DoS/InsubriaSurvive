package com.example.surviveinsubria.esami;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.surviveinsubria.R;
import com.example.surviveinsubria.objects.Esame;

public class EsameAdapter extends RecyclerView.Adapter<EsameAdapter.MyViewHolder> {
    private Esame[] esami;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public LinearLayout layout;
        public MyViewHolder(LinearLayout l) {
            super(l);
            l.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("bubububu");
                }
            });
            l.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
                @Override
                public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
                    final LinearLayout l = (LinearLayout) v;
                    menu.setHeaderTitle("Esame");
                    menu.add(0, v.getId(), 0, "Aggiungi al piano di battaglia").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            System.out.println(((TextView) l.getChildAt(0)).getText().toString());
                            return true;
                        }
                    });
                    menu.add(1, v.getId(), 0, "Rimuovi dall'elenco").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            System.out.println(menuInfo);
                            return true;
                        }
                    });
                }
        });
            layout = l;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public EsameAdapter(Esame[] esami) {
        this.esami = esami;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public EsameAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        // create a new view
        LinearLayout l = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.esame_item, parent, false);
        MyViewHolder vh = new MyViewHolder(l);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        ((TextView) holder.layout.getChildAt(0)).setText(esami[position].toString());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return esami.length;
    }
}
