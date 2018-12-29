

public class MonkeyKing<T extends Comparable> {
    /**
     * Created by Administrator on 2017/6/13.
     */
    class Monkey<T> {
        T power;
        Monkey<T> left;//左儿子
        Monkey<T> right;//右儿子
        int npl;//零路径长
        int key;//猴子号码
        int MonkeyKing;//该群体猴王编码
        Monkey(T power) {
            this(power, null, null);
        }

        Monkey(T power, Monkey<T> left, Monkey<T> right) {
            this.power = power;
            this.left = left;
            this.right = right;
            npl = 0;
            this.key = MonkeyNumber;
            this.MonkeyKing = MonkeyNumber;
        }
    }
    private Monkey<T> root;
    
    public Monkey<T>[] Monkeys = new Monkey[10000];
    
    public static int MonkeyNumber = 0;

    /**
     *   构造方法
     */
    public MonkeyKing() {
        root = null;
    }

    public Monkey<T> getRoot(){
        return this.root;
    }

    /**
     * 合并堆
     *
     * @param rhs 另一个左式堆
     */
    public void merge(MonkeyKing<T> rhs) {
        if (this == rhs) {
            return;
        }
        root = merge(root, rhs.root);
        rhs.root = null;
    }

    public void insert(T x) {
    	
    	Monkey<T> M = new Monkey(x);
    	Monkeys[MonkeyNumber] = M;
		/* root = merge(root, Monkeys[MonkeyNumber]); */
        MonkeyNumber++;
    }

    /**
     * 找出最大元素
     *
     * @return
     */
    public T findmax() {
        if(isEmpty()){
            System.out.println("该左式堆为空");
        }
        return root.power;
    }
    /**
     * 删除最大元素
     *
     * @return
     */ 
    public T deleteMax() {
        if (isEmpty()) {
            System.out.println("该左式堆为空");
        }
        T maxpower = root.power;
        root=merge(root.left, root.right);
        return maxpower;
    }

    /**
     * 是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * 置空
     */
    public void makeEmpty() {
        this.root = null;
    }

    /**
     * 合并两个左式堆(判断过程，真正合并过程由merge1操作)
     *
     * @param h1
     * @param h2
     * @return
     */
    private Monkey<T> merge(Monkey<T> h1, Monkey<T> h2) {
        if (h1 == null)
            return h2;
        if (h2 == null)
            return h1;
        if (h1.power.compareTo(h2.power) > 0)
            return merge1(h1, h2);
        else
            return merge1(h2, h1);
    }
    
	/* 
	 * 两个猴子是否打架
	 * 
	 * @param h1
	 * @param h2
	 * @return -1/root
	 * 
	 *  
	 *  
	 *  */
    
    private Monkey<T> Compare(Monkey<T> h1, Monkey<T> h2) {
    	
    	return h1;
    }

    /**
     * 合并两个左式堆的真正操作  h1的元素小于h2(即h2与h1的右子堆合并)
     *
     * @param h1
     * @param h2
     * @return
     */
    private Monkey<T> merge1(Monkey<T> h1, Monkey<T> h2) {
    	h2.MonkeyKing = h1.MonkeyKing ;
        if (h1.left == null) {//h1为单节点
            h1.left = h2;
        } else {//h1不是单节点
            h1.right = merge(h1.right, h2);
            if (h1.right.npl > h1.left.npl) {//比较零路径长，确保左式堆性质不被破坏
                swapChildren(h1);
            }
            h1.npl = h1.right.npl + 1;//零路径长为右儿子的零路径长+1
            
           
        }
        return h1;
    }

    /**
     * 交换左右儿子
     *
     * @param t
     * @return
     */
    private void swapChildren(Monkey<T> t) {
        Monkey<T> temp = t.right;
        t.right = t.left;
        t.left = temp;
    }

    private void print(Monkey t){
        if(t==null)
            return;
        print(t.left);
        System.out.println(t.power+":"+t.npl);
        print(t.right);
    }

    public static void main(String[] args) {
        int numItems = 100;
        MonkeyKing<Integer> h  = new MonkeyKing<>( );
        MonkeyKing<Integer> h1 = new MonkeyKing<>( );
        MonkeyNumber = 0;
        h.insert(3);
        h.insert(10);
        h.insert(8);
        h.insert(21); 
        h.insert(14);
        h.insert(23);
        h.insert(17);
        h.insert(26);
        h.merge(h.Monkeys[7],h.Monkeys[5]);

		/*
		 * h1.insert(6); h1.insert(12); h1.insert(7); h1.insert(18); h1.insert(24);
		 * h1.insert(37); h1.insert(18); h1.insert(23);
		 */
        
        System.out.println(h.Monkeys[5].key);
        System.out.println(h.Monkeys[5].MonkeyKing);
		/*
		 * System.out.println(h.findmax()); System.out.println(h1.findmax());
		 * h.merge(h1); System.out.println(h.findmax());
		 */


    }
}

