package web.servlets;

import java.net.UnknownHostException;
import java.util.List;

import database.DBException;
import database.DBProvider;
import model.beans.Autor;
import model.beans.Komentar;
import model.beans.Tekst;
import model.beans.Tema;

public class Demo {

	public static void main(String[] args) throws DBException, UnknownHostException {
		//Autor autor = DBProvider.getIPProvider().getAutorByUsername("Tea");
		//System.out.println(autor);
		
		//Autor autor2 = DBProvider.getIPProvider().getAutorByUsername("Lucija");
		//System.out.println(autor2);
		
		/*List<String> pictures = DBProvider.getIPProvider().getAllPicturesByTextId(1);
		System.out.println(pictures);
		if(pictures == null || pictures.isEmpty()) {
			System.out.println("null je");
		}else {
			for(String s : pictures) {
				System.out.println(s);
			}
		}*/
		
		/*List<Tema> teme = DBProvider.getIPProvider().getAllThemesByTextId(2);
		if(teme == null || teme.isEmpty()) {
			System.out.println("null je");
		}else {
			for(Tema s : teme) {
				System.out.println(s);
			}
		}*/
		
		/*Tekst tekst = DBProvider.getIPProvider().getTekstById(0);
		System.out.println(tekst.getNaslov());
		System.out.println(tekst.getText());
		System.out.println(tekst.getAutor());
		
		Komentar k = new Komentar();
		k.setAutor("Lucija");
		k.setText("Oke je");
		Autor autor = new Autor();
		autor.setSifra("lucijinasifra");
		autor.setUsername("Lucija");*/
		//DBProvider.getIPProvider().addNewComment(k, 11, autor);
		
		List<Komentar> komentari = DBProvider.getIPProvider().getAllCommentsByTextId(11);
		
		/*if(komentari == null || komentari.isEmpty()) {
			System.out.println("Null je");
		}else {
			for(Komentar ko : komentari) {
				System.out.println(ko);
			}
		}*/
		
		/*Tekst novi = new Tekst();
		//novi.setAutor(autor);
		novi.setId(17);
		novi.setKomentari(komentari);
		novi.setNaslov("Demo za bazu");
		novi.setSlike(null);
		novi.setTeme(null);
		novi.setText("Da probamo i ovo");*/
		
		//DBProvider.getIPProvider().addNewText(novi, autor);
	/*	tekst = DBProvider.getIPProvider().getTekstById(17);
		System.out.println(tekst.getNaslov());
		System.out.println(tekst.getText());
		System.out.println(tekst.getAutor());
		
		komentari = DBProvider.getIPProvider().getAllCommentsByTextId(17);
		System.out.println(komentari);*/
		
		//List<Tekst> tekstoviSvi = DBProvider.getIPProvider().getAllTextsByAuthorUsername("Tea");
		
		/*if(tekstoviSvi == null || tekstoviSvi.isEmpty()) {
			System.out.println("Prazno je");
		}else {
			
			for(Tekst te : tekstoviSvi) {
				System.out.println(te);
			}
		}*/
		
		//List<Autor> autori = DBProvider.getIPProvider().getAllAuthors();
		/*if(autori == null || autori.isEmpty()) {
			System.out.println("Prazno je");
		}else {
			
			for(Autor au : autori) {
				System.out.println(au);
			}
		}*/
		
	/*	Autor noviAutor = new Autor();
		noviAutor.setSifra("ivinaSifra");
		noviAutor.setUsername("Iva");*/
	//	DBProvider.getIPProvider().registerNewAutor(noviAutor);
		//List<Autor> autori = DBProvider.getIPProvider().getAllAuthors();
		/*if(autori == null || autori.isEmpty()) {
			//System.out.println("Prazno je");
		}else {
			
			for(Autor au : autori) {
				//System.out.println(au);
			}
		}*/
		
		//List<Tekst> tIva = DBProvider.getIPProvider().getAllTextsByAuthorUsername("Iva");
		
		/*if(tIva == null || tIva.isEmpty()) {
			//System.out.println("Dobro je, tocno je");
		}*/
		
		//Tekst promijena = DBProvider.getIPProvider().getTekstById(17);
		
		//promijena.setText("Promjena, provjera, valjda radi");
		
	//	DBProvider.getIPProvider().changeExistingText(promijena);
		
		//promijena = DBProvider.getIPProvider().getTekstById(17);
		
		//System.out.println(promijena.getText());
	}

}
