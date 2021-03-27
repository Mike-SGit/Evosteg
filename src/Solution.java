import java.util.*;

public class Solution {

    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        
        int testNum = input.nextInt();
        int index = 0;
        int row;
        int col;
        String consbuffer;
        int getint;
        int up = 1;
        int down = 1;
        int left = 1;
        int right = 1;
        int ls = 0;
        while (index < testNum){
            row = input.nextInt();
            col = input.nextInt();
            consbuffer = input.nextLine();  


            int[][] twoD_arr = new int[row][col];
            for(int i = 0; i < row; i++){
                for(int j = 0; j < col; j++){
                    getint = input.nextInt();
                    twoD_arr[i][j] = getint;
                }
            }

            // for(int i = 0; i < row; i++){
            //     for(int j = 0; j < col; j++){
            //     System.out.print(twoD_arr[i][j]);
            //     }
            //     System.out.print("\n");

            // }
            int tempup = 1;
            int tempdown = 1;
            int templeft = 1;
            int tempright = 1;

            int x, y;
            int q,w;
            int r,t;
            int a,s;

            for(int i = 0; i < row; i++){
                for(int j = 0; j < col; j++){
                if (twoD_arr[i][j] == 1){
                    x = i;
                    y = j;
                    while (twoD_arr[x][y] == 1){
                        x++;
                        tempdown ++;
                    }
                    x = i;
                    y = j;
                    while (twoD_arr[x][y] == 1){
                        y++;
                        tempright++;
                    }
                    x = i;
                    y = j;
                    while (twoD_arr[x][y] == 1){
                        x--;
                        tempup++;
                    }
                    x = i;
                    y = j;
                    while (twoD_arr[x][y] == 1){
                        y++;
                        templeft++;
                    }
                    x = i;
                    y = j;

                    if (tempup > templeft){
                        int temp = 0;
                        while (temp < templeft){
                            if (tempup/2 > temp) ls ++;
                            temp++;
                        }
                    }
                    if (tempup > tempright){
                        int temp = 0;
                        while (temp < tempright){
                            if (tempup/2 > temp) ls ++;
                            temp++;
                        }
                    }
                    if (tempdown > tempright){
                        int temp = 0;
                        while (temp < tempright){
                            if (tempdown/2 > temp) ls ++;
                            temp++;
                        }
                    }
                    if (tempdown > templeft){
                        int temp = 0;
                        while (temp < templeft){
                            if (tempdown/2 > temp) ls ++;
                            temp++;
                        }
                    }

                    if (tempright > tempup){
                        int temp = 0;
                        while (temp < tempup){
                            if (tempright/2 > temp) ls ++;
                            temp++;
                        }
                    }
                    if (tempright > tempdown){
                        int temp = 0;
                        while (temp < tempdown){
                            if (tempright/2 > temp) ls ++;
                            temp++;
                        }
                    }

                    if (templeft > tempdown){
                        int temp = 0;
                        while (temp < tempdown){
                            if (templeft/2 > temp) ls ++;
                            temp++;
                        }
                    }
                    if (templeft > tempup){
                        int temp = 0;
                        while (temp < tempup){
                            if (templeft/2 > temp) ls ++;
                            temp++;
                        }
                    }



                }


                }

            }


            System.out.println(ls);
            
            System.out.println("Case #");

            index++;
        }


    }
}