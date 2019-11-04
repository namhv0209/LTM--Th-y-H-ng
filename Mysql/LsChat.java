
package Mysql;

import java.io.Serializable;


public class LsChat implements Serializable{
    private int stt;
    private int id1;
    private int id2;
    private String noidung;
    private int sender;

    public LsChat() {
    }

    public LsChat(int id1, int id2, String noidung,int sender) {
        this.id1 = id1;
        this.id2 = id2;
        this.noidung = noidung;
        this.sender = sender;
    }

    public int getId1() {
        return id1;
    }

    public void setId1(int id1) {
        this.id1 = id1;
    }

    public int getId2() {
        return id2;
    }

    public void setId2(int id2) {
        this.id2 = id2;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }
    
    
}
