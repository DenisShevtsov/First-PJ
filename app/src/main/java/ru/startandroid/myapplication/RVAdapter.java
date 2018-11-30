package ru.startandroid.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vk.sdk.api.methods.VKApiFriends;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.FriendViewHolder>{
    public static class FriendViewHolder extends RecyclerView.ViewHolder{
        CardView cv;
        TextView friendsName;
        TextView friendsSex;
        ImageView friendsPhoto;
        FriendViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            friendsName = (TextView)itemView.findViewById(R.id.friend_name);
            friendsSex = (TextView)itemView.findViewById(R.id.friend_sex);
            friendsPhoto = (ImageView)itemView.findViewById(R.id.friend_photo);
        }
    }
    List<Friend> friends;
    RVAdapter(List<Friend> friends){
        this .friends = friends;
    }



    @NonNull
    @Override
    public FriendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.maket_1, parent, false);
        FriendViewHolder fvh = new FriendViewHolder(v);
        return fvh;
    }

    @Override
    public void onBindViewHolder(@NonNull FriendViewHolder holder, int position) {
        holder.friendsName.setText(friends.get(position).nickname);
        holder.friendsSex.setText(friends.get(position).sex);
        Glide.with(holder.itemView.getContext()).load(friends.get(position).photo).into(holder.friendsPhoto);
    }

    @Override
    public int getItemCount() { return friends.size(); }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView){
        super .onAttachedToRecyclerView(recyclerView);
    }
}
