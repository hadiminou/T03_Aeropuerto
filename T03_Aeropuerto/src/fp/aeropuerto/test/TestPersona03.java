package fp.aeropuerto.test;

import java.nio.file.spi.FileSystemProvider;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fp.aeropuerto.*;

public class TestPersona03 {

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
		
		pasaj1.add(p1); pasaj1.add(p2); pasaj1.add(p3);
		
		Vuelo v = new Vuelo("IBE-001","Valencia",LocalDateTime.of(2023,8,1,9,0),
				Duration.ofMinutes(115),890.5,150d,5,false,pasaj1);
		System.out.println("num pasajeros: " + v.numeroPasajeros());
		pasaj1.add(p4);
		System.out.println("num pasajeros: " + v.numeroPasajeros());
		
		v.pasajeros().add(p5); // en Vuelo con pasajeros=List.copyOf(pasajeros); 
								//hemos cambiado pasasjeros a una lista inmutable
		System.out.println("num pasajeros: " + v.numeroPasajeros());
		
		
	}

}
