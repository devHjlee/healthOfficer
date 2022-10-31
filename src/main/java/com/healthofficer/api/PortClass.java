package com.healthofficer.api;

import javax.management.*;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PortClass {
    public List<String> getEndPoints() throws MalformedObjectNameException,
            NullPointerException, UnknownHostException, AttributeNotFoundException,
            InstanceNotFoundException, MBeanException, ReflectionException {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        QueryExp subQuery1 = Query.match(Query.attr("protocol"), Query.value("HTTP/1.1"));
        QueryExp subQuery2 = Query.anySubString(Query.attr("protocol"), Query.value("Http11"));
        QueryExp query = Query.or(subQuery1, subQuery2);
        Set<ObjectName> objs = mbs.queryNames(new ObjectName("*:type=Connector,*"), query);
        String hostname = InetAddress.getLocalHost().getHostName();
        InetAddress[] addresses = InetAddress.getAllByName(hostname);
        ArrayList<String> endPoints = new ArrayList<String>();
        for (ObjectName obj : objs) {
            String scheme = mbs.getAttribute(obj, "scheme").toString();
            String port = obj.getKeyProperty("port");
            for (InetAddress addr : addresses) {
                if (addr.isAnyLocalAddress() || addr.isLoopbackAddress() ||
                        addr.isMulticastAddress()) {
                    continue;
                }
                String host = addr.getHostAddress();
                String ep = scheme + "://" + host + ":" + port;
                endPoints.add(ep);
            }
        }
        return endPoints;
    }
}
