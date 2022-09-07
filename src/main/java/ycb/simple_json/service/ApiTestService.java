//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ycb.simple_json.service;

import org.springframework.stereotype.Service;
import ycb.simple_json.message.Message;

@Service
public class ApiTestService {
    private Message msg = new Message();

    public ApiTestService() {
    }

    public void setMsg(Message msg) {
        this.msg = msg;
    }

    public Message getMsg() {
        return this.msg;
    }
}
