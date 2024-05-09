public class WebUser {
    String ip;
    String Name;
    boolean connected;
    public WebUser(String nIp){
        ip = nIp;
    }

    public String getIp() {
        return ip;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }
}
