package adt.heap;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import org.junit.*;

public class StudentHeapTest {

	private GenericHeap<Integer> heap;
	private PriorityQueue<Integer> queue;
	private static final int LENGHT_ELEMENTS = 25;

	@Before
	public void setUp() {
		getImplementations();

		for (int i = 0; i < LENGHT_ELEMENTS; i++) {
			this.heap.insert(i);
		}

		/*
		 * O aluno pode utilizar uma estrutura auxiliar no seus testes para
		 * verificar o funcionamento de sua heap. Ver:
		 * https://docs.oracle.com/javase/7/docs/api/java/util/PriorityQueue.
		 * html
		 */
		queue = new PriorityQueue<Integer>(LENGHT_ELEMENTS);
		for (int i = 0; i < LENGHT_ELEMENTS; i++) {
			this.queue.add(i);
		}
	}

	private void getImplementations(){
		//TODO O aluno deve ajustar aqui para instanciar sua implementação
		heap = new MinHeapImpl<Integer>();
	}
	
	// Arrays que podem ser utilizados para o teste do HeapSort.
	// Nem todos os cenarios de testes são abordados com esses arrays.
	Integer[] vetorRepetido = {4, 4, 3, 4, 3, 1, 4};
	Integer[] vetorCrescente = {2, 3, 4, 5, 6, 7};
	Integer[] vetorDescrescente = {7, 6, 5, 4, 3, 2};

	@Test
	public void testHeapsort() {
		List<Integer> vetor = new ArrayList<Integer>();
		Collections.addAll(vetor, this.vetorRepetido);
		Collections.sort(vetor);
		assertArrayEquals(vetor.toArray(), this.heap.heapsort(this.vetorRepetido));
		
		vetor = new ArrayList<Integer>();
		Collections.addAll(vetor, this.vetorCrescente);
		Collections.sort(vetor);
		assertArrayEquals(vetor.toArray(), this.heap.heapsort(this.vetorCrescente));
		
		vetor = new ArrayList<Integer>();
		Collections.addAll(vetor, this.vetorDescrescente);
		Collections.sort(vetor);
		assertArrayEquals(vetor.toArray(), this.heap.heapsort(this.vetorDescrescente));
	}
	
	@Test
	public void testIsEmpty() {
		for (int i = 0; i < LENGHT_ELEMENTS; i++) {
			assertEquals(this.queue.remove(), this.heap.extractRootElement());
		}
		assertTrue(this.heap.isEmpty());
		assertTrue(this.queue.isEmpty());
		assertArrayEquals(this.queue.toArray(), this.heap.toArray());
	}

	@Test
	public void testInsert() {
		this.heap.insert(-5);
		this.queue.add(-5);
		
		assertArrayEquals(this.queue.toArray(), this.heap.toArray());
		
		for (int i = 0; i < LENGHT_ELEMENTS; i++) {
			this.heap.insert(-i);
			this.queue.add(-i);
		}
		
		assertArrayEquals(this.queue.toArray(), this.heap.toArray());
	}

	@Test
	public void testExtractRootElement() {
		for (int i = 0; i < LENGHT_ELEMENTS; i++)
			assertEquals(this.queue.remove(), this.heap.extractRootElement());
	}

	@Test
	public void testRootElement() {
		for (int i = 0; i < LENGHT_ELEMENTS; i++) {
			assertEquals(this.queue.element(), this.heap.rootElement());
			assertEquals(this.queue.remove(), this.heap.extractRootElement());
		}
	}

	@Test
	public void testBuildHeap() {
		Integer[] copy = Arrays.copyOf(this.vetorCrescente, this.vetorCrescente.length);
		
		this.queue.clear();
		for (int i = 0; i < this.vetorCrescente.length; i++) {
			this.queue.add(this.vetorCrescente[i]);
		}
		
		this.heap.buildHeap(this.vetorCrescente);
		
		assertArrayEquals(this.queue.toArray(), this.heap.toArray());
		
		// TODO: esse teste faz sentido? Ou o buildHeap deve referenciar o vetor de entrada?
		assertArrayEquals(copy, this.vetorCrescente);
	}
	
}