package com.example.conexionapis.api;

import com.example.conexionapis.models.Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PokeapiService {

    @GET("pokemon")
    Call<Response> getPokemonList(@Query("limit") int limit, @Query("offset") int offset);
}
