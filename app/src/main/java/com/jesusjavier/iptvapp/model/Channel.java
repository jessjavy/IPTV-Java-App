package com.jesusjavier.iptvapp.model;

public class Channel {
    private String nombre;
    private String streamUrl;
    private String logoUrl;
    private String categoria;

    // Constructor
    public Channel(String nombre, String streamUrl, String logoUrl, String categoria) {
        this.nombre = nombre;
        this.streamUrl = streamUrl;
        this.logoUrl = logoUrl;
        this.categoria = categoria;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getStreamUrl() {
        return streamUrl;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public String getCategoria() {
        return categoria;
    }
}
