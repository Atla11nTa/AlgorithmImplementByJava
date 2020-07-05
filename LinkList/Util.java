package LinkList;

class Node<T>{
     T data;
     Node<T> next;
     Node(T data, Node<T> next){
        this.data = data;
        this.next = next;
    }
    @Override
    public String toString(){
         Node<T> head = this;
         StringBuilder result = new StringBuilder(head.data+"");
         head = head.next;
         while(head != null){
             result.append("->").append(head.data);
             head = head.next;
         }
         return result.toString();
    }
}

class List{
    static <T> Node<T> InitList(T[] arr){
        if(arr.length == 0)
            return null;
        Node<T> head = new Node<>(arr[0],null);
        Node<T> pre = head;
        for(int i=1;i<arr.length;i++){
            Node<T> newNode = new Node<>(arr[i],null);
            pre.next = newNode;
            pre = newNode;
        }
        return head;
    }
}

