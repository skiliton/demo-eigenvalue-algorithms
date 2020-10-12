package com.repeta.numerical_analysis.lab1.eigenvalue;

import com.repeta.numerical_analysis.lab1.matrix.SimpleMatrixHelper;
import org.ejml.simple.SimpleMatrix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QRMethod extends EigenvalueAlgorithm {

    private class QRDecomposition{
        private SimpleMatrix Q;
        private SimpleMatrix R;

        public QRDecomposition(SimpleMatrix q, SimpleMatrix r) {
            Q = q;
            R = r;
        }

        public SimpleMatrix getQ() {
            return Q;
        }

        public SimpleMatrix getR() {
            return R;
        }
    }

    @Override
    public Map<Double, List<SimpleMatrix>> apply(SimpleMatrix simpleMatrix) {
        SimpleMatrix A = new SimpleMatrix(simpleMatrix);
        SimpleMatrix Ap;
        int n = A.numRows();
        SimpleMatrix QChain = SimpleMatrix.identity(n);
        ArrayList<Double> eValues;
        ArrayList<SimpleMatrix> eVectors;
        SimpleMatrix Q,R;
        do{
            Ap = A;
            QRDecomposition QR = householderQRFact(A);
            Q = QR.getQ(); R = QR.getR();
            QChain = QChain.mult(Q).negative();
            A = R.mult(Q);
        }while (A.minus(Ap).elementMaxAbs()>eps);
        eValues = SimpleMatrixHelper.fetchDiagonalValues(R.negative());
        eVectors = SimpleMatrixHelper.fetchColumnVectors(QChain);
        Map<Double,List<SimpleMatrix>> res = new HashMap<>();
        for (int i=0;i<n;i++){
            res.put(eValues.get(i),eVectors.subList(i,i+1));
        }
        return res;
    }

    private QRDecomposition householderQRFact(SimpleMatrix A){
        int m = A.numRows(), n = A.numCols();
        SimpleMatrix Q = SimpleMatrix.identity(m);
        for(int i=0;i<n;i++){
            double aii = A.get(i, i);

            double s = Math.signum(-aii)*A.extractMatrix(i,m,i,i+1).normF();
            double fi = 1/Math.sqrt(2*s*(s - aii));

            SimpleMatrix w = new SimpleMatrix(m,1);
            w.set(i, aii - s);
            w = w.combine(i+1,0,A.extractMatrix(i+1,m,i,i+1));
            w = w.scale(fi);

            SimpleMatrix P = SimpleMatrix.identity(m).minus( w.mult(w.transpose()).scale(2) );
            Q = P.mult(Q);
            A = P.mult(A);
        }
        SimpleMatrix R = A;
        Q = Q.transpose();
        return new QRDecomposition(Q,R);
    }
}
