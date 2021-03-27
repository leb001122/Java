package dataStructure.first;

import java.util.Random;

public class Matrix {

    private int [][] matrix;
    final static int ROWS = 4; // 상수로 말고?
    final static int COLS = 4;

    // 최초행렬 만드는 생성자
    public Matrix() { // 인자로 ROWS와 COLS를 받아 생성하는 것을 추천
        Random ran = new Random();
        matrix = new int[ROWS][COLS];
        for(int i=0; i<ROWS; i++) {
            for(int j=0; j<COLS; j++) {
                matrix[i][j] = ran.nextInt(999)+1;
            }
        }
    }

    /* this.matrix = matrix;를 하면 matrix 객체의 matrix를
        transMat 객체의 matrix에 복사할 때 배열의 주소값을 복사하므로 (얕은 복사)
        selfTranspose 메소드를 실행했을 때 matrix 객체의 matrix도 변경됨.
        따라서 배열의 값 하나하나를 복사해주어야한다.
    */
    public Matrix (int [][] matrix) {
        this.matrix = matrix;
    }

    public Matrix clone() { // clone으로 만드는 방식은 정방행렬인 경우만 가능
        int [][] transMatrix = new int[COLS][ROWS];
        for(int i=0; i<ROWS; i++) {
            for(int j=0; j<COLS; j++) {
                transMatrix[i][j] = matrix[i][j];
            }
        }
        return new Matrix(transMatrix);
    }

    public Matrix turnRight() {
        int [][] rightMatrix = new int[COLS][ROWS];
        for(int i=0; i<ROWS; i++) {
            for(int j=0; j<COLS; j++) {
                rightMatrix[i][j] = matrix[ROWS-1-j][i];
            }
        }
        return new Matrix(rightMatrix);
    }

    public Matrix turnLeft() {
        int [][] leftMatrix = new int[COLS][ROWS];
        for(int i=0; i<ROWS; i++) {
            for(int j=0; j<COLS; j++) {
                leftMatrix[i][j] = matrix[j][ROWS-1-i];
            }
        }
        return new Matrix(leftMatrix);
    }

    // transMat 객체에 있는 matrix 배열의 값을 변경(전치).
    // 배열의 값을 직접 변경하는 것이므로 n이 4가 되었을 때 바뀐 행렬을 반환하는 것이 아님.
    // 따라서 return null을 해준다.
    public Matrix selfTranspose(int startCol) { // n = 행
        if(startCol>=COLS)
            return null;
        else {
            int tmp;
            for(int i=startCol; i<COLS; i++) {
                if(startCol!=i) {
                    tmp = matrix[startCol][i];
                    matrix[startCol][i] = matrix[i][startCol];
                    matrix[i][startCol] = tmp;
                }
            }
            return selfTranspose(startCol+1);
        }
    }

    // 각 행렬을 한 행씩 모두 출력하고 다음 행으로 넘어가기
    // 오른쪽 정렬하기
    public void printRow(int row) {
        System.out.print("| ");
        for(int e : matrix[row])
            System.out.printf("%3d ", e);
        System.out.print("| ");
    }
}