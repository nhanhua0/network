/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

/**
 *
 * @author Admin
 */
public class TimViTri {

    public String TimViTri(String s1, String s2) {

        int count = 0;
        String viTri = "";

        System.out.print("Vị trí xuất hiện:");
        for (int i = 0; i < s1.length();) {
            int index = s1.indexOf(s2, i);    
            if (index >= 0) {
                count++;
                i = index + 1;
                System.out.print(index + "; ");
                viTri = viTri + String.valueOf(index) + "; ";
                
            } else {
                break;
            }
        }
        System.out.println("return: "+viTri.length());
        return viTri;
    }

}
