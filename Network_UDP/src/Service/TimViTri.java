/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

/**
 * @author Admin
 */
public class Stuff {

    private String originalValue;
    private String searchValue;

    public TimViTri(String originalValue, String searchValue) {
        this.originalValue = originalValue;
        this.searchValue = searchValue;
    }

    public String TimViTri() {

        int count = 0;
        String viTri = "";

        System.out.print("Vị trí xuất hiện:");
        for (int i = 0; i < originalValue.length(); ) {
            int index = originalValue.indexOf(searchValue, i);
            if (index >= 0) {
                count++;
                i = index + 1;
                System.out.print(index + "; ");
                viTri = viTri + String.valueOf(index) + "; ";

            } else {
                break;
            }
        }
        return viTri;
    }

}
