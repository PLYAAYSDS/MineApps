package com.mine.firstapp.mine;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ForumAdapter extends RecyclerView.Adapter<ForumAdapter.BeritaViewHolders> {
    private List<Forum> beritas;
    protected Context context;
    public OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setItemOnClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public ForumAdapter(Context context, List<Forum> beritas) {
        this.beritas = beritas;
        this.context = context;
    }

    @Override
    public BeritaViewHolders onCreateViewHolder(ViewGroup parent, int
            viewType) {
        BeritaViewHolders viewHolder = null;
        View layoutView =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.lihat_berita, parent, false);
        viewHolder = new BeritaViewHolders(layoutView, beritas);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BeritaViewHolders holder, int position)
    {
        String judul = beritas.get(position).getTopik();
        String tanggal = beritas.get(position).getPertanyaan();
        String isi = beritas.get(position).getPertanyaan();

        holder.judul.setText(judul);
//        holder.tanggal.setText(tanggal);
        holder.isi.setText(isi);

    }
    @Override
    public int getItemCount() {
        return this.beritas.size();
    }

    public class BeritaViewHolders extends RecyclerView.ViewHolder{
        //private static final String TAG = com.motogp.motogpnews.BeritaViewHolderss.class.getSimpleName();
        public TextView id;
        public TextView judul;
        public TextView tanggal;
        public TextView isi;

        //public TextView poin;
        private List<Forum> berita;

        public BeritaViewHolders(final View itemView, final List<Forum> berita) {
            super(itemView);

            this.berita = berita;
            judul = (TextView)itemView.findViewById(R.id.judul);
            isi = (TextView)itemView.findViewById(R.id.isi);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(mListener != null){
                        if(position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
