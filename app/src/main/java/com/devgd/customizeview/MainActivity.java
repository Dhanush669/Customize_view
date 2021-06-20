package com.devgd.customizeview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;

import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;


public class MainActivity extends AppCompatActivity
        implements View.OnDragListener, View.OnLongClickListener{
    CardView cardView;
    ImageView imageView;
    RelativeLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=findViewById(R.id.imageView);
        cardView=findViewById(R.id.cardView);
        imageView.setOnLongClickListener(this);
        layout=findViewById(R.id.layout);
    }

    @Override
    public boolean onDrag(View view, DragEvent dragEvent) {

        if(dragEvent.getAction()==DragEvent.ACTION_DRAG_ENDED){
            float imgY=dragEvent.getY()-view.getHeight();
            float imgX=dragEvent.getX()-(view.getWidth()/2);
            view.setX(imgX);
            view.setY(imgY);
            if(imgX>=cardView.getX() && imgX<=(cardView.getX()+cardView.getWidth()) &&
                imgY>=cardView.getY() && imgY<=(cardView.getY()+cardView.getHeight())
            ) {
                cardView.setVisibility(View.GONE);
                layout.removeView(imageView);
            }
            return true;
        }


        return true;
    }

    @Override
    public boolean onLongClick(View view) {
        cardView.setVisibility(View.VISIBLE);
        View.DragShadowBuilder builder= new View.DragShadowBuilder(imageView);
        imageView.startDrag(null,
                builder,null,0);
        builder.getView().setOnDragListener(this);
        return true;
    }

}