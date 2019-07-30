package com.example.surviveinsubria;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Timeline extends Fragment {

    Button monte;
    Button morselli;
    Button seppilli;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_timeline, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        monte = (Button)getActivity().findViewById(R.id.button3);
        monte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://timeline.uninsubria.it/index.php?cid=&m=&view=pc&sede=mtg"));
                getActivity().startActivity(intent);
            }
        });
        morselli = (Button)getActivity().findViewById(R.id.button4);
        morselli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://timeline.uninsubria.it/index.php?cid=&m=&view=pc&sede=mrs"));
                getActivity().startActivity(intent);
            }
        });
        seppilli = (Button)getActivity().findViewById(R.id.button5);
        seppilli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://timeline.uninsubria.it/index.php?cid=&m=&view=pc&sede=sep"));
                getActivity().startActivity(intent);
            }
        });

    }
}
