    import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.NoSuchElementException;

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

    class FastScanner {
        private final InputStream in = System.in;
        private final byte[] buffer = new byte[1024];
        private int ptr = 0;
        private int buflen = 0;
        private boolean hasNextByte() {
            if (ptr < buflen) {
                return true;
            }else{
                ptr = 0;
                try {
                    buflen = in.read(buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (buflen <= 0) {
                    return false;
                }
            }
            return true;
        }
        private int readByte() { if (hasNextByte()) return buffer[ptr++]; else return -1;}
        private static boolean isPrintableChar(int c) { return 33 <= c && c <= 126;}
        public boolean hasNext() { while(hasNextByte() && !isPrintableChar(buffer[ptr])) ptr++; return hasNextByte();}
        public String next() {
            if (!hasNext()) throw new NoSuchElementException();
            StringBuilder sb = new StringBuilder();
            int b = readByte();
            while(isPrintableChar(b)) {
                sb.appendCodePoint(b);
                b = readByte();
            }
            return sb.toString();
        }
        public long nextLong() {
            if (!hasNext()) throw new NoSuchElementException();
            long n = 0;
            boolean minus = false;
            int b = readByte();
            if (b == '-') {
                minus = true;
                b = readByte();
            }
            if (b < '0' || '9' < b) {
                throw new NumberFormatException();
            }
            while(true){
                if ('0' <= b && b <= '9') {
                    n *= 10;
                    n += b - '0';
                }else if(b == -1 || !isPrintableChar(b)){
                    return minus ? -n : n;
                }else{
                    throw new NumberFormatException();
                }
                b = readByte();
            }
        }
        public int nextInt() {
            long nl = nextLong();
            if (nl < Integer.MIN_VALUE || nl > Integer.MAX_VALUE) throw new NumberFormatException();
            return (int) nl;
        }
        public double nextDouble() { return Double.parseDouble(next());}
    }