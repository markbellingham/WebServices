<%@page contentType="text/html;charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<HTML>
<HEAD>
<TITLE>Result</TITLE>
</HEAD>
<BODY>
<H1>Result</H1>

<jsp:useBean id="sampleCoursesControllerProxyid" scope="session" class="localhost.AssignmentV2.CoursesControllerProxy" />
<%
if (request.getParameter("endpoint") != null && request.getParameter("endpoint").length() > 0)
sampleCoursesControllerProxyid.setEndpoint(request.getParameter("endpoint"));
%>

<%
String method = request.getParameter("method");
int methodID = 0;
if (method == null) methodID = -1;

if(methodID != -1) methodID = Integer.parseInt(method);
boolean gotMethod = false;

try {
switch (methodID){ 
case 2:
        gotMethod = true;
        java.lang.String getEndpoint2mtemp = sampleCoursesControllerProxyid.getEndpoint();
if(getEndpoint2mtemp == null){
%>
<%=getEndpoint2mtemp %>
<%
}else{
        String tempResultreturnp3 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(getEndpoint2mtemp));
        %>
        <%= tempResultreturnp3 %>
        <%
}
break;
case 5:
        gotMethod = true;
        String endpoint_0id=  request.getParameter("endpoint8");
            java.lang.String endpoint_0idTemp = null;
        if(!endpoint_0id.equals("")){
         endpoint_0idTemp  = endpoint_0id;
        }
        sampleCoursesControllerProxyid.setEndpoint(endpoint_0idTemp);
break;
case 10:
        gotMethod = true;
        localhost.AssignmentV2.CoursesController_PortType getCoursesController_PortType10mtemp = sampleCoursesControllerProxyid.getCoursesController_PortType();
if(getCoursesController_PortType10mtemp == null){
%>
<%=getCoursesController_PortType10mtemp %>
<%
}else{
        if(getCoursesController_PortType10mtemp!= null){
        String tempreturnp11 = getCoursesController_PortType10mtemp.toString();
        %>
        <%=tempreturnp11%>
        <%
        }}
break;
case 13:
        gotMethod = true;
        String in_1id=  request.getParameter("in16");
            java.lang.String in_1idTemp = null;
        if(!in_1id.equals("")){
         in_1idTemp  = in_1id;
        }
        java.lang.String newOperation13mtemp = sampleCoursesControllerProxyid.newOperation(in_1idTemp);
if(newOperation13mtemp == null){
%>
<%=newOperation13mtemp %>
<%
}else{
        String tempResultreturnp14 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(newOperation13mtemp));
        %>
        <%= tempResultreturnp14 %>
        <%
}
break;
}
} catch (Exception e) { 
%>
Exception: <%= org.eclipse.jst.ws.util.JspUtils.markup(e.toString()) %>
Message: <%= org.eclipse.jst.ws.util.JspUtils.markup(e.getMessage()) %>
<%
return;
}
if(!gotMethod){
%>
result: N/A
<%
}
%>
</BODY>
</HTML>