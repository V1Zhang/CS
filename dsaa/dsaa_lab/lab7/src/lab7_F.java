import java.io.*;
import java.util.*;
public class lab7_F {
    private static nodeF root;
    public static void main(String[] args) {
        QReader5 in = new QReader5();
        QWriter5 out = new QWriter5();
        int n = in.nextInt();
        int count = 0;
        int current = 2;
        long sum = 0;
        for(int i=0;i<n;i++){
            int a = in.nextInt();
            long b = in.nextLong();
            if(count==0) {
                root = new nodeF();
                root.a = a;
                root.b = b;
                count++;
                current = a;
            }else {
                nodeF node = new nodeF();
                node.a = a;
                node.b = b;
                Insert(node);
                if(a != current){
                    nodeF pre = findPre(root, node.b);
                    nodeF next = findNext(root, node.b);
                    long pre_diff = 0;
                    long next_diff = 0;
                    if(pre==null && next!=null) {
                        next_diff = next.b - node.b;
                        sum += next_diff;
                        Delete(root, node.b);
                        Delete(root, next.b);
                        count--;
                    } else if (pre!=null && next==null) {
                        pre_diff = node.b - pre.b;
                        sum += pre_diff;
                        Delete(root, node.b);
                        Delete(root, pre.b);
                        count--;
                    }else{
                        pre_diff = node.b - pre.b;
                        next_diff = next.b - node.b;
                        if (pre_diff > next_diff) {
                            sum += next_diff;
                            Delete(root, node.b);
                            Delete(root, next.b);
                            count--;
                        } else {
                            sum += pre_diff;
                            Delete(root, node.b);
                            Delete(root, pre.b);
                            count--;
                        }
                    }
                }else{
                    count++;
                }
            }
        }
        out.println(sum);
        out.close();
    }
    public static nodeF findPre(nodeF node, long target) {
        if(node == null)return null;
        if(node.b >= target){
            // 从根节点向下找，直到找到第一个比target小的节点
            return findPre(node.left, target);
        } else {
            //找到第一个比target小的节点（node0），比target小的最大值肯定在node0的右子树内。
            //所以查找右子树，返回值为空说明右子树没有结果，则去当前节点值
            nodeF x= findPre(node.right, target);
            if(x==null){
                return node;
            }else{
                return x;
            }
        }
    }
    public static nodeF findNext(nodeF node, long target) {
        if(node == null)return null;
        if(node.b <= target){
            return findNext(node.right, target);
        } else {
            nodeF x= findNext(node.left, target);
            if(x==null){
                return node;
            }else{
                return x;
            }
        }
    }
    public static void Insert(nodeF node){
        nodeF p;
        nodeF temp = root;
        do {
            p = temp;
            if(temp.b < node.b) {
                temp = temp.right;
            }
            else if(temp.b > node.b) {
                temp = temp.left;
            }
        } while(temp != null);
        if(p.b < node.b) {
            p.right = node;
            node.parent = p;
        }else {
            p.left = node;
            node.parent = p;
        }
        nodeF renew = node;
        while(renew!=root) {
            Update(renew);
            renew=Rebuild(renew);
            renew=renew.parent;
        }
        Update(renew);
        Rebuild(renew);
    }
    private static int getHeight(nodeF node) {
        if (node == null) {
            return 0;
        } else {
            return node.height;
        }
    }
    private static int getNum(nodeF node) {
        if (node == null) {
            return 0;
        } else {
            return node.num;
        }
    }
    public static void Update(nodeF node) {
        node.height=Math.max(getHeight(node.left),getHeight(node.right))+1;
        node.num=getNum(node.left)+getNum(node.right)+1;
        node.BF=getHeight(node.left)-getHeight(node.right);
    }

    public static nodeF Rebuild(nodeF node) {
        if(node != null) {
            if(node.BF == 2) {
                nodeF leftChild = node.left;
                if(leftChild.BF>=0) {
                    rightRotation(node);
                }
                else {
                    leftRotation(leftChild);
                    rightRotation(node);
                }
                return leftChild;
            }
            else if(node.BF == -2) {
                nodeF rightChild = node.right;
                if(rightChild.BF<=0) {
                    leftRotation(node);
                }
                else {
                    rightRotation(rightChild);
                    leftRotation(node);
                }
                return  rightChild;
            }
            else return node;
        }
        return null;
    }


    public static void rightRotation(nodeF node) {
        if (node != null) {
            nodeF leftChild = node.left;
            leftChild.parent = node.parent;
            if (node.parent == null) {
                root = leftChild;
            } else if (node.parent.right == node) {
                node.parent.right = leftChild;
            } else if (node.parent.left == node) {
                node.parent.left = leftChild;
            }

            node.left = leftChild.right;
            if (leftChild.right != null) {
                leftChild.right.parent = node;
            }
            leftChild.right = node;
            node.parent = leftChild;

            Update(node);
            Update(leftChild);

        }
    }

    public static void leftRotation(nodeF node) {
        if (node != null) {
            nodeF rightChild = node.right;
            rightChild.parent = node.parent;
            if (node.parent == null) {
                root = rightChild;
            } else if (node.parent.right == node) {
                node.parent.right = rightChild;
            } else if (node.parent.left == node) {
                node.parent.left = rightChild;
            }

            node.right = rightChild.left;
            if (rightChild.left != null) {
                rightChild.left.parent = node;
            }

            rightChild.left = node;
            node.parent = rightChild;

            int x = rightChild.height + rightChild.BF;
            int h = node.height + node.BF;
            node.BF = h-x;
            rightChild.BF = rightChild.BF+1;

            Update(node);
            Update(rightChild);
        }
    }

    public static void Delete(nodeF node, long num) {
        nodeF renew;
        while(node.b!=num){
            if (num < node.b){
                node=node.left;
            }
            else {
                node=node.right;
            }
        }

        nodeF parent = node.parent;
        nodeF leftChild = node.left;
        nodeF rightChild = node.right;

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
        } else if (leftChild != null && rightChild == null) {
            if (parent != null && parent.b > node.b) {
                parent.left = leftChild;
                leftChild.parent=parent;
                renew= parent;
            } else if (parent != null && parent.b < node.b) {
                parent.right = leftChild;
                leftChild.parent=parent;
                renew= parent;
            } else {
                root = leftChild;
                root.parent=null;
                renew= root;
            }
        } else if (leftChild == null && rightChild != null) {
            if (parent != null && parent.b > node.b) {
                parent.left = rightChild;
                rightChild.parent=parent;
                renew= parent;
            } else if (parent != null && parent.b < node.b) {
                parent.right = rightChild;
                rightChild.parent=parent;
                renew= parent;
            } else {
                root = rightChild;
                rightChild.parent=null;
                renew= root;
            }
        } else {
            nodeF suc = successor(node);
            node.b = suc.b;
            if (suc.parent.left == suc) {
                suc.parent.left = suc.right;
                if(suc.right!=null) {
                    suc.right.parent = suc.parent;
                }
                renew= suc.parent;
            } else {
                suc.parent.right = suc.right;
                if(suc.right!=null) {
                    suc.right.parent = suc.parent;
                }

                renew= suc.parent;
            }
        }

        while(renew!=null&&renew!=root) {
            Update(renew);
            renew=Rebuild(renew);
            renew=renew.parent;
        }
        if (renew!=null) {
            Update(renew);
            Rebuild(renew);
        }
    }


    public static nodeF successor(nodeF node) {
        nodeF p = node.right;
        while (p.left != null) {
            p = p.left;
        }
        return p;
    }






}
class nodeF{
    int a;
    long b;
    nodeF parent;
    nodeF left;
    nodeF right;
    int num;
    int height;
    int BF;
    public nodeF(){
        this.height=1;
        this.num=1;
    }
}
class QReader5 {
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

class QWriter5 implements Closeable {
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








