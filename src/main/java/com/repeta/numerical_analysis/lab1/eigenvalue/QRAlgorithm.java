package com.repeta.numerical_analysis.lab1.eigenvalue;

import com.repeta.numerical_analysis.lab1.matrix.SimpleMatrixHelper;
import org.ejml.simple.SimpleMatrix;

import java.net.SocketImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QRAlgorithm extends EigenvalueAlgorithm {

    private SimpleMatrix QChain;
    private SimpleMatrix R;
    private SimpleMatrix A;
    private int n;

    @Override
    protected void init(SimpleMatrix simpleMatrix) {
        A = new SimpleMatrix(simpleMatrix);
        n = A.numRows();
        QChain = SimpleMatrix.identity(n);

    }

    @Override
    protected void iteration() {
        QRDecomposition QR = householderQRFact(A);
        SimpleMatrix Q = QR.getQ();
        R = QR.getR();
        QChain = QChain.mult(Q).negative();
        A = R.mult(Q);
    }

    @Override
    protected Map<Double, List<SimpleMatrix>> createEigenSpaces() {
        ArrayList<Double> eValues = SimpleMatrixHelper.fetchDiagonalValues(R.negative());
        ArrayList<SimpleMatrix> eVectors = SimpleMatrixHelper.fetchColumnVectors(QChain);
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
}
