package colin.app.common;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.bosh.XMPPBOSHConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by DELL on 2015/7/31.
 */
public class ChatConfigConnect {
    /**
     * 返回链接配置
     * @param username
     * @param password
     * @return
     * @throws IOException
     */
    public XMPPTCPConnectionConfiguration initConnection(String username, String password) throws IOException {
        //读取配置文件
        ClassPathResource chatPath = new ClassPathResource("chat.properties");
        if (chatPath.exists()) {
            Properties properties = new Properties();
            properties.load(chatPath.getInputStream());
            XMPPTCPConnectionConfiguration xmpptcpConnectionConfiguration = XMPPTCPConnectionConfiguration.builder().setUsernameAndPassword(username, password)
                    .setSecurityMode(ConnectionConfiguration.SecurityMode.disabled)
                    .setServiceName(properties.getProperty("serviceName"))
                    .setHost(properties.getProperty("host"))
                    .setPort(Integer.valueOf(properties.getProperty("port")))
                    .build();
            return xmpptcpConnectionConfiguration;
        } else {
            System.out.println("未找到配置文件");
            return null;
        }
    }
    public AbstractXMPPConnection initXMPPConnection(String username,String password) throws IOException {
        if(this.initConnection(username,password)!=null){
            AbstractXMPPConnection xmppConnection=new XMPPTCPConnection(this.initConnection(username,password));
            return  xmppConnection;
        }else{
            return null;
        }

    }
}
