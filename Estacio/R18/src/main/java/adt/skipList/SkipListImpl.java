package adt.skipList;

public class SkipListImpl<T> implements SkipList<T> {

    private static final int FIRST_INDEX = 0;
    protected SkipListNode<T> root;
    protected SkipListNode<T> NIL;

    protected int height;
    protected int maxHeight;

    protected boolean USE_MAX_HEIGHT_AS_HEIGHT;
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
        if (USE_MAX_HEIGHT_AS_HEIGHT) {
            for (int index = FIRST_INDEX; index < this.root.getHeight(); index++) {
                this.root.forward[index] = NIL;
            }
        } else {
            this.root.forward[FIRST_INDEX] = NIL;
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
    public void insert(int key, T newValue, int height) {
        if (height > this.maxHeight) return;
        this.adjustRootConnections();
        SkipListNode<T> newNode = new SkipListNode<T>(key, height, newValue);
        insert(this.root, newNode);
    }

    /**
     * Here, we adjust the root's connections according to the boolean {@code USE_MAX_HEIGHT_AS_HEIGHT}.
     */
    private void adjustRootConnections() {
        if (!USE_MAX_HEIGHT_AS_HEIGHT) {
            int index = FIRST_INDEX + 1;
            while (this.root.forward[index] != null && this.root.forward[index].equals(NIL)) {
                this.root.forward[index++] = null;
            }
        } else {
            int index = this.maxHeight - 1;
            while (this.root.forward[index] == null) {
                this.root.forward[index--] = NIL;
            }
        }
    }

    /**
     * We insert the element recursively. Much cleaner, such beautiful.
     *
     * @param node    Current node in the analysis.
     * @param newNode Node to be inserted.
     */
    private void insert(SkipListNode<T> node, SkipListNode<T> newNode) {
        for (int index = node.getHeight() - 1; index >= FIRST_INDEX; index--) {
            if (node.forward[index] == null) {
                if (index < newNode.getHeight()) node.forward[index] = NIL;
                else continue;
            }
            if (node.forward[index].getKey() == newNode.getKey()) {
                newNode.forward[index] = node.forward[index].forward[index];
                node.forward[index] = newNode;
            } else if (node.forward[index].getKey() < newNode.getKey()) {
                insert(node.forward[index], newNode);
                break;
            } else if (index < newNode.getHeight()) {
                newNode.forward[index] = node.forward[index];
                node.forward[index] = newNode;
            }
        }
    }

    @Override
    public void remove(int key) {
        this.remove(key, this.root);
        this.adjustRootConnections();
    }

    /**
     * If we insert recursively, we remove it the same way!
     *
     * @param key  Key of the node to be removed.
     * @param node Current node in the analysis.
     */
    private void remove(int key, SkipListNode<T> node) {
        for (int index = node.getHeight() - 1; index >= FIRST_INDEX; index--) {
            if (node.forward[index] == null) continue;

            if (node.forward[index].key == key) {
                node.forward[index] = node.forward[index].forward[index];
            } else if (node.forward[index].key < key) {
                this.remove(key, node.forward[index]);
                break;
            }
        }
    }

    @Override
    public int height() {
        return this.root.height;
    }

    @Override
    public SkipListNode<T> search(int key) {
        return search(key, this.root);
    }

    /**
     * Insert recursively, remove recursively... Of course we search recursively! It's so much elegant!
     *
     * @param key  Key of the searched element.
     * @param node Current node in the analysis.
     * @return {@code null} if there is no element with the given key, otherwise, return the node.
     */
    private SkipListNode<T> search(int key, SkipListNode<T> node) {
        if (node == null) return node;
        int index;
        for (index = node.getHeight() - 1; index >= FIRST_INDEX &&
                (node.forward[index] == null || node.forward[index].key > key); index--)
            ;
        if (index < FIRST_INDEX) return null;
        else if (node.forward[index].key == key) return node.forward[index];
        else return this.search(key, node.forward[index]);
    }

    @Override
    public int size() {
        int size = FIRST_INDEX;
        SkipListNode<T> node = this.root.forward[FIRST_INDEX];
        while (node != null && !node.equals(NIL)) {
            size++;
            node = node.forward[FIRST_INDEX];
        }
        return size;
    }

    @Override
    public SkipListNode<T>[] toArray() {
        SkipListNode<T>[] array = new SkipListNode[this.size() + 2];
        int index = FIRST_INDEX;

        SkipListNode<T> node = this.root;
        while (node != null) {
            array[index++] = node;
            node = node.forward[FIRST_INDEX];
        }

        return array;
    }

}
