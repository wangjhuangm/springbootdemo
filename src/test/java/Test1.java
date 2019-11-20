public class Test1 {
    private static int i = 1;

    public Test1() {
        i ++;
    }

    public int getI() {
        return i;
    }

    public static void main(String[] args) {
        String s1 = "name";
        String s2 = "name";
        String s3 = new String("name");
        if (s1.equals(s2)) {
            System.out.println("s1 = s2");
        }
        if (s1.equals(s3)) {
            System.out.println("s1 = s3");
        }
        Test1 test1 = new Test1();
        System.out.println(test1.getI());
        Test1 test2 = new Test1();
        System.out.println(test2.getI());
    }
}
