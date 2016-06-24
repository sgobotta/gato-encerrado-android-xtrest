package com.grupo3.gatoencerrado.service;

import retrofit.RestAdapter;

public class LaberintosServiceBuilder {

    static public String SERVER_IP = "192.168.1.102";
    static public String API_URL = "http://" + SERVER_IP + ":9001";

    static public LaberintosService createLaberintosService() {

        //String SERVER_IP = "10.0.2.2"; esta ip se usa para comunicarse con mi localhost en el emulador de Android Studio
        // 10.0.3.15
//        String SERVER_IP = "192.168.1.102";
////        String SERVER_IP_GENY = "192.168.56.1";//esta ip se usa para comunicarse con mi localhost en el emulador de Genymotion
//        String API_URL = "http://" + SERVER_IP + ":9001";

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(API_URL).build();
        LaberintosService laberintosService = restAdapter.create(LaberintosService.class);
        return laberintosService;
    }
}
