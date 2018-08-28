    import java.io.PrintWriter;

    public class MainC {
    	public static void main(String[] args){
    		FastScanner sc = new FastScanner();
    		PrintWriter out = new PrintWriter(System.out);
    		int N = sc.nextInt();
    		int K = sc.nextInt();
    		long[] map = new long[N];
    		for(int i=0;i<N;i++){
    			map[i] = sc.nextInt();
    		}
    		long dist=Long.MAX_VALUE;
    		for(int i=0;i<=N-K;i++){
    			long start = map[i];
    			long end = map[i+K-1];
    			if(start*end > 0){
    				dist = Math.min(dist, Math.max(Math.abs(start), Math.abs(end)));
    			}else{
    				dist = Math.min(dist, Math.min(Math.abs(start), Math.abs(end))*2+Math.max(Math.abs(start), Math.abs(end)));
    			}
    		}
    		out.println(dist);
    		out.flush();
    	}
    }
