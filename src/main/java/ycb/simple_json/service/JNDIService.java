//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ycb.simple_json.service;

import com.alibaba.fastjson.annotation.JSONType;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@JSONType
public class JNDIService {
    private String target;
    private Context context;

    public JNDIService() {
        System.out.println("JNDIService");
    }

    public void setTarget(String target) {
        System.out.println("setTarget");
        this.target = target;
    }

    public Context getContext() throws NamingException {
        System.out.println("getContext");
        if (this.context == null) {
            this.context = new InitialContext();
        }
        System.out.println(this.target);
        this.context.lookup(this.target);
        return this.context;
    }
}
