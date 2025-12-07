package bohush.specialdatastructures;
//C++
/*
#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
#include <fstream>

using namespace std;

struct Edge {
    int v;
    int cap;
    int flow;
    int rev;
};

struct Dinic {
    vector<vector<Edge>> g;
    vector<int> dist;
    vector<int> ptr;
    int N, S, T;

    Dinic(int N, int S, int T) : N(N), S(S), T(T) {
        g.resize(N);
        dist.resize(N);
        ptr.resize(N);
    }

    pair<int, int> addEdgeReturn(int u, int v, int cap) {
        Edge a = {v, cap, 0, (int)g[v].size()};
        Edge b = {u, 0, 0, (int)g[u].size()};
        g[u].push_back(a);
        g[v].push_back(b);
        return {u, (int)g[u].size() - 1};
    }

    bool bfs() {
        fill(dist.begin(), dist.end(), -1);
        dist[S] = 0;
        vector<int> q;
        q.reserve(N);
        q.push_back(S);

        int head = 0;
        while(head < q.size()) {
            int u = q[head++];
            for (const auto& e : g[u]) {
                if (dist[e.v] < 0 && e.flow < e.cap) {
                    dist[e.v] = dist[u] + 1;
                    q.push_back(e.v);
                }
            }
        }
        return dist[T] >= 0;
    }

    int dfs(int u, int pushed) {
        if (pushed == 0) return 0;
        if (u == T) return pushed;

        for (; ptr[u] < (int)g[u].size(); ptr[u]++) {
            Edge& e = g[u][ptr[u]];

            if (dist[e.v] == dist[u] + 1 && e.flow < e.cap) {
                int tr = dfs(e.v, min(pushed, e.cap - e.flow));
                if (tr == 0) continue;
                e.flow += tr;
                g[e.v][e.rev].flow -= tr;
                return tr;
            }
        }
        return 0;
    }

    int maxFlow() {
        int flow = 0;
        while (bfs()) {
            fill(ptr.begin(), ptr.end(), 0);
            while (int pushed = dfs(S, INT_MAX)) {
                flow += pushed;
            }
        }
        return flow;
    }
};

int n, m, k;
vector<int> xs, ys;
vector<vector<int>> maze;

int id(int r, int c) { return r * m + c; }

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    ifstream fin("input.txt");
    ofstream fout("output.txt");

    if (!(fin >> n >> m >> k)) return 0;

    xs.resize(k); ys.resize(k);
    for (int i = 0; i < k; i++) { fin >> xs[i]; xs[i]--; }
    for (int i = 0; i < k; i++) { fin >> ys[i]; ys[i]--; }

    maze.assign(n, vector<int>(m));
    for (int i = 0; i < n; i++)
        for (int j = 0; j < m; j++) fin >> maze[i][j];

    int NM = n * m;
    int S = 2 * NM;
    int T = 2 * NM + 1;
    int N_nodes = 2 * NM + 2;

    Dinic dinic(N_nodes, S, T);

    int dr[4] = {-1, 1, 0, 0}, dc[4] = {0, 0, -1, 1};
    vector<bool> isExit(m, false);

    for (int i = 0; i < k; i++)
        if (ys[i] >= 0 && ys[i] < m && maze[n - 1][ys[i]] == 0) isExit[ys[i]] = true;

    for (int r = 0; r < n; r++) {
        for (int c = 0; c < m; c++) {
            if (maze[r][c] == 1) continue;

            int in = id(r, c), out = in + NM;
            int cap = (r == n - 1 && isExit[c]) ? k : 1;

            dinic.addEdgeReturn(in, out, cap);
        }
    }

    for (int r = 0; r < n; r++) {
        for (int c = 0; c < m; c++) {
            if (maze[r][c] == 1) continue;

            int outu = id(r, c) + NM;

            for (int d = 0; d < 4; d++) {
                int rr = r + dr[d], cc = c + dc[d];
                if (rr < 0 || rr >= n || cc < 0 || cc >= m) continue;
                if (maze[rr][cc] == 1) continue;

                dinic.addEdgeReturn(outu, id(rr, cc), 1);
            }
        }
    }

    for (int i = 0; i < k; i++) {
        int col = ys[i];
        if (col >= 0 && col < m && maze[n - 1][col] == 0)
            dinic.addEdgeReturn(id(n - 1, col) + NM, T, k);
    }

    vector<pair<int, int>> sourceEdges(k, {-1, -1});
    for (int i = 0; i < k; i++) {
        int col = xs[i];
        if (col >= 0 && col < m && maze[0][col] == 0)
            sourceEdges[i] = dinic.addEdgeReturn(S, id(0, col), 1);
    }

    int totalFlow = dinic.maxFlow();

    vector<vector<int>> result(n, vector<int>(m));
    for (int r = 0; r < n; r++) result[r] = maze[r];

    for (int i = 0; i < k; i++) {
        if (sourceEdges[i].first == -1) continue;

        int u_idx = sourceEdges[i].first;
        int edge_idx = sourceEdges[i].second;
        Edge* se = &dinic.g[u_idx][edge_idx];

        if (se->flow == 0) continue;

        int curIn = se->v;
        int label = i + 2;
        bool finished = false;

        while (!finished) {
            int r = curIn / m; int c = curIn % m;

            result[r][c] = label;

            Edge* inOut = nullptr;
            for (auto& e : dinic.g[curIn]) {
                if (e.v == curIn + NM && e.flow > 0) {
                    inOut = &e;
                    break;
                }
            }

            if (inOut == nullptr) break;
            inOut->flow--;
            dinic.g[inOut->v][inOut->rev].flow++;

            int curOut = inOut->v;

            Edge* next = nullptr;
            for (auto& e : dinic.g[curOut]) {
                if (e.flow > 0) {
                    next = &e;
                    break;
                }
            }

            if (next == nullptr) break;

            if (next->v == T) {
                next->flow--;
                dinic.g[next->v][next->rev].flow++;
                finished = true;
            } else {
                next->flow--;
                dinic.g[next->v][next->rev].flow++;
                curIn = next->v;
            }
        }
    }

    fout << totalFlow << "\n";
    for (int r = 0; r < n; r++) {
        for (int c = 0; c < m; c++) {
            fout << result[r][c];
            if (c + 1 < m) fout << ' ';
        }
        fout << "\n";
    }
    fout.close();
}*/