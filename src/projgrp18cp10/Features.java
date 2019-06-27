/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projgrp18cp10;

/**
 *
 * @author ProjGrp18CP10 , Chinmay P., Kalpita
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
    
    public static void Correlation(ProjDataBase pdb)
    {

        double[][] co_occurence = pdb.co_occurence;
        double meanX = 0, meanY = 0, correlation = 0, stdX = 0, stdY = 0;
        
        // Calculate Mean of X & Y
        for (int x = 0; x < 256; x++)
        {
            for (int y = 0; y < 256; y++)
            {
                meanX += x * co_occurence[x][y];
                meanY += y * co_occurence[x][y];
            }
        }

        // Calculate Standard Deviation 
        for (int x = 0; x < 256; x++)
        {
            for (int y = 0; y < 256; y++)
            {
                stdX += (x - meanX) * (x - meanX) * co_occurence[x][y];
                stdY += (y - meanY) * (y - meanY) * co_occurence[x][y];
            }
        }
        stdX = Math.sqrt(stdX);
        stdY = Math.sqrt(stdY);

        // Calculate Correlation
        for (int x = 0; x < 256; x++)
        {
            for (int y = 0; y < 256; y++)
            {
                double nu = (x - meanX) * (y - meanY) * co_occurence[x][y];
                double de = stdX * stdY;
                correlation += nu / de;
            }
        }
        pdb.correlation = correlation;
    }
    
    public static void ASM(ProjDataBase pdb)
    {
        double asm = 0;
        double[][] co_occurence = pdb.co_occurence;

        for (int x = 0; x < 256; x++)
        {
            for (int y = 0; y < 256; y++)
            {
                asm += co_occurence[x][y] * co_occurence[x][y];
            }
        }
        pdb.asm = asm;
    }
    
    public static void Contrast(ProjDataBase pdb)
    {
        double contrast = 0;
        double[][] co_occurence = pdb.co_occurence;
        for (int x = 0; x < 256; x++)
        {
            for (int y = 0; y < 256; y++)
            {
                contrast += (x - y) * (x - y) * co_occurence[x][y];
            }
        }
        pdb.contrast = contrast;
    }
    
    public static void Energy(ProjDataBase pdb)
    {
        double energy = 0;
        double[][] co_occurence = pdb.co_occurence;
        for (int x = 0; x < 256; x++)
        {
            for (int y = 0; y < 256; y++)
            {
                energy += co_occurence[x][y] * co_occurence[x][y];
            }
        }
        energy = Math.sqrt(energy);
        pdb.energy = energy;
    }
    
    public static void Homogeneity(ProjDataBase pdb)
    {
        double homogeneity = 0;
        double[][] co_occurence = pdb.co_occurence;
        for (int x = 0; x < 256; x++)
        {
            for (int y = 0; y < 256; y++)
            {
                homogeneity += co_occurence[x][y] / (1 + Math.abs(x - y));
            }
        }
        pdb.homogeneity = homogeneity;
    }
    
    public static void IDM(ProjDataBase pdb)
    {
        double idm = 0;
        double[][] co_occurence = pdb.co_occurence;
        for (int x = 0; x < 256; x++)
        {
            for (int y = 0; y < 256; y++)
            {
                idm += co_occurence[x][y] / (1 + ((x - y) * (x - y)));
            }
        }
        pdb.idm=idm;
    }
    
    public static void Entropy(ProjDataBase pdb)
    {
        double entropy = 0;
        double[][] co_occurence = pdb.co_occurence;
        for (int x = 0; x < 256; x++)
        {
            for (int y = 0; y < 256; y++)
            {
                entropy += -co_occurence[x][y] * (Math.log(co_occurence[x][y]) / Math.log(2));
            }
        }
        pdb.entropy=entropy;
    }
    
    public static void MSE(ProjDataBase pdb)
    {
        double mse = 0;
        int[][] SegmentedImg = pdb.SegmentedImg;
        int[][] GrayScaleImg = pdb.GrayScaleImg;
        for (int x = 0; x < pdb.OrigImgRow; x++)
        {
            for (int y = 0; y < pdb.OrigImgColumn; y++)
            {
                if (SegmentedImg[x][y] == -1)
                    SegmentedImg[x][y] = 0;
                mse += (GrayScaleImg[x][y] - SegmentedImg[x][y]) * (GrayScaleImg[x][y] - SegmentedImg[x][y]);
            }
        }
        mse /= (pdb.OrigImgRow * pdb.OrigImgColumn);
        pdb.mse = mse;
    }
    
    public static void PSNR(ProjDataBase pdb)
    {
        double psnr = 0, mse = pdb.mse;
        psnr = 10 * (Math.log((255*255)/mse) / Math.log(10));
        pdb.psnr = psnr;
    }
    
	
}
