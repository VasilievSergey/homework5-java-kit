package gb;

public class Philosopher implements Runnable{
    private final int NUMBER_OF_MEALS = 3;
    private int countEat = 0;
    private final Object leftFork;
    private final Object rightFork;

    public Philosopher(Object leftFork, Object rightFork) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }
    public void run() {
        try {
            while (countEat < NUMBER_OF_MEALS) {

                // думает
                doAction(System.nanoTime() + ": Размышляет");
                synchronized (leftFork) {
                    doAction(
                            System.nanoTime()
                                    + ": Взял левую вилку");
                    synchronized (rightFork) {
                        // ест
                        doAction(
                                System.nanoTime()
                                        + ": Взял правую вилку - ест");

                        doAction(
                                System.nanoTime()
                                        + ": Положил правую вилку");
                    }

                    // опять думает
                    doAction(
                            System.nanoTime()
                                    + ": Положил левую вилку. Вернулся к размышлениям");
                }
                countEat++;
                if (countEat == 3) System.out.println(": Наелся!!!");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
    }

    private void doAction(String action) throws InterruptedException {
        System.out.println(
                Thread.currentThread().getName() + " " + action);
        Thread.sleep(((int) (Math.random() * 100)));
    }
}