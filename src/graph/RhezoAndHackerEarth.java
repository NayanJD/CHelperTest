package graph;

import nj.io.InputReader;
import nj.io.OutputWriter;

import nj.graph.Graph;

public class RhezoAndHackerEarth {
    public void solve(int testNumber, InputReader in, OutputWriter out) {

        int N=in.nextInt();
        int M=in.nextInt();

        long[][] edges=new long[M][2];

        for(int i=0;i<M;i++){
            edges[i][0]=in.nextLong()-1;
            edges[i][1]=in.nextLong()-1;

            //out.write(edges[i][0]+" "+edges[i][1]+"\n");
        }

        //out.flush();

        Graph g=new Graph(edges,N,out);

        boolean[] bedges=g.getBridges();

        int Q=in.nextInt();

        for(int i=0;i<Q;i++){
            if(bedges[in.nextInt()-1]){
                out.write("Unhappy\n");
            }else{
                out.write("Happy\n");
            }
        }

        out.flush();
    }
}
