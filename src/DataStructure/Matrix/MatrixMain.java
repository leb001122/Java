package DataStructure.Matrix;

public class MatrixMain {
    public static void main(String [] args) {

        Matrix matrix = new Matrix();
        Matrix rightMat = matrix.turnRight();
        Matrix leftMat = matrix.turnLeft();
        Matrix transMat = matrix.clone();
        transMat.selfTranspose(0);
        printMatrix(matrix, rightMat, leftMat, transMat);

    }

    public static void printMatrix(Matrix matrix, Matrix rightMat, Matrix leftMat, Matrix transMat) {

        System.out.println("      최초행렬         " +
                            "우측으로 90도 회전     " +
                            "좌측으로 90도 회전    " +
                            "    전치행렬");

        int MAX = 4;
        for(int line=0; line<MAX; line++) {
            matrix.printRow(line);
            rightMat.printRow(line);
            leftMat.printRow(line);
            transMat.printRow(line);
            System.out.println();
        }
    }
}
