import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class task12 {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("lis.in"));
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("lis.out"));
        int n=in.nextInt();
        int taken[]=new int [101];
        int untaken[]=new int [101];
        int p[]=new int [101];
        int q[]=new int [101];
        int t[]=new int [101];
        for (int i = 1; i <n+1 ; i++) {
            p[i]=in.nextInt();
            q[i]=in.nextInt();
        }
        int cnt = 1;
        int currentParent = 0;
        ArrayList<Integer> currentQueue = new ArrayList<Integer>();
        ArrayList<ArrayList<Integer>> childs = new ArrayList<ArrayList<Integer>>();
            childs.add(new ArrayList<Integer>());



        while (cnt <= n)
        {
            for (int i = 1; i <= n; ++i)
            {
                if (p[i] == currentParent)
                {
                    t[cnt++] = i;
                    childs.get(currentParent).add(i);
                    currentQueue.add(i);
                }
            }
            currentParent = currentQueue.get(0);
            currentQueue.remove(0);
        }
        for (int i = n; i>0; i--)
        {
            if (q[t[i]] > 0)
            {
                taken[t[i]] = q[t[i]];
            }
            else
            {
                taken[t[i]] = 0;
            }
            untaken[t[i]] = 0;
            if (!(childs.get(t[i]).isEmpty()))
            {
                for (int j = 0; j < childs.get(t[i]).get(n-1); ++j)
                {
                    taken[t[i]] += untaken[childs.get(t[i]).get(j)];
                    untaken[t[i]] += Math.max(taken[childs.get(t[i]).get(j)], untaken[childs.get(t[i]).get(j)]);
                }
            }
        }
        int result = Math.max(taken[t[1]], untaken[t[1]]);
        out.write(String.valueOf(result));
        out.close();
/*
* int n, p[10000], q[10000], tree[10000], taken[10000], untaken[10000], result;
vector < vector <int> > children;

void treeFilling()
{
    children.resize(n + 1);
    int cnt = 1;
    int currentParent = 0;
    queue <int> currentQueue;
    while (cnt <= n)
    {
        for (int i = 1; i <= n; ++i)
        {
            if (p[i] == currentParent)
            {
                tree[cnt++] = i;
                children[currentParent].push_back(i);
                currentQueue.push(i);
            }
        }
        currentParent = currentQueue.front();
        currentQueue.pop();
    }
}

void takenAndUnTakenFilling()
{
    for (int i = n; i; --i)
    {
        if (q[tree[i]] > 0)
        {
            taken[tree[i]] = q[tree[i]];
        }
        else
        {
            taken[tree[i]] = 0;
        }
        untaken[tree[i]] = 0;
        if (!children[tree[i]].empty())
        {
            for (int j = 0; j < children[tree[i]].size(); ++j)
            {
                taken[tree[i]] += untaken[children[tree[i]][j]];
                untaken[tree[i]] += max(taken[children[tree[i]][j]], untaken[children[tree[i]][j]]);
            }
        }
    }
    result = max(taken[tree[1]], untaken[tree[1]]);
}

void dataOutput()
{
    freopen("selectw.out", "w", stdout);
    //freopen("b.txt", "w", stdout);
    cout << result;
    fclose(stdout);
}*/
    }
}
