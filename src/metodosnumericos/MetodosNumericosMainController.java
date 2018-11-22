package metodosnumericos;


import java.awt.HeadlessException;
import java.awt.event.WindowEvent;
import java.sql.*;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.TimerTask;
import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Jorge
 */
public class MetodosNumericosMainController implements Initializable {
    
    @FXML
    public Button btnSalirPrincipal,btnIniciarSesionPrincipal,btnEquipo,btnVolver,btnIniciarSesion,btnRegistro,btnCerrarSesion,btnRegistrarse,
            btnSiguiente, btnAnterior, btnVolverIMain, btnInterpolacion, btnEcNoLin, btnEcLin, btnMinCuad, btnInt, btnEcDif;
    public PasswordField textPass, inicioPassword;
    public TextField inicioUsuario,textUsuario,textNombre,textApePat,textApeMat,textTel,textEmail;
    public Label label;
    public TextField textfield;
    
    Stage stage = null; 
    Parent root = null;
    String ruta = "jdbc:ucanaccess://C:\\Users\\Jorge\\Desktop\\Base.accdb";
    public static String usuario;
    public static String password;
    public static Ventana ti = new Ventana();
    public static int intentos = 0;
    
    @FXML
    public void Cronometro(){
  ti.setVisible(true);
  ti.setAlwaysOnTop(true);
  ti.getDefaultCloseOperation();
  ti.setDefaultCloseOperation(Ventana.DO_NOTHING_ON_CLOSE);
  ti.resetSeg();
  ti.setResizable(false);
  ti.resetHora();
  ti.resetMin();
  ti.setLocation(800, 500);
  ti.btn_comenzar.doClick();
    }
    
    public void Cronometro2(){
    int Hora = ti.getHora();
    int Min = ti.getMin();
    int Seg = ti.getSeg();
    ti.parar();
    JOptionPane.showMessageDialog(ti,"Respuesta Correcta.\n Tiempo: "+Hora+":"+Min+":"+Seg+"\nIntentos: "+intentos);
    intentos =0;
    ti.setVisible(false);
    ti.btn_comenzar.setEnabled(true);
    }
    
    
    
     @FXML
 public void handleButtonAction(ActionEvent event) throws IOException, InterruptedException, SQLException{
     if(event.getSource()==btnIniciarSesionPrincipal){       
        stage=(Stage) btnIniciarSesionPrincipal.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("IniciarSesion.fxml"));
      }
     else if(event.getSource()==btnEquipo){
       stage=(Stage) btnEquipo.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("Equipos.fxml"));
     }
     //=========================================BASE DE DATOS==================================================
     else if(event.getSource()==btnIniciarSesion){
         try{
         usuario = inicioUsuario.getText();
         password = inicioPassword.getText();
         String url = "jdbc:obdc:manuu";
         Connection con = DriverManager.getConnection(ruta);
         Statement x = con.createStatement();
         String sql = ("SELECT usu_usu, pas_usu FROM Usuario WHERE usu_usu = '"+ usuario +"' AND pas_usu = '"+password+"'");
         ResultSet rs = x.executeQuery(sql);
         int count=0;
         while(rs.next()){
             count++;
         }
         if(count == 1){
             JOptionPane.showMessageDialog(null, "Bienvenido "+ usuario);
             stage=(Stage) btnIniciarSesion.getScene().getWindow();
             root = FXMLLoader.load(getClass().getResource("IndiceMAIN.fxml"));
         }
         else{
             JOptionPane.showMessageDialog(null, "El nombre de usuario y/o contraseña son incorrectos.");
         }
         }
         catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
     }
  //=========================================BASE DE DATOS==================================================
     else if(event.getSource()==btnRegistro){
       stage=(Stage) btnRegistro.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("Registro.fxml"));
     }
     else if(event.getSource()==btnCerrarSesion){
       stage=(Stage) btnCerrarSesion.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("IniciarSesion.fxml"));
     }
    //=========================================BASE DE DATOS==================================================
     else if(event.getSource()==btnRegistrarse){
       try {
Connection con = DriverManager.getConnection(ruta);
Statement x = con.createStatement();
String sql = "insert into Usuario(usu_usu,nom_usu,ape_pat,ape_mat,tel_usu, ema_usu, pas_usu)values"+"('"+ this.textUsuario.getText()+ "','" + this.textNombre.getText()+"','"+ this.textApePat.getText() +"','"+ this.textApeMat.getText()+"','"+this.textTel.getText()+"','"+this.textEmail.getText()+"','"+this.textPass.getText()+"')"; 
x.executeUpdate(sql);
JOptionPane.showMessageDialog(null,"Registrado Exitosamente");
        }
        catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Error en "+ e);
        }
         stage=(Stage) btnRegistrarse.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("IniciarSesion.fxml"));
     }
     //=========================================BASE DE DATOS==================================================
     else if(event.getSource()==btnSiguiente){
       stage=(Stage) btnSiguiente.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("Indice2.fxml"));
     }
     else if(event.getSource()==btnAnterior){
       stage=(Stage) btnAnterior.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("IndiceMAIN.fxml"));
  ti.parar();
  ti.setVisible(false);
  ti.btn_comenzar.setEnabled(true);
  intentos =0;
     }
     else if(event.getSource()==btnVolverIMain){
       stage=(Stage) btnVolverIMain.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("IndiceMAIN.fxml"));
     }
     else if(event.getSource()==btnInterpolacion){
       stage=(Stage) btnInterpolacion.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("1Interpolacion.fxml"));
     }
     else if(event.getSource()==btnEcNoLin){
       stage=(Stage) btnEcNoLin.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("2EcNoLineales.fxml"));
     }
     else if(event.getSource()==btnEcLin){
       stage=(Stage) btnEcLin.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("3EcLineales.fxml"));
     }
     else if(event.getSource()==btnMinCuad){
       stage=(Stage) btnMinCuad.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("4MinimosCuadrados.fxml"));
     }
     else if(event.getSource()==btnInt){
       stage=(Stage) btnInt.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("5Integracion.fxml"));
     }
     else if(event.getSource()==btnEcDif){
       stage=(Stage) btnEcDif.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("6EcDif.fxml"));
     }
     else{
         stage=(Stage) btnVolver.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("MetodosNumericosMain.fxml"));
     }
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
    }
    
 
 //BOTONES DEL INDICE
 @FXML
 public TextField txtfRespuesta, txtfRespuesta2, txtfRespuesta3, txtfRespuesta4;
 public static Thread thread= new Thread();
 public Button
         btn11, btn11Diagrama, btn11Ejercicio,btn11R1,btn11R2,
         btn12, btn12Diagrama, btn12Ejercicio,btn12R1,btn12R2,
         btn13, btn13Diagrama, btn13Ejercicio,btn13R1,btn13R2,
         btn14, btn14Diagrama, btn14Ejercicio,btn14R1,btn14R2,
         btn15, btn15Diagrama, btn15Ejercicio,btn15R1,btn15R2,
         btn21, btn21Diagrama, btn21Ejercicio,btn21R1,btn21R2,
         btn22, btn22Diagrama, btn22Ejercicio,btn22R1,btn22R2,
         btn23, btn23Diagrama, btn23Ejercicio,btn23R1,btn23R2,
         btn24, btn24Diagrama, btn24Ejercicio,btn24R1,btn24R2,
         btn25, btn25Diagrama, btn25Ejercicio,btn25R1,btn25R2,
         btn26, btn26Diagrama, btn26Ejercicio,btn26R1,btn26R2,
         btn31, btn31Diagrama, btn31Ejercicio,btn31R1,btn31R2,
         btn32, btn32Diagrama, btn32Ejercicio,btn32R1,btn32R2,
         btn33, btn33Diagrama, btn33Ejercicio,btn33R1,btn33R2,
         btn34, btn34Diagrama, btn34Ejercicio,btn34R1,btn34R2,
         btn35, btn35Diagrama, btn35Ejercicio,btn35R1,btn35R2,
         btn36, btn36Diagrama, btn36Ejercicio,btn36R1,btn36R2,
         btn41, btn41Diagrama, btn41Ejercicio,btn41R1,btn41R2,
         btn42, btn42Diagrama, btn42Ejercicio,btn42R1,btn42R2,
         btn43, btn43Diagrama, btn43Ejercicio,btn43R1,btn43R2,
         btn44, btn44Diagrama, btn44Ejercicio,btn44R1,btn44R2,
         btn45, btn45Diagrama, btn45Ejercicio,btn45R1,btn45R2,
         btn51, btn51Diagrama, btn51Ejercicio,btn51R1,btn51R2,
         btn52, btn52Diagrama, btn52Ejercicio,btn52R1,btn52R2,
         btn53, btn53Diagrama, btn53Ejercicio,btn53R1,btn53R2,
         btn54, btn54Diagrama, btn54Ejercicio,btn54R1,btn54R2,
         btn55, btn55Diagrama, btn55Ejercicio,btn55R1,btn55R2,
         btn56, btn56Diagrama, btn56Ejercicio,btn56R1,btn56R2,
         btn61, btn61Diagrama, btn61Ejercicio,btn61R1,btn61R2,
         btn62, btn62Diagrama, btn62Ejercicio,btn62R1,btn62R2,
         btn63, btn63Diagrama, btn63Ejercicio,btn63R1,btn63R2,
         btn64, btn64Diagrama, btn64Ejercicio,btn64R1,btn64R2,
         btn65, btn65Diagrama, btn65Ejercicio,btn65R1,btn65R2,
         btn66, btn66Diagrama, btn66Ejercicio,btn66R1,btn66R2,
         btn67, btn67Diagrama, btn67Ejercicio,btn67R1,btn67R2,
         btn68, btn68Diagrama, btn68Ejercicio,btn68R1,btn68R2,
         btn69, btn69Diagrama, btn69Ejercicio,btn69R1,btn69R2,
         btn610, btn610Diagrama, btn610Ejercicio,btn610R1,btn610R2,
         btn611, btn611Diagrama, btn611Ejercicio, btn611R1,btn611R2
         ;
 
 @FXML
public void handleIndices(ActionEvent event) throws IOException {
    if(event.getSource()==btn11){       
        stage=(Stage) btn11.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/Interpolacion/1.1Teoria.fxml"));
      }
    else if(event.getSource()==btn11Diagrama){
       stage=(Stage) btn11Diagrama.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/Interpolacion/1.1Diagrama.fxml"));
     }
    else if(event.getSource()==btn11Ejercicio){
        int aleatorio = (int)(Math.random()*2);
        if(aleatorio==0){
       stage=(Stage) btn11Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/Interpolacion/1.1E1.fxml"));
  Cronometro();
        }
        else
        {
        stage=(Stage) btn11Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/Interpolacion/1.1E2.fxml"));
  Cronometro();
        }
     }
    ////===================================================================================
    else if(event.getSource()==btn12){       
        stage=(Stage) btn12.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/Interpolacion/1.2Teoria.fxml"));
      }
    else if(event.getSource()==btn12Diagrama){
       stage=(Stage) btn12Diagrama.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/Interpolacion/1.2Diagrama.fxml"));
     }
    else if(event.getSource()==btn12Ejercicio){
        int aleatorio = (int)(Math.random()*2);
        if(aleatorio==0){
       stage=(Stage) btn12Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/Interpolacion/1.2E1.fxml"));
  Cronometro();
        }
        else
        {
        stage=(Stage) btn12Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/Interpolacion/1.2E2.fxml"));
  Cronometro();
        }
     }
    ////===================================================================================
    else if(event.getSource()==btn13){       
        stage=(Stage) btn13.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/Interpolacion/1.3Teoria.fxml"));
      }
    else if(event.getSource()==btn13Diagrama){
       stage=(Stage) btn13Diagrama.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/Interpolacion/1.3Diagrama.fxml"));
     }
    else if(event.getSource()==btn13Ejercicio){
        int aleatorio = (int)(Math.random()*2);
        if(aleatorio==0){
       stage=(Stage) btn13Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/Interpolacion/1.3E1.fxml"));
  Cronometro();
        }
        else
        {
        stage=(Stage) btn13Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/Interpolacion/1.3E2.fxml"));
  Cronometro();
        }
     }
    ////===================================================================================
    else if(event.getSource()==btn14){       
        stage=(Stage) btn14.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/Interpolacion/1.4Teoria.fxml"));
      }
    else if(event.getSource()==btn14Diagrama){
       stage=(Stage) btn14Diagrama.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/Interpolacion/1.4Diagrama.fxml"));
     }
    else if(event.getSource()==btn14Ejercicio){
        int aleatorio = (int)(Math.random()*2);
        if(aleatorio==0){
       stage=(Stage) btn14Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/Interpolacion/1.4E1.fxml"));
  Cronometro();
        }
        else
        {
        stage=(Stage) btn14Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/Interpolacion/1.4E2.fxml"));
  Cronometro();
        }
     }
    ////===================================================================================
    else if(event.getSource()==btn15){       
        stage=(Stage) btn15.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/Interpolacion/1.5Teoria.fxml"));
      }
    else if(event.getSource()==btn15Diagrama){
       stage=(Stage) btn15Diagrama.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/Interpolacion/1.5Diagrama.fxml"));
     }
    else if(event.getSource()==btn15Ejercicio){
        int aleatorio = (int)(Math.random()*2);
        if(aleatorio==0){
       stage=(Stage) btn15Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/Interpolacion/1.5E1.fxml"));
  Cronometro();
        }
        else
        {
        stage=(Stage) btn15Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/Interpolacion/1.5E2.fxml"));
  Cronometro();
        }
     }
    ////===================================================================================
    else if(event.getSource()==btn21){       
        stage=(Stage) btn21.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcuacionesNoLineales/2.1Teoria.fxml"));
      }
    else if(event.getSource()==btn21Diagrama){
       stage=(Stage) btn21Diagrama.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcuacionesNoLineales/2.1Diagrama.fxml"));
     }
    else if(event.getSource()==btn21Ejercicio){
        int aleatorio = (int)(Math.random()*2);
        if(aleatorio==0){
       stage=(Stage) btn21Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcuacionesNoLineales/2.1E1.fxml"));
  Cronometro();
        }
        else
        {
        stage=(Stage) btn21Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcuacionesNoLineales/2.1E2.fxml"));
  Cronometro();
        }
     }
    ////===================================================================================
    else if(event.getSource()==btn22){       
        stage=(Stage) btn22.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcuacionesNoLineales/2.2Teoria.fxml"));
      }
    else if(event.getSource()==btn22Diagrama){
       stage=(Stage) btn22Diagrama.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcuacionesNoLineales/2.2Diagrama.fxml"));
     }
    else if(event.getSource()==btn22Ejercicio){
        int aleatorio = (int)(Math.random()*2);
        if(aleatorio==0){
       stage=(Stage) btn22Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcuacionesNoLineales/2.2E1.fxml"));
  Cronometro();
        }
        else
        {
        stage=(Stage) btn22Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcuacionesNoLineales/2.2E2.fxml"));
  Cronometro();
        }
     }
    ////===================================================================================
    else if(event.getSource()==btn23){       
        stage=(Stage) btn23.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcuacionesNoLineales/2.3Teoria.fxml"));
      }
    else if(event.getSource()==btn23Diagrama){
       stage=(Stage) btn23Diagrama.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcuacionesNoLineales/2.3Diagrama.fxml"));
     }
    else if(event.getSource()==btn23Ejercicio){
        int aleatorio = (int)(Math.random()*2);
        if(aleatorio==0){
       stage=(Stage) btn23Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcuacionesNoLineales/2.3E1.fxml"));
  Cronometro();
        }
        else
        {
        stage=(Stage) btn23Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcuacionesNoLineales/2.3E2.fxml"));
  Cronometro();
        }
     }
    ////===================================================================================
    else if(event.getSource()==btn24){       
        stage=(Stage) btn24.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcuacionesNoLineales/2.4Teoria.fxml"));
      }
    else if(event.getSource()==btn24Diagrama){
       stage=(Stage) btn24Diagrama.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcuacionesNoLineales/2.4Diagrama.fxml"));
     }
    else if(event.getSource()==btn24Ejercicio){
        int aleatorio = (int)(Math.random()*2);
        if(aleatorio==0){
       stage=(Stage) btn24Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcuacionesNoLineales/2.4E1.fxml"));
  Cronometro();
        }
        else
        {
        stage=(Stage) btn24Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcuacionesNoLineales/2.4E2.fxml"));
  Cronometro();
        }
     }
    ////===================================================================================
    else if(event.getSource()==btn25){       
        stage=(Stage) btn25.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcuacionesNoLineales/2.5Teoria.fxml"));
      }
    else if(event.getSource()==btn25Diagrama){
       stage=(Stage) btn25Diagrama.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcuacionesNoLineales/2.5Diagrama.fxml"));
     }
    else if(event.getSource()==btn25Ejercicio){
        int aleatorio = (int)(Math.random()*2);
        if(aleatorio==0){
       stage=(Stage) btn25Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcuacionesNoLineales/2.5E1.fxml"));
  Cronometro();
        }
        else
        {
        stage=(Stage) btn25Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcuacionesNoLineales/2.5E2.fxml"));
  Cronometro();
        }
     }
    //====================================================================
    else if(event.getSource()==btn26){       
        stage=(Stage) btn26.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcuacionesNoLineales/2.6Teoria.fxml"));
      }
    else if(event.getSource()==btn26Diagrama){
       stage=(Stage) btn26Diagrama.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcuacionesNoLineales/2.6Diagrama.fxml"));
     }
    else if(event.getSource()==btn26Ejercicio){
        int aleatorio = (int)(Math.random()*2);
        if(aleatorio==0){
       stage=(Stage) btn26Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcuacionesNoLineales/2.6E1.fxml"));
  Cronometro();
        }
        else
        {
        stage=(Stage) btn26Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcuacionesNoLineales/2.6E2.fxml"));
  Cronometro();
        }
     }
    ////===================================================================================
    else if(event.getSource()==btn31){       
        stage=(Stage) btn31.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcuacionesLineales/3.1Teoria.fxml"));
      }
    else if(event.getSource()==btn31Diagrama){
       stage=(Stage) btn31Diagrama.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcuacionesLineales/3.1Diagrama.fxml"));
     }
    else if(event.getSource()==btn31Ejercicio){
        int aleatorio = (int)(Math.random()*2);
        if(aleatorio==0){
       stage=(Stage) btn31Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcuacionesLineales/3.1E1.fxml"));
  Cronometro();
        }
        else
        {
        stage=(Stage) btn31Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcuacionesLineales/3.1E2.fxml"));
  Cronometro();
        }
     }
    ////===================================================================================
    else if(event.getSource()==btn32){       
        stage=(Stage) btn32.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcuacionesLineales/3.2Teoria.fxml"));
      }
    else if(event.getSource()==btn32Diagrama){
       stage=(Stage) btn32Diagrama.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcuacionesLineales/3.2Diagrama.fxml"));
     }
    else if(event.getSource()==btn32Ejercicio){
        int aleatorio = (int)(Math.random()*2);
        if(aleatorio==0){
       stage=(Stage) btn32Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcuacionesLineales/3.2E1.fxml"));
  Cronometro();
        }
        else
        {
        stage=(Stage) btn32Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcuacionesLineales/3.2E2.fxml"));
  Cronometro();
        }
     }
    ////===================================================================================
    else if(event.getSource()==btn33){       
        stage=(Stage) btn33.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcuacionesLineales/3.3Teoria.fxml"));
      }
    else if(event.getSource()==btn33Diagrama){
       stage=(Stage) btn33Diagrama.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcuacionesLineales/3.3Diagrama.fxml"));
     }
    else if(event.getSource()==btn33Ejercicio){
        int aleatorio = (int)(Math.random()*2);
        if(aleatorio==0){
       stage=(Stage) btn33Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcuacionesLineales/3.3E1.fxml"));
  Cronometro();
        }
        else
        {
        stage=(Stage) btn33Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcuacionesLineales/3.3E2.fxml"));
  Cronometro();
        }
     }
    ////===================================================================================
    else if(event.getSource()==btn34){       
        stage=(Stage) btn34.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcuacionesLineales/3.4Teoria.fxml"));
      }
    else if(event.getSource()==btn34Diagrama){
       stage=(Stage) btn34Diagrama.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcuacionesLineales/3.4Diagrama.fxml"));
     }
    else if(event.getSource()==btn34Ejercicio){
        int aleatorio = (int)(Math.random()*2);
        if(aleatorio==0){
       stage=(Stage) btn34Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcuacionesLineales/3.4E1.fxml"));
  Cronometro();
        }
        else
        {
        stage=(Stage) btn34Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcuacionesLineales/3.4E2.fxml"));
  Cronometro();
        }
     }
    ////===================================================================================
    else if(event.getSource()==btn35){       
        stage=(Stage) btn35.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcuacionesLineales/3.5Teoria.fxml"));
      }
    else if(event.getSource()==btn35Diagrama){
       stage=(Stage) btn35Diagrama.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcuacionesLineales/3.5Diagrama.fxml"));
     }
    else if(event.getSource()==btn35Ejercicio){
        int aleatorio = (int)(Math.random()*2);
        if(aleatorio==0){
       stage=(Stage) btn35Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcuacionesLineales/3.5E1.fxml"));
  Cronometro();
        }
        else
        {
        stage=(Stage) btn35Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcuacionesLineales/3.5E2.fxml"));
  Cronometro();
        }
     }
    ////===================================================================================
    else if(event.getSource()==btn36){       
        stage=(Stage) btn36.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcuacionesLineales/3.6Teoria.fxml"));
      }
    else if(event.getSource()==btn36Diagrama){
       stage=(Stage) btn36Diagrama.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcuacionesLineales/3.6Diagrama.fxml"));
     }
    else if(event.getSource()==btn36Ejercicio){
        int aleatorio = (int)(Math.random()*2);
        if(aleatorio==0){
       stage=(Stage) btn36Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcuacionesLineales/3.6E1.fxml"));
  Cronometro();
        }
        else
        {
        stage=(Stage) btn36Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcuacionesLineales/3.6E2.fxml"));
  Cronometro();
        }
     }
    ////===================================================================================
    else if(event.getSource()==btn41){       
        stage=(Stage) btn41.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/MinimosCuadrados/4.1Teoria.fxml"));
      }
    else if(event.getSource()==btn41Diagrama){
       stage=(Stage) btn41Diagrama.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/MinimosCuadrados/4.1Diagrama.fxml"));
     }
    else if(event.getSource()==btn41Ejercicio){
        int aleatorio = (int)(Math.random()*2);
        if(aleatorio==0){
       stage=(Stage) btn41Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/MinimosCuadrados/4.1E1.fxml"));
  Cronometro();
        }
        else
        {
        stage=(Stage) btn41Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/MinimosCuadrados/4.1E2.fxml"));
  Cronometro();
        }
     }
    ////===================================================================================
    else if(event.getSource()==btn42){       
        stage=(Stage) btn42.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/MinimosCuadrados/4.2Teoria.fxml"));
      }
    else if(event.getSource()==btn42Diagrama){
       stage=(Stage) btn42Diagrama.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/MinimosCuadrados/4.2Diagrama.fxml"));
     }
    else if(event.getSource()==btn42Ejercicio){
        int aleatorio = (int)(Math.random()*2);
        if(aleatorio==0){
       stage=(Stage) btn42Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/MinimosCuadrados/4.2E1.fxml"));
  Cronometro();
        }
        else
        {
        stage=(Stage) btn42Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/MinimosCuadrados/4.2E2.fxml"));
  Cronometro();
        }
     }
    ////===================================================================================
    else if(event.getSource()==btn43){       
        stage=(Stage) btn43.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/MinimosCuadrados/4.3Teoria.fxml"));
      }
    else if(event.getSource()==btn43Diagrama){
       stage=(Stage) btn43Diagrama.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/MinimosCuadrados/4.3Diagrama.fxml"));
     }
    else if(event.getSource()==btn43Ejercicio){
        int aleatorio = (int)(Math.random()*2);
        if(aleatorio==0){
       stage=(Stage) btn43Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/MinimosCuadrados/4.3E1.fxml"));
  Cronometro();
        }
        else
        {
        stage=(Stage) btn43Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/MinimosCuadrados/4.3E2.fxml"));
  Cronometro();
        }
     }
    ////===================================================================================
    else if(event.getSource()==btn44){       
        stage=(Stage) btn44.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/MinimosCuadrados/4.4Teoria.fxml"));
      }
    else if(event.getSource()==btn44Diagrama){
       stage=(Stage) btn44Diagrama.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/MinimosCuadrados/4.4Diagrama.fxml"));
     }
    else if(event.getSource()==btn44Ejercicio){
        int aleatorio = (int)(Math.random()*2);
        if(aleatorio==0){
       stage=(Stage) btn44Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/MinimosCuadrados/4.4E1.fxml"));
  Cronometro();
        }
        else
        {
        stage=(Stage) btn44Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/MinimosCuadrados/4.4E2.fxml"));
  Cronometro();
        }
     }
    ////===================================================================================
    else if(event.getSource()==btn45){       
        stage=(Stage) btn45.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/MinimosCuadrados/4.5Teoria.fxml"));
      }
    else if(event.getSource()==btn45Diagrama){
       stage=(Stage) btn45Diagrama.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/MinimosCuadrados/4.5Diagrama.fxml"));
     }
    else if(event.getSource()==btn45Ejercicio){
        int aleatorio = (int)(Math.random()*2);
        if(aleatorio==0){
       stage=(Stage) btn45Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/MinimosCuadrados/4.5E1.fxml"));
  Cronometro();
        }
        else
        {
        stage=(Stage) btn45Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/MinimosCuadrados/4.5E2.fxml"));
  Cronometro();
        }
     }
    ////===================================================================================
    else if(event.getSource()==btn51){       
        stage=(Stage) btn51.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/Integracion/5.1Teoria.fxml"));
      }
    else if(event.getSource()==btn51Diagrama){
       stage=(Stage) btn51Diagrama.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/Integracion/5.1Diagrama.fxml"));
     }
    else if(event.getSource()==btn51Ejercicio){
        int aleatorio = (int)(Math.random()*2);
        if(aleatorio==0){
       stage=(Stage) btn51Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/Integracion/5.1E1.fxml"));
  Cronometro();
        }
        else
        {
        stage=(Stage) btn51Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/Integracion/5.1E2.fxml"));
  Cronometro();
        }
     }
    ////===================================================================================
    else if(event.getSource()==btn52){       
        stage=(Stage) btn52.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/Integracion/5.2Teoria.fxml"));
      }
    else if(event.getSource()==btn52Diagrama){
       stage=(Stage) btn52Diagrama.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/Integracion/5.2Diagrama.fxml"));
     }
    else if(event.getSource()==btn52Ejercicio){
        int aleatorio = (int)(Math.random()*2);
        if(aleatorio==0){
       stage=(Stage) btn52Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/Integracion/5.2E1.fxml"));
  Cronometro();
        }
        else
        {
        stage=(Stage) btn52Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/Integracion/5.2E2.fxml"));
  Cronometro();
        }
     }
    ////===================================================================================
    else if(event.getSource()==btn53){       
        stage=(Stage) btn53.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/Integracion/5.3Teoria.fxml"));
      }
    else if(event.getSource()==btn53Diagrama){
       stage=(Stage) btn53Diagrama.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/Integracion/5.3Diagrama.fxml"));
     }
    else if(event.getSource()==btn53Ejercicio){
        int aleatorio = (int)(Math.random()*2);
        if(aleatorio==0){
       stage=(Stage) btn53Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/Integracion/5.3E1.fxml"));
  Cronometro();
        }
        else
        {
        stage=(Stage) btn53Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/Integracion/5.3E2.fxml"));
  Cronometro();
        }
     }
    ////===================================================================================
    else if(event.getSource()==btn54){       
        stage=(Stage) btn54.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/Integracion/5.4Tabla1.fxml"));
      }
    else if(event.getSource()==btn54Diagrama){
       stage=(Stage) btn54Diagrama.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/Integracion/5.4Tabla2.fxml"));
     }
    else if(event.getSource()==btn54Ejercicio){
       stage=(Stage) btn54Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/Integracion/5.4Tabla1.fxml"));
        }
    ////===================================================================================
    else if(event.getSource()==btn55){       
        stage=(Stage) btn55.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/Integracion/5.5Teoria.fxml"));
      }
    else if(event.getSource()==btn55Diagrama){
       stage=(Stage) btn55Diagrama.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/Integracion/5.5Diagrama.fxml"));
     }
    else if(event.getSource()==btn55Ejercicio){
        int aleatorio = (int)(Math.random()*2);
        if(aleatorio==0){
       stage=(Stage) btn55Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/Integracion/5.5E1.fxml"));
  Cronometro();
        }
        else
        {
        stage=(Stage) btn55Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/Integracion/5.5E2.fxml"));
  Cronometro();
        }
     }
    ////===================================================================================
    else if(event.getSource()==btn56){       
        stage=(Stage) btn56.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/Integracion/5.6Teoria.fxml"));
      }
    else if(event.getSource()==btn56Diagrama){
       stage=(Stage) btn56Diagrama.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/Integracion/5.6Diagrama.fxml"));
     }
    else if(event.getSource()==btn56Ejercicio){
        int aleatorio = (int)(Math.random()*2);
        if(aleatorio==0){
       stage=(Stage) btn56Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/Integracion/5.6E1.fxml"));
  Cronometro();
        }
        else
        {
        stage=(Stage) btn56Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/Integracion/5.6E2.fxml"));
  Cronometro();
        }
     }
    ////===================================================================================
    else if(event.getSource()==btn61){       
        stage=(Stage) btn61.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcDifOrdinarias/6.1Teoria.fxml"));
      }
    else if(event.getSource()==btn61Diagrama){
       stage=(Stage) btn61Diagrama.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcDifOrdinarias/6.1Diagrama.fxml"));
     }
    else if(event.getSource()==btn61Ejercicio){
        int aleatorio = (int)(Math.random()*2);
        if(aleatorio==0){
       stage=(Stage) btn61Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcDifOrdinarias/6.1E1.fxml"));
  Cronometro();
        }
        else
        {
        stage=(Stage) btn61Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcDifOrdinarias/6.1E2.fxml"));
  Cronometro();
        }
     }
    ////===================================================================================
    else if(event.getSource()==btn62){       
        stage=(Stage) btn62.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcDifOrdinarias/6.2Teoria.fxml"));
      }
    else if(event.getSource()==btn62Diagrama){
       stage=(Stage) btn62Diagrama.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcDifOrdinarias/6.2Diagrama.fxml"));
     }
    else if(event.getSource()==btn62Ejercicio){
        int aleatorio = (int)(Math.random()*2);
        if(aleatorio==0){
       stage=(Stage) btn62Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcDifOrdinarias/6.2E1.fxml"));
  Cronometro();
        }
        else
        {
        stage=(Stage) btn62Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcDifOrdinarias/6.2E2.fxml"));
  Cronometro();
        }
     }
    ////===================================================================================
    else if(event.getSource()==btn63){       
        stage=(Stage) btn63.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcDifOrdinarias/6.3Teoria.fxml"));
      }
    else if(event.getSource()==btn63Diagrama){
       stage=(Stage) btn63Diagrama.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcDifOrdinarias/6.3Diagrama.fxml"));
     }
    else if(event.getSource()==btn63Ejercicio){
        int aleatorio = (int)(Math.random()*2);
        if(aleatorio==0){
       stage=(Stage) btn63Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcDifOrdinarias/6.3E1.fxml"));
  Cronometro();
        }
        else
        {
        stage=(Stage) btn63Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcDifOrdinarias/6.3E2.fxml"));
  Cronometro();
        }
     }
    ////===================================================================================
    else if(event.getSource()==btn64){       
        stage=(Stage) btn64.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcDifOrdinarias/6.4Teoria.fxml"));
      }
    else if(event.getSource()==btn64Diagrama){
       stage=(Stage) btn64Diagrama.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcDifOrdinarias/6.4Diagrama.fxml"));
     }
    else if(event.getSource()==btn64Ejercicio){
       stage=(Stage) btn64Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcDifOrdinarias/6.4E1.fxml"));
  Cronometro();
        }
    ////===================================================================================
    else if(event.getSource()==btn65){       
        stage=(Stage) btn65.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcDifOrdinarias/6.5Teoria.fxml"));
      }
    else if(event.getSource()==btn65Diagrama){
       stage=(Stage) btn65Diagrama.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcDifOrdinarias/6.5Diagrama.fxml"));
     }
    else if(event.getSource()==btn65Ejercicio){
       stage=(Stage) btn65Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcDifOrdinarias/6.5E1.fxml"));
  Cronometro();
    }
    ////===================================================================================
    else if(event.getSource()==btn66){       
        stage=(Stage) btn66.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcDifOrdinarias/6.6Teoria.fxml"));
      }
    else if(event.getSource()==btn66Diagrama){
       stage=(Stage) btn66Diagrama.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcDifOrdinarias/6.6Diagrama.fxml"));
     }
    else if(event.getSource()==btn66Ejercicio){
       stage=(Stage) btn66Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcDifOrdinarias/6.6E1.fxml"));
  Cronometro();
        }
    ////===================================================================================
    else if(event.getSource()==btn67){       
        stage=(Stage) btn67.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcDifOrdinarias/6.7Teoria.fxml"));
      }
    else if(event.getSource()==btn67Diagrama){
       stage=(Stage) btn67Diagrama.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcDifOrdinarias/6.7Diagrama.fxml"));
     }
    else if(event.getSource()==btn67Ejercicio){
       stage=(Stage) btn67Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcDifOrdinarias/6.7E1.fxml"));
  Cronometro();
     }
    ////===================================================================================
    else if(event.getSource()==btn68){       
        stage=(Stage) btn68.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcDifOrdinarias/6.8Teoria.fxml"));
      }
    else if(event.getSource()==btn68Diagrama){
       stage=(Stage) btn68Diagrama.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcDifOrdinarias/6.8Diagrama.fxml"));
     }
    else if(event.getSource()==btn68Ejercicio){
       stage=(Stage) btn68Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcDifOrdinarias/6.8E1.fxml"));
  Cronometro();
     }
    ////===================================================================================
    else if(event.getSource()==btn69){       
        stage=(Stage) btn69.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcDifOrdinarias/6.9Teoria.fxml"));
      }
    else if(event.getSource()==btn69Diagrama){
       stage=(Stage) btn69Diagrama.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcDifOrdinarias/6.9Diagrama.fxml"));
     }
    else if(event.getSource()==btn69Ejercicio){
       stage=(Stage) btn69Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcDifOrdinarias/6.9E1.fxml"));
  Cronometro();
     }
    ////===================================================================================
    else if(event.getSource()==btn610){       
        stage=(Stage) btn610.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcDifOrdinarias/6.10Teoria.fxml"));
      }
    else if(event.getSource()==btn610Diagrama){
       stage=(Stage) btn610Diagrama.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcDifOrdinarias/6.10Diagrama.fxml"));
     }
    else if(event.getSource()==btn610Ejercicio){
       stage=(Stage) btn610Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcDifOrdinarias/6.10E1.fxml"));
  Cronometro();
     }
    ////===================================================================================
    else if(event.getSource()==btn611){       
        stage=(Stage) btn611.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcDifOrdinarias/6.11Teoria.fxml"));
      }
    else if(event.getSource()==btn611Diagrama){
       stage=(Stage) btn611Diagrama.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcDifOrdinarias/6.11Diagrama.fxml"));
     }
    else if(event.getSource()==btn611Ejercicio){
       stage=(Stage) btn611Ejercicio.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/EcDifOrdinarias/6.11E1.fxml"));
  Cronometro();
     }
    ////===================================================================================
    
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
}
    @FXML
    public AnchorPane Panel;
    public Button contador1;

 @FXML
 public void Contador (ActionEvent e) throws InterruptedException{
      Ventana ti = new Ventana();
      ti.setVisible(true);
 }
 
 
 @FXML
 public void handleRespuestas(ActionEvent event) throws IOException{
     if(event.getSource()==btn12R1){
         double R = Double.parseDouble(txtfRespuesta.getText());
         if(R==5.855416667){
             Cronometro2();
             stage=(Stage) btn12R1.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/metodosnumericos/1Interpolacion.fxml"));
  ti.setVisible(false);
         }
         else{
             JOptionPane.showMessageDialog(ti, "Respuesta incorrecta.");
             intentos++;
         }
     }
     else if (event.getSource()==btn12R2){
         double R = Double.parseDouble(txtfRespuesta.getText());
         if(R==1.475){
             Cronometro2();
             stage=(Stage) btn12R2.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/metodosnumericos/1Interpolacion.fxml"));
  ti.setVisible(false);
         }
         else{
             JOptionPane.showMessageDialog(ti, "Respuesta incorrecta.");
             intentos++;
         }
     }
     //===========================================================================
     else if(event.getSource()==btn13R1){
         double R = Double.parseDouble(txtfRespuesta.getText());
         if(R==5.8884){
             Cronometro2();
             stage=(Stage) btn13R1.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/metodosnumericos/1Interpolacion.fxml"));
  ti.setVisible(false);
         }
         else{
             JOptionPane.showMessageDialog(ti, "Respuesta incorrecta.");
             intentos++;
         }
     }
     else if (event.getSource()==btn13R2){
         double R = Double.parseDouble(txtfRespuesta.getText());
         if(R==8.8288){
             Cronometro2();
             stage=(Stage) btn13R2.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/metodosnumericos/1Interpolacion.fxml"));
  ti.setVisible(false);
         }
         else{
             JOptionPane.showMessageDialog(ti, "Respuesta incorrecta.");
             intentos++;
         }
     }
     //===========================================================================
     else if(event.getSource()==btn14R1){
         double R = Double.parseDouble(txtfRespuesta.getText());
         if(R==7.319305785){
             Cronometro2();
             stage=(Stage) btn14R1.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/metodosnumericos/1Interpolacion.fxml"));
  ti.setVisible(false);
         }
         else{
             JOptionPane.showMessageDialog(ti, "Respuesta incorrecta.");
             intentos++;
         }
     }
     else if (event.getSource()==btn14R2){
         double R = Double.parseDouble(txtfRespuesta.getText());
         if(R==6.667593985){
             Cronometro2();
             stage=(Stage) btn14R2.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/metodosnumericos/1Interpolacion.fxml"));
  ti.setVisible(false);
         }
         else{
             JOptionPane.showMessageDialog(ti, "Respuesta incorrecta.");
             intentos++;
         }
     }
     //===========================================================================
     else if(event.getSource()==btn15R1){
         double R = Double.parseDouble(txtfRespuesta.getText());
         if(R==21.1225){
             Cronometro2();
             stage=(Stage) btn15R1.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/metodosnumericos/1Interpolacion.fxml"));
  ti.setVisible(false);
         }
         else{
             JOptionPane.showMessageDialog(ti, "Respuesta incorrecta.");
             intentos++;
         }
     }
     else if (event.getSource()==btn15R2){
         double R = Double.parseDouble(txtfRespuesta.getText());
         if(R==8.849824011){
             Cronometro2();
             stage=(Stage) btn15R2.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/metodosnumericos/1Interpolacion.fxml"));
  ti.setVisible(false);
         }
         else{
             JOptionPane.showMessageDialog(ti, "Respuesta incorrecta.");
             intentos++;
         }
     }
     //===========================================================================
     else if(event.getSource()==btn24R1){
         double R = Double.parseDouble(txtfRespuesta.getText());
         if(R==0.00000004285136){
             Cronometro2();
             stage=(Stage) btn24R1.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/metodosnumericos/2EcNoLineales.fxml"));
  ti.setVisible(false);
         }
         else{
             JOptionPane.showMessageDialog(ti, "Respuesta incorrecta.");
             intentos++;
         }
     }
     else if (event.getSource()==btn24R2){
         double R = Double.parseDouble(txtfRespuesta.getText());
         if(R==0.00001539776608){
             Cronometro2();
             stage=(Stage) btn24R2.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/metodosnumericos/2EcNoLineales.fxml"));
  ti.setVisible(false);
         }
         else{
             JOptionPane.showMessageDialog(ti, "Respuesta incorrecta.");
             intentos++;
         }
     }
     //===========================================================================
     else if(event.getSource()==btn25R1){
         double R = Double.parseDouble(txtfRespuesta.getText());
         if(R==0.001016329058){
             Cronometro2();
             stage=(Stage) btn25R1.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/metodosnumericos/2EcNoLineales.fxml"));
  ti.setVisible(false);
         }
         else{
             JOptionPane.showMessageDialog(ti, "Respuesta incorrecta.");
             intentos++;
         }
     }
     else if (event.getSource()==btn25R2){
         double R = Double.parseDouble(txtfRespuesta.getText());
         if(R==0.001450065807){
             Cronometro2();
             stage=(Stage) btn25R2.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/metodosnumericos/2EcNoLineales.fxml"));
  ti.setVisible(false);
         }
         else{
             JOptionPane.showMessageDialog(ti, "Respuesta incorrecta.");
             intentos++;
         }
     }
     //===========================================================================
     else if(event.getSource()==btn26R1){
         double R = Double.parseDouble(txtfRespuesta.getText());
         if(R==-0.704537549){
             Cronometro2();
             stage=(Stage) btn26R1.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/metodosnumericos/2EcNoLineales.fxml"));
  ti.setVisible(false);
         }
         else{
             JOptionPane.showMessageDialog(ti, "Respuesta incorrecta.");
             intentos++;
         }
     }
     else if (event.getSource()==btn26R2){
         double R = Double.parseDouble(txtfRespuesta.getText());
         if(R==-0.866358449){
             Cronometro2();
             stage=(Stage) btn26R2.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/metodosnumericos/2EcNoLineales.fxml"));
  ti.setVisible(false);
         }
         else{
             JOptionPane.showMessageDialog(ti, "Respuesta incorrecta.");
             intentos++;
         }
     }
     //===========================================================================
     //===========================================================================
     else if(event.getSource()==btn31R1){
         double R = Double.parseDouble(txtfRespuesta.getText());
         double R2 = Double.parseDouble(txtfRespuesta2.getText());
         double R3 = Double.parseDouble(txtfRespuesta3.getText());
         if((R==1) && (R2==1) && (R3==1)){
             Cronometro2();
             stage=(Stage) btn31R1.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/metodosnumericos/3EcLineales.fxml"));
  ti.setVisible(false);
         }
         else{
             JOptionPane.showMessageDialog(ti, "Respuesta incorrecta.");
             intentos++;
         }
     }
     else if (event.getSource()==btn31R2){
         double R = Double.parseDouble(txtfRespuesta.getText());
         double R2 = Double.parseDouble(txtfRespuesta2.getText());
         double R3 = Double.parseDouble(txtfRespuesta3.getText());
         double R4 = Double.parseDouble(txtfRespuesta4.getText());
         if((R==5) && (R2==1) && (R3==6) && (R4==9)){
             Cronometro2();
             stage=(Stage) btn31R2.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/metodosnumericos/3EcLineales.fxml"));
  ti.setVisible(false);
         }
         else{
             JOptionPane.showMessageDialog(ti, "Respuesta incorrecta.");
             intentos++;
         }
     }
     //===========================================================================
     else if(event.getSource()==btn32R1){
         double R = Double.parseDouble(txtfRespuesta.getText());
         double R2 = Double.parseDouble(txtfRespuesta2.getText());
         double R3 = Double.parseDouble(txtfRespuesta3.getText());
         if((R==14.7) && (R2==-9.5) && (R3==1.35)){
             Cronometro2();
             stage=(Stage) btn32R1.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/metodosnumericos/3EcLineales.fxml"));
  ti.setVisible(false);
         }
         else{
             JOptionPane.showMessageDialog(ti, "Respuesta incorrecta.");
             intentos++;
         }
     }
     else if (event.getSource()==btn32R2){
         double R = Double.parseDouble(txtfRespuesta.getText());
         double R2 = Double.parseDouble(txtfRespuesta2.getText());
         double R3 = Double.parseDouble(txtfRespuesta3.getText());
         if((R==0.552380952) && (R2==1.847619048) && (R3==1.828571429)){
             Cronometro2();
             stage=(Stage) btn32R2.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/metodosnumericos/3EcLineales.fxml"));
  ti.setVisible(false);
         }
         else{
             JOptionPane.showMessageDialog(ti, "Respuesta incorrecta.");
             intentos++;
         }
     }
     //===========================================================================
     else if(event.getSource()==btn33R1){
         double R = Double.parseDouble(txtfRespuesta.getText());
         double R2 = Double.parseDouble(txtfRespuesta2.getText());
         double R3 = Double.parseDouble(txtfRespuesta3.getText());
         if((R==0.701863354) && (R2==-0.161490683) && (R3==1.124223602)){
             Cronometro2();
             stage=(Stage) btn33R1.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/metodosnumericos/3EcLineales.fxml"));
  ti.setVisible(false);
         }
         else{
             JOptionPane.showMessageDialog(ti, "Respuesta incorrecta.");
             intentos++;
         }
     }
     else if (event.getSource()==btn33R2){
         double R = Double.parseDouble(txtfRespuesta.getText());
         double R2 = Double.parseDouble(txtfRespuesta2.getText());
         double R3 = Double.parseDouble(txtfRespuesta3.getText());
         if((R==1) && (R2==1) && (R3==-1)){
             Cronometro2();
             stage=(Stage) btn33R2.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/metodosnumericos/3EcLineales.fxml"));
  ti.setVisible(false);
         }
         else{
             JOptionPane.showMessageDialog(ti, "Respuesta incorrecta.");
             intentos++;
         }
     }
     //===========================================================================
     else if(event.getSource()==btn34R1){
         double R = Double.parseDouble(txtfRespuesta.getText());
         double R2 = Double.parseDouble(txtfRespuesta2.getText());
         double R3 = Double.parseDouble(txtfRespuesta3.getText());
         if((R==3) && (R2==-2.5) && (R3==7)){
             Cronometro2();
             stage=(Stage) btn34R1.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/metodosnumericos/3EcLineales.fxml"));
  ti.setVisible(false);
         }
         else{
             JOptionPane.showMessageDialog(ti, "Respuesta incorrecta.");
             intentos++;
         }
     }
     else if (event.getSource()==btn34R2){
         double R = Double.parseDouble(txtfRespuesta.getText());
         double R2 = Double.parseDouble(txtfRespuesta2.getText());
         double R3 = Double.parseDouble(txtfRespuesta3.getText());
         if((R==1.187709134) && (R2==-0.312509573) && (R3==0.93747889)){
             Cronometro2();
             stage=(Stage) btn34R2.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/metodosnumericos/3EcLineales.fxml"));
  ti.setVisible(false);
         }
         else{
             JOptionPane.showMessageDialog(ti, "Respuesta incorrecta.");
             intentos++;
         }
     }
     //===========================================================================
     else if(event.getSource()==btn35R1){
         double R = Double.parseDouble(txtfRespuesta.getText());
         double R2 = Double.parseDouble(txtfRespuesta2.getText());
         double R3 = Double.parseDouble(txtfRespuesta3.getText());
         if((R==2.16073832) && (R2==0.570617536) && (R3==0.232010366)){
             Cronometro2();
             stage=(Stage) btn35R1.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/metodosnumericos/3EcLineales.fxml"));
  ti.setVisible(false);
         }
         else{
             JOptionPane.showMessageDialog(ti, "Respuesta incorrecta.");
             intentos++;
         }
     }
     else if (event.getSource()==btn35R2){
         double R = Double.parseDouble(txtfRespuesta.getText());
         double R2 = Double.parseDouble(txtfRespuesta2.getText());
         double R3 = Double.parseDouble(txtfRespuesta3.getText());
         if((R==2.10503928) && (R2==0.755906402) && (R3==1.707778304)){
             Cronometro2();
             stage=(Stage) btn35R2.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/metodosnumericos/3EcLineales.fxml"));
  ti.setVisible(false);
         }
         else{
             JOptionPane.showMessageDialog(ti, "Respuesta incorrecta.");
             intentos++;
         }
     }
     //===========================================================================
     else if(event.getSource()==btn41R1){
         double R = Double.parseDouble(txtfRespuesta.getText());
         if(R==0.2569444444){
             Cronometro2();
             stage=(Stage) btn41R1.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/metodosnumericos/4MinimosCuadrados.fxml"));
  ti.setVisible(false);
         }
         else{
             JOptionPane.showMessageDialog(ti, "Respuesta incorrecta.");
             intentos++;
         }
     }
     else if (event.getSource()==btn41R2){
         double R = Double.parseDouble(txtfRespuesta.getText());
         if(R==3.91666667){
             Cronometro2();
             stage=(Stage) btn41R2.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/metodosnumericos/4MinimosCuadrados.fxml"));
  ti.setVisible(false);
         }
         else{
             JOptionPane.showMessageDialog(ti, "Respuesta incorrecta.");
             intentos++;
         }
     }
     //===========================================================================
     else if(event.getSource()==btn42R1){
         double R = Double.parseDouble(txtfRespuesta.getText());
         if(R==0.2569444444){
             Cronometro2();
             stage=(Stage) btn42R1.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/metodosnumericos/4MinimosCuadrados.fxml"));
  ti.setVisible(false);
         }
         else{
             JOptionPane.showMessageDialog(ti, "Respuesta incorrecta.");
             intentos++;
         }
     }
     else if (event.getSource()==btn42R2){
         double R = Double.parseDouble(txtfRespuesta.getText());
         if(R==3.91666667){
             Cronometro2();
             stage=(Stage) btn42R2.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/metodosnumericos/4MinimosCuadrados.fxml"));
  ti.setVisible(false);
         }
         else{
             JOptionPane.showMessageDialog(ti, "Respuesta incorrecta.");
             intentos++;
         }
     }
     //===========================================================================
     else if(event.getSource()==btn43R1){
         double R = Double.parseDouble(txtfRespuesta.getText());
         if(R==0.2569444444){
             Cronometro2();
             stage=(Stage) btn43R1.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/metodosnumericos/4MinimosCuadrados.fxml"));
  ti.setVisible(false);
         }
         else{
             JOptionPane.showMessageDialog(ti, "Respuesta incorrecta.");
             intentos++;
         }
     }
     else if (event.getSource()==btn43R2){
         double R = Double.parseDouble(txtfRespuesta.getText());
         if(R==3.91666667){
             Cronometro2();
             stage=(Stage) btn43R2.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/metodosnumericos/4MinimosCuadrados.fxml"));
  ti.setVisible(false);
         }
         else{
             JOptionPane.showMessageDialog(ti, "Respuesta incorrecta.");
             intentos++;
         }
     }
     //===========================================================================
     else if(event.getSource()==btn44R1){
         double R = Double.parseDouble(txtfRespuesta.getText());
         if(R==0.2569444444){
             Cronometro2();
             stage=(Stage) btn44R1.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/metodosnumericos/4MinimosCuadrados.fxml"));
  ti.setVisible(false);
         }
         else{
             JOptionPane.showMessageDialog(ti, "Respuesta incorrecta.");
             intentos++;
         }
     }
     else if (event.getSource()==btn44R2){
         double R = Double.parseDouble(txtfRespuesta.getText());
         if(R==3.91666667){
             Cronometro2();
             stage=(Stage) btn44R2.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/metodosnumericos/4MinimosCuadrados.fxml"));
  ti.setVisible(false);
         }
         else{
             JOptionPane.showMessageDialog(ti, "Respuesta incorrecta.");
             intentos++;
         }
     }
     //===========================================================================
     else if(event.getSource()==btn45R1){
         double R = Double.parseDouble(txtfRespuesta.getText());
         if(R==0.2569444444){
             Cronometro2();
             stage=(Stage) btn45R1.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/metodosnumericos/4MinimosCuadrados.fxml"));
  ti.setVisible(false);
         }
         else{
             JOptionPane.showMessageDialog(ti, "Respuesta incorrecta.");
             intentos++;
         }
     }
     else if (event.getSource()==btn45R2){
         double R = Double.parseDouble(txtfRespuesta.getText());
         if(R==3.91666667){
             Cronometro2();
             stage=(Stage) btn45R2.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/metodosnumericos/4MinimosCuadrados.fxml"));
  ti.setVisible(false);
         }
         else{
             JOptionPane.showMessageDialog(ti, "Respuesta incorrecta.");
             intentos++;
         }
     }
     //===========================================================================
     else if(event.getSource()==btn51R1){
         double R = Double.parseDouble(txtfRespuesta.getText());
         if(R==0.2569444444){
             Cronometro2();
             stage=(Stage) btn51R1.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/metodosnumericos/5Integracion.fxml"));
  ti.setVisible(false);
         }
         else{
             JOptionPane.showMessageDialog(ti, "Respuesta incorrecta.");
             intentos++;
         }
     }
     else if (event.getSource()==btn51R2){
         double R = Double.parseDouble(txtfRespuesta.getText());
         if(R==3.91666667){
             Cronometro2();
             stage=(Stage) btn51R2.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/metodosnumericos/5Integracion.fxml"));
  ti.setVisible(false);
         }
         else{
             JOptionPane.showMessageDialog(ti, "Respuesta incorrecta.");
             intentos++;
         }
     }
     //===========================================================================
     else if(event.getSource()==btn52R1){
         double R = Double.parseDouble(txtfRespuesta.getText());
         if(R==2.6925){
             Cronometro2();
             stage=(Stage) btn52R1.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/metodosnumericos/5Integracion.fxml"));
  ti.setVisible(false);
         }
         else{
             JOptionPane.showMessageDialog(ti, "Respuesta incorrecta.");
             intentos++;
         }
     }
     else if (event.getSource()==btn52R2){
         double R = Double.parseDouble(txtfRespuesta.getText());
         if(R==0.375){
             Cronometro2();
             stage=(Stage) btn52R2.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/metodosnumericos/5Integracion.fxml"));
  ti.setVisible(false);
         }
         else{
             JOptionPane.showMessageDialog(ti, "Respuesta incorrecta.");
             intentos++;
         }
     }
     //===========================================================================
     else if(event.getSource()==btn53R1){
         double R = Double.parseDouble(txtfRespuesta.getText());
         if(R==0.5126567){
             Cronometro2();
             stage=(Stage) btn53R1.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/metodosnumericos/5Integracion.fxml"));
  ti.setVisible(false);
         }
         else{
             JOptionPane.showMessageDialog(ti, "Respuesta incorrecta.");
             intentos++;
         }
     }
     else if (event.getSource()==btn53R2){
         double R = Double.parseDouble(txtfRespuesta.getText());
         if(R==0.2643){
             Cronometro2();
             stage=(Stage) btn53R2.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/metodosnumericos/5Integracion.fxml"));
  ti.setVisible(false);
         }
         else{
             JOptionPane.showMessageDialog(ti, "Respuesta incorrecta.");
             intentos++;
         }
     }
     //===========================================================================
     else if(event.getSource()==btn55R1){
         double R = Double.parseDouble(txtfRespuesta.getText());
         if(R==1.71828917){
             Cronometro2();
             stage=(Stage) btn55R1.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/metodosnumericos/5Integracion.fxml"));
  ti.setVisible(false);
         }
         else{
             JOptionPane.showMessageDialog(ti, "Respuesta incorrecta.");
             intentos++;
         }
     }
     else if (event.getSource()==btn55R2){
         double R = Double.parseDouble(txtfRespuesta.getText());
         if(R==2.906057249){
             Cronometro2();
             stage=(Stage) btn55R2.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/metodosnumericos/5Integracion.fxml"));
  ti.setVisible(false);
         }
         else{
             JOptionPane.showMessageDialog(ti, "Respuesta incorrecta.");
             intentos++;
         }
     }
     //===========================================================================
     else if(event.getSource()==btn56R1){
         double R = Double.parseDouble(txtfRespuesta.getText());
         if(R==8.46206296){
             Cronometro2();
             stage=(Stage) btn56R1.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/metodosnumericos/5Integracion.fxml"));
  ti.setVisible(false);
         }
         else{
             JOptionPane.showMessageDialog(ti, "Respuesta incorrecta.");
             intentos++;
         }
     }
     else if (event.getSource()==btn56R2){
         double R = Double.parseDouble(txtfRespuesta.getText());
         if(R==0.751437227){
             Cronometro2();
             stage=(Stage) btn56R2.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/metodosnumericos/5Integracion.fxml"));
  ti.setVisible(false);
         }
         else{
             JOptionPane.showMessageDialog(ti, "Respuesta incorrecta.");
             intentos++;
         }
     }
     //===========================================================================
     else if(event.getSource()==btn62R1){
         double R = Double.parseDouble(txtfRespuesta.getText());
         if(R==1.24956){
             Cronometro2();
             stage=(Stage) btn62R1.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/metodosnumericos/6EcDif.fxml"));
  ti.setVisible(false);
         }
         else{
             JOptionPane.showMessageDialog(ti, "Respuesta incorrecta.");
             intentos++;
         }
     }
     else if (event.getSource()==btn62R2){
         double R = Double.parseDouble(txtfRespuesta.getText());
         if(R==4.47125333){
             Cronometro2();
             stage=(Stage) btn62R2.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/metodosnumericos/6EcDif.fxml"));
  ti.setVisible(false);
         }
         else{
             JOptionPane.showMessageDialog(ti, "Respuesta incorrecta.");
             intentos++;
         }
     }
     //===========================================================================
     else if(event.getSource()==btn63R1){
         double R = Double.parseDouble(txtfRespuesta.getText());
         if(R==1.21494){
             Cronometro2();
             stage=(Stage) btn63R1.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/metodosnumericos/6EcDif.fxml"));
  ti.setVisible(false);
         }
         else{
             JOptionPane.showMessageDialog(ti, "Respuesta incorrecta.");
             intentos++;
         }
     }
     else if (event.getSource()==btn63R2){
         double R = Double.parseDouble(txtfRespuesta.getText());
         if(R==0.678){
             Cronometro2();
             stage=(Stage) btn63R2.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/metodosnumericos/6EcDif.fxml"));
  ti.setVisible(false);
         }
         else{
             JOptionPane.showMessageDialog(ti, "Respuesta incorrecta.");
             intentos++;
         }
     }
     //===========================================================================
     else if(event.getSource()==btn64R1){
         double R = Double.parseDouble(txtfRespuesta.getText());
         if(R==0.939){
             Cronometro2();
             stage=(Stage) btn64R1.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/metodosnumericos/6EcDif.fxml"));
  ti.setVisible(false);
         }
         else{
             JOptionPane.showMessageDialog(ti, "Respuesta incorrecta.");
             intentos++;
         }
     }
     else if (event.getSource()==btn64R2){
         double R = Double.parseDouble(txtfRespuesta.getText());
         if(R==0.678){
             Cronometro2();
             stage=(Stage) btn64R2.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/metodosnumericos/6EcDif.fxml"));
  ti.setVisible(false);
         }
         else{
             JOptionPane.showMessageDialog(ti, "Respuesta incorrecta.");
             intentos++;
         }
     }
     //===========================================================================
     else if(event.getSource()==btn66R1){
         double R = Double.parseDouble(txtfRespuesta.getText());
         if(R==0.651632664){
             Cronometro2();
             stage=(Stage) btn66R1.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/metodosnumericos/6EcDif.fxml"));
  ti.setVisible(false);
         }
         else{
             JOptionPane.showMessageDialog(ti, "Respuesta incorrecta.");
             intentos++;
         }
     }
     else if (event.getSource()==btn66R2){
         double R = Double.parseDouble(txtfRespuesta.getText());
         if(R==0.651632664){
             Cronometro2();
             stage=(Stage) btn66R2.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/metodosnumericos/6EcDif.fxml"));
  ti.setVisible(false);
         }
         else{
             JOptionPane.showMessageDialog(ti, "Respuesta incorrecta.");
             intentos++;
         }
     }
     //===========================================================================
     else if(event.getSource()==btn67R1){
         double R = Double.parseDouble(txtfRespuesta.getText());
         if(R==1.211723276){
             Cronometro2();
             stage=(Stage) btn67R1.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/metodosnumericos/6EcDif.fxml"));
  ti.setVisible(false);
         }
         else{
             JOptionPane.showMessageDialog(ti, "Respuesta incorrecta.");
             intentos++;
         }
     }
     else if (event.getSource()==btn67R2){
         double R = Double.parseDouble(txtfRespuesta.getText());
         if(R==0.651632664){
             Cronometro2();
             stage=(Stage) btn67R2.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/metodosnumericos/6EcDif.fxml"));
  ti.setVisible(false);
         }
         else{
             JOptionPane.showMessageDialog(ti, "Respuesta incorrecta.");
             intentos++;
         }
     }
     //===========================================================================
     else if(event.getSource()==btn69R1){
         double R = Double.parseDouble(txtfRespuesta.getText());
         if(R==0.511617621){
             Cronometro2();
             stage=(Stage) btn69R1.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/metodosnumericos/6EcDif.fxml"));
  ti.setVisible(false);
         }
         else{
             JOptionPane.showMessageDialog(ti, "Respuesta incorrecta.");
             intentos++;
         }
     }
     else if (event.getSource()==btn69R2){
         double R = Double.parseDouble(txtfRespuesta.getText());
         if(R==0.651632664){
             Cronometro2();
             stage=(Stage) btn69R2.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/metodosnumericos/6EcDif.fxml"));
  ti.setVisible(false);
         }
         else{
             JOptionPane.showMessageDialog(ti, "Respuesta incorrecta.");
             intentos++;
         }
     }
     //===========================================================================
     else if(event.getSource()==btn610R1){
         double R = Double.parseDouble(txtfRespuesta.getText());
         if(R==0.552579997){
             Cronometro2();
             stage=(Stage) btn610R1.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/metodosnumericos/6EcDif.fxml"));
  ti.setVisible(false);
         }
         else{
             JOptionPane.showMessageDialog(ti, "Respuesta incorrecta.");
             intentos++;
         }
     }
     else if (event.getSource()==btn610R2){
         double R = Double.parseDouble(txtfRespuesta.getText());
         if(R==0.651632664){
             Cronometro2();
             stage=(Stage) btn610R2.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/metodosnumericos/6EcDif.fxml"));
  ti.setVisible(false);
         }
         else{
             JOptionPane.showMessageDialog(ti, "Respuesta incorrecta.");
             intentos++;
         }
     }
     //===========================================================================
     else if(event.getSource()==btn611R1){
         double R = Double.parseDouble(txtfRespuesta.getText());
         if(R==1.843980435){
             Cronometro2();
             stage=(Stage) btn611R1.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/metodosnumericos/6EcDif.fxml"));
  ti.setVisible(false);
         }
         else{
             JOptionPane.showMessageDialog(ti, "Respuesta incorrecta.");
             intentos++;
         }
     }
     else if (event.getSource()==btn611R2){
         double R = Double.parseDouble(txtfRespuesta.getText());
         if(R==0.651632664){
             Cronometro2();
             stage=(Stage) btn611R2.getScene().getWindow();
  root = FXMLLoader.load(getClass().getResource("/metodosnumericos/6EcDif.fxml"));
  ti.setVisible(false);
         }
         else{
             JOptionPane.showMessageDialog(ti, "Respuesta incorrecta.");
             intentos++;
         }
     }
     //===========================================================================
     Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
 }
 
 
 /*@FXML
 public Label countDownText;
 int countDown=0;
 public void startCountDown() {
    timer.schedule(new TimerTask() {
    @Override
        public void run() {
        Platform.runLater(new Runnable() {
           public void run() {
               countDown--;
                countDownText.setText("Time left:" + countDown);

                if (countDown < 0)
                    timer.cancel();
          }
        });
    }
    }, 1000, 1000); //Every 1 second
}*/
 
 @FXML
public void handleCloseButtonAction(ActionEvent event) {
    Stage stage = (Stage) btnSalirPrincipal.getScene().getWindow();
    stage.close();
}
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    

}
