<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="javax.persistence.*,
com.mcnz.jpa.examples.*,java.util.*" %>
<jsp:useBean class="com.mcnz.jpa.examples.Client" 
            id="client" scope="session"/>	
<%
EntityManagerFactory emf =   
  Persistence.createEntityManagerFactory("jpa-tutorial"); 
EntityManager em = emf.createEntityManager(); 
em.getTransaction().begin();
TypedQuery query = 
    em.createQuery("from Skill", Skill.class);
String[] clientSkills = request.getParameterValues("allskills");
if(clientSkills!=null) {
  client.getSkills().clear();
  for (int i = 0; i< clientSkills.length; i++) {
    Long id = new Long(clientSkills[i]);
    Skill skill=em.find(Skill.class, id);
    client.getSkills().add(skill);
  }
}
%>
<html><head><title>skills</title></head><body>
<form action="skills.jsp" method="post">
<select size="20" name="allskills" multiple>
<c:forEach items="<%=query.getResultList()%>" var="skill" >
<option value="${skill.id}" id="${skill.id}">
<c:out value="${skill.name}"></c:out></option>
</c:forEach>
</select>
<input type="submit" name="command" value="Update Skills">	
<select size="20" name="allskills" multiple>
<c:forEach items="<%=client.getSkills()%>" var="skill" >
<option value="${skill.id}" id="${skill.id}">
<c:out value="${skill.name}"></c:out></option>
</c:forEach>
</select>		
</form>
<a href="summary.jsp">Finish  </a>
</body></html>
<%
em.getTransaction().commit();
%>
