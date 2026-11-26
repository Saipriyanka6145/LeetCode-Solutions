class Solution {
    public boolean canAliceWin(int n) {
        int stonesToRemove = 10;
        boolean isAliceTurn = true;
        
        while (n >= stonesToRemove) {
            n -= stonesToRemove;
            stonesToRemove--;
            isAliceTurn = !isAliceTurn;
        }
        
        // If the loop breaks on Bob's turn (isAliceTurn == false), Bob cannot move and Alice wins.
        // If it breaks on Alice's turn (isAliceTurn == true), Alice cannot move and loses.
        return !isAliceTurn;
    }
}