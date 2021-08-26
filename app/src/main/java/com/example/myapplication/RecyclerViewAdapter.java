package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

/**
 * create an class that define how to display each contact as individual view in the overall
 * recycler view
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private static final String TAG = "RecylerViewAdapter";
    private FirebaseAuth mAuth;

    private ArrayList<String> mContactNames = new ArrayList<>();
    private Context mContext;
    private ArrayList<Integer> mContact_id;


    public RecyclerViewAdapter(ArrayList<String> contact_name, Context context, ArrayList<Integer> contact_id){
        mContactNames = contact_name;
        mContext = context;
        mContact_id = contact_id;

    }


    /**
     * @param parent
     * @param viewType
     * @return each individual information for each recycler view
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layoutlist,parent,false);
        ViewHolder holder = new ViewHolder(view,mContext);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder:called.");

//        Glide.with(mContext)
//                .asBitmap()
//                .load(btn.get(position))
//                .into(holder.btn);

        holder.contact_name.setText(mContactNames.get(position));

        holder.contact_id = mContact_id.get(position);
        holder.ParentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onClick: clicked on:" + mContactNames.get(position));

                Toast.makeText(mContext, mContactNames.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mContactNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        FloatingActionButton btn;
        TextView contact_name;
        RelativeLayout ParentLayout;
        Integer contact_id;

        public ViewHolder( View itemView, Context context) {
            super(itemView);
            mAuth = FirebaseAuth.getInstance();
            FirebaseUser cur_user = mAuth.getCurrentUser();

            btn = itemView.findViewById(R.id.floatingActionButton2);
            contact_name = itemView.findViewById(R.id.contact_name);
            ParentLayout = itemView.findViewById(R.id.parent_layout);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updatecontact(contact_id);

                }
            });
        }
    }

    /**
     * @param contact_id
     * call the updatacontact page with the contact_id that just being clicked
     */
    private void updatecontact(Integer contact_id){
        Intent intent = new Intent(mContext.getApplicationContext(), updateindc.class);
        intent.putExtra("contact_id",contact_id);
        mContext.startActivity(intent);
    }

}

