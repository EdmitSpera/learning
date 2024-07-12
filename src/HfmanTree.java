import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HfmanTree {
    public static void main(String[] args) {
        HfmanTree main = new HfmanTree();
        String str = "This is a HFMTree example";
        Map<String, Integer> mappingMap = main.getMappingMap(str);
        Node node = main.buildTree(mappingMap);
        System.out.println();
    }

    // 构建哈夫曼结点
    class Node implements Comparable<Node>{
        String data;
        int val;
        Node left;
        Node right;

        public Node(String data, int val) {
            this.data = data;
            this.val = val;
            left = right = null;
        }

        @Override
        public int compareTo(Node o) {
            return this.val - o.val;
        }
    }
    // 思路

    // 构建一个频次映射表
    public Map<String,Integer> getMappingMap(String str){
        HashMap<String, Integer> map = new HashMap<>();
        for(int i=0;i<str.length();i++){
            if(!map.containsKey(String.valueOf(str.charAt(i)))){
                map.put(String.valueOf(str.charAt(i)),1);
            }else{
                map.put(String.valueOf(str.charAt(i)),map.get(String.valueOf(str.charAt(i))) + 1);
            }
        }
        return map;
    }
    // 构建哈夫曼树

    public Node buildTree(Map<String,Integer> mappingMap){
        PriorityQueue<Node> minHeap = new PriorityQueue<>();

        // 初始化小根堆
        for(Map.Entry<String,Integer> entry : mappingMap.entrySet()){
            minHeap.offer(new Node(entry.getKey(),entry.getValue()));
        }

        while(minHeap.size()>0){
            // 临界条件
            if(minHeap.size()==1){
                break;
            }
            Node left = minHeap.poll();
            Node right = minHeap.poll();
            Node parent = new Node("\0", left.val + right.val);
            parent.left = left;
            parent.right = right;

            minHeap.offer(parent);
        }
        return minHeap.poll();
    }

    // 递归构建哈夫曼编码
    public Map<String,String> codeMapEntry(Node root){
        return null;
    }

    public Map<String,String> recursionBuildCode(Node root){
        return null;
    }

}
