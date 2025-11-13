# DAA_Bonus

## Project Structure
```bash
mst-replacement/
├── README.md                 
├── src/
   └── mst/
       ├── Main.java        
       ├── Graph.java       
       ├── Edge.java        
       ├── UnionFind.java   
       └── MSTReplacer.java 
```
## How to Run
```bash
git clone https://github.com/yourusername/mst-replacement.git
cd mst-replacement
```
## How It Works

### 1. MST Construction (Kruskal's Algorithm)

Sort all edges by weight in ascending order
Use Union-Find to detect cycles
Add edges that don't form cycles until we have V-1 edges

### 2. Edge Removal

Remove selected edge from MST
Graph splits into two disconnected components

### 3. Finding Replacement Edge

Identify vertices in each component using DFS
Search all graph edges for connections between components
Select the edge with minimum weight

### 4. Building New MST

Add the replacement edge to reconnect components
Verify the tree structure is maintained
