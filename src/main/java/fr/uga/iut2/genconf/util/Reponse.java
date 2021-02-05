package fr.uga.iut2.genconf.util;

public class Reponse<T>{

    private boolean success;
    private T data;
    private String message;

    public Reponse(){}

    public Reponse(String message)
    {
        this.success = false;
        this.message = message;
    }

    public Reponse(T data)
    {
        this.success = true;
        this.data = data;
    }

    public Reponse(boolean success, T data, String message){
        this.success = success;
        this.data = data;
        this.message = message;
    }

    public boolean succeed(){ return this.success; }

    public T getData(){ return this.data; }

    public String getMessage() { return this.message; }
}
