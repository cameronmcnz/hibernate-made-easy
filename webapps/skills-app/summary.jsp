<%@page import="javax.persistence.*,
com.mcnz.jpa.examples.*,java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean class="com.mcnz.jpa.examples.Client" 
     id="client" scope="session"/>
<%
EntityManagerFactory emf =   
  Persistence.createEntityManagerFactory("jpa-tutorial"); 
EntityManager em = emf.createEntityManager();
em.getTransaction().begin();
em.merge(client);


%>
<html><head><title>summary</title></head><body>
<p>Congratulations! You have registered!</p>
<c:out value="${client.id}" /><br/>
<c:out value="${client.username}" /><br/>
<c:out value="${client.password}" /><br/>
<c:out value="${client.clientDetail.passwordHint}" /><br/>
<c:out value="${client.id}" /><br/>
<c:out value="${client.clientDetail.firstName}" /><br/>
<c:out value="${client.clientDetail.middleName}" /><br/>
<c:out value="${client.clientDetail.lastName}" /><br/>
<c:out value="${client.clientDetail.emailAddress}" /><br/>
<c:out value="${client.clientDetail.registrationDate}" /><br/>
<c:out value="${client.clientDetail.verificationDate}" /><br/>
<c:out value="${client.clientDetail.middleName}" /><br/>

<p>Skills:</p>
<c:forEach items="${client.skills}"  var="skill" >
<c:out value="${skill.name}"/><br/> 
</c:forEach>

<p>Address Info</p>
<c:forEach items="${client.addresses}" var="addr">
<c:out value="${addr.addressLine1}"/><br/>
<c:out value="${addr.addressLine2}"/><br/>
<c:out value="${addr.city}"/><br/>
<c:out value="${addr.state}"/><br/>
<c:out value="${addr.country}"/><br/><hr/>
</c:forEach>
</body></html>
<% 
em.flush();
em.getTransaction().commit(); 
%>
