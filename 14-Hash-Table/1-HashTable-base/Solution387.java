/**
 * Leetcode 387号问题
 * 给定一个字符串，找到它的第一个不重复的字符
 * 返回索引，如果不存在，则返回-1;
 */

public class Solution387 {

    public int firstUniqChar(String s) {
        int[] freq = new int[26];

        for(int i = 0; i < s.length(); i ++){
            char c = s.charAt(i);
            freq[c-'a'] += 1;   // 小技巧，通过 - 'a' 把小写字母的索引映射到0-25
        }

        for(int i = 0; i < s.length(); i ++){
            char c = s.charAt(i);
            if(freq[c-'a'] == 1)
                return i;
        }
        return -1;
    }
}
