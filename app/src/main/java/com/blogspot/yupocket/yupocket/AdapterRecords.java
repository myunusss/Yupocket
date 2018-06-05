package com.blogspot.yupocket.yupocket;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AdapterRecords extends RecyclerView.Adapter<AdapterRecords.ViewHolder> {

    private List<Record> mRecordList;
    private Context mContext;
    private RecyclerView mRecyclerView;

    @Override
    public AdapterRecords.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.card_record, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterRecords.ViewHolder holder, int position) {
        final Record record = mRecordList.get(position);
        holder.tv_recordTanggal.setText("Tanggal : " + record.getTanggal());
        holder.tv_recordJenis.setText("Jenis Pengeluaran : " + record.getJenis());
        holder.tv_recordKeterangan.setText("Keterangan : " + record.getKeterangan());
        holder.tv_recordBiaya.setText("Biaya : " + record.getBiaya());
    }

    @Override
    public int getItemCount() {
        return mRecordList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_recordTanggal;
        public TextView tv_recordJenis;
        public TextView tv_recordKeterangan;
        public TextView tv_recordBiaya;

        public View layout;

        public ViewHolder(View itemView) {
            super(itemView);
            layout = itemView;

            tv_recordTanggal = itemView.findViewById(R.id.view_tanggal);
            tv_recordJenis = itemView.findViewById(R.id.view_jenis);
            tv_recordKeterangan = itemView.findViewById(R.id.view_keterangan);
            tv_recordBiaya = itemView.findViewById(R.id.view_biaya);
        }
    }

    public void add(int position, Record record) {
        mRecordList.add(position, record);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        mRecordList.remove(position);
        notifyItemRemoved(position);
    }

    public AdapterRecords(List<Record> myDataset, Context context, RecyclerView recyclerView) {
        mRecordList = myDataset;
        mContext = context;
        mRecyclerView = recyclerView;
    }
}
