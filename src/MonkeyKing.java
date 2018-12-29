

public class MonkeyKing<T extends Comparable> {
    /**
     * Created by Administrator on 2017/6/13.
     */
    class Monkey<T> {
        T power;
        Monkey<T> left;//�����
        Monkey<T> right;//�Ҷ���
        int npl;//��·����
        int key;//���Ӻ���
        int MonkeyKing;//��Ⱥ���������
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
     *   ���췽��
     */
    public MonkeyKing() {
        root = null;
    }

    public Monkey<T> getRoot(){
        return this.root;
    }

    /**
     * �ϲ���
     *
     * @param rhs ��һ����ʽ��
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
     * �ҳ����Ԫ��
     *
     * @return
     */
    public T findmax() {
        if(isEmpty()){
            System.out.println("����ʽ��Ϊ��");
        }
        return root.power;
    }
    /**
     * ɾ�����Ԫ��
     *
     * @return
     */ 
    public T deleteMax() {
        if (isEmpty()) {
            System.out.println("����ʽ��Ϊ��");
        }
        T maxpower = root.power;
        root=merge(root.left, root.right);
        return maxpower;
    }

    /**
     * �Ƿ�Ϊ��
     *
     * @return
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * �ÿ�
     */
    public void makeEmpty() {
        this.root = null;
    }

    /**
     * �ϲ�������ʽ��(�жϹ��̣������ϲ�������merge1����)
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
	 * ���������Ƿ���
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
     * �ϲ�������ʽ�ѵ���������  h1��Ԫ��С��h2(��h2��h1�����ӶѺϲ�)
     *
     * @param h1
     * @param h2
     * @return
     */
    private Monkey<T> merge1(Monkey<T> h1, Monkey<T> h2) {
    	h2.MonkeyKing = h1.MonkeyKing ;
        if (h1.left == null) {//h1Ϊ���ڵ�
            h1.left = h2;
        } else {//h1���ǵ��ڵ�
            h1.right = merge(h1.right, h2);
            if (h1.right.npl > h1.left.npl) {//�Ƚ���·������ȷ����ʽ�����ʲ����ƻ�
                swapChildren(h1);
            }
            h1.npl = h1.right.npl + 1;//��·����Ϊ�Ҷ��ӵ���·����+1
            
           
        }
        return h1;
    }

    /**
     * �������Ҷ���
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

