package com.technoirarts.kittinder;

public class GenericResponse {

    private final String status;
    private final Object data;

    public GenericResponse(String status) {
        this.status = status;
        this.data = null;
    }

    public GenericResponse(String status, Object data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public Object getData() {
        return data;
    }
}
