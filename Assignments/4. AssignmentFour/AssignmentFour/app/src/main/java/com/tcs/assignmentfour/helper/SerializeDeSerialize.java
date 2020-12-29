package com.tcs.assignmentfour.helper;

import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializeDeSerialize {
    private String serializedObject;
    private Object deSerializedObject;

    public SerializeDeSerialize(String serializedObject) {
        this.serializedObject = serializedObject;
    }

    public SerializeDeSerialize(Object deSerializedObject) {
        this.deSerializedObject = deSerializedObject;
    }

    public String getSerializedObject() {
        return serializedObject;
    }

    public void setSerializedObject(String serializedObject) {
        this.serializedObject = serializedObject;
    }

    public Object getDeSerializedObject() {
        return deSerializedObject;
    }

    public void setDeSerializedObject(Object deSerializedObject) {
        this.deSerializedObject = deSerializedObject;
    }

    public String serialize(){
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(deSerializedObject);
            objectOutputStream.flush();
            serializedObject = byteArrayOutputStream.toString();
        } catch (Exception e) {
            Log.e("SerializeDeSerialize.serialize",String.valueOf(e));
        }
        return serializedObject;
    }

    public Object deSerialize(){
        try {
            byte b[] = serializedObject.getBytes();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(b);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            deSerializedObject = (Object) objectInputStream.readObject();
        } catch (Exception e) {
            Log.e("SerializeDeSerialize.deSerialize",String.valueOf(e));
        }
        return deSerializedObject;
    }
}
