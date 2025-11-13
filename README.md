# DAA_Bonus

#  Project Description

This project demonstrates the implementation of a Minimum Spanning Tree (MST) algorithm using Kruskal's approach, with the ability to efficiently handle edge removal and find replacement edges to maintain the MST property.

## Project Structure
```bash
BonusDAA/
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


# Example Run

```bash
GRAPH STRUCTURE:
Vertices: 6 (labeled 0-5)
Edges: 9

Edge List:
  0 -- 1 (weight: 4)
  0 -- 3 (weight: 8)
  1 -- 2 (weight: 8)
  1 -- 3 (weight: 11)
  2 -- 3 (weight: 7)
  2 -- 4 (weight: 2)
  3 -- 4 (weight: 9)
  3 -- 5 (weight: 14)
  4 -- 5 (weight: 10)

ORIGINAL MINIMUM SPANNING TREE:
2 -- 4 (weight: 2)
0 -- 1 (weight: 4)
2 -- 3 (weight: 7)
0 -- 3 (weight: 8)
4 -- 5 (weight: 10)
Total Weight: 31
Number of Edges: 5
EDGE REMOVAL AND REPLACEMENT

Removed Edge: 2 -- 3 (weight: 7)
Components after removal:
Component 1: [2, 4, 5]
Component 2: [0, 1, 3]
Replacement Edge Found: 2 -- 3 (weight: 7)

NEW MINIMUM SPANNING TREE:
2 -- 4 (weight: 2)
0 -- 1 (weight: 4)
0 -- 3 (weight: 8)
4 -- 5 (weight: 10)
2 -- 3 (weight: 7)
Total Weight: 31
Number of Edges: 5

VERIFICATION:
Original MST weight: 31
New MST weight: 31
Weight difference: 0
✓ Tree structure maintained (correct number of edges)
✓ Replacement edge found successfully
Program completed successfully!
```

# Algorithm Complexity

| Operation                     | Time Complexity | Space Complexity |
|-------------------------------|----------------|-------------------|
| MST Construction (Kruskal)    | O(E log E)     | O(V)              |
| Edge Removal                  | O(1)           | O(1)              |
| Component Detection (DFS)     | O(V + E)       | O(V)              |
| Replacement Edge Search       | O(E)           | O(V)              |
| **Total**                     | O(E log E)     | O(V + E)          |

Where:

V = number of vertices
E = number of edges
