import java.io.*;
import java.util.*;
public class lab7_E{
    private static AVLnode root;

    public static void main(String[] args) {
        QReader4 in = new QReader4();
        QWriter4 out = new QWriter4();
        int m = in.nextInt();
        int k = in.nextInt();
        AVLnode[] nodes = new AVLnode[m+1];
        for(int i=1;i<m+1;i++){
            nodes[i] = new AVLnode();
            nodes[i].val = in.nextInt();
        }
        int[] order = new int[m-k+1];
        for(int i=0;i<m-k+1;i++){
            order[i] = in.nextInt();
        }
        root = nodes[1];
        for(int i=2;i<k;i++) {
            insert(nodes[i]);
        }
        for(int i=k;i<m+1;i++){
            insert(nodes[i]);
            int index = order[i-k];
            AVLnode temp = root;
            while(index!=0){
                if(index>getNum(temp.left)){
                    index-=getNum(temp.left);
                    if(index == 1){
                        break;
                    }else {
                        index-=1;
                        temp = temp.right;
                    }
                } else {
                    temp = temp.left;
                }
            }
            out.println(temp.val);
            delete(root,nodes[i-k+1].val);
        }

        out.close();
    }

    private static void insert(AVLnode node){
        AVLnode p;
        AVLnode temp = root;
        do {
            p = temp;
            if(temp.val < node.val) {
                temp = temp.right;
            }
            else if(temp.val > node.val) {
                temp = temp.left;
            }
        } while(temp != null);
        if(p.val < node.val) {
            p.right = node;
            node.parent = p;
        }else {
            p.left = node;
            node.parent = p;
        }
        AVLnode renew = node;
        while(renew!=root) {
            update(renew);
            renew=rebuild(renew);
            renew=renew.parent;
        }
        update(renew);
        rebuild(renew);
    }
    private static void traverse(AVLnode root){
        if (root==null) return;
        traverse(root.left);
        // System.out.print(root.val+" ");
        traverse(root.right);
    }
    private static void traverse_num(AVLnode root){
        if (root==null) return;
        traverse_num(root.left);
        // System.out.print(root.height+" ");
        traverse_num(root.right);
    }
    private static int getHeight(AVLnode node) {
        if (node == null) {
            return 0;
        } else {
            return node.height;
        }
    }
    private static int getNum(AVLnode node) {
        if (node == null) {
            return 0;
        } else {
            return node.num;
        }
    }
    public static void update(AVLnode node) {
        node.height=Math.max(getHeight(node.left),getHeight(node.right))+1;
        node.num=getNum(node.left)+getNum(node.right)+1;
        node.BF=getHeight(node.left)-getHeight(node.right);
    }

    public static AVLnode rebuild(AVLnode node) {
        if(node != null) {
            if(node.BF == 2) { //左子树高
                AVLnode leftChild = node.left;
                if(leftChild.BF>=0) {//右旋
                    rightRotation(node);
                }
                else { //左右旋
                    leftRotation(leftChild);
                    rightRotation(node);
                }
                return leftChild;
            }
            else if(node.BF == -2) {//右子树高
                AVLnode rightChild = node.right;
                if(rightChild.BF<=0) {//左旋
                    leftRotation(node);
                }
                else {//右左旋
                    rightRotation(rightChild);
                    leftRotation(node);
                }
                return  rightChild;
            }
            else return node;
        }
        return null;
    }

    // 右旋
    public static void rightRotation(AVLnode node) {
        if (node != null) {
            AVLnode leftChild = node.left;
            // 节点的左孩子代替该节点的位置
            leftChild.parent = node.parent;
            if (node.parent == null) {
                root = leftChild;
            } else if (node.parent.right == node) {// 即node节点在它原父节点的右子树中
                node.parent.right = leftChild;
            } else if (node.parent.left == node) {
                node.parent.left = leftChild;
            }

            // 如果leftChild的右节点存在，则需将该右节点的父节点指给node节点
            node.left = leftChild.right;
            if (leftChild.right != null) {
                leftChild.right.parent = node;
            }

            // 节点本身成为左孩子的右子树
            leftChild.right = node;
            node.parent = leftChild;

            // 深度和孩子数量更新
            update(node);
            update(leftChild);

        }
    }

    // 左旋
    public static void leftRotation(AVLnode node) {
        if (node != null) {
            AVLnode rightChild = node.right;
            // 节点的右孩子代替该节点的位置
            rightChild.parent = node.parent;
            if (node.parent == null) {
                root = rightChild;
            } else if (node.parent.right == node) {
                node.parent.right = rightChild;
            } else if (node.parent.left == node) {
                node.parent.left = rightChild;
            }

            // 如果rightChild的左节点存在，则需将该左节点的父节点指给node节点
            node.right = rightChild.left;
            if (rightChild.left != null) {
                rightChild.left.parent = node;
            }

            // 节点本身成为右孩子的左子树
            rightChild.left = node;
            node.parent = rightChild;

            // 深度和孩子数量更新
            int x = rightChild.height + rightChild.BF;
            int h = node.height + node.BF;
            node.BF = h-x;
            rightChild.BF = rightChild.BF+1;

            update(node);
            update(rightChild);
        }
    }

    public static void delete(AVLnode node, int num) {
        AVLnode renew;
        while(node.val!=num){
            if (num < node.val){
                node=node.left;
            }
            else {
                node=node.right;
            }
        }

        AVLnode parent = node.parent;
        AVLnode leftChild = node.left;
        AVLnode rightChild = node.right;

        // 删除的是叶子节点
        if (leftChild == null && rightChild == null) {
            if (parent != null) {
                if (parent.left == node) {
                    parent.left = null;
                } else if (parent.right == node) {
                    parent.right = null;
                }
                renew= parent;
            } else {
                root = null;
                renew= null;
            }
        }
        // 删除节点有左子树没有右子树
        else if (leftChild != null && rightChild == null) {
            if (parent != null && parent.val > node.val) {
                parent.left = leftChild;
                leftChild.parent=parent;
                renew= parent;
            } else if (parent != null && parent.val < node.val) {
                parent.right = leftChild;
                leftChild.parent=parent;
                renew= parent;
            } else {
                root = leftChild;
                root.parent=null;
                renew= root;
            }
        }
        // 删除节点有右子树没有左子树
        else if (leftChild == null && rightChild != null) {
            if (parent != null && parent.val > node.val) {
                parent.left = rightChild;
                rightChild.parent=parent;
                renew= parent;
            } else if (parent != null && parent.val < node.val) {
                parent.right = rightChild;
                rightChild.parent=parent;
                renew= parent;
            } else {
                root = rightChild;
                rightChild.parent=null;
                renew= root;
            }
        }
        // 既有左子树又有右子树
        else {
            AVLnode suc = successor(node);
            node.val = suc.val;
            if (suc.parent.left == suc) {
                suc.parent.left = suc.right;
                if(suc.right!=null) {
                    suc.right.parent = suc.parent;
                }
                renew = suc.parent;
            } else {
                suc.parent.right = suc.right;
                if(suc.right!=null) {
                    suc.right.parent = suc.parent;
                }
                renew = suc.parent;
            }
        }

        while(renew!=null&&renew!=root) {
            update(renew);
            renew=rebuild(renew);
            renew=renew.parent;
        }
        if (renew!=null) {
            update(renew);
            rebuild(renew);
        }
    }
    // 既有左子树 又有右子树 进行删除时候 先找到这个节点的后继
    // 后继：右孩子的最左孩子
    public static AVLnode successor(AVLnode node) {
        AVLnode p = node.right;
        while (p.left != null) {
            p = p.left;
        }
        return p;
    }


}

class AVLnode{
    int val;
    AVLnode parent;
    AVLnode left;
    AVLnode right;
    int height;
    int num;
    int BF;
    public AVLnode(){
        this.height=1;
        this.num=1;
    }

}

class QReader4 {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer tokenizer = new StringTokenizer("");

    private String innerNextLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }
    public boolean hasNext() {
        while (!tokenizer.hasMoreTokens()) {
            String nextLine = innerNextLine();
            if (nextLine == null) {
                return false;
            }
            tokenizer = new StringTokenizer(nextLine);
        }
        return true;
    }

    public String nextLine() {
        tokenizer = new StringTokenizer("");
        return innerNextLine();
    }

    public String next() {
        hasNext();
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }
}

class QWriter4 implements Closeable {
    private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public void print(Object object) {
        try {
            writer.write(object.toString());
        } catch (IOException e) {
            return;
        }
    }

    public void println(Object object) {
        try {
            writer.write(object.toString());
            writer.write("\n");
        } catch (IOException e) {
            return;
        }
    }

    @Override
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            return;
        }
    }
}


