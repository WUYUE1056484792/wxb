package com.fh.util;

public class DataMsg {

    private String  stateMsg="success";

    private  String  stateCode="200";

    private Object data;


    public DataMsg() {
    }

    public String getStateMsg() {
        return stateMsg;
    }

    public void setStateMsg(String stateMsg) {
        this.stateMsg = stateMsg;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "DataMsg{" +
                "stateMsg:'" + stateMsg + '\'' +
                ", stateCode:'" + stateCode + '\'' +
                ", data:" + data +
                '}';
    }

    public  static  void main (String [] args){
           DataMsg dataMsg = new DataMsg();
        dataMsg.setStateCode("400");
       System.out.println(dataMsg.toString());

    }
}
