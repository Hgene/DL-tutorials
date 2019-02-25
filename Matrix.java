package ANN;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Matrix {
    // return a random m-by-n matrix with values between 0 and 1
    public static float[][] randomF(int m, int n) {
        float[][] a = new float[m][n];

        for(int i=0; i<a.length; i++){
            for(int j=0; j<a[0].length; j++){
                a[i][j]=(float)(2*(Math.random())-1);  // Range of weight : (-1,1)
                System.out.print(a[i][j]+" ");
            }
            System.out.println("");
        }
        return a;
    }

    public static float[] randomF(int m) {
        float[] a = new float[m];

        for(int i=0; i<a.length; i++){
            a[i]=(float)(2*(Math.random())-1);  // Range of weight : (-1,1)
            System.out.print(a[i]+" ");
        }
        return a;
    }

    // return n-by-n identity matrix I
    public static float[][] identity(int n) {
        float[][] a = new float[n][n];
        for (int i = 0; i < n; i++)
            a[i][i] = 1;
        return a;
    }

    // return x^T y
    public static float dot(float[] x, float[] y) {
        if (x.length != y.length) throw new RuntimeException("Illegal vector dimensions.");
        float sum = 0.0F;
        for (int i = 0; i < x.length; i++)
            sum += x[i] * y[i];
        return sum;
    }

    // return B = A^T
    public static float[][] transpose(float[][] a) {
        int m = a.length;
        int n = a[0].length;
        float[][] b = new float[n][m];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                b[j][i] = a[i][j];
        return b;
    }

    // return C = A + B
    public static float[][] add(float[][] a, float[][] b) {
        int m = a.length;
        int n = a[0].length;
        float[][] c = new float[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                c[i][j] = a[i][j] + b[i][j];
        return c;
    }

    public static float[] add(float[] a, float[] b) {
        int m = a.length;
        int n = b.length;
        if (m != n) throw new RuntimeException("Illegal vector dimensions.");
        float[] c = new float[m];
        for (int i = 0; i < m; i++)
            c[i] = a[i] + b[i];
        return c;
    }

    //Return C = A - B
    public static float[] subtract(float[] a, float[] b) {
        int m = a.length;
        float[] c = new float[m];
        for (int i = 0; i < m; i++)
            c[i] = a[i] - b[i];
        return c;
    }
    //Substract broadcasting Return c[i] = a-b[i] for all B
    public static float[] subtract(float a, float[] b) {
        int m = b.length;
        float[] c = new float[m];
        for (int i = 0; i < m; i++)
            c[i] = a - b[i];
        return c;
    }

    // C = A*B
    public static float[][] multiply(float[][] a, float[][] b) {
        int m1 = a.length;
        int n1 = a[0].length;
        int m2 = b.length;
        int n2 = b[0].length;
        if (n1 != m2) throw new RuntimeException("Illegal matrix dimensions.");
        float[][] c = new float[m1][n2];
        for (int i = 0; i < m1; i++)
            for (int j = 0; j < n2; j++)
                for (int k = 0; k < n1; k++)
                    c[i][j] += a[i][k] * b[k][j];
        return c;
    }

    public static float[] multiply(float[][] a, float[] x) {
        int m = a.length;
        int n = a[0].length;
        if (x.length != n) throw new RuntimeException("Illegal matrix dimensions.");
        float[] y = new float[m];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                y[i] += a[i][j] * x[j];
        return y;
    }

    public static float[] multiply(float[] a, float[][] x) {
        int m = x[0].length;
        int n = a.length;
        if (x.length != n) throw new RuntimeException("Illegal matrix dimensions.");
        float[] y = new float[m];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                y[i] += a[j] * x[j][i];
        return y;
    }

    public static float multiply(float[] a, float[] x) {
        int m = a.length;
        if (x.length != m) throw new RuntimeException("Illegal matrix dimensions.");
        float y = 0;
        for (int i = 0; i < m; i++)
            y += a[i] * x[i];
        return y;
    }
    //Return c[i]=a[i]*b[i] for all a in A, b in B
    public static float[] multiplyArr(float[] a, float[] x) {
        int m = a.length;
        if (x.length != m) throw new RuntimeException("Illegal matrix dimensions.");
        float[] y = new float[m];
        for (int i = 0; i < m; i++)
            y[i] = a[i] * x[i];
        return y;
    }
    //Return (m*1)(1*n) m by n Matrix
    public static float[][] multiplyTrans(float[] a, float[] x) {
        int m = a.length;
        int n = x.length;
        float[][] y = new float[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                y[i][j] = a[i] * x[j];
            }
        return y;
    }

    public static float[] multiply(float a, float[] x) {
        int m = x.length;
        float[] y = new float[m];
        for (int i = 0; i < m; i++)
            y[i] = a * x[i];
        return y;
    }


    public static void printMat(float[][] matrix){
        //System.out.println("Matrix : --- below ---");
        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[0].length; j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println("");
        }
    }

    public static void printMat(float[] matrix){
        //System.out.println("float[] : --- below ---");
        for(int i=0; i<matrix.length; i++){
            System.out.print(matrix[i]+" ");
            System.out.println("");
        }
    }
}
