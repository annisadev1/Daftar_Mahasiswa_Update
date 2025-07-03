package com.example.daftarmahasiswa;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ListViewAdapter extends BaseAdapter {

    private List<dataMahasiswa> listMahasiswa;
    private Context context;
    private SQLietHelper helper;

    public ListViewAdapter(List<dataMahasiswa> listMahasiswa, Context context) {
        this.listMahasiswa = listMahasiswa;
        this.context = context;
        this.helper = new SQLietHelper(context);
    }

    @Override
    public int getCount() {
        return listMahasiswa.size();
    }

    @Override
    public Object getItem(int position) {
        return listMahasiswa.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_item, parent, false);

        TextView tvNama = view.findViewById(R.id.tvnama);
        TextView tvNim = view.findViewById(R.id.tvnim);
        TextView tvJurusan = view.findViewById(R.id.tvjurusan);
        TextView tvAlamat = view.findViewById(R.id.tvalamat);
        ImageView btnHapus = view.findViewById(R.id.hapus);

        final dataMahasiswa mahasiswa = listMahasiswa.get(position);

        tvNama.setText("Nama: " + mahasiswa.getNama());
        tvNim.setText("NIM: " + mahasiswa.getNim());
        tvJurusan.setText("Jurusan: " + mahasiswa.getJurusan());
        tvAlamat.setText("Alamat: " + mahasiswa.getAlamat());

        // Klik biasa pada item untuk edit
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditActivity.class);
                intent.putExtra("id", mahasiswa.getId());
                intent.putExtra("nama", mahasiswa.getNama());
                intent.putExtra("nim", mahasiswa.getNim());
                intent.putExtra("jurusan", mahasiswa.getJurusan());
                intent.putExtra("alamat", mahasiswa.getAlamat());
                context.startActivity(intent);
            }
        });

        // Klik tombol hapus
        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setMessage("Apakah data akan dihapus?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int isDelete = helper.deleteData(mahasiswa.getId());
                                if (isDelete > 0) {
                                    Toast.makeText(context, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
                                    listMahasiswa.remove(position);
                                    notifyDataSetChanged();
                                } else {
                                    Toast.makeText(context, "Data gagal dihapus", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("Tidak", null);
                alertDialog.show();
            }
        });

        return view;
    }
}
