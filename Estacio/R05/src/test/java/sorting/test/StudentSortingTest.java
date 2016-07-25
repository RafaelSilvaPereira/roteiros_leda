package sorting.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import sorting.AbstractSorting;
import sorting.variationsOfSelectionsort.OddEvenSelectionSort;

public class StudentSortingTest {
	/**
	 * Quantity of random tests to be done
	 */
	private static final int QTD_TESTS = 20;
	/**
	 * Smallest element to be in a array (inclusive)
	 */
	private static final int LOW_BOUND_ELEM = -100000;
	/**
	 * Biggest element to be in an array (exclusive)
	 */
	private static final int UP_BOUND_ELEM = 100000;
	/**
	 * Maximum array length to be created (2 to number - exclusive)
	 */
	private static final int MAX_LEN_ARRAYS = 10000;
	/**
	 * Print time for each test
	 */
	private static final boolean PRINT_TESTS = false;

	private Integer[] vetorTamPar;
	private Integer[] vetorTamImpar;
	private Integer[] vetorVazio = {};
	private Integer[] vetorValoresRepetidos;
	private Integer[] vetorValoresIguais;

	public AbstractSorting<Integer> implementation;

	// Look at fillUpSorts method
	private List<AbstractSorting<Integer>> sorts;

	/**
	 * Populate the sortings list. You must add here your implementations
	 */
	private void fillUpSorts() {
		this.sorts = new ArrayList<>();
		sorts.add(new OddEvenSelectionSort<Integer>());
	}

	/**
	 * The main method, executes all the sortings
	 */
	@Test
	public void executeAllSortings() {
		for (AbstractSorting<Integer> sort : this.sorts) {
			this.implementation = sort;
			String sortName = sort.getClass().getSimpleName();
			System.out.println("===========================================");
			System.out.println("=> Running sort for " + sortName);
			System.out.println("=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
			long timeInit = System.currentTimeMillis();
			runAllTests();
			long elapsed = System.currentTimeMillis() - timeInit;
			System.out.println("=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
			System.out.println("=> Running time: " + elapsed + "ms. " + sortName + ". " + QTD_TESTS + " tests.");
			System.out.println("=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
		}
	}

	/**
	 * Execute all the tests
	 */
	private void runAllTests() {
		testSortEvenLen();
		testSortOddLen();
		testSortAllEquals();
		testSortNull();
		testSortRepeatedNumbers();
		testSortZeroLen();
		testInvalidIndexes();
		for (int i = 0; i < QTD_TESTS; i++)
			execTestAndGetTime();
	}

	/**
	 * Execute a random test and print the elapsed time and vector size.
	 */
	private void execTestAndGetTime() {
		long timeInit = System.currentTimeMillis();
		testRandomSort();
		if (PRINT_TESTS) {
			long elapsed = System.currentTimeMillis() - timeInit;
			System.out.printf("Testing for %6d elements. ", vetor.length);
			System.out.println("Time elapsed: " + elapsed + "ms");
		}
	}

	@Before
	public void setUp() {
		fillUpSorts();
		populaVetorTamanhoPar(new Integer[] { 30, 28, 7, 29, 11, 26, 4, 22, 23, 31 });
		populaVetorTamanhoImpar(new Integer[] { 6, 41, 32, 7, 26, 4, 37, 49, 11, 18, 36 });
		populaVetorRepetido(new Integer[] { 4, 9, 3, 4, 0, 5, 1, 4 });
		populaVetorIgual(new Integer[] { 6, 6, 6, 6, 6, 6 });
		populaVetor();
	}

	// // MÉTODOS AUXILIARES DA INICIALIZAÇÃO
	private void populaVetorTamanhoPar(Integer[] arrayPadrao) {
		this.vetorTamPar = Arrays.copyOf(arrayPadrao, arrayPadrao.length);
	}

	private void populaVetorTamanhoImpar(Integer[] arrayPadrao) {
		this.vetorTamImpar = Arrays.copyOf(arrayPadrao, arrayPadrao.length);
	}

	private void populaVetorRepetido(Integer[] arrayPadrao) {
		this.vetorValoresRepetidos = Arrays.copyOf(arrayPadrao, arrayPadrao.length);
	}

	private void populaVetorIgual(Integer[] arrayPadrao) {
		this.vetorValoresIguais = Arrays.copyOf(arrayPadrao, arrayPadrao.length);
	}

	// FIM DOS METODOS AUXILIARES DA INICIALIZAÇÃO
	// MÉTODOS DE TESTE
	private void genericTest(Integer[] array) {
		Integer[] copy1 = Arrays.copyOf(array, array.length);
		Integer[] copy2 = Arrays.copyOf(array, array.length);
		Integer[] copy3 = Arrays.copyOf(array, array.length);
		implementation.sort(array);
		Arrays.sort(copy1);
		Assert.assertArrayEquals(copy1, array);

		if (copy3.length > 0) {
			int i = getRandomNumberInRange(0, array.length / 2);
			implementation.sort(copy2, 0, i);
			Arrays.sort(copy3, 0, i + 1);
		}
		Assert.assertArrayEquals(copy2, copy3);
	}

	private void testSortEvenLen() {
		genericTest(vetorTamPar);
	}

	private void testSortOddLen() {
		genericTest(vetorTamImpar);
	}

	private void testSortZeroLen() {
		genericTest(vetorVazio);
	}

	private void testSortAllEquals() {
		genericTest(vetorValoresIguais);
	}

	private void testSortRepeatedNumbers() {
		genericTest(vetorValoresRepetidos);
	}

	// MÉTODOS QUE OS ALUNOS PODEM CRIAR
	/**
	 * O ALUNO PODE IMPLEMENTAR METODOS DE ORDENAÇÃO TESTANDO O SORT COM TRES
	 * ARGUMENTOS PARA TESTAR A ORDENACAO EM UM PEDAÇO DO ARRAY. DICA: PROCUREM
	 * SEGUIR A ESTRUTURA DOS MÉTODOS DE TESTE ACIMA DESCRITOS, ORDENANDO APENAS
	 * UMA PARTE DO ARRAY.
	 */
	private Integer[] vetor;

	private void populaVetor() {
		int size = getRandomNumberInRange(2, MAX_LEN_ARRAYS);
		vetor = new Integer[size];
		for (int i = 0; i < size; i++) {
			vetor[i] = getRandomNumberInRange(LOW_BOUND_ELEM, UP_BOUND_ELEM);
		}
	}

	/**
	 * Execute a partial and complete sorting, each one using copies of the
	 * original array
	 *
	 * @param array
	 *            To be used in the tests
	 */
	private void partialAndFullSort(Integer[] array) {
		populaVetor();
		int i = getRandomNumberInRange(0, array.length / 2);
		int j = getRandomNumberInRange(i, array.length);
		// Copying array
		Integer[] copy1 = Arrays.copyOf(array, array.length);
		partialSort(array, i, j, false);
		// Ordering the complete array
		implementation.sort(array);
		Arrays.sort(copy1);
		Assert.assertArrayEquals(copy1, array);
	}

	/**
	 * Executes a partial sorting in the array in the specified bounds,
	 * inclusively
	 */
	private void partialSort(Integer[] array, int i, int j) {
		partialSort(array, i, j, true);
	}

	/**
	 * Executes a partial sorting in the array in the specified bounds,
	 * inclusively. If generateNewArray is true it'll re-populate the array to
	 * be used in the next tests
	 */
	private void partialSort(Integer[] array, int i, int j, boolean generateNewArray) {
		if (generateNewArray)
			populaVetor();
		Integer[] copy1 = Arrays.copyOf(array, array.length);
		implementation.sort(array, i, j);
		if (i < 0 || j >= array.length)
			return;
		Arrays.sort(copy1, i, j + 1);
		Assert.assertArrayEquals(copy1, array);
	}

	/**
	 * Random sorting of the array, executes a partial and a full sorting
	 */
	private void testRandomSort() {
		partialAndFullSort(vetor);
	}

	/**
	 * Test sorting an array in invalid indexes
	 */
	private void testInvalidIndexes() {
		partialSort(vetor, -1, 0);
		partialSort(vetor, 0, MAX_LEN_ARRAYS);
		partialSort(vetor, 1, 0);
		partialSort(vetor, -1, MAX_LEN_ARRAYS);
	}

	/**
	 * Test sorting a null array
	 */
	private void testSortNull() {
		implementation.sort(null, 0, 1);
		implementation.sort(null, -1, 1);
		implementation.sort(null, 1, 0);
		implementation.sort(null, 1, 1000000);
	}

	private int getRandomNumberInRange(int min, int max) {
		if (min > max) {
			throw new IllegalArgumentException("max must be greater than min");
		}
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
}