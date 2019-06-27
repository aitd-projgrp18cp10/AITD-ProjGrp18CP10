package projgrp18cp10;

/**
 *
 * @author ProjGrp18CP10 : Chinmay , Kalpita , Amruta
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
    
    public static void Classify(ProjDataBase pdb)
    {
        double asm = pdb.asm;
        double correlation = pdb.correlation;
        double energy = pdb.energy;
        double homogeneity = pdb.homogeneity;
        double contrast = pdb.contrast;
        double idm = pdb.idm;
        
        // ASM
        if (asm >= 0 && asm <= 0.00025)
            pdb.Lasm = "1";
        else if (asm > 0.00025 && asm <= 0.0005)
            pdb.Lasm = "2";
        else if (asm > 0.0005 && asm <= 0.00075)
            pdb.Lasm = "3";
        else if (asm > 0.00075 && asm <= 0.0001)
            pdb.Lasm = "4";
        
        // Correlation
        if (correlation >0 && correlation <0.9 )  
            pdb.Lcorrelation = "2";
        else if (correlation >=0.9 && correlation <= 1) 
            pdb.Lcorrelation = "1";
        
        // Energy
        if (energy >= 0 && energy <= 0.020)
            pdb.Lenergy = "1";
        else if (energy > 0.020 && energy <= 0.040)
            pdb.Lenergy = "2";
        else if (energy > 0.040 && energy <= 0.060)
            pdb.Lenergy = "3";
        else if (energy > 0.060 && energy <= 0.080)
            pdb.Lenergy = "4";
        else if (energy > 0.080 && energy <= 0.1)
            pdb.Lenergy = "5";
        else if (energy > 0.1 && energy <= 0.12)
            pdb.Lenergy = "6";
        
        // Homogeneity
        if (homogeneity >= 0 && homogeneity <= 0.10)
            pdb.Lhomogeneity = "c";
        else if (homogeneity > 0.10 && homogeneity <= 0.20)
            pdb.Lhomogeneity = "B";
        else if (homogeneity > 0.20 && homogeneity <= 0.44)
            pdb.Lhomogeneity = "A";
        
        // Contrast
        if (contrast >= 0 && contrast <= 45) 
            pdb.Lcontrast = "1";             
        else if (contrast > 45 && contrast <= 90)
            pdb.Lcontrast = "2";
        else if (contrast > 90 && contrast <= 135)
            pdb.Lcontrast = "3";
        else if (contrast > 90 && contrast <= 135)
            pdb.Lcontrast = "4";
        else if (contrast > 90 && contrast <= 135)
            pdb.Lcontrast = "5";
        else if (contrast > 90 && contrast <= 135)
            pdb.Lcontrast = "6";
        else if (contrast > 90 && contrast <= 135)
            pdb.Lcontrast = "7";
        else if (contrast > 90 && contrast <= 135)
            pdb.Lcontrast = "8";
        else if (contrast > 90 && contrast <= 135)
            pdb.Lcontrast = "9";
        else if (contrast > 90 && contrast <= 135)
            pdb.Lcontrast = "10";
        
        // IDM
        if (idm >= 0 && idm <= 0.20)
            pdb.Lidm = "2";
        else if (idm > 0.20 && idm <= 0.40)
            pdb.Lidm = "1"; 
    }
    
    public static String testFunction(String ASM,String Correlation,String Energy,String Homogeneity,String Contrast,String IDM) 
    {
        String Decision="";
        if(Homogeneity == "c")
            Decision="Eczema";
        else if(Homogeneity == "B")
            Decision="Melanoma";
        else if(Homogeneity == "A")
            Decision="Healthy";
        return Decision;
    }

}
