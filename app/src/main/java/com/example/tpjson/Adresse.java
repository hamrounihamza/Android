package com.example.tpjson;

public class Adresse {

    int idAdresse;
    String longitude;
    String latitude;
    String type;

    public Adresse(int idAdresse, String longitude, String latitude, String type) {
        this.idAdresse = idAdresse;
        this.longitude = longitude;
        this.latitude = latitude;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Adresse{" +
                "idAdresse=" + idAdresse +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}

