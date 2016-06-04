<%@ page import="com.util.*"%>
<%@ page import="com.bamboo.*"%>
<pre><%GlobalVariable m = GlobalVariable.getInstance();
String json = request.getAttribute(m.ATTR_OUTPUT_JSON);
out.write(json);%>tt</pre>