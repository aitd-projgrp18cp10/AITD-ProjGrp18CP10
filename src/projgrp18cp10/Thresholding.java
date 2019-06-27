/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projgrp18cp10;

import static java.lang.Math.round;
import java.util.Arrays;

/**
 *
 * @author Vinita
 */
public class Thresholding
{

    public static void ThresholdingCalc(ProjDataBase pdb)
    {
        double sum1, sum2, count1, count2, T, T1, avg1, avg2, sum, c;
        int[][] GrayScaleImg = pdb.GrayScaleImg;
        int[][] SegmentedBackGround = new int[pdb.OrigImgRow][pdb.OrigImgColumn];
        int[][] SegmentedImg = new int[pdb.OrigImgRow][pdb.OrigImgColumn];
        for (int[] row : SegmentedBackGround) Arrays.fill(row, -1); 
        for (int[] row : SegmentedImg) Arrays.fill(row, -1); 
        
        sum1 = sum2 = count1 = count2 = sum = c = 0;
        for(int i = 0; i < GrayScaleImg.length; i++)
        {
            for (int j = 0; j < GrayScaleImg[i].length; j++)
            {
                sum = sum + GrayScaleImg[i][j];
                c++;
            }
        }
        T = sum / c;
        T1 = T;
        while(T > 1)
        {
            T = T1;
            for(int i = 0; i < pdb.OrigImgRow; i++)
            {
                for (int j = 0; j < pdb.OrigImgColumn; j++)
                {
                    if (T < GrayScaleImg[i][j])
                    {
                        sum1 = sum1 + GrayScaleImg[i][j];
                        SegmentedBackGround[i][j] = (int) round(GrayScaleImg[i][j]);
                        count1++;
                    }
                    else
                    {
                        sum2 = sum2 + GrayScaleImg[i][j];
                        SegmentedImg[i][j] = (int) round(GrayScaleImg[i][j]);
                        count2++;
                    }
                }
            }
            avg1 = sum1 / count1;
            avg2 = sum2 / count2;
            T1 = round((avg1 + avg2) / 2);
            T = T - T1;
        }
        pdb.SegmentedBackGround = SegmentedBackGround;
        pdb.SegmentedImg = SegmentedImg;
    }
}
