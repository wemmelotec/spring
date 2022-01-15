package com.aulawebflux.client;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import java.util.List;
//essa é uma classe de mapeamento do objeto que eu vou receber da api
//não preciso mapear todos os campos
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

public class LocationResponse {

    private String id;
    private String name;
    private String type;
    private List<String> residents;
    private String url;
}
