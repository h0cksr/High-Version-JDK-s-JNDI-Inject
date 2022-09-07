//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ycb.simple_json.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ycb.simple_json.message.Message;
import ycb.simple_json.service.ApiTestService;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping({"/ApiTest"})
public class JsonApiTestController {
    @Autowired
    private ApiTestService apiTestService;

    public JsonApiTestController() {
    }

    @GetMapping({"/get"})
    public String getApiTest() {
        return this.apiTestService.getMsg().toString();
    }

    @PostMapping({"/post"})
    public String postApiTest(HttpServletRequest request) {
        ServletInputStream inputStream = null;
        String jsonStr = null;

        try {
            inputStream = request.getInputStream();
            StringBuffer stringBuffer = new StringBuffer();
            byte[] buf = new byte[1024];
            boolean var6 = false;

            int len;
            while((len = inputStream.read(buf)) != -1) {
                stringBuffer.append(new String(buf, 0, len));
            }

            inputStream.close();
            jsonStr = stringBuffer.toString();
            return ((Message)JSON.parseObject(jsonStr, Message.class)).toString();
        } catch (IOException var7) {
            var7.printStackTrace();
            return "Test fail";
        }
    }
}
