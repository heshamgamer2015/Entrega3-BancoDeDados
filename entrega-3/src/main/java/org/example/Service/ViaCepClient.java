package org.example.Service;

import com.google.gson.Gson;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.example.Entity.Endereco;

import java.io.IOException;

public class ViaCepClient {
    private static final String URL_TEMPLATE = "https://viacep.com.br/ws/%s/json/";

    public Endereco buscarEnderecoPorCep(String cep) throws IOException {
        String url = String.format(URL_TEMPLATE, cep);

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                String json = EntityUtils.toString(response.getEntity());
                Gson gson = new Gson();
                ViaCepResponse viaCepResponse = gson.fromJson(json, ViaCepResponse.class);
                if (viaCepResponse.getErro() == null || !viaCepResponse.getErro()) {
                    Endereco endereco = new Endereco();
                    endereco.setCep(viaCepResponse.getCep());
                    endereco.setRua(viaCepResponse.getLogradouro());
                    endereco.setCidade(viaCepResponse.getLocalidade());
                    endereco.setEstado(viaCepResponse.getUf());
                    return endereco;
                } else {
                    throw new RuntimeException("CEP não encontrado.");
                }
            }
        }
    }

    private class ViaCepResponse {
        private String cep;
        private String logradouro;
        private String localidade;
        private String uf;
        private Boolean erro;

        public String getCep() { return cep; }
        public void setCep(String cep) { this.cep = cep; }
        public String getLogradouro() { return logradouro; }
        public void setLogradouro(String logradouro) { this.logradouro = logradouro; }
        public String getLocalidade() { return localidade; }
        public void setLocalidade(String localidade) { this.localidade = localidade; }
        public String getUf() { return uf; }
        public void setUf(String uf) { this.uf = uf; }
        public Boolean getErro() { return erro; }
        public void setErro(Boolean erro) { this.erro = erro; }
    }
}