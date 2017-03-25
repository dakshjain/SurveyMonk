package com.example.rajatjain.surveymonk;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;




public class MyRecyclerViewAdapter2 extends RecyclerView
        .Adapter<MyRecyclerViewAdapter2
        .DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private static Context mContext;
    private ArrayList<DataObject1> mDataset;
    private static MyClickListener myClickListener;
    private static Context context;
    private MyRecyclerViewAdapter2.MyClickListener listener;
    String day;

    public  class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView ques;
        TextView option1;
        TextView option2;
        TextView option3;
        TextView option4;

        public DataObjectHolder(View itemView) {
            super(itemView);

            ques = (TextView) itemView.findViewById(R.id.card_textView_2);
            option1 = (TextView) itemView.findViewById(R.id.card_textView2_2);
            option2 = (TextView) itemView.findViewById(R.id.card_textView3_2);
            option3 = (TextView) itemView.findViewById(R.id.card_textView4_2);
            option4 = (TextView) itemView.findViewById(R.id.card_textView5_2);

            Log.i(LOG_TAG, "Adding Listener" );

        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(),v);
        }
    }


    public MyRecyclerViewAdapter2(ArrayList<DataObject1> myDataset) {
        mDataset = myDataset;
        this.listener = listener;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_row_2, parent, false);
        MyRecyclerViewAdapter2.context =parent.getContext();
        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {

        holder.ques.setText(mDataset.get(position).getmText1());
        holder.option1.setText(mDataset.get(position).getmText2());
        holder.option2.setText(mDataset.get(position).getmText3());
        holder.option3.setText(mDataset.get(position).getmText4());
        holder.option4.setText(mDataset.get(position).getmText5());

    }

    public void addItem(DataObject1 dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }
    public String getday(){
        return day;
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }

}
