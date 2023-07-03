<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../comunes/inicioHTML.jsp">
    <jsp:param name="elTitulo" value="Listando alumnos" />
</jsp:include>

<jsp:include page="../comunes/navbar.jsp" />

<section class="container">
    <div class="row pt-3">
        <h1 class="h3">Listado de alumnos</h1>
        <p class="lead">Se listan todos los alumnos existentes en la base de datos</p>
        <p class="small">Los datos de la personas aquí listadas son ficticios. Sus rostros fueron generados con inteligencia artificial.</p>
        <div>
            <a href="#" class="btn btn-success"
               data-bs-toggle="modal" data-bs-target="#modalAgregarAlumno"><i class="bi bi-person-add"></i>
                Agregar alumno</a>
        </div>
    </div>
    <hr/>
    <c:choose>
        <c:when test = "${listaAlumnos != null && !listaAlumnos.isEmpty()}">
            <div class="row g-4 mb-3 row-cols-1 row-cols-sm-2 row-cols-lg-3 row-cols-xl-4 row-cols-xxl-5" data-masonry='{"percentPosition": true }' >
                <jsp:include page="partes/cardsAlumnos.jsp"/>
            </div>
        </c:when>
        <c:otherwise>
            <div class="row my-4">
                <div class="col-12">
                    <p class="display-5 text-danger">Ooops! Parece que no hay alumnos...</p>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
</section>

<jsp:include page="partes/modalAgregarAlumno.jsp"/>     
<script src="scripts/fotobase64.js"></script>
<script src="https://cdn.jsdelivr.net/npm/masonry-layout@4.2.2/dist/masonry.pkgd.min.js" integrity="sha384-GNFwBvfVxBkLMJpYMOABq3c+d3KnQxudP/mGPkzpZSTYykLBNsZEnG2D9G/X/+7D" crossorigin="anonymous" async></script>

<jsp:include page="../comunes/footer.jsp"/>
<jsp:include page="../comunes/finHTML.jsp"/>