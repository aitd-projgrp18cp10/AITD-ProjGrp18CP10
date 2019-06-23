package projgrp18cp10;

import static java.lang.Math.round;

/**
 *
 * @author Chinmay P.
 */
public class HistogramEqualization {
    
    // Function to Calculate Histogram Equalization Matrix for each Color
    public static void HistEqzCalc(ProjDataBase pdb)
    {
        int shades = 256;
        
        int[][] OrigImgRed = pdb.OrigImgRed;
        int[][] OrigImgGreen = pdb.OrigImgGreen;
        int[][] OrigImgBlue = pdb.OrigImgBlue;
        
        int[] HistCountRed = new int[shades];
        int[] HistCountGreen = new int[shades];
        int[] HistCountBlue = new int[shades];
        
        // Calculate Count for each Color
        for(int i=0; i < pdb.OrigImgRow; i++)
        {
            for(int j=0; j < pdb.OrigImgColumn; j++)
            {
                int Red = OrigImgRed[i][j];
                int Green = OrigImgGreen[i][j];
                int Blue = OrigImgBlue[i][j];
                
                HistCountRed[Red]++;
                HistCountGreen[Green]++;
                HistCountBlue[Blue]++;
            }
        }
        
        int[] HistCdfRed = new int[shades];
        int[] HistCdfGreen = new int[shades];
        int[] HistCdfBlue = new int[shades];
        
        int HistCdfMinRed = 0;
        int HistCdfMinGreen = 0;
        int HistCdfMinBlue = 0;
        
        // Calculate CDF for each Color
        for(int i=0,r=0,g=0,b=0; i < shades ; i++)
        {
            if(HistCountRed[i] != 0)
            {
                if(r == 0)
                {
                    HistCdfMinRed = HistCdfRed[i] = HistCountRed[i];
                    r=i;
                }
                else
                {
                    HistCdfRed[i] = HistCdfRed[r] + HistCountRed[i];
                    r=i;
                }
            }
            if(HistCountGreen[i] != 0)
            {
                if(g == 0)
                {
                    HistCdfMinGreen = HistCdfGreen[i] = HistCountGreen[i];
                    g=i;
                }
                else
                {
                    HistCdfGreen[i] = HistCdfGreen[g] + HistCountGreen[i];
                    g=i;
                }
            }
            if(HistCountBlue[i] != 0)
            {
                if(b == 0)
                {
                    HistCdfMinBlue = HistCdfBlue[i] = HistCountBlue[i];
                    b=i;
                }
                else
                {
                    HistCdfBlue[i] = HistCdfBlue[b] + HistCountBlue[i];
                    b=i;
                }
            }
        }
        
        int[] HistEqzRed = new int[shades];
        int[] HistEqzGreen = new int[shades];
        int[] HistEqzBlue = new int[shades];
        
        int LastShade = shades-1;
        int Dimension = pdb.OrigImgRow * pdb.OrigImgColumn;
        
        // Calculate Histogram Equalization Values for each Color
        for(int i=0; i < shades ; i++)
        {
            if(HistCdfRed[i] != 0)
            {
                HistEqzRed[i] = (int)round(((double)(HistCdfRed[i]-HistCdfMinRed)/(double)(Dimension-HistCdfMinRed))*LastShade);
            }
            if(HistCdfGreen[i] != 0)
            {
                HistEqzGreen[i] = (int)round(((double)(HistCdfGreen[i]-HistCdfMinGreen)/(double)(Dimension-HistCdfMinGreen))*LastShade);
            }
            if(HistCdfBlue[i] != 0)
            {
                HistEqzBlue[i] = (int)round(((double)(HistCdfBlue[i]-HistCdfMinBlue)/(double)(Dimension-HistCdfMinBlue))*LastShade);
            }
        }
        
        int[][] HistEqzImgRed = new int[pdb.OrigImgRow][pdb.OrigImgColumn];
        int[][] HistEqzImgGreen = new int[pdb.OrigImgRow][pdb.OrigImgColumn];
        int[][] HistEqzImgBlue = new int[pdb.OrigImgRow][pdb.OrigImgColumn];
        
        // Save Calculated Histogram Equalization Values for each Color
        for(int i=0; i < pdb.OrigImgRow; i++)
        {
            for(int j=0; j < pdb.OrigImgColumn; j++)
            {
                int Red = OrigImgRed[i][j];
                int Green = OrigImgGreen[i][j];
                int Blue = OrigImgBlue[i][j];
                
                HistEqzImgRed[i][j] = HistEqzRed[Red];
                HistEqzImgGreen[i][j] = HistEqzGreen[Green];
                HistEqzImgBlue[i][j] = HistEqzBlue[Blue];
            }
        }
        
        pdb.HistEqzImgRed = HistEqzImgRed;
        pdb.HistEqzImgGreen = HistEqzImgGreen;
        pdb.HistEqzImgBlue = HistEqzImgBlue;
    }
    
}
