package BusinessLayer;

public class Client {

    private String userName;
    private String passWord;

    public Client(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "Client{" +
                "userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }
}
