var datas = datas || {};
datas.pesquisa = [
	'01/07/2017', '02/07/2017', '03/07/2017', '04/07/2017', '05/07/2017', '06/07/2017', '07/07/2017', '08/07/2017'
];

/**
 * Verifica se a data atual é um dia de pesquisa
 * @param {String} dataTeste
 * @returns {Boolean}
 */
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