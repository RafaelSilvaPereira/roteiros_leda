package adt.skipList;

public class SkipListImpl<T> implements SkipList<T> {

	protected SkipListNode<T> root;
	protected SkipListNode<T> NIL;

	protected int height;
	protected int maxHeight;

	// SET THIS VALUE TO TRUE IF YOU ARE IMPLEMENTING MAX_LEVEL = LEVEL
	// SET THIS VALUE TO FALSE IF YOU ARE IMPLEMENTING MAX_LEVEL != LEVEL
	protected boolean USE_MAX_HEIGHT_AS_HEIGHT = false;
	protected double PROBABILITY = 0.5;

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
	}

	/**
	 * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL Caso
	 * esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve
	 * conectar todos os forward. Senao o ROOT eh inicializado com level=1 e o
	 * metodo deve conectar apenas o forward[0].
	 */
	private void connectRootToNil() {
		for (int i = 0; i < this.height; i++) {
			root.getForward()[i] = NIL;
		}

	}

	/**
	 * Metodo que gera uma altura aleatoria para ser atribuida a um novo no no
	 * metodo insert(int,V)
	 */
	private int randomLevel() {
		int randomLevel = 1;
		double random = Math.random();
		while (Math.random() <= PROBABILITY && randomLevel < maxHeight) {
			randomLevel = randomLevel + 1;
		}
		return randomLevel;
	}

	@Override
	public void insert(int key, T newValue) {
		int height = randomLevel();
		insert(key, newValue, height);
	}

	@Override
	public void insert(int key, T newValue, int height) {
		SkipListNode[] update = new SkipListNode[height];
		SkipListNode<T> aux = this.root;

		for (int i = height - 1; i >= 0; i--) {
			while (aux.getForward(i) != null && aux.getForward(i).key < key) {
				aux = aux.getForward(i);
			}
			update[i] = aux;
		}
		aux = aux.getForward(0);
		if (aux.getKey() == key) {
			aux.setValue(newValue);

		} else {
			aux = new SkipListNode<T>(key, height, newValue);
			for (int i = 0; i < update.length; i++) {
				if (update[i].getForward(i) == null) {
					aux.getForward()[i] = NIL;

				} else {
					aux.getForward()[i] = update[i].getForward(i);
				}
				update[i].getForward()[i] = aux;
			}

		}
		this.height = height();

	}

	@Override
	public void remove(int key) {
		int height = search(key).height;
		SkipListNode[] update = new SkipListNode[height];
		SkipListNode<T> aux = this.root;

		for (int i = height - 1; i >= 0; i--) {
			while (aux.getForward(i) != null && aux.getForward(i).key < key) {
				aux = aux.getForward(i);
			}
			update[i] = aux;
		}

		aux = aux.getForward(0);
		if (aux.getKey() == key) {
			for (int i = 0; i < update.length; i++) {
				if (i > 0 && update[i].equals(root)
						&& aux.getForward()[i].equals(NIL)) {
					update[i].getForward()[i] = null;
				} else {
					update[i].getForward()[i] = aux.getForward(i);
				}

			}
		}
		this.height = height();

	}

	@Override
	public int height() {
		int height = 0;
		SkipListNode<T> aux = root.getForward(0);
		while (!aux.equals(NIL)) {
			if (aux.getHeight() > height)
				height = aux.getHeight();
			aux = aux.getForward(0);
		}

		return height;

	}

	@Override
	public SkipListNode<T> search(int key) {
		SkipListNode<T> aux = root;
		for (int i = height - 1; i >= 0; i--) {
			while (aux.forward[i] != null && aux.forward[i].key <= key) {
				aux = aux.forward[i];
			}
		}
		if (aux.key == key) {
			return aux;
		} else {
			return null;
		}

	}

	@Override
	public int size() {
		SkipListNode<T> aux = root.forward[0];
		int size = 0;
		while (aux.key != NIL.key) {
			aux = aux.getForward(0);
			size++;
		}
		return size;

	}

	@Override
	public SkipListNode<T>[] toArray() {
		SkipListNode<T> aux = root;

		SkipListNode<T>[] array = new SkipListNode[this.size() + 2];

		int i = 0;
		while (aux != NIL) {
			array[i] = aux;
			aux = aux.forward[0];
			i++;
		}
		array[i] = aux;
		return array;

	}

}
