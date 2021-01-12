package com.example.linrecurrencerelation;

import android.util.Log;
import android.util.Pair;

public class Matrix {

     public static final String LOG_CAT = Matrix.class.getSimpleName();

    // TODO: validate input
    public static long[][] MatrixMultiplication(long[][] A, long[][] B, long modulo) {

        int m = A.length;
        int n = A[0].length;
        int p = B[0].length;
        long[][] C = new long[m][p];
        long product;

        for (int i = 0 ; i < m ; i ++)
            for (int j = 0 ; j < p ; j ++) {
                C[i][j] = 0;
                for (int k = 0 ; k < n ; k ++)
                    C[i][j] = (C[i][j] + (((A[i][k] % modulo) * (B[k][j] % modulo)) % modulo)) % modulo;
            }

        return C;
    }

    public static long[][] identityMatrix(int m) {
        long[][] A = new long[m][m];
        for (int i = 0 ; i < m ; i ++) A[i][i] = 1;
        return A;
    }

    public static long[][] MatrixExponentiation(long[][] A, long exp, long modulo) {
        long[][] B = identityMatrix(A.length);

        while (exp > 0) {
            if (exp % 2 == 1) B = MatrixMultiplication(B, A, modulo);
            exp = (long) exp / 2;
            A = MatrixMultiplication(A, A, modulo);
        }
        return B;
    }

    public static long[][] createVerticalMatrix(int[] data) {
        long[][] A = new long[data.length][1];
        for (int i = 0 ; i < data.length ; i ++) A[i][0] = data[data.length - i - 1];
        return A;
    }

    public static long[][] createBaseMatrix(int[] data) {
        long [][] A = new long[data.length][data.length];
        for (int i = 0 ; i < data.length ;  i++) {
            for (int j = 0 ; j < data.length ; j ++)
                A[i][j] = 0;
        }

        for (int i = 0 ; i < data.length ; i ++) {
            A[0][i] = data[data.length - i - 1];
        }

        for (int i = 1 ; i < data.length ; i ++) {
            A[i][i - 1] = 1;
        }
        return A;
    }

    public static void Test() {
        int m = 2;
        int n = 2;
        int p = 2;
        long[][] A = new long[m][n], B = new long[n][p];

        A[0][0] = 1;
        A[0][1] = 1;
        A[1][0] = 1;
        A[1][1] = 0;

        long[][] C = MatrixExponentiation(A, 1000000L, 1000000000L);

        Log.e(LOG_CAT, Long.toString(C[0][0]));

    }
}
