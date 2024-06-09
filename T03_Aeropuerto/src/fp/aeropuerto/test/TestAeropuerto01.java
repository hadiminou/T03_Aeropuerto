package fp.aeropuerto.test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fp.aeropuerto.Aeropuerto;
import fp.aeropuerto.Vuelo;

public class TestAeropuerto01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vuelo v1=new Vuelo("IBE-001","Valencia",LocalDateTime.of(2023,8,1,9,0),
				Duration.ofMinutes(115),890.5,150d,5,false);
		Vuelo v2=new Vuelo("IBE-002","Madrid",LocalDateTime.of(2023,9,3,1,0),
				Duration.ofMinutes(110),690.5,100d,3,false);
		
		List<Vuelo> vuelos1 = new ArrayList<Vuelo>();
		vuelos1.add(v1);
		vuelos1.add(v2);
		Aeropuerto a1 = new Aeropuerto("San Pablo","Sevilla",vuelos1);
		
		System.out.println("mi aeropuerto: "+a1);
		System.out.println("nombre: "+a1.nombre());
		System.out.println("localidad: "+a1.localidad());
		System.out.println("lista de vuelos: "+a1.vuelos());
		System.out.println("numero de vuelos: "+a1.numeroDeVuelos());
	}

}
