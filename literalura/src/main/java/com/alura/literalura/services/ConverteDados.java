package com.alura.literalura.services;

import tools.jackson.databind.ObjectMapper;

public class ConverteDados implements IConverteDados{

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        if (json == null || json.isBlank()) {
            throw new IllegalArgumentException("JSON vazio ou nulo recebido da API");
        }
        try {
            return mapper.readValue(json, classe);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
