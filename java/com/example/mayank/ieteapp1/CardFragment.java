package com.example.mayank.ieteapp1;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class CardFragment extends Fragment {
    ArrayList<EventModel> listitems = new ArrayList<>();
    RecyclerView MyRecyclerView;
    String Wonders[] = {"Clubs and Chapters Expo","Arduino","Happy Diwali","Neural Networks","Android Workshop","Electronics","Freshers"};
    String Wonders1[] = {"Clubs and Chapters Expo","Arduino","Happy Diwali","Neural Networks","Android Workshop","Electronics","Freshers"};
    int  Images[] = {R.drawable.expo,R.drawable.arduinoworkshop,R.drawable.diwali1,R.drawable.neural,R.drawable.taj_mahal,R.drawable.electronicsworkshop,R.drawable.taj_mahal};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeList();
        getActivity().setTitle("Upcoming Events");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_card, container, false);
        MyRecyclerView = (RecyclerView) view.findViewById(R.id.cardView);
        MyRecyclerView.setHasFixedSize(true);
        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getActivity());
        MyLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        if (listitems.size() > 0 & MyRecyclerView != null) {
            MyRecyclerView.setAdapter(new MyAdapter(listitems));
        }
        MyRecyclerView.setLayoutManager(MyLayoutManager);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private ArrayList<EventModel> list;

        public MyAdapter(ArrayList<EventModel> Data) {
            list = Data;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
            // create a new view
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycle_items, parent, false);
            MyViewHolder holder = new MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {

            holder.titleTextView.setText(list.get(position).getCardName());
            holder.descTextView.setText(list.get(position).getCardDesc());
            holder.coverImageView.setImageResource(list.get(position).getImageResourceId());
            holder.coverImageView.setTag(list.get(position).getImageResourceId());
            holder.likeImageView.setTag(R.drawable.ic_like);

        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView titleTextView;
        public ImageView coverImageView;
        public ImageView likeImageView;
        public ImageView shareImageView;
        public TextView descTextView;

        public MyViewHolder(View v) {
            super(v);
            titleTextView = (TextView) v.findViewById(R.id.titleTextView);
            descTextView = (TextView) v.findViewById(R.id.desctextView);
            coverImageView = (ImageView) v.findViewById(R.id.coverImageView);
            likeImageView = (ImageView) v.findViewById(R.id.likeImageView);
            shareImageView = (ImageView) v.findViewById(R.id.shareImageView);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //int id = (int)coverImageView.getTag();
                    Toast.makeText(getActivity(),titleTextView.getText()+" clicked",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(v.getContext(),FullImage.class);

                    //ImageView cover = (ImageView) v.findViewById(R.id.coverImageView);

                    i.putExtra("Title",((TextView) v.findViewById(R.id.titleTextView)).getText().toString());
                    //i.putExtra("Description",((TextView) v.findViewById(R.id.descTextView)).getText().toString());
                    v.getContext().startActivity(i);

                }
            });




            likeImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    int id = (int)likeImageView.getTag();
                    if( id == R.drawable.ic_like){

                        likeImageView.setTag(R.drawable.ic_liked);
                        likeImageView.setImageResource(R.drawable.ic_liked);

                        Toast.makeText(getActivity(),titleTextView.getText()+" added to favourites",Toast.LENGTH_SHORT).show();

                    }else{

                        likeImageView.setTag(R.drawable.ic_like);
                        likeImageView.setImageResource(R.drawable.ic_like);
                        Toast.makeText(getActivity(),titleTextView.getText()+" removed from favourites",Toast.LENGTH_SHORT).show();


                    }

                }
            });





            shareImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Uri imageUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                            "://" + getResources().getResourcePackageName(coverImageView.getId())
                            + '/' + "drawable" + '/' + getResources().getResourceEntryName((int)coverImageView.getTag()));


                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_STREAM,imageUri);
                    shareIntent.setType("image/jpeg");
                    startActivity(Intent.createChooser(shareIntent, getResources().getText(R.string.send_to)));



                }
            });



        }
    }

    public void initializeList() {
        listitems.clear();

        for(int i =0;i<7;i++){


            EventModel item = new EventModel();
            item.setCardName(Wonders[i]);
            item.setCardDesc(Wonders1[i]);
            item.setImageResourceId(Images[i]);
            item.setIsfav(0);
            item.setIsturned(0);
            listitems.add(item);

        }
    }
}
