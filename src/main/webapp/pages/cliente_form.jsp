<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="./head.jsp"/>
<script src="./js/clientes.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-12  col-md-12">
				<jsp:include page="./menu.jsp"/>
				<h1>Clientes</h1>
				
				<form id="form-cliente">
					
					<div class="row">
						<div class="col">
					    	<div class="form-group">
								<label for="example">Identificación</label> <input type="text"
									class="form-control" id="identificacion" name="identificacion"
									placeholder="Identificación">
							</div>
					    </div>
					    <div class="col">
					    	<div class="form-group">
								<label for="example">Nombre Completo</label> <input type="text"
									class="form-control" id="nombreCompleto" name="nombreCompleto"
									placeholder="Nombre Completo">
							</div>
					    </div>
					</div>
					<div class="row">
						<div class="col">
					    	<div class="form-group">
								<label for="example">Dirección</label> <input type="text"
									class="form-control" id="direccion" name="direccion"
									placeholder="Dirección">
							</div>
					    </div>
					    <div class="col">
					    	<div class="form-group">
								<label for="example">Celular</label> <input type="text"
									class="form-control" id="celular" name="celular"
									placeholder="Celular">
							</div>
					    </div>
					</div>
					<div class="row">
						<div class="col-md-6 col-sm-12">
					    	<div class="form-group">
								<label for="example">Fecha Nacimiento</label> <input type="date"
									class="form-control" id="fechaNacimiento" name="fechaNacimiento"
									placeholder="Fecha Nacimiento">
							</div>
					    </div>
					</div>
					<input type="hidden" id="accion" name="accion" value="crear">
					<input type="button" class="btn btn-primary" id="btnGuardarCliente"
						value="Guardar Cliente" onclick="guardarCliente()">
					<br><br>
					<div class="alert alert-danger" role="alert"
						style="display: none; width: 100%;" id="error-alert">
						<button type="button" class="close">
							<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
						</button>
						<span class="alert-content" id="errorDiv"></span>
					</div>
					<div class="alert alert-success" role="alert"
						style="display: none; width: 100%;" id="success-alert">
						<button type="button" class="close">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<span class="alert-content" id="successDiv"></span><span
							class="sr-only">Close</span>
					</div>
				</form>
			</div>
		</div>
	</div>
	</body>
</html>