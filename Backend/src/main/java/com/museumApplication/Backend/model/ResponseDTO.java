package com.museumApplication.Backend.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ResponseDTO {

    @Setter
    @Getter
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public Object getData() {
        return Data;
    }

    public void setData(Object data) {
        Data = data;
    }

    @Setter
    @Getter
    private String Message;
    @Setter
    @Getter
    private Object Data;

}
