package adt.skipList;

public class SkipListImpl<T> implements SkipList<T> {

	private static final int SENTINELS_LENGTH = 2;
	protected SkipListNode<T> root;
	protected SkipListNode<T> NIL;

	protected int height;
	protected int maxHeight;

	// SET THIS VALUE TO TRUE IF YOU ARE IMPLEMENTING MAX_LEVEL = LEVEL
	// SET THIS VALUE TO FALSE IF YOU ARE IMPLEMENTING MAX_LEVEL != LEVEL
	protected boolean USE_MAX_HEIGHT_AS_HEIGHT = false;
	protected double PROBABILITY = 0.5;
	private int size;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public SkipListImpl(int maxHeight) {
		if (USE_MAX_HEIGHT_AS_HEIGHT) {
			this.height = maxHeight;
		} else {
			this.height = 1;
		}
		this.maxHeight = maxHeight;
		root = new SkipListNode(Integer.MIN_VALUE, maxHeight, null);
		NIL = new SkipListNode(Integer.MAX_VALUE, maxHeight, null);
		connectRootToNil();
		this.size = 0;
	}

	/**
	 * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL Caso
	 * esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve
	 * conectar todos os forward. Senao o ROOT eh inicializado com level=1 e o
	 * metodo deve conectar apenas o forward[0].
	 */
	private void connectRootToNil() {
		if (!USE_MAX_HEIGHT_AS_HEIGHT) {
			root.forward[0] = NIL;
		}
		else
			for (int i = 0; i < this.maxHeight; i++) {
				root.forward[i] = NIL;
			}
	}

	/**
	 * Metodo que gera uma altura aleatoria para ser atribuida a um novo no no
	 * metodo insert(int,V)
	 */
	private int randomLevel() {
		int randomLevel = 1;
		while (Math.random() <= PROBABILITY && randomLevel < this.maxHeight) {
			randomLevel = randomLevel + 1;
		}
		return randomLevel;
	}

	@Override
	public void insert(int key, T newValue) {
		int height = this.randomLevel();
		insert(key, newValue, height);
	}

	@Override
	public void insert(int key, T newValue, int height) {
		if(height > this.maxHeight) return ;
		ajustRootToNil(height);
		@SuppressWarnings("unchecked")
		SkipListNode<T>[] update = new SkipListNode[this.height];
		SkipListNode<T> aux = this.root;
		for (int i = this.height - 1; i >= 0; i--) {
			while (aux.getForward(i) != null && aux.getForward(i).key < key) {
				aux = aux.getForward(i);
			}
			update[i] = aux;
		}
		aux = aux.forward[0]; // recuperar sucessor ou elemento que será inserido
		if (aux.key == key) { // atualizar, caso já exista
			aux.value = newValue;
		} else {
			aux = new SkipListNode<T>(key, height, newValue);
			for (int i = 0; i < height; i++) {
				aux.forward[i] = update[i].forward[i];
				update[i].forward[i] = aux;
			}
			this.size++;
		}
	}

	private void ajustRootToNil(int height) {
		if (this.USE_MAX_HEIGHT_AS_HEIGHT) {
			height = this.maxHeight;
		}
		if (this.height < height) {
			// atualizando as referências, pois root e NIL devem crescer
			for (int i = this.height; i < height; i++) {
				// this.NIL.forward[i] = new SkipListNode<T>(Integer.MAX_VALUE, this.maxHeight, null);
				// this.root.forward[i] = this.NIL.forward[i];
				this.root.forward[i] = this.NIL;
			}
			this.height = height;
		}
	}

	@Override
	public void remove(int key) {
		if (key == this.root.key || key == this.NIL.key)
			return ;
		@SuppressWarnings("unchecked")
		SkipListNode<T>[] update = new SkipListNode[this.height];
		SkipListNode<T> aux = this.root;
		// caminhar até o antecessor do elemento a ser removido
		for (int i = this.height - 1; i >= 0; i--) {
			while (aux.getForward(i) != null && aux.getForward(i).key < key) {
				aux = aux.getForward(i);
			}
			update[i] = aux;
		}
		// se achou o elemento, atualizar links entre antecessores e sucessores
		if (aux.getForward(0).key == key) {
			aux = aux.getForward(0);
			for (int i = aux.height - 1; i >= 0; i--) {
				update[i].forward[i] = aux.forward[i];
				if (!this.USE_MAX_HEIGHT_AS_HEIGHT && 
						(update[i] == this.root) && 
						(update[i].forward[i] == this.NIL) && 
						i != 0)
					update[i].forward[i] = null;
			}
			this.size--;
		}
	}

	@Override
	public int height() {
		return this.height;
	}

	@Override
	public SkipListNode<T> search(int key) {
		if (key == this.root.key)
			return this.root;
		if (key == this.NIL.key)
			return this.NIL;
		SkipListNode<T> aux = this.root;
		for (int i = this.height - 1; i >= 0; i--) {
			while (aux.getForward(i) != null && aux.getForward(i).key < key) {
				aux = aux.getForward(i);
			}
		}
		if (aux.getForward(0) != null && aux.getForward(0).key == key) {
			return aux.getForward(0);
		}
		return null;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public SkipListNode<T>[] toArray() {
		@SuppressWarnings("unchecked")
		SkipListNode<T>[] array = new SkipListNode[this.size + SENTINELS_LENGTH];
		SkipListNode<T> aux = this.root;
		for (int i = 0; i < array.length; i++) {
			array[i] = aux;
			aux = aux.getForward(0);
		}
		return array;
	}

}
