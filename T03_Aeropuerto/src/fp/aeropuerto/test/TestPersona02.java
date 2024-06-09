package fp.aeropuerto.test;

import java.nio.file.spi.FileSystemProvider;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fp.aeropuerto.Persona;

public class TestPersona02 {

	public static void main(String[] args) {
		Persona p1 = new Persona("FRANCISCO MIGUEL","AARAB ORTIZ","12346678A");
		Persona p2 = new Persona("ALBERTO","AGUILAR RALSTON","13457789B");
		Persona p3 = new Persona("ALVARO","AGUILAR RALSTON","14568900C");
		Persona p4 = new Persona("ADRIÁN","ALARCON MARTIN","15680011D");
		Persona p5 = new Persona("PABLO","ALBA CONRADI","16791122E");
		
		//pasaj1 con las 3 primeras personas
		//pasaj2 con las 2 últimas personas
		//pasaj3 con las 3 personas que ocupan las posiciones impares
		
		List <Persona> pasaj1 = new ArrayList<Persona>();
		List <Persona> pasaj2 = new ArrayList<Persona>();
		List <Persona> pasaj3 = new ArrayList<Persona>();
		
		pasaj1.add(p1);
		pasaj1.add(p2);
		pasaj1.add(p3);
		
		pasaj2.add(p4);
		pasaj2.add(p5);
		
		pasaj3.add(p1);
		pasaj3.add(p3);
		pasaj3.add(p5);
		
		System.out.println("numero de elementos de pasaj1: " + pasaj1.size());
		System.out.println("numero de elementos de pasaj2: " + pasaj2.size());
		System.out.println("numero de elementos de pasaj3: " + pasaj3.size());
		
		pasaj1.addAll(pasaj3);
		System.out.println("nuevo numero de elementos de pasaj1: " + pasaj1.size());
		
		Set<Persona>conj1=new HashSet<Persona>();
		conj1.addAll(pasaj1);
		System.out.println("numero de elementos de conj1: " + conj1.size());
		
		pasaj2.addAll(1, conj1);
		System.out.println("nuevo num elem de pasaj2: " + pasaj2.size());
		
		System.out.println("p1 esta en pasaj2: " + pasaj2.contains(p1));
		pasaj2.remove(p1);
		System.out.println("num pasaj2: " + pasaj2.size());
		System.out.println("p1 esta en pasaj2: " + pasaj2.contains(p1));
		
		
		
	}

}
