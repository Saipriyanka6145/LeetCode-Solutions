class Solution {
    public String[] createGrid(int m, int n) {
        List<String> grid = new ArrayList<>();
        grid.add(".".repeat(n));
        String row ="#".repeat(n-1)+".";
        for (int i = 1;i < m;i++){
            grid.add(row);
        } 
        return grid.toArray(new String[0]);
    }
}