package ANN;

import java.util.ArrayList;
import java.util.*;
import ANN.Matrix;

public class SGD_CE {
    float[][] X, W1;
    float[] D, W2;
    float alpha = 0.9F;
    int HNode;

    public SGD_CE(float[][] X, float[] D, int HNode_int){
        this.X = X;
        this.D = D;
        this.HNode = HNode_int;

        if (X.length != D.length) throw new RuntimeException("Illegal vector dimensions.");

        System.out.println("---Random Weight Matrix Generated---\n W1 and W2");
        this.W1 = Matrix.randomF(this.X[0].length, HNode);
        System.out.println("------");
        this.W2 = Matrix.randomF(HNode);
    }

    public static void main(String[] args) {
        float[][] X = new float[][]{{0,0,1},{0,1,1},{1,0,1},{1,1,1}};
        float[] D = new float[]{0,1,1,0};

        int Hidden_layer = 4;
        SGD_CE C1 = new SGD_CE(X, D, Hidden_layer);
        System.out.println("---X---");
        Matrix.printMat(C1.X);
        System.out.println("---D---");
        Matrix.printMat(C1.D);

        C1.train(10000);

    }

    public void train(int epoch){
        float[] y, e;
        y = new float[this.X.length];
        e = new float[this.X.length];

        for (int j = 0; j < epoch; j++) {
            for (int i = 0; i < this.X.length; i++) {
                float[] v1 = Matrix.multiply(this.X[i],this.W1);
                float[] y1 = sigmoid(v1);
                float v = Matrix.multiply(y1,this.W2);
                y[i] = sigmoid(v);
                e[i] = this.D[i]-y[i];
                //System.out.println("Entropy : " + e[i]);

                float delta = e[i];
                float[] e1 = Matrix.multiply(delta, this.W2);
                float[] delta1 = Matrix.subtract(1, y1);
                delta1 = Matrix.multiplyArr(y1, delta1);
                delta1 = Matrix.multiplyArr(delta1, e1);

                float[][] dw1 = Matrix.multiplyTrans(Matrix.multiply(this.alpha, delta1), this.X[i]);
                this.W1 = Matrix.add(this.W1, Matrix.transpose(dw1));
                float[] dw2 = Matrix.multiply(this.alpha*delta, y1);
                this.W2 = Matrix.add(this.W2, dw2);
            }
            float rmse_val = rmse(D, y);
            if (j%100==0){
                System.out.println("Epoch " + j + "Test Acc(RMSE) : " + (1-rmse_val));
            }

        }
        System.out.println("----Final Weight matrix W1, W2--");
        Matrix.printMat(this.W1);
        Matrix.printMat(this.W2);
        System.out.println("----predict y value--");
        Matrix.printMat(y);
        System.out.println("----Real value--");
        Matrix.printMat(D);
        float rmse_val = rmse(D, y);
        System.out.println("----RMSE--");
        System.out.println(rmse_val);
    }


    public static float[] sigmoid(float[] x){
        float[] y = new float[x.length];
        for (int i = 0; i < x.length; i++) y[i] = (float) (1 / (1 + Math.exp(-1 * x[i])));
        return y;
    }
    public static float sigmoid(float x){
        float y = (float) (1 / (1 + Math.exp(-1 * x)));
        return y;
    }
    public static float rmse(float[] ans, float[] pred){
        int m = ans.length;
        float y = 0;
        if (m != pred.length) throw new RuntimeException("Illegal vector dimensions.");
        for (int i = 0; i < m; i++) {
            y += Math.pow((ans[i]-pred[i]),2);
        }
        y = (float)Math.sqrt(y);
        return y;
    }
    /*
    public static float entropy(float ans, float pred){
        float y = -1*ans*(float)Math.log(pred)-1*(1-ans)*(float)Math.log(1-pred);
        return y;
    }*/

}

