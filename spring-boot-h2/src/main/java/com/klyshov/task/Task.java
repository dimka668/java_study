package com.klyshov.task;

/**
 * Created by 16688641 on 12.02.2019.
 */

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.File;
import java.io.FileOutputStream;

@XmlAccessorType(XmlAccessType.FIELD)
//@XmlType(name = "Task")
@XmlRootElement(name = "Task")
public class Task {

    @XmlElement(name = "user")
    protected String user;
    @XmlElement(name = "command")
    String command;
    @XmlAttribute(name = "priority")
    int priority;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Task(){
    }

    public Task(String user, String command, int priority){
        this.user = user;
        this.command = command;
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "user: "+user+"; command:"+command;
    }

    public void marshalToFile(String fileName) {
        try {
            //JAXBContext jc = JAXBContext.newInstance(this.getClass().getPackage().getName());
            JAXBContext jc = JAXBContext.newInstance(this.getClass());
            Marshaller m = jc.createMarshaller();
            m.marshal( this, new FileOutputStream(fileName) );
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static Task unmarshalFromFile(String fileName) {
        try {
            JAXBContext jc = JAXBContext.newInstance((new Task()).getClass());
            Unmarshaller um = jc.createUnmarshaller();
            return (Task) um.unmarshal(new File(fileName));
        } catch (Exception e) {
            return new Task();
        }
    }
    private class taskGenegator{
        public int counter;
        new


    }

}
