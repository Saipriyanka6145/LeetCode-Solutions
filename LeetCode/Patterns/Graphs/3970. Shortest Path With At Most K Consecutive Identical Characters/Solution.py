from heapq import heappop, heappush
from math import inf


class Solution:

  def shortestPath(
      self, n: int, edges: List[List[int]], labels: str, k: int
  ) -> int:
    g = [[] for _ in range(n)]
    for u, v, w in edges:
      g[u].append((v, w))
    dist = [[inf] * (k + 1) for _ in range(n)]
    dist[0][1], pq = 0, [(0, 0, 1)]
    while pq:
      d, u, cur = heappop(pq)
      if u == n - 1:
        return d
      if d > dist[u][cur]:
        continue
      for v, w in g[u]:
        nxt = 1 if labels[v] != labels[u] else cur + 1
        if nxt <= k and d + w < dist[v][nxt]:
          dist[v][nxt] = d + w
          heappush(pq, (d + w, v, nxt))
    return -1