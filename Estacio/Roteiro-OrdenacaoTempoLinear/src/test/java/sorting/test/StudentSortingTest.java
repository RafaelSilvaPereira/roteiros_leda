package sorting.test;

import java.util.Arrays;
import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import sorting.AbstractSorting;
import sorting.linearSorting.CountingSort;

public class StudentSortingTest {

	private Integer[] vetorTamPar;
	private Integer[] vetorTamImpar;
	private Integer[] vetorVazio = {};
	private Integer[] vetorValoresRepetidos;
	private Integer[] vetorValoresIguais;
	
	public AbstractSorting<Integer> implementation;

	@Before
	public void setUp() {
		populaVetorTamanhoPar(new Integer[] {30, 28, 7, 29, 11, 26, 4, 22, 23, 31});
		populaVetorTamanhoImpar(new Integer[] {6, 41, 32, 7, 26, 4, 37, 49, 11, 18, 36});
		populaVetorRepetido(new Integer[] {4, 9, 3, 4, 0, 5, 1, 4});
		populaVetorIgual(new Integer[] {6, 6, 6, 6, 6, 6});
		populaVetor();
		
		getImplementation();
	}
	
	//// MÉTODOS AUXILIARES DA INICIALIZAÇÃO
	/**
	 * Método que inicializa a implementação a ser testada com a implementação do aluno
	 */
	private void getImplementation() {
		//TODO O aluno deve instanciar sua implementação abaixo ao invés de null 
		this.implementation = new CountingSort();
	}

	public void populaVetorTamanhoPar(Integer[] arrayPadrao){
		this.vetorTamPar = Arrays.copyOf(arrayPadrao, arrayPadrao.length);
	}

	public void populaVetorTamanhoImpar(Integer[] arrayPadrao){
		this.vetorTamImpar = Arrays.copyOf(arrayPadrao, arrayPadrao.length);
	}

	public void populaVetorRepetido(Integer[] arrayPadrao){
		this.vetorValoresRepetidos = Arrays.copyOf(arrayPadrao, arrayPadrao.length);
	}

	public void populaVetorIgual(Integer[] arrayPadrao){
		this.vetorValoresIguais = Arrays.copyOf(arrayPadrao, arrayPadrao.length);
	}

	// FIM DOS METODOS AUXILIARES DA INICIALIZAÇÃO
	
	//MÉTODOS DE TESTE
	
	public void genericTest(Integer[] array) {
		Integer[] copy1 = Arrays.copyOf(array, array.length);
		implementation.sort(array);
		Arrays.sort(copy1);
		Assert.assertArrayEquals(copy1, array);
	}
	
	@Test
	public void testSort01() {
		genericTest(vetorTamPar);
	}

	@Test
	public void testSort02() {
		genericTest(vetorTamImpar);
	}

	@Test
	public void testSort03() {
		genericTest(vetorVazio);
	}

	@Test
	public void testSort04() {
		genericTest(vetorValoresIguais);
	}
	
	@Test
	public void testSort05() {
		genericTest(vetorValoresRepetidos);
	}
	
	//MÉTODOS QUE OS ALUNOS PODEM CRIAR 
	/**
	 * O ALUNO PODE IMPLEMENTAR METODOS DE ORDENAÇÃO TESTANDO O SORT COM TRES ARGUMENTOS
	 * PARA TESTAR A ORDENACAO EM UM PEDAÇO DO ARRAY. DICA: PROCUREM SEGUIR A ESTRUTURA DOS
	 * MÉTODOS DE TESTE ACIMA DESCRITOS, ORDENANDO APENAS UMA PARTE DO ARRAY.
	 */

	private Integer[] vetor;

	private void populaVetor() {
		int size = getRandomNumberInRange(0, 10000);
		vetor = new Integer[size];

		for(int i = 0; i < size; i++){
			vetor[i] = getRandomNumberInRange(0, 10000);
		}
	}

	public void genericTest2(Integer[] array) {
		Integer[] copy1 = Arrays.copyOf(array, array.length);
		int i = getRandomNumberInRange(0, array.length);
		int j = getRandomNumberInRange(i, array.length);

		implementation.sort(array);
		Arrays.sort(copy1);
		Assert.assertArrayEquals(copy1, array);

		populaVetor();
		copy1 = Arrays.copyOf(array, array.length);
		implementation.sort(array, i, j);
		Arrays.sort(copy1, i, j);
		Assert.assertArrayEquals(copy1, array);
	}

	@Test
	public void testSort10() {
		genericTest2(vetor);
	}

	@Test
	public void testSort11() {
		genericTest2(vetor);
	}

	@Test
	public void testSort12() {
		genericTest2(vetor);
	}

	@Test
	public void testSort13() {
		genericTest2(vetor);
	}

	@Test
	public void testSort14() {
		genericTest2(vetor);
	}

	@Test
	public void testSort06() {
		genericTest2(vetor);
	}

	@Test
	public void testSort07() {
		genericTest2(vetor);
	}

	@Test
	public void testSort08() {
		genericTest2(vetor);
	}

	@Test
	public void testSort09() {
		genericTest2(vetor);
	}

	private static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
}