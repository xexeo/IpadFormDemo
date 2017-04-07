var datas = datas || {};
datas.pesquisa = [
	'21/04/2017', '22/04/2017', '23/04/2017', '24/04/2017', '25/04/2017', '26/04/2017', '27/04/2017', '28/04/2017'
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