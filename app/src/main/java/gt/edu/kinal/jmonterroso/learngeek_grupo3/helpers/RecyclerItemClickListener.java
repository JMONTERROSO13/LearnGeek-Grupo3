package gt.edu.kinal.jmonterroso.learngeek_grupo3.helpers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;

/**
 * Created by JMONTERROSO on 04/06/2015.
 */
public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {

    private OnItemClickListener mListener;

    GestureDetector mGestureDetector;

    public RecyclerItemClickListener(Context context, OnItemClickListener mListener) {
        this.mListener = mListener;
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e){
                return true;
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View childView = rv.findChildViewUnder(e.getX(), e.getY());
        if(childView != null && mListener != null && mGestureDetector.onTouchEvent(e)){
            mListener.onItemClick(childView, rv.getChildPosition(childView));
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }
}