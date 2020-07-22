import java.util.HashSet;

public class LinkedListQuestion {
    public static class ListNode {
        int val;
        ListNode next;


        ListNode(int x) {
            val = x;
        }
        public void add(int newval){
            ListNode newNode = new ListNode(newval);
            if(this.next == null)
                this.next = newNode;
            else
                this.next.add(newval);
        }
        public void print() {
            System.out.print(this.val);
            if(this.next != null)
            {
                System.out.print("-->");
                this.next.print();
            }
        }

    }

    /**
     * 重排链表
     * @param head
     */
    public static void reorderList(ListNode head) {
        //找到中点位置，反转后半段链表并断开
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy.next;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode part2 = slow.next;
        slow.next = null;
        part2 = reverse(part2);


        ListNode part1 = head;

        ListNode cur = dummy;
        dummy = cur;
        ListNode cur1 = part1;
        ListNode cur2 = part2;
        int i = 0;
        while (cur1 != null && cur2 !=null){
            if(i % 2 == 0){
                cur.next = cur1;
                cur1 = cur1.next;
            }else {
                cur.next = cur2;
                cur2 = cur2.next;
            }
            cur = cur.next;
            i++;
        }
        cur.next = cur2;
        head = dummy.next;
    }

    /**
     * 判断一个链表是否为回文链表
     * @param head
     * @return
     */
    public static boolean isPalindrome(ListNode head) {

        //找到中点位置，从这里开始反转后半段链表
        //再次找到中点位置，中点与head开始进行对比，中途有不相同的返回false，否则最后返回true
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode slow = dummy;//遍历结束时slow指向的是mid的前一个
        ListNode fast = head;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode head2 = reverse(slow.next);
        slow.next = null;
        while (head != null && head2 != null){
            if(head.val != head2.val)
                return false;
            else{
                head = head.next;
                head2 = head2.next;
            }
        }
        return true;
    }

    /**
     * 反转链表
     * @param head
     * @return
     */
    private static ListNode reverse(ListNode head){
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = pre.next;
        while (cur.next != null){
            ListNode nex = cur.next;

            cur.next = nex.next;
            nex.next = pre.next;
            pre.next = nex;
        }
        return pre.next;

    }
    private int getLen(ListNode node){
        int count = 0;
        while (node != null){
            node = node.next;
            count++;
        }
        return  count;

    }

    /**
     * 移除链表中的重复节点，链表无序
     * @param head
     * @return
     */
    public ListNode removeDuplicateNodes(ListNode head) {

//        if(head == null || head.next == null)
//            return null;

        HashSet<Integer> set = new HashSet<>();
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode pre = dummy;
        while(pre.next != null){

            if(set.contains(pre.next.val)){
                ListNode delNode = pre.next;
                pre.next = delNode.next;
                delNode.next = null;
            }
            else{
                set.add(pre.next.val);
                pre = pre.next;
            }
        }
        return head;
    }
}
