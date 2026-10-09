class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int openBrackets = 0;
        
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                openBrackets++;
            } else {
                // Check if the next character is also a ')'
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                    i++; // Skip the next ')' since we found a consecutive pair
                } else {
                    // Missing one ')' to form a pair
                    insertions++;
                }
                
                if (openBrackets > 0) {
                    openBrackets--;
                } else {
                    // Need a '(' to match this closing pair
                    insertions++;
                }
            }
        }
        
        // Each remaining open bracket needs two ')'
        insertions += openBrackets * 2;
        
        return insertions;
    }
}