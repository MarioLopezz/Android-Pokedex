package com.example.conexionapis;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.GridLayout;

import com.example.conexionapis.api.PokeapiService;
import com.example.conexionapis.models.Pokemon;
import com.example.conexionapis.models.Response;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokemonList extends AppCompatActivity {



    private static final String TAG="POKEDEX";
    private Retrofit retrofit;

    private RecyclerView recyclerView;
    private PokemonListAdapter pokemonListAdapter;

    private int offset;
    private int limit;

    private EditText searchEditText;
    private ArrayList<Pokemon> pokemonList;
    private boolean load;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_list);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        offset= (int) bundle.get("start");
        limit= (int) bundle.get("end");

        recyclerView=findViewById(R.id.recyclerView);
        pokemonListAdapter=new PokemonListAdapter(this);
        recyclerView.setAdapter(pokemonListAdapter);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);

        /*recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy>0){
                    int visibleItems = layoutManager.getChildCount();
                    int totalItems = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                    if(load){
                        if((visibleItems+pastVisibleItems)>=totalItems){
                            load=false;
                            offset+=20;
                            getData(offset);
                        }
                    }
                }
            }
        });*/

        retrofit= new Retrofit.Builder().
                baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        load=true;
        getData(offset, limit);

        searchEditText=findViewById(R.id.searchEditText);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });
    }


    private void getData(int offset, int limit){
        PokeapiService service = retrofit.create(PokeapiService.class);

        Call<Response> responseCall = service.getPokemonList(limit, offset);

        responseCall.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                load=true;
                if(response.isSuccessful()){
                    Response responseBody = response.body();
                    pokemonList= responseBody.getResults();

                    pokemonListAdapter.addPokemonList(pokemonList);

                }else{
                    Log.e(TAG, " onResponse: "+ response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                load=true;
                Log.e(TAG, " onFailure : "+ t.getMessage());
            }
        });
    }

    private void filter(String text){
        ArrayList<Pokemon> filterdList=new ArrayList<>();

        for (Pokemon p : pokemonList){
            if(p.getName().toLowerCase().contains(text.toLowerCase())){
                filterdList.add(p);
            }
        }
        pokemonListAdapter.filterList(filterdList);

    }

}
