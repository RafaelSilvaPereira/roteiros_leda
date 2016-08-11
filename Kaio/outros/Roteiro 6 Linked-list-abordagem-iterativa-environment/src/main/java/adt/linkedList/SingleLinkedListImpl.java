package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	protected int size;
	private SingleLinkedListNode<T> nillNode;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
		this.nillNode = new SingleLinkedListNode<T>();
		this.size = 0;
	}

	@Override
	public boolean isEmpty() {
		return head.isNIL();
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public T search(T element) {

		if (!isEmpty()) {

			SingleLinkedListNode<T> auxNode = head;

			while (!auxNode.isNIL()) {
				if (auxNode.getData().equals(element)) {
					return auxNode.getData();
				}
				auxNode = auxNode.getNext();
			}
		}

		return null;
	}

	@Override
	public void insert(T element) {

		SingleLinkedListNode<T> newNode = new SingleLinkedListNode<T>(element, nillNode);

		if (isEmpty()) {
			head = newNode;
		} else {

			SingleLinkedListNode<T> auxNode = head;

			// ter uma variável last melhoraria a eficiência de O(n) pra O(1)
			// mas acredito que a ideia do roteiro é testar nossa capacidade de se mover na lista
			// o comando abaixo demonstra essa capacidade
			while (!auxNode.getNext().isNIL()) {
				auxNode = auxNode.getNext();
			}

			auxNode.setNext(newNode);
		}

		size += 1;
	}

	@Override
	public void remove(T element) {

		if (!isEmpty()) {

			if (head.getData().equals(element)) {
				head = head.next;
			} else {

				SingleLinkedListNode<T> auxNode = head.getNext();
				SingleLinkedListNode<T> previous = head;

				while (!auxNode.isNIL() && !auxNode.getData().equals(element)) {
					auxNode = auxNode.getNext();
					previous = previous.getNext();
				}

				if (!auxNode.isNIL())
					previous.setNext(auxNode.getNext());
			}
			
			size -= 1;
			
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {

		T[] array = (T[]) new Object[this.size];

		if (!isEmpty()) {

			SingleLinkedListNode<T> auxNode = head;

			for (int i = 0; !auxNode.isNIL(); i++) {
				array[i] = auxNode.getData();
				auxNode = auxNode.getNext();
			}
		}

		return array;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	// não deveria existir, por questões de segurança
	// exemplo: alguém pode setar nova cabeça pra lista e isso mudará lista inteira
	// inclusive seu tamanho
	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

	
}
