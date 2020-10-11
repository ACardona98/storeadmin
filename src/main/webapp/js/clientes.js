function error(mensaje){
	jQuery('#errorDiv').html(mensaje);
	jQuery('#success-alert').css('display', 'none');
	jQuery('#error-alert').css('display', 'block');
}
function ok(mensaje){
	jQuery('#successDiv').html(mensaje);
	jQuery('#success-alert').css('display', 'block');
	jQuery('#error-alert').css('display', 'none');
}

function guardarCliente(){
	jQuery.ajax({
		url : 'AdministrarClientesServlet',
		data: $("#form-cliente").serialize(),
		type: 'POST',
		success: function(data) {
			
			var respuestaVacia = !Object.keys(data).length;
			if (respuestaVacia){
				error("Ocurrió un error inesperado");
				return;
			}
			try {
				if (data.codigo != undefined && data.codigo == 1) { //Si el código de la respuesta es OK
					ok(data.respuesta);
				}else if(data.codigo != undefined && data.codigo == 0){
					error(data.respuesta);
				}else{
					error("Ocurrió un error inesperado");
				}
			} catch (err) {
				error("Ocurrió un error inesperado");
			}
		},
		error: function(){
			error("Ocurrió un error inesperado");
		}
	});
	
}