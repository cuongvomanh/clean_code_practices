package PrintPrimes;

import java.util.ArrayList;
import java.util.List;

public class PrintPrimes2 {
    public static void main(String[] args){
        final int NUMBER_OF_PRIMES = 1000;

        int[] primes = PrimeGenerator.generate(NUMBER_OF_PRIMES);

        final int ROW_PER_PAGE= 50;
        final int COLUMN_PER_PAGE = 4;
        PrimePrinter primePrinter = new PrimePrinter(NUMBER_OF_PRIMES, ROW_PER_PAGE, COLUMN_PER_PAGE);
        primePrinter.print(primes);
    }

    public static class PrimeGenerator{
        private static int[] primes;
        private static List<Integer> mult = new ArrayList<Integer>();

        private static int[] generate(int numberOfPrimes) {

            primes = new int[numberOfPrimes +1];
            set2AsFirstPrime();

            return generateFromSecondPrime(numberOfPrimes);
        }

        private static void set2AsFirstPrime() {
            primes[1] = 2;
            mult.add(2);
        }

        private static int[] generateFromSecondPrime(int numberOfPrimes) {
            int primeIndex = 1;
            for(int candidate=3; primeIndex < numberOfPrimes;candidate +=2){
                if (candidate == primes[mult.size()] * primes[mult.size()]){
                    mult.add(candidate);
                }
                if(isPrime(candidate)) {
                    primes[++primeIndex] = candidate;
                }
            }
            return primes;
        }

        private static boolean isPrime(int candidate) {
            for (int i =2; i < mult.size(); i ++){
                while (mult.get(i) < candidate)
                    mult.set(i, mult.get(i) + primes[i] + primes[i]);
                if (mult.get(i) == candidate)
                    return false;
            }
            return true;
        }

    }

    public static class PrimePrinter{
        int numberOfPrimes;
        int rowPerPage;
        int columnPerPage;

        public PrimePrinter(int numberOfPrimes, int rowPerPage, int columnPerPage) {
            this.numberOfPrimes = numberOfPrimes;
            this.rowPerPage = rowPerPage;
            this.columnPerPage = columnPerPage;
        }

        private void print(int[] primes) {
            int pageNumber=1;
            for(int firstIndexOnPage=1;firstIndexOnPage < primes.length; firstIndexOnPage += rowPerPage * columnPerPage){
                printHeader(pageNumber);
                for (int rowOffset = firstIndexOnPage; rowOffset < firstIndexOnPage + rowPerPage; rowOffset++){
                    printPage(primes, rowOffset);
                }
                System.out.println("\f");
                pageNumber = pageNumber +1;
            }
        }

        private void printPage(int[] primes, int rowOffset) {
            for (int i=0; i < columnPerPage; i++)
                if (rowOffset + i* rowPerPage <= numberOfPrimes)
                    System.out.format("%10d", primes[rowOffset +i* rowPerPage]);
            System.out.println("");
        }

        private void printHeader(int pageNumber) {
            System.out.println("The First "  + numberOfPrimes + " Prime Numbers --- Page " + pageNumber);
            System.out.println("");
        }

    }

    
}
