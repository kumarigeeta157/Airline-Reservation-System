import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JOptionPane;

class button3 implements ActionListener {
    DomesticFlight type;
    LoginPage type1;

    button3(DomesticFlight var1, LoginPage var2) {
        this.type = var1;
        this.type1 = var2;
    }

    public void actionPerformed(ActionEvent var1) {
        String var2 = (String)this.type.CBFrom.getSelectedItem();
        String var3 = (String)this.type.CBTo.getSelectedItem();
        String var4 = (String)this.type.CBClass.getSelectedItem();
        String var5 = this.type.TFBookingDate.getText();
        Integer var6 = 0;
        String var7 = "";
        Integer var8 = Integer.parseInt((String)this.type.CBAdult.getSelectedItem());
        Integer var9 = Integer.parseInt((String)this.type.CBChildren.getSelectedItem());
        Integer var10 = Integer.parseInt((String)this.type.CBInfant.getSelectedItem());
        int var11 = 0;
        if (var4.equals("Economic")) {
            try {
                while(var11 < 20) {
                    if (this.type1.row1[var11][1].equals(var3)) {
                        var6 = Integer.parseInt((String)this.type1.row1[var11][2]);
                        var7 = (String)this.type1.row1[var11][3];
                        break;
                    }

                    ++var11;
                }
            } catch (Exception var29) {
                JOptionPane.showMessageDialog((Component)null, "You have no rights to access");
                System.exit(0);
            }
        } else {
            try {
                while(var11 < 20) {
                    if (this.type1.row1[var11][1].equals(var3)) {
                        var6 = Integer.parseInt((String)this.type1.row3[var11][2]);
                        var7 = (String)this.type1.row3[var11][3];
                        break;
                    }

                    ++var11;
                }
            } catch (Exception var30) {
                JOptionPane.showMessageDialog((Component)null, "You have no rights to access it");
                System.exit(0);
            }
        }

        this.type.setTitle(var6 + " " + var7);
        var6 = var6 * var8 + var6 * (var9 / 2);
        int var12 = 0;
        int var13 = 0;
        String[] var14 = new String[1250];
        String[] var15 = new String[1250];
        String[] var16 = new String[1250];
        String[] var17 = new String[1250];
        String[] var18 = new String[1250];
        Integer[] var19 = new Integer[1250];
        Integer[] var20 = new Integer[1250];
        Integer[] var21 = new Integer[1250];
        Integer[] var22 = new Integer[1250];

        try {
            ObjectInputStream var24 = new ObjectInputStream(new FileInputStream("save2"));

            Save2 var23;
            do {
                var23 = (Save2)var24.readObject();
                var14[var12] = var23.sFrom;
                var15[var12] = var23.sTo;
                var16[var12] = var23.sClass;
                var17[var12] = var23.sBookingDate;
                var18[var12] = var23.sTime;
                var19[var12] = var23.iAdult;
                var20[var12] = var23.iChildren;
                var21[var12] = var23.iInfant;
                var22[var12] = var23.iPrice;
                ++var12;
                if (var23.sBookingDate.equals(var5) && var23.sTo.equals(var3)) {
                    var13 = var13 + var23.iAdult + var23.iChildren + var23.iInfant;
                }
            } while(var23 != null);

            var24.close();
        } catch (Exception var28) {
        }

        var13 = var13 + var8 + var9 + var10;
        if (var13 > 60) {
            JOptionPane.showMessageDialog((Component)null, "Seats are full. Sorry!");
        } else {
            int var31 = JOptionPane.showConfirmDialog((Component)null, "Seats available. Do you want to Book now?");
            if (var31 == 0) {
                new PrintTicket1(var2, var3, var4, var8, var9, var10, var5, var6, var7);

                try {
                    Save2 var32 = new Save2(var2, var3, var4, var8, var9, var10, var5, var6, var7);
                    ObjectOutputStream var25 = new ObjectOutputStream(new FileOutputStream("save2"));

                    for(var11 = 0; var11 < var12; ++var11) {
                        Save2 var26 = new Save2(var14[var11], var15[var11], var16[var11], var19[var11], var20[var11], var21[var11], var17[var11], var22[var11], var18[var11]);
                        var25.writeObject(var26);
                        System.out.println(var26);
                    }

                    var25.writeObject(var32);
                    var25.close();
                } catch (Exception var27) {
                    System.out.println(var27);
                }
            }
        }

    }
}
