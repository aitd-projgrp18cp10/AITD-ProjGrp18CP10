package projgrp18cp10;

import static java.lang.Math.round;

/**
 *
 * @author Shreya
 */
public class GrayScale {
    
    // Function to Calculate Gray Scale Matrix
    public static void GrayScaleCalc(ProjDataBase pdb)
    {
        int[][] HistEqzImgRed = pdb.HistEqzImgRed;
        int[][] HistEqzImgGreen = pdb.HistEqzImgGreen;
        int[][] HistEqzImgBlue = pdb.HistEqzImgBlue;
        
        int[][] GrayScaleImg = new int[pdb.OrigImgRow][pdb.OrigImgColumn];
        
        // Calculate Count for each Color
        for(int i=0; i < pdb.OrigImgRow; i++)
        {
            for(int j=0; j < pdb.OrigImgColumn; j++)
            {
                GrayScaleImg[i][j] = (int) round((HistEqzImgRed[i][j]*0.30)+(HistEqzImgGreen[i][j]*0.59)+(HistEqzImgBlue[i][j]*0.11));
            }
        }
        
        pdb.GrayScaleImg = GrayScaleImg;
    }
	
}
