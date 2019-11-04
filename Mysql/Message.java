
package Mysql;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Vector;

public class Message implements Serializable{
    
    private static final long serialVersionUID = 1L;
    public String type, sender, recipient;
    public Vector content;
    
    public Message(String type, String sender, Vector content, String recipient){
        this.type = type; this.sender = sender; this.content = content; this.recipient = recipient;
    }
    
    @Override
    public String toString(){
        return "{type='"+type+"', sender='"+sender+"', content='"+content+"', recipient='"+recipient+"'}";
    }
}
