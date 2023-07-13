<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach items="${listaAlumnos}" var="alumno">  
    <div class="col">
        <div class="card h-100">
            <jsp:include page="dataCardAlumno.jsp">
                <jsp:param name="fotoAlumno" value="${alumno.foto}" />
                <jsp:param name="nombreCompletoAlumno" value="${alumno.nombreCompleto}" />
            </jsp:include>
            <div class="card-footer">
                <div class="row justify-content-center">
                    <div class="col-4">
                        <a href="PENDIENTE" class="btn bg-info w-100"><i class="bi bi-eye"></i></a>
                    </div>
                    <div class="col-4">
                        <a href="${pageContext.request.contextPath}/app?accion=editar&id=${alumno.id}" class="btn bg-warning w-100"><i class="bi bi-pencil"></i></a>
                    </div>
                    <div class="col-4">
                        <a href="${pageContext.request.contextPath}/app?accion=borrar&id=${alumno.id}" class="btn bg-danger text-light w-100"><i class="bi bi-trash3"></i></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:forEach>