/// @file datas.js
/// @namespace datas
/// Datas válidas para a pesquisa
var datas = datas || {};
datas.pesquisa = [
	'18/11/2017', '19/11/2017', '20/11/2017', '21/11/2017', '22/11/2017', '23/11/2017', '24/11/2017', '25/11/2017'
];

/// @function datas.verificaData
/// Verifica se a data atual é um dia de pesquisa
/// @param {string} dataTeste	a data a ser testada
/// @return {bool}				true se a data testada for um dia de pesquisa 
datas.verificaData = function(){
	var r = false;
	var dataTeste = util.formateDateOnly(new Date());
	$.each(datas.pesquisa, function(index, value){
		if (dataTeste == value){
			r = true;
		}
	})
	return r;
}