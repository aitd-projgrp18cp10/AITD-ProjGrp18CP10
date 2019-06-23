/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projgrp18cp10;

/**
 *
 * @author ProjGrp18CP10 , Chinmay P.
 */
public class Features
{
    public static void GLCM(ProjDataBase pdb)
    {
        int[][] SegmentedImg = pdb.SegmentedImg;
        double[][] co_occurence = new double[256][256];
        
        // Calculate Gray-Level Co-ocurance Matrix
        int Current, Adjacent;
        for (int i = 0; i < pdb.OrigImgRow; i++)
        {
            for (int j = 0; j < (pdb.OrigImgColumn - 1); j++)
            {
                Current = SegmentedImg[i][j];
                Adjacent = SegmentedImg[i][j + 1];
                if (Current != -1 && Adjacent != -1)
                {
                    co_occurence[Current][Adjacent]++;
                }
            }
        }
        
        // Normalize the Matrix to [0 - 1]
        for (int i = 0; i < 256; i++)
        {
            for (int j = 0; j < 256; j++)
            {
                co_occurence[i][j] /= (pdb.OrigImgRow * (pdb.OrigImgColumn - 1));
            }
        }
        
        pdb.co_occurence = co_occurence;
    }
    
	
}
