
package Service;

import java.awt.Point;
import java.util.Scanner;


public class PlayFairCipher {

  
    private int length = 0;
  
    private String[][] table;

    
    public String parseString(String parse) {

        parse = parse.toUpperCase();

        parse = parse.replaceAll("[^A-Z]", "");
 
        parse = parse.replace("J", "I");
        return parse;
    }
//tạo bảng table dựa trên key được input 

    public String[][] cipherTable(String key) {
  
        String[][] playfairTable = new String[5][5];
        String keyString = key + "ABCDEFGHIKLMNOPQRSTUVWXYZ";
  
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                playfairTable[i][j] = "";
            }
        }
        for (int k = 0; k < keyString.length(); k++) {
            boolean repeat = false;
            boolean used = false;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (playfairTable[i][j].equals("" + keyString.charAt(k))) {
                        repeat = true;
                    } else if (playfairTable[i][j].equals("") && !repeat && !used) {
                        playfairTable[i][j] = "" + keyString.charAt(k);
                        used = true;
                    }
                }
            }
        }
        table = playfairTable;
        return playfairTable;
    }
//cipher: mã hoá 

    public String cipher(String in) {
        length = (int) in.length() / 2 + in.length() % 2;
 
// chèn kí tự x vào chữ cái lặp nhau liên tiếp, lấy length
        for (int i = 0; i < (length-1); i++) {
            if (in.charAt(2 * i) == in.charAt(2 * i + 1)) {
                in = new StringBuffer(in).insert(2 * i + 1, 'X').toString();
                length = (int) in.length() / 2 + in.length() % 2;
            }
        }
 
//tạo mảng chứa các cặp từ
        String[] digraph = new String[length];

        for (int j = 0; j < length; j++) {
//kiểm tra độ dài bản rõ là chẵn hay lẻ  
            if (j == (length -1 ) && in.length() / 2 == (length - 1)) //if not addends X at the end of the plaintext    
            {
                in = in + "X";
            }
            digraph[j] = in.charAt(2 * j) + "" + in.charAt(2 * j + 1);
        }
 
        String out = "";
        String[] encDigraphs = new String[length];
        encDigraphs = encodeDigraph(digraph);
        for (int k = 0; k < length; k++) {
            out = out + encDigraphs[k];
        }
        return out;
    }
//---------------thuật toán mã hoá-----------------  
 

    public String[] encodeDigraph(String di[]) {
        String[] encipher = new String[length];
        for (int i = 0; i < length; i++) {
            char a = di[i].charAt(0);
            char b = di[i].charAt(1);
            int r1 = (int) getPoint(a).getX();
            int r2 = (int) getPoint(b).getX();
            int c1 = (int) getPoint(a).getY();
            int c2 = (int) getPoint(b).getY();


            if (r1 == r2) {
                c1 = (c1 + 1) % 5;
                c2 = (c2 + 1) % 5;
            }
            else if (c1 == c2) {
                r1 = (r1 + 1) % 5;
                r2 = (r2 + 1) % 5;
            }
            else {
                int temp = c1;
                c1 = c2;
                c2 = temp;
            }

            encipher[i] = table[r1][c1] + "" + table[r2][c2];
        }
        return encipher;
    }
//-----------------------thuật toán giải mã---------------------  


    public String decode(String out) {
        String decoded = "";
        for (int i = 0; i < out.length() / 2; i++) {
            char a = out.charAt(2 * i);
            char b = out.charAt(2 * i + 1);
            int r1 = (int) getPoint(a).getX();
            int r2 = (int) getPoint(b).getX();
            int c1 = (int) getPoint(a).getY();
            int c2 = (int) getPoint(b).getY();
            if (r1 == r2) {
                c1 = (c1 + 4) % 5;
                c2 = (c2 + 4) % 5;
            } else if (c1 == c2) {
                r1 = (r1 + 4) % 5;
                r2 = (r2 + 4) % 5;
            } else {
   
                int temp = c1;
                c1 = c2;
                c2 = temp;
            }
            decoded = decoded + table[r1][c1] + table[r2][c2];
        }

        return decoded;
    }
// trả về toạ độ(điểm) được lấy từ bảng mã hoá(hàng, cột)

    public Point getPoint(char c) {
        Point pt = new Point(0, 0);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (c == table[i][j].charAt(0)) {
                    pt = new Point(i, j);
                   
                }
            }
        }
        return pt;
    }

// in bảng mã hoá
    public void printTable(String[][] printTable) {
        System.out.println("Playfair Cipher Key Matrix: ");
        System.out.println();

        for (int i = 0; i < 5; i++) {
    
            for (int j = 0; j < 5; j++) {
    
                System.out.print(printTable[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
//in kết quả 

    public void printResults(String encipher, String dec) {
        System.out.print("Encrypted Message: ");
 
        System.out.println(encipher);
        System.out.println();
        System.out.print("Decrypted Message: ");

        System.out.println(dec);
    }

}
