<%@page import="javax.persistence.*,javax.persistence.criteria.*,
com.mcnz.jpa.examples.*,java.util.*" contentType="text/html" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<jsp:useBean class="com.mcnz.jpa.examples.Player" id="player" scope="request"/>			 
<jsp:setProperty name="player" property="*" />
<%
EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-tutorial");
EntityManager em = emf.createEntityManager();
em.getTransaction().begin();

String command = request.getParameter("command");
java.util.List players = null;
Long id = player.getId();
  if (command != null) {
    if (command.equals("Create")) {
      em.persist(player);
    }
    if (command.equals("Update")) {
	  em.merge(player);
    }
    if (command.equals("edit")) {
      player = em.find(Player.class, id);   
    } 
    if (command.equals("delete")) {  
      em.remove(em.find(Player.class, id));
      player = new Player();
    }
	if (command.equals("Clear")) { 
      player = new Player();
    }
    if (command.equals("Fuzzy Search")) {
      	Query query=  em.createNamedQuery("Player.findByFuzzyEmail");
		String fuzzy = "%"+player.getEmailAddress()+"%"; 
		query.setParameter("email", fuzzy);
		players = query.getResultList();
    }
    if (command.equals("Strict Search")) {
        Query query=  em.createNamedQuery("Player.findByEmail");
		query.setParameter("email", player.getEmailAddress());
		players = query.getResultList();
    }
  }
if (players==null) {
	TypedQuery query = em.createQuery("from Player", Player.class);
	players = query.getResultList();
}
em.getTransaction().commit();
request.setAttribute("players", players);
request.setAttribute("player", player);
%>







 
<html>
<body>
<form action="player.jsp">
<!--  Here are our four textfields  -->
<input type="text" size="7" readonly name="id" value="${player.id}"> Id
<br/>
<input type="text" size="30" name="loginName" value="${player.loginName}"> Name
<br/>
<input type="text" size="30" name="password" value="${player.password}"> Password
<br/>
<input type="text" size="30" name="emailAddress" value="${player.emailAddress}"> Email (Searchable)
<br/>
<!-- Here are all of our buttons!!! -->
<input type="submit" name="command" value="Strict Search">
<input type="submit" name="command" value="Fuzzy Search"><br/>
<input type="submit" name="command" value="Update">
<input type="submit" name="command" value="Create">
<input type="submit" name="command" value="Clear"> 
<BR>
<c:forEach items="${players}" var="player">

<c:url  var="editurl" value="player.jsp" >
  <c:param name="command" value="edit" />
  <c:param name="id" value="${player.id}"/>
</c:url>
<c:url  var="deleteurl" value="player.jsp" >
  <c:param name="command" value="delete" />
  <c:param name="id" value="${player.id}"/>
</c:url>
 | <a href="${editurl}">edit</a>
 | <a href="${deleteurl}">delete</a>
 | <c:out value="${player.id}" />
 | <c:out value="${player.loginName}" />
 | <c:out value="${player.emailAddress}" />
 | 
<br/>

</c:forEach>
</form>
</body>
</html>
