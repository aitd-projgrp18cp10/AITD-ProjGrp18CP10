package projgrp18cp10;

/**
 *
 * @author ProjGrp18CP10
 */
public class ProjGrp18CP10 {

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        ProjDataBase pdb = new ProjDataBase();

		HistogramEqualization.HistEqzCalc(pdb);
		GrayScale.GrayScaleCalc(pdb);
		Thresholding.ThresholdingCalc(pdb);
		
		Features.GLCM(pdb);
		Features.ASM(pdb);
		Features.Correlation(pdb);
		Features.Energy(pdb);
		Features.Homogeneity(pdb);
		Features.Contrast(pdb);
		Features.IDM(pdb);
		Features.MSE(pdb);
		Features.PSNR(pdb);
		Features.Classify(pdb);
		
		System.out.println(" ASM value:" + pdb.asm);
        System.out.println(" Correlation value:" + pdb.correlation);
        System.out.println(" Energy value:" + pdb.energy);
        System.out.println(" Homogeneity value:" + pdb.homogeneity);
        System.out.println(" Contrast value:" + pdb.contrast);
        System.out.println(" IDM value:" + pdb.idm);
        System.out.println(" MSE value:" + pdb.mse);
        System.out.println(" PSNR value:" + pdb.psnr);

        String Decision = Features.testFunction(pdb.Lasm, pdb.Lcorrelation, pdb.Lenergy, pdb.Lhomogeneity, pdb.Lcontrast, pdb.Lidm);
        System.out.println("Decision: "+Decision);
		
    }
    
}
