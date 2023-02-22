package WritingIntoCSV;

import java.io.*;

public class Serializator implements Serializable   {

    private static final long serialVersionUID = 1234567L;

    private FileInputStream fileInputStream ;

    private ObjectInputStream inputStream;

    private FileOutputStream fileOutputStream;

    private ObjectOutputStream outputStream ;

    public Serializator(){

        try {
            this.fileOutputStream =  new FileOutputStream("DeliveryService.ser");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            this.outputStream =  new ObjectOutputStream(fileOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            this.fileInputStream=new FileInputStream("E:\\PT2022_30225_Ciprian_Gabriel_Hirtescu_Assignment_4\\DeliveryService.ser");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            this.inputStream  = new ObjectInputStream(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileInputStream getFileInputStream() {
        return fileInputStream;
    }

    public void setFileInputStream(FileInputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }

    public ObjectInputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(ObjectInputStream inputStream) {
        this.inputStream = inputStream;
    }

    public FileOutputStream getFileOutputStream() {
        return fileOutputStream;
    }

    public void setFileOutputStream(FileOutputStream fileOutputStream) {
        this.fileOutputStream = fileOutputStream;
    }

    public ObjectOutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(ObjectOutputStream outputStream) {
        this.outputStream = outputStream;
    }


}
