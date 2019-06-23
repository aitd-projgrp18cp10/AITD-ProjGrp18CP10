/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projgrp18cp10;

/**
 *
 * @author ProjGrp18CP10
 */
public class ProjDataBase {
    public int OrigImgRow;
    public int OrigImgColumn;

    public int[][] OrigImgRed;
    public int[][] OrigImgGreen;
    public int[][] OrigImgBlue;

    public int[][] HistEqzImgRed;              
    public int[][] HistEqzImgGreen;              
    public int[][] HistEqzImgBlue; 
    
    public int[][] GrayScaleImg;
    
    public int[][] thresholding;
    public int[][] SegmentedImg;
    public int[][] SegmentedBackGround;
    
    public double[][] co_occurence;
    public double asm;
    public double correlation;
    public double energy;
    public double homogeneity;
    public double contrast;
    public double idm;
    public double entropy;
    
    public double mse;
    public double psnr;
    
    public String Lasm;
    public String Lcorrelation;
    public String Lenergy;
    public String Lhomogeneity;
    public String Lcontrast;
    public String Lidm;
    public String Lentropy;
    
    public String Lmse;
    public String Lpsnr;
}
