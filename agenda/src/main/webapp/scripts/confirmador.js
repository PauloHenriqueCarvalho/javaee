/**
 * @author Paulo Henrique Verissimo de Carvalho
 * @param idcon
 */

 function confirmar(idcon){
	 let resposta = confirm("Confima a exclusão deste contato?")
	 if(resposta === true){
		 window.location.href = "delet?idcon=" + idcon
	 }
 }