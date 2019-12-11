public class Test {

    public static void Test_Console_RunAll() {
        Test_Console_NewLine();
        Test_Console_Print();
        Test_Console_PrintLn();
        Test_Console_RepeatChar();
        Test_Console_RepeatStr();
    }

    public static void Test_Console_NewLine() {
        for (int i = 0; i < 10; ++i) {
            Debug.logInfo("Test_Console_NewLine() - Test " + i + " - Printing " + i + " newLines");
            Console.newLine(i);
        }
    }

    public static void Test_Console_Print() {
        for (int i = 0; i < 10; ++i) {
            Debug.logInfo("Test_Console_Print() - Test " + i);
            Console.print("\tTest: " + i);
        }
    }

    public static void Test_Console_PrintLn() {
        for (int i = 0; i < 10; ++i) {
            Debug.logInfo("Test_Console_PrintLn() - Test " + i);
            Console.println("\tTest: " + i);
        }
    }

    public static void Test_Console_RepeatChar() {
        for (int i = 0; i < 10; ++i) {
            Debug.logInfo("Test_Console_repeatChar() - Test " + i + " will print via type casting " + i + " to its char equivalence");
            Console.repeatChar(i, (char) i);
        }
    }

    public static void Test_Console_RepeatStr() {
        for (int i = 0; i < 10; ++i) {
            Debug.logInfo("Test_Console_repeatStr() - Test " + i + " will print " + i);
            Console.repeatStr(i, "\tTest: " + i);
        }
    }

    public static void Test_MathRunAll() {
        Test_MathAdd();
        Test_MathSub();
        Test_MathMul();
        Test_MathDiv();
    }

    public static void Test_MathAdd() {
        int ia = 3, ib = 5;
        Debug.logInfo(String.format("Test_MathAdd() - Text: %d + %d = %d", ia, ib, BasicMath.add(ia, ib)));
        double da = 3.3, db = 5.5;
        Debug.logInfo(String.format("Test_MathAdd() - Text: %f + %f = %f", da, db, BasicMath.add(da, db)));
        float fa = 3.5f, fb = 5.3f;
        Debug.logInfo(String.format("Test_MathAdd() - Text: %f + %f = %f", fa, fb, BasicMath.add(fa, fb)));
    }

    public static void Test_MathSub() {
        int ia = 3, ib = 5;
        Debug.logInfo(String.format("Test_MathSub() - Text: %d - %d = %d", ia, ib, BasicMath.subtract(ia, ib)));
        double da = 3.3, db = 5.5;
        Debug.logInfo(String.format("Test_MathSub() - Text: %f - %f = %f", da, db, BasicMath.subtract(da, db)));
        float fa = 3.5f, fb = 5.3f;
        Debug.logInfo(String.format("Test_MathSub() - Text: %f - %f = %f", fa, fb, BasicMath.subtract(fa, fb)));
    }

    public static void Test_MathMul() {
        int ia = 3, ib = 5;
        Debug.logInfo(String.format("Test_MathMul() - Text: %d * %d = %d", ia, ib, BasicMath.multiply(ia, ib)));
        double da = 3.3, db = 5.5;
        Debug.logInfo(String.format("Test_MathMul() - Text: %f * %f = %f", da, db, BasicMath.multiply(da, db)));
        float fa = 3.5f, fb = 5.3f;
        Debug.logInfo(String.format("Test_MathMul() - Text: %f * %f = %f", fa, fb, BasicMath.multiply(fa, fb)));
    }

    public static void Test_MathDiv() {
        int ia = 3, ib = 5;
        try {
            Debug.logInfo(String.format("Test_MathDiv() - Text: %d / %d = %d", ia, ib, BasicMath.subtract(ia, ib)));
        } catch (ArithmeticException e) {
            Debug.logWarning("CANNOT divide by zero");
        }
        double da = 3.3, db = 5.5;
        try {
            Debug.logInfo(String.format("Test_MathDiv() - Text: %f / %f = %f", da, db, BasicMath.subtract(da, db)));
        } catch (ArithmeticException e) {
            Debug.logWarning("CANNOT divide by zero");
        }
        float fa = 3.5f, fb = 5.3f;
        try {
            Debug.logInfo(String.format("Test_MathDiv() - Text: %f / %f = %f", fa, fb, BasicMath.subtract(fa, fb)));
        } catch (ArithmeticException e) {
            Debug.logWarning("CANNOT divide by zero");
        }
    }
}
