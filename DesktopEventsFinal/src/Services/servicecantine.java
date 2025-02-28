/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entites.Menu;
import Entites.TrayIconDemo;
import Entites.User;
import Entites.abonneC;
import Utils.DataBase;
import java.awt.AWTException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author mazen
 */
public class servicecantine {
     private Connection con;
    private Statement ste;

    public servicecantine() {
        con = DataBase.getInstance().getConnection();

    }
    
    

    public void ajouterAbonné(abonneC p) throws SQLException, AWTException {
        Statement st;
        try {
           // String[] args1 = null;
            // TrayIconDemo.main(args1);
            st = con.createStatement();
            String req = "INSERT INTO `cantine`(`cin`, `type_abonement`, `dureé`, `nom`, `prenom`) VALUES  ('"+ p.getCin()+"','" + p.getType_abonement()+ "','" + p.getDureé()+ "','" + p.getNom()+ "','" + p.getPrenom()+ "')";
            
            st.executeUpdate(req);
        } catch (SQLException ex) {
            
            
        JOptionPane.showMessageDialog(null,"existe deja");
        throw ex ;
            //Logger.getLogger(servicecantine.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void afficherAbonné() {
        PreparedStatement pt;
        try {

            pt = con.prepareStatement("select * from cantine");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                System.out.println("Personne [id_c:" + rs.getInt(1) + ",cin" + rs.getString(2) +",type_abonement" + rs.getString(3) + ",dureé" + rs.getString(4)+ ",nom" + rs.getString(5)+ ",prenom" + rs.getString(6) );
            }
        } catch (SQLException ex) {
            Logger.getLogger(servicecantine.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void modifierAbonné(int id_c, String type_abonement) throws AWTException {
        try {
           // String[] args1 = null;
            // TrayIconDemo.main(args1);
            PreparedStatement pt = con.prepareStatement("update cantine set type_abonement=?where id_c=?");
            pt.setString(1, type_abonement);
            pt.setInt(2, id_c);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(servicecantine.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void supprimerAbonné(abonneC p) throws AWTException {
        PreparedStatement pt;
        try {
            String[] args1 = null;
             TrayIconDemo.main(args1);
            pt = con.prepareStatement("delete from cantine where cin=? ");
            pt.setInt(1, p.getCin());
            pt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(servicecantine.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
     public ArrayList<abonneC> getAllPAC() throws SQLException{
        ArrayList<abonneC> retour = new ArrayList<abonneC>();
        Statement stm = con.createStatement();
        abonneC per=null;
        String req = "SELECT `id_c`, `cin`, `type_abonement`, `dureé`, `nom`, `prenom` FROM `cantine`" ;
        ResultSet resultat = stm.executeQuery(req);
      while (resultat.next()) {   
          
                    
      per=new abonneC(resultat.getInt(1),resultat.getInt(2),resultat.getString(3),resultat.getString(4),resultat.getString(5),resultat.getString(6));
  
      retour.add(per);
        }
  
  return retour;
  }
     
     
     public ArrayList<Menu> getmenu() throws SQLException{
        ArrayList<Menu> retour = new ArrayList<Menu>();
        Statement stm = con.createStatement();
        Menu per=null;
        String req = "SELECT `petitdej`, `dej`, `dinner`, `jour` FROM `menu`" ;
        ResultSet resultat = stm.executeQuery(req);
      while (resultat.next()) {   
          
                    
      per=new Menu(resultat.getString(1),resultat.getString(2),resultat.getString(3),resultat.getString(4));
  
      retour.add(per);
        }
  
  return retour;
}
     
     
           public ArrayList<abonneC> rechercherNomab(String rech) throws SQLException {

        ArrayList<abonneC> off = new ArrayList<>();
           abonneC e = null;
        String req = "SELECT  `id_c`, `cin`, `type_abonement`, `dureé`, `nom`, `prenom` FROM `cantine` where nom Like '%"+rech+"%'";  
         
        Statement stm = con.createStatement();
        ResultSet rst = stm.executeQuery(req);
        

        while (rst.next()) {
                              e = new abonneC();
                
              
                e.setId_c(rst.getInt("id_c"));
                e.setCin(rst.getInt("cin"));
                e.setType_abonement(rst.getString("type_abonement"));
                e.setDureé(rst.getString("dureé"));
                e.setNom(rst.getString("nom"));
                e.setPrenom(rst.getString("prenom"));
                
            
        

             
                
                      
   abonneC per = new abonneC(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5));
  

            off.add(e);
        }
        return off;
    } 
     
}

