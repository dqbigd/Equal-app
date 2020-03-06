package com.example.equal.Fragment;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.equal.Adapter.ChatAdapter;
import com.example.equal.Api.ApiClient;
import com.example.equal.Api.ApiInterface;
import com.example.equal.Chat.Chat;
import com.example.equal.Chat.ResponseChat;
import com.example.equal.Preferences;
import com.example.equal.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConsultFragment extends Fragment {
    private RecyclerView rvChat;
    private ImageButton btnSend;
    private EditText txtMessage;

    private ArrayList<Chat> chatList = new ArrayList<>();
    private String message;
    private Integer type, user_id;
    public Boolean validation = true;
    private ChatAdapter chatAdapter;
    final ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);

    public ConsultFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_consult, container, false);

        btnSend = view.findViewById(R.id.btnSend);
        rvChat = view.findViewById(R.id.rvChat);
        txtMessage = view.findViewById(R.id.txtMessage);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
//        linearLayoutManager.setReverseLayout(true);
        rvChat.setLayoutManager(linearLayoutManager);
        rvChat.setAdapter(chatAdapter);
        loadChat ();

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendChatUser();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sendChatBot();
                    }
                },3000);

            }
        });

        return view;
    }

    private void sendChatBot() {
        String notreply = "Maaf saya tidak dapat mengetahui apa yang anda maksud, bisa ulangi yang anda maksut?";
        Boolean tanggapan = false;

        if(chatList.get(chatList.size()-1).getType() == 1){
            notreply = "";
        }else{
            notreply = chatList.get(chatList.size()-1).getMessage();
        }

        Log.d("bot chat", notreply);
        Preferences preferences = new Preferences(getContext());
        user_id = preferences.getUserId();
        type = 1;

        if (notreply.toLowerCase().contains("halo".toLowerCase()) || notreply.toLowerCase().contains("hai".toLowerCase()) || notreply.toLowerCase().contains("hi".toLowerCase())) {
            message = "Halo, Selamat datang di Fitur Konsultasi kami, ada yang bisa kami bantu?";
            tanggapan = true;
        }else if (notreply.toLowerCase().contains("mengalami masalah".toLowerCase()) &&
                (notreply.toLowerCase().contains("pernikahan di usia dini".toLowerCase()) || notreply.toLowerCase().contains("pernikahan usia dini".toLowerCase()) || notreply.toLowerCase().contains("pernikahan dini".toLowerCase()))) {
            message = "silahkan keluhkan masalah anda disini,kami akan membantu memberi yang anda butuhkan";
            tanggapan = true;
        }else if((notreply.toLowerCase().contains("bagaimana".toLowerCase()) && notreply.toLowerCase().contains("mengatasi".toLowerCase())) &&
                (notreply.toLowerCase().contains("kdrt".toLowerCase()) || notreply.toLowerCase().contains("kekerasan dalam rumah tangga".toLowerCase()))) {
            message = " Ada beberapa cara mengatasi kdrt : \n1. Istri dan suami melakukan komunikasi yang intens, \n2. Selesaikan masalah Kdrt dengan kepala dingin"
                    + "\n3. Meminta Solusi kepada kerabat dekat yang bisa untuk mengatasi masalah, \n4. Jika sudah parah seperti luka - luka,maka lakukan visum dan laporkan kepada pihak yang berwajib";
            tanggapan = true;
        }else if(notreply.toLowerCase().contains("bagaimana".toLowerCase()) && notreply.toLowerCase().contains("mencegah".toLowerCase()) &&
                (notreply.toLowerCase().contains("kdrt".toLowerCase()) || notreply.toLowerCase().contains("kekerasan dalam rumah tangga".toLowerCase()))){
            message = "Cara untuk mencegah timbulnya kdrt : \n1. menerapkan pendidikan agama dan moral dalam rumah tangga \n2. menerapkan komunikasi timbal balik antarsesama keluarga"
                    + "\n3. Harus adanya saling kepedulian antaranggota keluarga";
            tanggapan = true;
        }else if(notreply.toLowerCase().contains("Apa dampak".toLowerCase()) &&
                (notreply.toLowerCase().contains("kdrt".toLowerCase()) || notreply.toLowerCase().contains("kekerasan dalam rumah tangga".toLowerCase()))){
            message = "Berikut adalah dampak dari kdrt : \n1. Trauma \n2. Mental lemah \n3. Ketakutan \n4. Paranoid ";
            tanggapan = true;
        }else if(notreply.toLowerCase().contains("Apa bahaya".toLowerCase()) &&
                (notreply.toLowerCase().contains("Pernikahan dini".toLowerCase()) || notreply.toLowerCase().contains("pernikahan di usia dini".toLowerCase()))){
            message = "bahaya dalam pernikahan dini menyebabkan terganggunya kesehatan psikis atau mental wanita, salah satu ancamannya adalah wanita muda "
                    + " rentan menjadi korban kekerasan dalam rumah tangga dan mereka tidak memiliki pengetahuan bagaimana caranya terbebas dari kekerasan tersebut"
                    + "pernikahan dini juga mengancam kesejahtaraan anak karena menyebabkan kemiskinan akibat perampasan hak anak untuk bertumbuh kembang,meraih pendidikan,dan bekerja ";
            tanggapan = true;
        }else if(notreply.toLowerCase().contains("Berapa usia".toLowerCase()) &&
                (notreply.toLowerCase().contains("menikah".toLowerCase()) || notreply.toLowerCase().contains("nikah".toLowerCase() ) || notreply.toLowerCase().contains("pernikahan".toLowerCase() ))){
            message = " Baiknya menikah itu dilakukan pada usia matang 20 hingga 21 tahun untuk perempuan dan 25 tahun untuk laki-laki, usia kurang dari 18 tahun"
                    + "masih dikatakan anak - anak.";
            tanggapan = true;
        }else if (notreply.toLowerCase().contains("terima kasih".toLowerCase())) {
            message = "Sama - sama, Ada yang ingin dikonsultasikan lagi? jika tidak kami akan mengakhiri konsultasi ini";
            tanggapan = true;
        }

        if(!tanggapan){
            message = "Maaf saya tidak dapat mengetahui apa yang anda maksud, bisa ulangi yang anda maksut?";
        }

        Call<Chat> call = apiInterface.setChat(user_id, message,type);
        call.enqueue(new Callback<Chat>() {
            @Override
            public void onResponse(Call<Chat> call, Response<Chat> response) {
                if (response.isSuccessful()){
                    chatAdapter.notifyDataSetChanged();
                    txtMessage.setText("");
                    loadChat();
                    Log.d("response send", String.valueOf(response.code())+" "+response.message());
                }else{
                    Toast.makeText(getContext(), response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                    Log.d("chat no response", String.valueOf(Integer.valueOf(response.code())));
                    Log.d("chat no response", response.message().toString());
                }
            }

            @Override
            public void onFailure(Call<Chat> call, Throwable t) {
                Log.d("Failure Load", t.getMessage());
            }
        });
    }

    private void sendChatUser() {
        Preferences preferences = new Preferences(getContext());
        user_id = preferences.getUserId();
        message = txtMessage.getText().toString();
        type = 0;

        if (TextUtils.isEmpty(message)) {
            validation = false;
        }else {
            validation = true;
        }

        if(validation){
            Call<Chat> call = apiInterface.setChat(user_id, message,type);
            call.enqueue(new Callback<Chat>() {
                @Override
                public void onResponse(Call<Chat> call, Response<Chat> response) {
                    if (response.isSuccessful()){
                        chatAdapter.notifyDataSetChanged();
                        txtMessage.setText("");
                        loadChat();
                        Log.d("response send", String.valueOf(response.code())+" "+response.message());
                    }else{
                        Toast.makeText(getContext(), response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                        Log.d("chat no response", String.valueOf(Integer.valueOf(response.code())));
                        Log.d("chat no response", response.message());
                    }
                }

                @Override
                public void onFailure(Call<Chat> call, Throwable t) {
                    Log.d("Failure Load", t.getMessage());
                }
            });

        }

    }

    private void loadChat() {
        Preferences preferences = new Preferences(getContext());

        String key_api = "PdSgVkYp3s6v9y$B";
        final Integer user_id = preferences.getUserId();

        final ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.show();

        Call<ResponseChat> call = apiInterface.getChat(key_api+"/chat/"+user_id);
        call.enqueue(new Callback<ResponseChat>() {
            @Override
            public void onResponse(Call<ResponseChat> call, Response<ResponseChat> response) {
                if(response.isSuccessful()) {
                    if (response.body().getId().equals(user_id)) {
//                    response.body().getResultSaveJob();
                        dialog.hide();
                        chatList = new ArrayList<>(response.body().getChat());
                        chatAdapter = new ChatAdapter(chatList);
                        rvChat.setAdapter(chatAdapter);
                        rvChat.scrollToPosition(chatList.size() - 1);
                        Log.d("Success ", response.message());
                    }
                }else{
                    Toast.makeText(getActivity(), response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                    Log.d("Chat no resp", response.errorBody().toString());
                    Log.d("Chat no resp", String.valueOf(Integer.valueOf(response.code())));
                    Log.d("Chat no resp", response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseChat> call, Throwable t) {
                Log.d("Failure Load", t.getMessage());
            }
        });
    }

}
