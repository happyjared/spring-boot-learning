package cn.mariojd.bsc.util;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Jared
 * @date 2019/8/9 17:15
 */
public class IpUtil {

    private static final String UNKNOWN = "unknown";

    private static final String STRING = "127.0.0.1";

    private static final String X_FORWARDED_FOR = "x-forwarded-for";

    private static final String PROXY_CLIENT_IP = "Proxy-Client-IP";

    private static final String WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";

    private static final String COMMA = ",";

    private static final Integer FIFTEEN = 15;

    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = request.getHeader(X_FORWARDED_FOR);
        if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader(PROXY_CLIENT_IP);
        }
        if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader(WL_PROXY_CLIENT_IP);
        }
        if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (STRING.equals(ipAddress)) {
                try {
                    // 根据网卡取本机配置的IP
                    InetAddress address = InetAddress.getLocalHost();
                    ipAddress = address.getHostAddress();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > FIFTEEN) {
            if (ipAddress.indexOf(COMMA) > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(COMMA));
            }
        }
        return ipAddress;
    }

}
