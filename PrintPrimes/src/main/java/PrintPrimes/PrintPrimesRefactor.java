package PrintPrimes;

public class PrintPrimesRefactor {
    public static class CalPrime {
        int J;
        int ORD;
        int SQUARE;
        final int ORDMAX = 30;
        int MULT[] = new int[ORDMAX +1];

        public CalPrime(int j, int ORD, int SQUARE) {
            J = j;
            this.ORD = ORD;
            this.SQUARE = SQUARE;
        }

        private int calPrime(int[] P) {
            boolean JPRIME;
            do {
                J = J +2;
                updateORDAndSquareAndMULT(P);
                JPRIME = true;
                JPRIME = isJprime(P, JPRIME, 2);
            } while(!JPRIME);
            return J;
        }

        private void updateORDAndSquareAndMULT(int[] P) {
            if (J == SQUARE){
                ORD = ORD +1;
                SQUARE = P[ORD] * P[ORD];
                MULT[ORD -1] = J;
            }
        }

        private boolean isJprime(int[] P, boolean JPRIME, int N) {
            while (N < ORD && JPRIME){
                while (MULT[N] < J)
                    MULT[N] = MULT[N] + P[N] + P[N];
                if (MULT[N] == J)
                    JPRIME = false;
                N = N +1;
            }
            return JPRIME;
        }
    }
    public static void main(String[] args){
        int[] P = calPrimes();
        {
            printPages(1000, 50, 4, P);
        }
    }

    private static int[] calPrimes() {
        int P[] = new int[1000 +1];
        P[1]= 2;
        CalPrime calPrime = new CalPrime(1, 2, 9);

        int K=1;
        while(K < 1000){
            int J = calPrime.calPrime(P);
            K = K +1;
            P[K] = J;
        }
        return P;
    }


    private static void printPages(int M, int RR, int CC, int[] P) {
        int C;
        int ROWOFFSET;
        int PAGENUMBER;
        int PAGEOFFSET;
        PAGENUMBER = 1;
        PAGEOFFSET = 1;
        while(PAGEOFFSET <= M){
            System.out.println("The First "  + M + " Prime Numbers --- Page " + PAGENUMBER);
            System.out.println("");
            for (ROWOFFSET = PAGEOFFSET; ROWOFFSET < PAGEOFFSET + RR; ROWOFFSET++){
                for (C=0; C < CC; C++)
                    if (ROWOFFSET + C* RR <= M)
                        System.out.format("%10d", P[ROWOFFSET +C* RR]);
                System.out.println("");
            }
            System.out.println("\f");
            PAGENUMBER = PAGENUMBER +1;
            PAGEOFFSET = PAGEOFFSET + RR * CC;

        }
    }
}
