public class util {
    // DICA 1: 
	// Onde vc gostaria de fazer isso: "no1.filhos[i] = no2", 
	// faca isso: "no1.filhos[i] = no2.endereco"
	// ATENCAO: se o no for recem nascido, antes de acessar 
	// o campo "endereco", chame gravaNovoNo(no);
	
	// DICA 1.2 (esqueci de falar disso no video :P): 
	// Onde vc gostaria de fazer isso: "metodo(no.filhos[i])"
	// ou isso "No no2 = no1.filhos[i]", faca isso: 
	// "metodo(leNo(no.filhos[i]))" ou "No no2 = leNo(no1.filhos[i])"
				
	// DICA 2:
	// No split, apenas um novo noh eh criado. Portanto, chame a funcao
	// "gravaNovoNo" apenas uma vez no split. Os outros dois nos envolvidos
	// ja existiam antes da chamada, entao basta chamar "atualizaNo"
	
	// DICA 3: 
	// Toda vez que for fazer split na raiz, voce tera que criar uma nova 
	// raiz antes de chamar o split, certo? Antes de chamar o split na raiz,
	// chame "gravaNovoNo" na nova raiz que voce criou e em seguida chame
	// "trocaRaiz(novaRaiz)".
}
