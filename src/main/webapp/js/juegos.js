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

function pagarPremio(idPremio){
	jQuery.ajax({
		url : 'PagarPremioServlet',
		data: {idPremio: idPremio},
		type: 'POST',
		success: function(o) {
			if(o=="") {
				return;
			}
			var data;
			try {
				data = jQuery.parseJSON(o);
				if (data.codigo != undefined && data.codigo == 1) {
					var rString = data.respuesta;
					var r = jQuery.parseJSON(rString);
					if(r != undefined && r.codigo == 1){
						
						var mensaje = "Se realizó el pago satisfactoriamente, el código del pago es: " + r.respuesta;
						ok(mensaje);
						
					}else if(r != undefined && r.codigo == 0){
						error(r.respuesta);
					}else{
						error("Ocurrió un error inesperado");
					}
				}else if(data.codigo != undefined && data.codigo == 0){
					error(data.respuesta);
				}else{
					error("Ocurrió un error inesperado");
				}
			} catch (err) {
				error("Ocurrió un error inesperado");
				return;
			}
		},
		error: function(){
			error("Ocurrió un error inesperado");
		}
	});
	
}

$(function() {
	$("#consultar-colilla").click(function() {
		jQuery.ajax({
			url : 'ConsultarPremioServlet',
			
			data: {idColilla: $("#idColilla").val()},
			type: 'POST',
			success: function(o) {
				if(o=="") {
					return;
				}
				var data;
				try {
					data = jQuery.parseJSON(o);
					
					if (data.codigo != undefined && data.codigo == 1) {
						var rString = data.respuesta;
						var r = jQuery.parseJSON(rString);
						if(r != undefined && r.codigo == 1){
							var idPremio = r.respuesta;
							var mensaje = "El código del premio es: " + idPremio + 
							"&nbsp;<a href='javascript:pagarPremio(" + idPremio + ")' id='pagar-premio' class='btn btn-secondary'>Pagar Premio</a>";
							ok(mensaje);
						}else if(r != undefined && r.codigo == 0){
							error(r.respuesta);
						}else{
							error("Ocurrió un error inesperado");
						}
					}else if(data.codigo != undefined && data.codigo == 0){
						error(data.respuesta);
					}else{
						error("Ocurrió un error inesperado");
					}
				} catch (err) {
					error("Ocurrió un error inesperado");
					return;
				}
			},
			error: function(){
				error("Ocurrió un error inesperado");
			}
		});
	});
});