package com.thecat.TesteApi;

public class MassaDeDados {
    String vote_id;
    String favourite_id;

    String urlCadastro = "user/passwordlesssignup";
    // barras antes das aspas para identificar corretamente as diferentes Strings
    String corpoCadastro = "{\"email\": \"fulano@detal.com\", \"appDescription\": \"tests with rest assured\"}";
    String corpoVotacao = "{\"image_id\": \"ZdhQh9wc9\", \"value\": \"true\", \"sub_id\": \"demo-1fa94e\"}";
    String corpoFavorita = "{\"image_id\": \"2uo\",\"sub_id\": \"your-user-1234\"}";
    String urlDeletaFavorito = "favourites/{favourite_id}";
}
