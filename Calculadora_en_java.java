

package Paquete1;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;

// esta es una calculadora

public class Calculadora extends JFrame {
	private static final long serialVersionUID = 1L;
    private JTextField despliega = new JTextField("0");
    private JPanel panBotones = new JPanel();  //creamos un nuevo panel de botones un marco pequeÃ±o 
    private JButton [] boton = new JButton[42];   //creamos un arreglo de 30 botones
    private String [] botmsg = {"x²","n!","MC","MR","MS","M+","M-","x^y","y\u221Ax","←","CE","C","+-","\u221A","x^3","3\u221Ax","7","8","9","/","%",
    		"log","10^x","4","5","6","*","1/x","sin","cos","1","2","3","-","+","exp","tan","0",".","Mod","\u03c0","="};
    
    
    private String texto = "";
    private boolean bPunto = true;   //bandera de punto
    private double op1,op2,res,memoria;
    private char oper;
    
    
    class ManejadorEventos implements ActionListener{  //clase interna
    	public void actionPerformed(ActionEvent evento ){
    		for (int  i=0; i < boton.length;i++){
    			
    			//Que hay q hacer cuando preisonmos el boton[i]
    			if (evento.getSource() == boton[i]){
    				
    				//elevar al cuadrado
    				if (i == 0){   
    					texto = ""+Math.pow(Double.parseDouble(texto),2);
    					despliega.setText(texto);
    				}
    				//calcular factorial
    				if (i == 1){ 
    					double cont1=1,r=1,factorial;
    					factorial=Double.parseDouble(texto);
    					do {
    					r*=cont1;
    					cont1++;
    					}
    					while(cont1<=factorial);
    					despliega.setText(r+"");
    				}
    				
    				if(i==2)  //borrar memoria
    				memoria=0;
    				
    				if (i == 3){   //memori restore
    					texto = ""+memoria;
    					despliega.setText(texto);
    				}
    				
    				if (i == 4 && texto!=""){  //guardo el primer numero
    					memoria= Double.parseDouble(texto);
    				}
    				
    				if (i == 5 && texto!=""){  //memori mas
    					memoria+= Double.parseDouble(texto);
    				}
    				if (i == 6 && texto!=""){  //memori menos
    					memoria-= Double.parseDouble(texto);
    				}
    				
    				
    				if(i==7){  //x elevado a la y
    					oper = boton[i].getText().charAt(0);
    					op1 = Double.parseDouble(texto);
    					texto = "0";
    					bPunto = true;
    					despliega.setText(texto); 
    					texto = "";
    					
    				}
    					
    					if(i==8){//y raiz de x
    						oper = boton[i].getText().charAt(0);
        					op1 = Double.parseDouble(texto);
        					texto = "0";
        					bPunto = true;
        					despliega.setText(texto); 
        					texto = "";
    						
    					}
    						
    						
    				//borrar un solo numero
    				if (i == 9 && texto!=""){
    					if(texto.charAt(texto.length()-1)=='.')bPunto=true;  //charAt devuelve el caracter length menos 1
    					if(texto.length()==1){
    						despliega.setText("0");
    						texto="";
    					}
    					else if(texto.length()>1){
    						texto = texto.substring(0,texto.length()-1);
    						despliega.setText(texto);
    					}
    				}
    				
    				
    				if (i == 10){
    				//CE
    					texto= "";           //borra el ultimo texto
    					bPunto= true;
    					despliega.setText("0");
    				}
    				
    				
    				
    				if (i== 11){
    					texto= "";        //i = 7 == c borra todo
    					bPunto= true;
    					op1 = 0;
    					op2 = 0;
    					despliega.setText("0"); 
    				}
    			
    				
    				//teclado numerico
    				if (i >= 16 && i<=18 || (i>= 23 && i<=25) ||   //
    						(i>=30 && i<= 32) || i==37 || i==38){
    					if(i == 38 && bPunto){
    						if (texto.equals("")) texto = "0";
    						texto+=boton[i].getText();    //concatena el texto 
    						bPunto = false;     //no puede poner punto
    					} 
    					else if(i != 38){  //si es el cero que lo ponga una sola vez
    					     if(i==37 && !texto.equals("0") || i !=37 )   //si el valor de l objeto es igual  == equals
    					    	 texto+=boton[i].getText(); 
    					}
    					despliega.setText(texto);          //despleiga texto
    			}
    				
    				
    				
    				
    				
    				
    				if (i== 19 || i == 26 || i== 33 || i == 34){  //operadores 
    					oper = boton[i].getText().charAt(0);
    					op1 = Double.parseDouble(texto);
    					texto = "0";
    					bPunto = true;
    					despliega.setText(texto); 
    					texto = "";
    				}
    				
    				//operador mas menos
    				if (i == 12 && texto !=""){
    					res = -Double.parseDouble(texto);
    					texto = ""+ res;
    					despliega.setText(texto);
    				}
    				
    				//raiz
    				if (i == 13 && texto !=""){
    					res = Math.sqrt(Double.parseDouble(texto));
    					texto = ""+ res;
    					despliega.setText(texto);
    				}
    				
    				//potencia elevada a la 3
    				if(i==14){
    					texto = ""+Math.pow(Double.parseDouble(texto),3);
    					despliega.setText(texto);
    				}
    				
    				//raiz cubica
    				if(i==15){
    					texto = ""+Math.cbrt(Double.parseDouble(texto));
    					despliega.setText(texto);
    				}
    				
    				
    				
    				//porcentaje
    				if (i== 20 && texto!= ""){
    					texto = ""+Double.parseDouble(texto)/100;
    					despliega.setText(texto);
    				}
    				
    				if(i==22){  //10 elevado a la x
    					texto = ""+Math.pow(10,Double.parseDouble(texto));
    					despliega.setText(texto);
    				}
    				
    				if (i == 27 && texto !=""){
    					res = 1/Double.parseDouble(texto);   //tecla inverso
    					texto = ""+res;
    					despliega.setText(texto);
    				}
    					//logaritmo base 10
    				if (i == 21 && texto != ""){
    					texto= ""+Math.log10(Double.parseDouble(texto));  //
    					despliega.setText(texto);
    				}
  
    				
    				//seno
    				if(i==28){
    					
    					texto= ""+Math.sin(Math.toRadians(Double.parseDouble(texto)));
    					despliega.setText(texto); 
    				}
    				
    				//coseno
    				if(i==29){
    					texto= ""+Math.cos(Math.toRadians(Double.parseDouble(texto)));
    					despliega.setText(texto); 
    				}
    				
    				if(i == 35){
    					oper = boton[i].getText().charAt(0);
    					op1 = Double.parseDouble(texto);
    					texto = "0";
    					bPunto = false;
    					despliega.setText(texto); 
    					texto = "";
    				}
    				
    				
    				//tangente
    				if(i==36){
    					double tan,rad,resp;
    					tan=Double.parseDouble(texto);
    					if (tan%90==0) {
    					despliega.setText("Error: Tangente de "+tan);
    					tan=0;
    					resp=0;
    					}
    					else {
    					rad = Math.toRadians(tan);
    					resp=Math.tan(rad);
    					despliega.setText(resp+"");
    					}
    				}
    				
    				//residuo modulo
    				if(i==39){
    					oper = boton[i].getText().charAt(0);
    					op1 = Double.parseDouble(texto);
    					texto = "0";
    					bPunto = true;
    					despliega.setText(texto); 
    					texto = "";
    					
    				}
    				
    				
    				if (i== 40){
    					texto ="" + Math.PI;     //tecla pi
    					despliega.setText(texto);
    				}
    				
    				if (i == 41){
    					op2 = Double.parseDouble(texto);
    					bPunto=false;     //cuando muestra el resultado no permite poner punto
    					switch (oper){
    					case '/': res = op1 /op2;
    					break;
    					case '*': res = op1 *op2;
    					break;
    					case '-': res = op1 -op2;
    					break;
    					case '+': res = op1 +op2;
    					break;
    					case 'x': res =Math.pow(op1, op2);  //x elevado a la y resultado
    					break;
    					case 'M': res = op1 % op2;  //sacra el modulo
    					break;
    					case 'y': res = Math.pow(op1, 1/op2);
    					break;
    					case 'e': res = op1 *Math.pow(10,op2);
    					break;
    					}
    					texto = ""+res;   //eel numero l conviete atexto
    					despliega.setText(texto); 
    					if(texto.length() > 2 && !texto.equals("0.0") && //el texto debe ser mayor q 2 y NO es igual a 0.0  y el subsring desde antepenultimo hasta el utimo
    	    					texto.substring(texto.length()-2).equals(".0")){
    	    						texto= texto.substring(0, texto.length()-2);  //recibe de parametros (tegresa una subcadena a partir de:
    					}
    				//si es un entero q no lo ponga
    				if (!texto.equals("")){       //
    					despliega.setText(texto); 
    					}
    				
    					}
    		      }
    		}
    		
    	}
    }
    
    
	public Calculadora(){
		super ("Calculadora");
		ManejadorEventos maneja = new ManejadorEventos();   //para poder escuchar el evento con maneja
		
		setLayout(new BorderLayout());   //el marco principal esta dividido en regiones
		despliega.setEditable(false);    //no se puede editar este campo  viene siendo  l pantalla de la calcu
		despliega.setHorizontalAlignment(SwingConstants.RIGHT);  //despleiga el textfield a la derecha
		despliega.setFont(new Font("Courier",Font.BOLD,24));   //cambia el tipo de letra
		
		
		panBotones.setLayout(new GridLayout(6,5,3,3));   //un nuevo gridlayout de 6 * 5  prametro 3,3 separacion entre botones
		for (int i=0;i<boton.length;i++){
			boton[i] = new JButton(botmsg[i]);  
			boton  [i].addActionListener(maneja);
			boton[i].setFont(new Font("Courier",Font.BOLD,18)); //coor de bootnes
			boton[i].setBackground(new Color(50,20,250));  //color de letras
			boton[i].setForeground(new Color(250,250,250));
			panBotones.add(boton[i]);
		}
		
		
		add(BorderLayout.NORTH,despliega); 
		add(BorderLayout.CENTER,panBotones);   //ubicar el marco de botones en el centro
		
		
	
		
		//poner imagen de pitbull en la apliacion
		BufferedImage image = null;
		
		try{
			image = ImageIO.read(getClass().getClassLoader().getResource("Pitbull.png"));
		} catch(IOException e){
			e.printStackTrace();
		}
		super.setIconImage(image);
		
		}
	
	
	public static void main(String[] args) {
		Calculadora calc = new Calculadora();
		
		calc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		calc.setLocation(300, 300);
		calc.setSize(570,300);
		calc.setVisible(true);
		
		}

}

//descargar imageicon 
//arrastra imagen png
//crea un icon
//crear un nuevo shortcut


