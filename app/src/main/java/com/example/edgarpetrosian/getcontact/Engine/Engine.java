package com.example.edgarpetrosian.getcontact.Engine;

import android.content.Context;

public class Engine {
    private static  Engine engine=null;
    private Services service=null;
    public static Engine getInstance(){
        return (engine==null)?engine=new Engine():engine;
    }
    public Services getServices(Context context){
        return (service==null)?service=new Services(context):service;
    }
}
