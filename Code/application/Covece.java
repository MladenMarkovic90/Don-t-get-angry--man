package application;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Covece implements Initializable {
	//obican meni
	@FXML Menu Game;
	//izabrati broj igraca
	@FXML MenuItem Play_2;
	@FXML MenuItem Play_3;
	@FXML MenuItem Play_4;
	@FXML MenuItem Info;
	//visuelni design
	@FXML AnchorPane main;
	//visualno kvadrat sa krugovima
	@FXML AnchorPane kocka_polje;
	//krugovi u kocki
	@FXML Circle c1; @FXML Circle c2; @FXML Circle c3; @FXML Circle c4;
	@FXML Circle c5; @FXML Circle c6; @FXML Circle c7;
	//dugme za pokretanje kocke
	@FXML Button kocka;
	//glavna polja
	@FXML Circle p01; @FXML Circle p02; @FXML Circle p03; @FXML Circle p04; @FXML Circle p05;
	@FXML Circle p06; @FXML Circle p07; @FXML Circle p08; @FXML Circle p09; @FXML Circle p10;
	@FXML Circle p11; @FXML Circle p12; @FXML Circle p13; @FXML Circle p14; @FXML Circle p15;
	@FXML Circle p16; @FXML Circle p17; @FXML Circle p18; @FXML Circle p19; @FXML Circle p20;
	@FXML Circle p21; @FXML Circle p22; @FXML Circle p23; @FXML Circle p24; @FXML Circle p25;
	@FXML Circle p26; @FXML Circle p27; @FXML Circle p28; @FXML Circle p29; @FXML Circle p30;
	@FXML Circle p31; @FXML Circle p32; @FXML Circle p33; @FXML Circle p34; @FXML Circle p35;
	@FXML Circle p36; @FXML Circle p37; @FXML Circle p38; @FXML Circle p39; @FXML Circle p40;
	//pocetna polja i kucica od zelene
	@FXML Circle green1; @FXML Circle green2; @FXML Circle green3; @FXML Circle green4;
	@FXML Circle green5; @FXML Circle green6; @FXML Circle green7; @FXML Circle green8;
	//pocetna polja i kucica od crvene
	@FXML Circle red1; @FXML Circle red2; @FXML Circle red3; @FXML Circle red4;
	@FXML Circle red5; @FXML Circle red6; @FXML Circle red7; @FXML Circle red8;
	//pocetna polja i kucica od plave
	@FXML Circle blue1; @FXML Circle blue2; @FXML Circle blue3; @FXML Circle blue4;
	@FXML Circle blue5; @FXML Circle blue6; @FXML Circle blue7; @FXML Circle blue8;
	//pocetna polja i kucica od zute
	@FXML Circle yellow1; @FXML Circle yellow2; @FXML Circle yellow3; @FXML Circle yellow4;
	@FXML Circle yellow5; @FXML Circle yellow6; @FXML Circle yellow7; @FXML Circle yellow8;
	//pokazatelj ko je trenutno na potezu
	@FXML Circle potez;
	//provera za prvi pijun i bacanje 3 puta
	boolean prvi_potez[]=new boolean[5];
	//konstante za olaksavanje programa
	final static int PRAZNO=0;
	final int PLAVO=1;
	final int ZUTO=2;
	final int CRVENO=3;
	final int ZELENO=4;
	//salje se u drugi prozor kada neko pobedi
	static public int pobednik=PRAZNO;
	//grupisanje
	MenuItem meni[]=new MenuItem[4];
	Circle p[]=new Circle[40];
	Circle p_boja[][]=new Circle[5][8];
	int polja[]=new int[40];
	int polja_boja[][]=new int[5][8];
	
	int player=0;//koji igrac je na potezu(pocinje od 0)
	int players=2;//koliko igraca igra
	boolean again=false;//da li igrac ponovo baca kocku
	boolean stanje=false;//promena bacanje kocke i sledeci potez dugmeta
	int broj=0;//koliko polja mozemo pomeriti
	
	static public String boja[]={"-fx-fill:rgb(240,240,240);","-fx-fill:rgb(30,30,255);","-fx-fill:rgb(255,255,30);",
			"-fx-fill:rgb(255,30,30);","-fx-fill:rgb(30,255,30);"};//olaksavanje setStyle boje
	//SIVO PLAVA ZUTA CRVENO ZELENO
	
	Random R=new Random();
	//za novi prozor
	Stage stage=new Stage();
	Scene scene=null;
	
	int br_prvi=0;//brojac zbog prvog bacanja 3 puta
	
	
	public void baci_kocku(ActionEvent e)
	{//ako neko ubaci sve boje u kucicu, taj pobedjuje
		if(polja_boja[CRVENO][4]!=PRAZNO && polja_boja[CRVENO][5]!=PRAZNO && polja_boja[CRVENO][6]!=PRAZNO && polja_boja[CRVENO][7]!=PRAZNO)
		//if(true) //debug provera
			pobednik=CRVENO;
		else if(polja_boja[PLAVO][4]!=PRAZNO  && polja_boja[PLAVO][5]!=PRAZNO && polja_boja[PLAVO][6]!=PRAZNO && polja_boja[PLAVO][7]!=PRAZNO)
			pobednik=PLAVO;
		else if(polja_boja[ZELENO][4]!=PRAZNO && polja_boja[ZELENO][5]!=PRAZNO && polja_boja[ZELENO][6]!=PRAZNO && polja_boja[ZELENO][7]!=PRAZNO)
			pobednik=ZELENO;
		else if(polja_boja[ZUTO][4]!=PRAZNO && polja_boja[ZUTO][5]!=PRAZNO && polja_boja[ZUTO][6]!=PRAZNO && polja_boja[ZUTO][7]!=PRAZNO)
			pobednik=ZUTO;
		if(pobednik!=PRAZNO)//ako postoji pobednik otvaramo novi prozor i blokiramo trenutnu igru
		{
			potez.setVisible(false);
			kocka.setDisable(true);
			Parent root=null;
			try {
				root = FXMLLoader.load(getClass().getResource("Kraj.fxml"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			scene = new Scene(root,650,200);
		    stage.setTitle("Pobednik");
		    stage.setScene(scene);
		    stage.show();
		}
		pobednik=PRAZNO;//nije skroz potrebno, ali moze debug
		if(stanje==false)//kada je stanje false, onda je bacanje kocke
		{
			kocka.setText("CONTINUE");
			stanje=true;//menjamo dugme na funkciju dalje
			broj=R.nextInt(6)+1;//bacamo kocku
			if(broj>1)
				c1.setVisible(true);
			if(broj==6)
				c2.setVisible(true);
			if(broj>3)
				c3.setVisible(true);
			if(broj==1 || broj==3 || broj==5)
				c4.setVisible(true);
			if(broj>3)
				c5.setVisible(true);
			if(broj==6)
				c6.setVisible(true);
			if(broj>1)
				c7.setVisible(true);
			if(broj==6)//ako je baceno 6 onda ponovo igra
				again=true;
			else
				again=false;
			br_prvi++;
			if(br_prvi<3 && prvi_potez[player+1]==false)//3 puta bacanje dok ne izadje prva figura
				again=true;
		}
		else
		{
		stanje=false;//postavljamo na funkciju kocke
		//brisemo vrednost kocke
		c1.setVisible(false);c2.setVisible(false);c3.setVisible(false);
		c4.setVisible(false);c5.setVisible(false);c6.setVisible(false);
		c7.setVisible(false);
		kocka.setText("THROW");
		if(again==false)//ako ne mozemo opet baciti kocku
			{
			br_prvi=0;
			player++;
			}
		player=player%players;//menjamo na prvog igraca, ako stignemo na zadnjeg
		potez.setStyle(boja[player+1]);//bojimo krug koji pokazuje ko je na potezu
		broj=0;//koliko polja mozemo pomeriti
		}
	}

	public void initialize(URL arg0, ResourceBundle arg1)
	{
		//prebacivanje u niz i matricu
		meni[0]=Play_2;meni[1]=Play_3;meni[2]=Play_4;meni[3]=Info;
		
		p[0]=p01;p[1]=p02;p[2]=p03;p[3]=p04;p[4]=p05;
		p[5]=p06;p[6]=p07;p[7]=p08;p[8]=p09;p[9]=p10;
		p[10]=p11;p[11]=p12;p[12]=p13;p[13]=p14;p[14]=p15;
		p[15]=p16;p[16]=p17;p[17]=p18;p[18]=p19;p[19]=p20;
		p[20]=p21;p[21]=p22;p[22]=p23;p[23]=p24;p[24]=p25;
		p[25]=p26;p[26]=p27;p[27]=p28;p[28]=p29;p[29]=p30;
		p[30]=p31;p[31]=p32;p[32]=p33;p[33]=p34;p[34]=p35;
		p[35]=p36;p[36]=p37;p[37]=p38;p[38]=p39;p[39]=p40;
		
		p_boja[PLAVO][0]=blue1;p_boja[PLAVO][1]=blue2;
		p_boja[PLAVO][2]=blue3;p_boja[PLAVO][3]=blue4;
		p_boja[PLAVO][4]=blue5;p_boja[PLAVO][5]=blue6;
		p_boja[PLAVO][6]=blue7;p_boja[PLAVO][7]=blue8;
		
		p_boja[CRVENO][0]=red1;p_boja[CRVENO][1]=red2;
		p_boja[CRVENO][2]=red3;p_boja[CRVENO][3]=red4;
		p_boja[CRVENO][4]=red5;p_boja[CRVENO][5]=red6;
		p_boja[CRVENO][6]=red7;p_boja[CRVENO][7]=red8;
		
		p_boja[ZUTO][0]=yellow1;p_boja[ZUTO][1]=yellow2;
		p_boja[ZUTO][2]=yellow3;p_boja[ZUTO][3]=yellow4;
		p_boja[ZUTO][4]=yellow5;p_boja[ZUTO][5]=yellow6;
		p_boja[ZUTO][6]=yellow7;p_boja[ZUTO][7]=yellow8;
		
		p_boja[ZELENO][0]=green1;p_boja[ZELENO][1]=green2;
		p_boja[ZELENO][2]=green3;p_boja[ZELENO][3]=green4;
		p_boja[ZELENO][4]=green5;p_boja[ZELENO][5]=green6;
		p_boja[ZELENO][6]=green7;p_boja[ZELENO][7]=green8;
		
		main.setStyle("-fx-background-color:rgb(150,150,150);");//visuelni design pozadine
		kocka_polje.setStyle("-fx-border-color:black;-fx-background-color:white;");//visuelni design kocke
		for(int i=0;i<p.length;i++)
		{
			final int I=i;
			p[i].setOnMouseClicked(new EventHandler<MouseEvent>()
					{

						public void handle(MouseEvent e) 
						{
							if(broj==0)//ako ne mozemo pomeriti figuru
								return;
							else if(player+1==CRVENO && I+broj>30 && I<=30)//provera da li je kandidat za kucicu
							{
								int t=(I+broj)%30;
								if(t-1>3 || polja[I]!=CRVENO)//ako vrednost prelazi ili ako se ne poklapaju boje
									return;
								else if(polja_boja[CRVENO][t+3]==CRVENO)// da li je zauzeto polje
									return;
								else //ako mozemo tu skociti, pomeramo figuru
									{
									p[I].setStyle(boja[PRAZNO]);
									polja[I]=PRAZNO;
									p_boja[CRVENO][t+3].setStyle(boja[CRVENO]);
									polja_boja[CRVENO][t+3]=CRVENO;
									broj=0;
									}
								
							}
							else if((player+1==PLAVO && I+broj>40 && I>30) || (player+1==PLAVO && I==0))//provera da li je kandidat za kucicu
							{
								int t=(I+broj)%40;
								if(t-1>3 || polja[I]!=PLAVO)//ako vrednost prelazi ili ako se ne poklapaju boje
									return;
								else if(polja_boja[PLAVO][t+3]==PLAVO)// da li je zauzeto polje
									return;
								else//ako mozemo tu skociti, pomeramo figuru
									{
									p[I].setStyle(boja[PRAZNO]);
									polja[I]=PRAZNO;
									p_boja[PLAVO][t+3].setStyle(boja[PLAVO]);
									polja_boja[PLAVO][t+3]=PLAVO;
									broj=0;
									}
								
							}
							else if(player+1==ZELENO && I+broj>10 && I<=10)//provera da li je kandidat za kucicu
							{
								int t=(I+broj)%10;
								if(t-1>3 || polja[I]!=ZELENO)//ako vrednost prelazi ili ako se ne poklapaju boje
									return;
								else if(polja_boja[ZELENO][t+3]==ZELENO)// da li je zauzeto polje
									return;
								else//ako mozemo tu skociti, pomeramo figuru
									{
									p[I].setStyle(boja[PRAZNO]);
									polja[I]=PRAZNO;
									p_boja[ZELENO][t+3].setStyle(boja[ZELENO]);
									polja_boja[ZELENO][t+3]=ZELENO;
									broj=0;
									}
								
							}	
							else if(player+1==ZUTO && I+broj>20 && I<=20)//provera da li je kandidat za kucicu
							{
								int t=(I+broj)%20;
								if(t-1>3 || polja[I]!=ZUTO)//ako vrednost prelazi ili ako se ne poklapaju boje
									return;
								else if(polja_boja[ZUTO][t+3]==ZUTO)// da li je zauzeto polje
									return;
								else//ako mozemo tu skociti, pomeramo figuru
									{
									p[I].setStyle(boja[PRAZNO]);
									polja[I]=PRAZNO;
									p_boja[ZUTO][t+3].setStyle(boja[ZUTO]);
									polja_boja[ZUTO][t+3]=ZUTO;
									broj=0;
									}
								
							}
							else if(player+1==polja[I] && polja[(I+broj)%40]!=player+1)//ako polje nije zauzeto ili nije njegova figura
								{
								polja[I]=PRAZNO;
								p[I].setStyle(boja[PRAZNO]);
								int boja_polja=polja[(I+broj)%40];
								if(boja_polja!=PRAZNO)//ako je bila figura koja je od drugog igraca
									for(int i=0;i<4;i++)//moramo je staviti na pocetak
										if(polja_boja[boja_polja][i]==PRAZNO)
											{
											polja_boja[boja_polja][i]=boja_polja;
											p_boja[boja_polja][i].setStyle(boja[boja_polja]);
											break;
											}
								//bojimo trenutnim igracem
								polja[(I+broj)%40]=player+1;
								p[(I+broj)%40].setStyle(boja[player+1]);
								broj=0;//posle uspesnog poteza stavljamo vrednost pomeranja polja na 0
								}
						}
				
					});
		}
		for(int i=0;i<4;i++)//inicijalizacija pocetnih kucica
		{
			polja_boja[CRVENO][i]=CRVENO;
			polja_boja[PLAVO][i]=PLAVO;
			polja_boja[ZUTO][i]=ZUTO;
			polja_boja[ZELENO][i]=ZELENO;
		}
		for(int i=0;i<4;i++)//podesavanje funkcija pocetnih kucica
		{
			final int I=i;
			p_boja[CRVENO][i].setOnMouseClicked(new EventHandler<MouseEvent>()
					{

						public void handle(MouseEvent e) {
							if(player+1!=CRVENO || broj==0 || polja[31]==CRVENO)//ako nije figura od igraca ili ako nemamo potez 
								return;//ili ako ne moze izaci iz pocetne kucice
							if(polja_boja[CRVENO][I]==CRVENO && broj==6)//ako smo bacili broj 6, mozemo izaci iz pocetne kucice
								{
								prvi_potez[CRVENO]=true;//u trenutnoj igri smo uspeli da izbacimo figuru
								int boja_polja=polja[31];
								if(boja_polja!=PRAZNO)//ukoliko se nalazi neki drugi igrac na polju gde treba izaci figura
									for(int k=0;k<4;k++)//moramo je ukloniti i vratiti na pocetak
										if(polja_boja[boja_polja][k]==PRAZNO)
											{
											polja_boja[boja_polja][k]=boja_polja;
											p_boja[boja_polja][k].setStyle(boja[boja_polja]);
											break;
											}
								polja[31]=CRVENO;
								p[31].setStyle(boja[CRVENO]);
								polja_boja[CRVENO][I]=PRAZNO;
								p_boja[CRVENO][I].setStyle(boja[PRAZNO]);
								broj=0;
								}
						}
				
					});
			p_boja[ZELENO][i].setOnMouseClicked(new EventHandler<MouseEvent>()
					{

						public void handle(MouseEvent e) {
							if(player+1!=ZELENO || broj==0 || polja[11]==ZELENO)//ako nije figura od igraca ili ako nemamo potez 
								return;//ili ako ne moze izaci iz pocetne kucice
							if(polja_boja[ZELENO][I]==ZELENO && broj==6)//ako smo bacili broj 6, mozemo izaci iz pocetne kucice
								{
								prvi_potez[ZELENO]=true;//u trenutnoj igri smo uspeli da izbacimo figuru
								int boja_polja=polja[11];
								if(boja_polja!=PRAZNO)//ukoliko se nalazi neki drugi igrac na polju gde treba izaci figura
									for(int k=0;k<4;k++)//moramo je ukloniti i vratiti na pocetak
										if(polja_boja[boja_polja][k]==PRAZNO)
											{
											polja_boja[boja_polja][k]=boja_polja;
											p_boja[boja_polja][k].setStyle(boja[boja_polja]);
											break;
											}
								polja[11]=ZELENO;
								p[11].setStyle(boja[ZELENO]);
								polja_boja[ZELENO][I]=PRAZNO;
								p_boja[ZELENO][I].setStyle(boja[PRAZNO]);
								broj=0;
								}
						}
				
					});
			p_boja[PLAVO][i].setOnMouseClicked(new EventHandler<MouseEvent>()
					{

						public void handle(MouseEvent e) {
							if(player+1!=PLAVO || broj==0 || polja[1]==PLAVO)//ako nije figura od igraca ili ako nemamo potez 
								return;//ili ako ne moze izaci iz pocetne kucice
							if(polja_boja[PLAVO][I]==PLAVO && broj==6)//ako smo bacili broj 6, mozemo izaci iz pocetne kucice
								{
								prvi_potez[PLAVO]=true;//u trenutnoj igri smo uspeli da izbacimo figuru
								int boja_polja=polja[1];
								if(boja_polja!=PRAZNO)//ukoliko se nalazi neki drugi igrac na polju gde treba izaci figura
									for(int k=0;k<4;k++)//moramo je ukloniti i vratiti na pocetak
										if(polja_boja[boja_polja][k]==PRAZNO)
											{
											polja_boja[boja_polja][k]=boja_polja;
											p_boja[boja_polja][k].setStyle(boja[boja_polja]);
											break;
											}
								polja[1]=PLAVO;
								p[1].setStyle(boja[PLAVO]);
								polja_boja[PLAVO][I]=PRAZNO;
								p_boja[PLAVO][I].setStyle(boja[PRAZNO]);
								broj=0;
								}
						}
				
					});
			p_boja[ZUTO][i].setOnMouseClicked(new EventHandler<MouseEvent>()
					{

						public void handle(MouseEvent e) {
							if(player+1!=ZUTO || broj==0 || polja[21]==ZUTO)//ako nije figura od igraca ili ako nemamo potez 
								return;//ili ako ne moze izaci iz pocetne kucice
							if(polja_boja[ZUTO][I]==ZUTO && broj==6)//ako smo bacili broj 6, mozemo izaci iz pocetne kucice
								{
								prvi_potez[ZUTO]=true;//u trenutnoj igri smo uspeli da izbacimo figuru
								int boja_polja=polja[21];
								if(boja_polja!=PRAZNO)//ukoliko se nalazi neki drugi igrac na polju gde treba izaci figura
									for(int k=0;k<4;k++)//moramo je ukloniti i vratiti na pocetak
										if(polja_boja[boja_polja][k]==PRAZNO)
											{
											polja_boja[boja_polja][k]=boja_polja;
											p_boja[boja_polja][k].setStyle(boja[boja_polja]);
											break;
											}
								polja[21]=ZUTO;
								p[21].setStyle(boja[ZUTO]);
								polja_boja[ZUTO][I]=PRAZNO;
								p_boja[ZUTO][I].setStyle(boja[PRAZNO]);
								broj=0;
								}
						}
				
					});
		}
		for(int i=4;i<7;i++)//podesavanja krajnih kucica
		{
			final int I=i;
			p_boja[CRVENO][i].setOnMouseClicked(new EventHandler<MouseEvent>()
					{

						public void handle(MouseEvent e) {
							if(player+1!=CRVENO || broj==0)//ako nije odgovarajuci igrac ili ne moze igrati
								return;
							if(I+broj>7 || polja_boja[CRVENO][I+broj]==CRVENO)//ako prelazi zadnju kucicu ili postoji vec figura iste boje
								return;
							//inace pomeramo figuru
							polja_boja[CRVENO][I]=PRAZNO;
							p_boja[CRVENO][I].setStyle("-fx-fill:rgb(255,200,200);");
							polja_boja[CRVENO][I+broj]=CRVENO;
							p_boja[CRVENO][I+broj].setStyle(boja[CRVENO]);
							broj=0;
						}
				
					});
			p_boja[ZELENO][i].setOnMouseClicked(new EventHandler<MouseEvent>()
					{

					public void handle(MouseEvent e) {
						if(player+1!=ZELENO || broj==0)//ako nije odgovarajuci igrac ili ne moze igrati
							return;
						if(I+broj>7 || polja_boja[ZELENO][I+broj]==ZELENO)//ako prelazi zadnju kucicu ili postoji vec figura iste boje
							return;
						//inace pomeramo figuru
						polja_boja[ZELENO][I]=PRAZNO;
						p_boja[ZELENO][I].setStyle("-fx-fill:rgb(200,255,200);");
						polja_boja[ZELENO][I+broj]=ZELENO;
						p_boja[ZELENO][I+broj].setStyle(boja[ZELENO]);
						broj=0;
					}
				
					});
			p_boja[PLAVO][i].setOnMouseClicked(new EventHandler<MouseEvent>()
					{

					public void handle(MouseEvent e) {
						//System.out.println(I+broj); //debug
						if(player+1!=PLAVO || broj==0)//ako nije odgovarajuci igrac ili ne moze igrati
							return;
						if(I+broj>7 || polja_boja[PLAVO][I+broj]==PLAVO)//ako prelazi zadnju kucicu ili postoji vec figura iste boje
							return;
						//inace pomeramo figuru
							polja_boja[PLAVO][I]=PRAZNO;
							p_boja[PLAVO][I].setStyle("-fx-fill:rgb(200,200,255);");
							polja_boja[PLAVO][I+broj]=PLAVO;
							p_boja[PLAVO][I+broj].setStyle(boja[PLAVO]);
							broj=0;
					}
					});
			p_boja[ZUTO][i].setOnMouseClicked(new EventHandler<MouseEvent>()
					{

					public void handle(MouseEvent e) {
						if(player+1!=ZUTO || broj==0)//ako nije odgovarajuci igrac ili ne moze igrati
							return;
						if(I+broj>7 || polja_boja[ZUTO][I+broj]==ZUTO)//ako prelazi zadnju kucicu ili postoji vec figura iste boje
							return;
						//inace pomeramo figuru
						polja_boja[ZUTO][I]=PRAZNO;
						p_boja[ZUTO][I].setStyle("-fx-fill:rgb(255,255,200);");
						polja_boja[ZUTO][I+broj]=ZUTO;
						p_boja[ZUTO][I+broj].setStyle(boja[ZUTO]);
						broj=0;
					}
				
					});
		}
		for(int s=0;s<meni.length-1;s++)//podesavanje menija da resetuje igru i postavi na odgovarajuci broj igraca
		{
			final int I=s;
			meni[s].setOnAction(new EventHandler<ActionEvent>()//reset na pocetnu vrednost i postavljanje borj igraca i ko prvi igra
					{

						public void handle(ActionEvent arg0) {
							//resetovanje
							c1.setVisible(false);c2.setVisible(false);c3.setVisible(false);
							c4.setVisible(false);c5.setVisible(false);c6.setVisible(false);
							c7.setVisible(false);
							br_prvi=0;
							pobednik=PRAZNO;
							kocka.setText("THROW");
							players=I+2;//broj igraca
							broj=0;
							again=false;
							stanje=false;
							player=R.nextInt(I+2);//koja boja prvo pocinje sa igrom
							potez.setStyle(boja[player+1]);//postavljamo bojuko prvi igra
							potez.setVisible(true);
							kocka.setDisable(false);
							for(int j=0;j<p.length;j++)//reset na pocetnu vrednost
								{
								p[j].setStyle(boja[PRAZNO]);
								polja[j]=PRAZNO;
								}
							for(int j=0;j<4;j++)//reset na pocetnu vrednost
							{
								prvi_potez[j]=false;
								p_boja[PLAVO][j].setStyle(boja[PLAVO]);
								polja_boja[PLAVO][j]=PLAVO;
								p_boja[CRVENO][j].setStyle(boja[CRVENO]);
								polja_boja[CRVENO][j]=CRVENO;
								p_boja[ZELENO][j].setStyle(boja[ZELENO]);
								polja_boja[ZELENO][j]=ZELENO;
								p_boja[ZUTO][j].setStyle(boja[ZUTO]);
								polja_boja[ZUTO][j]=ZUTO;
							}
							prvi_potez[4]=false;
							for(int j=4;j<8;j++)//reset na pocetnu vrednost
							{
								p_boja[PLAVO][j].setStyle("-fx-fill:rgb(200,200,255);");
								polja_boja[PLAVO][j]=PRAZNO;
								p_boja[CRVENO][j].setStyle("-fx-fill:rgb(255,200,200);");
								polja_boja[CRVENO][j]=PRAZNO;
								p_boja[ZELENO][j].setStyle("-fx-fill:rgb(200,255,200);");
								polja_boja[ZELENO][j]=PRAZNO;
								p_boja[ZUTO][j].setStyle("-fx-fill:rgb(255,255,200);");
								polja_boja[ZUTO][j]=PRAZNO;
							}/*
							//debug
							p_boja[ZUTO][5].setStyle(boja[ZUTO]);
							polja_boja[ZUTO][5]=ZUTO;
							p_boja[ZUTO][7].setStyle(boja[ZUTO]);
							polja_boja[ZUTO][7]=ZUTO;
							p[20].setStyle(boja[ZUTO]);
							polja[20]=ZUTO;
							p[18].setStyle(boja[PLAVO]);
							polja[18]=PLAVO;*/
							
							
						}
					
					});
		}

			meni[3].setOnAction(new EventHandler<ActionEvent>()//reset na pocetnu vrednost i postavljanje borj igraca i ko prvi igra
					{

						public void handle(ActionEvent arg0) {
							Parent root=null;
							try {
								root = FXMLLoader.load(getClass().getResource("Info.fxml"));
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							scene = new Scene(root,400,300);
						    stage.setTitle("Pobednik");
						    stage.setScene(scene);
						    stage.show();
							
						}
						
					});
					


	}

}
