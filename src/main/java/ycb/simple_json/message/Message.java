//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ycb.simple_json.message;

public class Message {
    private String msg = "Test Ok";
    private Object content;

    public Message() {
    }

    public String getMsg() {
        return this.msg;
    }

    public Object getContent() {
        return this.content;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public String toString() {
        return "Message{msg='" + this.msg + '\'' + '}';
    }
}
