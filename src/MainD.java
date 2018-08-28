import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class MainD {
	private static int start =0;
	private static int end = 1000_000_001;
	private static int n;
	public static void main(String[] args){
		solve();
	}


	static void solve(){
		PrintWriter out = new PrintWriter(System.out);
		FastScanner sc = new FastScanner();
        n = sc.nextInt();
		int[] a = new int[n];
        for(int i = 0; i < n; i++){
            a[i] = sc.nextInt();
        }
        while(end-start>1){
            int mid = Math.floorDiv((start+end),2)+1;
            if(func(a,mid)>= Math.max((long)n*(n+1)/4,1)){
                start = mid;
            }
            else end = mid;
        }
        out.println(start);
        out.flush();
    }

    static long func(int[] a, int x){
        int[] cum = new int[n+1];
        for(int i = 0; i < n; i++){
            if(a[i]>=x) cum[i+1] = cum[i]+1;
            else cum[i+1] = cum[i]-1;
        }
        BIT bit = new BIT(2*n+1);
        long ret = 0;
        bit.add(n,1);
        for(int i = 0; i < n; i++){
            ret += bit.sum(cum[i+1]+n+1);
            bit.add(cum[i+1]+n,1);
        }
        return ret;
    }

}


class BIT {
	final int N;
	final long[] bit;

	public BIT(int size){
		this.N=size;
		bit= new long[N+1];
	}

	public void clear(){
		Arrays.fill(bit, 0);
	}

	public void add(int a, int w){
		for(int x=a+1;x<=N; x += x & -x) bit[x] += w;
	}

	public int sum(int a){
		int ret = 0;
		for(int x=a;x>0;x-=x&-x) ret += bit[x];
		return ret;
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