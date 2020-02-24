package ab2;


    public class Counter {

        //current value
        private Integer count = 0;

        /**
         * Initializes a new counter with the given value
         *
         * @param value the value of the counter
         */
        public Counter(Integer value) {
            this.count = value;
        }

        /**
         * Increments the counter by 1.
         */
        public void increment() {
            this.count++;
        }

        /**
         * Decrements the counter by 1.
         */
        public void decrement() {
            this.count--;
        }

        public void mirrorIterative(int val) {
            this.count = val;
            this.mirrorIterative();
        }

        public void mirrorRecursive(int val) {
            this.count = val;
            this.mirrorRecursive();
        }

        public void mirrorIterative() {
            int temp = count;

            for (int i = 0; i < temp; i++) {
                System.out.print(count);
                this.decrement();
            }
            for (int i = 0; i < temp; i++) {

                this.increment();
                System.out.print(count);
            }
        }

        public void mirrorRecursive() {
            if (count <= 0) return;
            System.out.println(count);
            decrement();
            mirrorRecursive();
            increment();
            System.out.println(count);
        }


        //TODO Put your code here
    }

    class CTest {
        public static void main(String[] args) {
            new Counter(5).mirrorIterative();
            System.out.println();
            new Counter(10).mirrorIterative();
            System.out.println();
            new Counter(0).mirrorIterative(10);
            System.out.println();
            new Counter(5).mirrorIterative();

        }
    }
