<%@page import="com.mcnz.jpa.examples.*;"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean class="com.mcnz.jpa.examples.Client"  
     id="client" scope="session"/>
<jsp:useBean class="com.mcnz.jpa.examples.ClientDetail" 
     id="clientDetail" scope="request"/>
<jsp:useBean class="com.mcnz.jpa.examples.Address" 
     id="address" scope="request"/>
<jsp:setProperty name="client" property="*" />
<jsp:setProperty name="clientDetail" property="*" />	
<jsp:setProperty name="address" property="*" />
<% 
String command = request.getParameter("command");
if ((command!=null) && command.equals("Next")){
  client.setClientDetail(clientDetail);
  clientDetail.setClient(client);
}
if ((command!=null)&& command.equals("Add Address")){
  address.setClient(client);
  client.getAddresses().add(address);
}
%>
<html>
<head><title>address</title></head>
<body>
<form action="address.jsp" method="get">
Address Line 1:
<input type="text" name="addressLine1" size="30"><br/>
Address Line 2:
<input type="text" name="addressLine2" size="30"><br/>
City:<input type="text" name="city" size="30"><br/>
State:<input type="text" name="state" size="30"><br/>
Country:<input type="text" name="country" size="30"><br/>
Code:<input type="text" name="code" size="30"><br/>
<input type="submit" name="command" value="Add Address">
</form>
<a href="skills.jsp">Continue to Add Skills++</a>
<c:forEach items="${client.addresses}" var="a">
<hr/>
<c:out value="${a.addressLine1}"></c:out><BR/>
<c:out value="${a.addressLine2}"></c:out><BR/>
<c:out value="${a.city}"></c:out><BR/>
<c:out value="${a.state}"></c:out><BR/>
<c:out value="${a.country}"></c:out><BR/>
</c:forEach>
<a href="skills.jsp">Continue to Add Skills ++ </a>
</body>
</html>
