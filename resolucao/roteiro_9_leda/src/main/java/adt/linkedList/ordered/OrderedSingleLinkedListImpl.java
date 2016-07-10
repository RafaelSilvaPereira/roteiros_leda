package adt.linkedList.ordered;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import adt.linkedList.SingleLinkedListNode;

/**
 * Para testar essa classe voce deve implementar seu comparador. 
 * Primeiro implemente todos os métodos requeridos. Depois implemente dois comparadores (com idéias opostas)
 * e teste sua classe com eles. Dependendo do comparador que você utilizar a lista funcionar como ascendente
 * ou descendente, mas a implemntação dos métodos é a mesma.
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class OrderedSingleLinkedListImpl<T> implements
		OrderedSingleLinkedList<T> {

	private SingleLinkedListNode<T> head;
	private Comparator<T> comparator;
	
	public OrderedSingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}
	
	public OrderedSingleLinkedListImpl(Comparator<T> comparator) {
		this.comparator = comparator;
		this.head = new SingleLinkedListNode<T>();
	}


	@Override
	public boolean isEmpty() {
		return this.head.isNIL();
	}

	@Override
	public int size() {
		int size =0;
		SingleLinkedListNode<T> aux = head;
		while(aux != null && !aux.isNIL()){
			size++;
			aux = aux.getNext();
		}
		return size;
	}

	@Override
	public T search(T element) {
		SingleLinkedListNode<T> aux = head;
		if(isEmpty()){
			return null;
		}
		while (!aux.isNIL() && ! aux.getData().equals(element)){
			aux = aux.getNext();
		}
		return aux.getData();
	}

	@Override
	public void insert(T element) {
		if(element==null){
			return;
		}SingleLinkedListNode<T> newHead = new SingleLinkedListNode<T>(element,  new SingleLinkedListNode<T>());
		if(isEmpty()){
			setHead(newHead);
		}else{
			if(comparator.compare(element, head.getData())<=0){
				newHead.setNext(head);
				head = newHead;
			}else{
				SingleLinkedListNode<T> aux = head.getNext();
				SingleLinkedListNode<T> previous = head;
				while(!aux.isNIL() && comparator.compare(element, aux.getData())>0){
					aux = aux.getNext();
					previous = previous.getNext();
				}
				aux = newHead;
				previous.setNext(aux);
			}
		}
	}

	@Override
	public void remove(T element) {
		if(head.getData().equals(element)){
			head = head.getNext();
		}else{
			SingleLinkedListNode<T> aux = head;
			SingleLinkedListNode<T> prev = aux;
			while(!aux.isNIL() && !aux.getData().equals(element)){
				prev = aux;
				aux = aux.getNext();
			}if(!aux.isNIL()){
				prev.setNext(aux.getNext());
			}
		}
		
	}

	@Override
	public T[] toArray() {
		if(isEmpty()){
			T [] array= (T[]) new Object[size()];
			return array;
		}

		ArrayList<T> list= new ArrayList<T>();
		SingleLinkedListNode<T> aux = head;
		for (int j = 0; j < size(); j++) {
			list.add(aux.getData());
			aux = aux.getNext();
		}
		Collections.sort(list, this.comparator);
		return (T[]) list.toArray();
	}

	@Override
	public T minimum() {
		Integer[] array = viraArrayInteiro();
		Integer min = null;
		if(array.length>0){
		if(array[0].compareTo(array[array.length-1])==-1){
			min = array[0];
			
		}else{
			min = array[array.length-1];
		}
		}
		return (T) min;
	}

	@Override
	public T maximum() {
		Integer[] array = viraArrayInteiro();
		Integer max = null;

		if(array.length>0){
		if(array[0].compareTo(array[array.length-1])==1){
			max = array[0];

		}else{
			max = array[array.length-1];
		}
		}
		return (T) max;
	}

	private Integer[] viraArrayInteiro(){
		T[] array = toArray();
		Integer[] arrayInteiro = new Integer[size()];
		for (int i = 0; i < arrayInteiro.length; i++) {
			arrayInteiro[i] = (Integer) array[i];
			
		}
		return arrayInteiro;
	}
	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}
	
}
