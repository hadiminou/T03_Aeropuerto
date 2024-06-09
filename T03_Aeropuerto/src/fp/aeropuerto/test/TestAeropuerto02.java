package fp.aeropuerto.test;

import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import fp.aeropuerto.Aeropuerto;
import fp.aeropuerto.Compania;
import fp.aeropuerto.FactoriaAeropuerto;
import fp.aeropuerto.Persona;
import fp.aeropuerto.Vuelo;

public class TestAeropuerto02 {

	public static void main(String[] args) {
		Aeropuerto miAeropuerto = FactoriaAeropuerto.creaAeropuerto("San Pablo", "Sevilla",
				"data/vuelos.csv");
		
		//visualizaAeropuerto(miAeropuerto);
		//testExisteVueloADestino(miAeropuerto);
		//testTodosLosVuelosCuestanMenosQue(miAeropuerto);
		//testVueloMasDuracion(miAeropuerto);
		//testCuentaVuelosPorDestino(miAeropuerto);
		//testdistintosDestinosPorCompania (miAeropuerto);
		//testdistintosDestinosVuelosCompletosPorCompania (miAeropuerto);
		//testvuelosPorOrdenNatural(miAeropuerto);
		//testmaximoVueloPorOrdenNatural(miAeropuerto);
		//testvuelosPorInversoAlOrdenNatural(miAeropuerto);
		//testvuelosPorPrecioYHoraSalida(miAeropuerto);
		//testvuelosPorDuracionYMayorNroPasajeros(miAeropuerto);
		//testpasajerosDePrimerVueloPorNombreYDni(miAeropuerto);
		//testdiferentesNombresDePasajerosPorOrdenAlfabeticoInverso(miAeropuerto);
		//testpasajerosDeTodosLosVuelosPorApellidosYNombre(miAeropuerto);
		//testpasajerosSinRepetirDeTodosLosVuelosPorApellidosYNombre(miAeropuerto);
		//testnumeroVuelosADestino(miAeropuerto);
		//testnumeroPasajerosADestino(miAeropuerto);
		//testvueloMenorRecaudacionVuelosCompletos(miAeropuerto);
		//testcodigoDeAlgunVueloADestinoConPlazasLibres(miAeropuerto);
		//testexisteVueloPrecioMenorQue(miAeropuerto);
		//testpromedioPreciosVuelosCompletos(miAeropuerto);
		//testsumaPreciosDistintosVuelosCompletos(miAeropuerto);
		//testcontarDistintosPasajeros(miAeropuerto);
		//testdistintosDNIPasajeroDeCompania(miAeropuerto);
		//testnombresOrdenadosDistintosPasajeroDeCompania(miAeropuerto);
		//testmapListaVuelosPorDestinos(miAeropuerto);
		//testmapSetVuelosPorFechaLlegada(miAeropuerto);
		//testmapSetOrdenadoVuelosPorFechaSalida(miAeropuerto);
		//testmapNumVuelosCompletosPorCompania(miAeropuerto);
		//testmapPreciosOrdenadosPorDestino(miAeropuerto);
		//testmapPrecioMedioPorCompania(miAeropuerto);
		//testmapPrecioVuelosConEscalasMasBaratoPorDestino(miAeropuerto);
		//testmapSumaPlazasLibresPorHoraDeLlegada(miAeropuerto);
		//testdestinoMayorNumeroDeplazasLibres(miAeropuerto);
		//testpromediosDePasajerosPorFechasDeSalida(miAeropuerto);
		//testpromediosDePasajerosPorFechasDeSalida2(miAeropuerto);
		//testmapDestinosPorDuracion(miAeropuerto);
		//testcompaniaConMayorSumaDePlazasLibres(miAeropuerto);
		//testsegundoDiaConMenosVuelos(miAeropuerto);
		//testmapPreciosSuperioresPorFechas(miAeropuerto);
		//testdestinoConMayorDiferenciaDePrecio(miAeropuerto);
		//testfechaEnQueLLeganMasConDistintoNombrePasajerosConDestinos(miAeropuerto);
		//testtopNMediaPromediosPorDestino(miAeropuerto);
		//testvuelosPorPasajero(miAeropuerto);
		//testtiempoDeVuelosPorPasajero(miAeropuerto);
		testdniPasajeroMasTiempoDeVuelo(miAeropuerto);
	}
	
	private static void visualizaAeropuerto (Aeropuerto a) {
		System.out.println("object aeropuerto: "+ a);
		System.out.println("primer vuelo: "+ a.vuelos().get(0));
		System.out.println("pasajeros del primer vuelo: "+ a.vuelos().get(0).pasajeros());
	}
	
	private static void testExisteVueloADestino(Aeropuerto a) {
		try {
			System.out.println("\n13a. testexisteVueloADestino:"); // para mostrar el ejercicio
			System.out.println("existeVueloADestino Málaga?: " + a.existeVueloADestino("Málaga"));
			System.out.println("existeVueloADestino Viena?: " + a.existeVueloADestino("Viena"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
		
	private static void testTodosLosVuelosCuestanMenosQue(Aeropuerto a) {
		try {
			System.out.println("\n13b. testTodosLosVuelosCuestanMenosQue:");
			System.out.println("todosLosVueloCuestanMenosQue 1000?: "+
			a.todosLosVueloCuestanMenosQue(1000d));
			System.out.println("todosLosVueloCuestanMenosQue 50?: " +
			a.todosLosVueloCuestanMenosQue(50d));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testVueloMasDuracion(Aeropuerto a) {
		try {
			System.out.println("\n14a. testVueloMasDuracion:");
			System.out.println("El codigo de vuelo mas duracion: " + a.vueloMasDuracion().codigo() 
					+ " y la duracion: " + a.vueloMasDuracion().duracion());
			System.out.println(a.vueloMasDuracion().codigo()+ "-" + 
					a.vueloMasDuracion().duracion().toHoursPart()
					+ ":" + a.vueloMasDuracion().duracion().toMinutesPart());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testCuentaVuelosPorDestino (Aeropuerto a) {
		try {
			System.out.println("\n15a. testCuentaVuelosPorDestino:");
			for(Entry<String,Integer> par: a.cuentaVuelosPorDestino().entrySet()) {
				System.out.println(par);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testdistintosDestinosPorCompania (Aeropuerto a) {
		try {
			System.out.println("\n15b. testdistintosDestinosPorCompania:");
			for(Entry<Compania, Set<String>> par: a.distintosDestinosPorCompania().entrySet()) {
				System.out.println(par.getKey()+"--->"+ par.getValue());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testdistintosDestinosVuelosCompletosPorCompania (Aeropuerto a) {
		try {
			System.out.println("\n15c. testdistintosDestinosVuelosCompletosPorCompania:");
			for(Entry<Compania, Set<String>> par: a.distintosDestinosVuelosCompletosPorCompania()
					.entrySet()) {
				System.out.println("(" + par.getKey() + "," + par.getValue() + ")");
			}
		}
		catch  (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testvuelosPorOrdenNatural(Aeropuerto a) {
		try {
			System.out.println("\n16a. testvuelosPorOrdenNatural:");
			for(Vuelo v : a.vuelosPorOrdenNatural()) {
			System.out.println(v);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testmaximoVueloPorOrdenNatural(Aeropuerto a) {
		try {
			System.out.println("\n16b. testmaximoVueloPorOrdenNatural:");
			System.out.println(a.maximoVueloPorOrdenNatural());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testvuelosPorInversoAlOrdenNatural(Aeropuerto a) {
		try {
			System.out.println("\n17a. testvuelosPorInversoAlOrdenNatural:");
			for(Vuelo v:a.vuelosPorInversoAlOrdenNatural()) {
				System.out.println(v.codigo()+"--->"+v.destino()+"-->"+
			v.fechaHoraSalida().toLocalDate());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testvuelosPorPrecioYHoraSalida(Aeropuerto a) {
		try {
			System.out.println("\n17b. testvuelosPorPrecioYHoraSalida:");
			for(Vuelo v:a.vuelosPorPrecioYHoraSalida()) {
				System.out.println(v.codigo()+"--->"+v.precio()+"-->"+
			v.fechaHoraSalida().toLocalTime());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testvuelosPorDuracionYMayorNroPasajeros(Aeropuerto a) {
		try {
			System.out.println("\n17c. testvuelosPorDuracionYMayorNroPasajeros:");
			for(Vuelo v:a.vuelosPorDuracionYMayorNroPasajeros()) {
				System.out.println(v.codigo()+"--->"+v.duracion()+"-->"+
			v.numeroPasajeros());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testpasajerosDePrimerVueloPorNombreYDni(Aeropuerto a) {
		try {
			System.out.println("\n17d. testpasajerosDePrimerVueloPorNombreYDni:");
			for(Persona p:a.pasajerosDePrimerVueloPorNombreYDni()) {
				System.out.println(p);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testdiferentesNombresDePasajerosPorOrdenAlfabeticoInverso(Aeropuerto a) {
		try {
			System.out.println("\n17e. testdiferentesNombresDePasajerosPorOrdenAlfabeticoInverso:");
			for(String s:a.diferentesNombresDePasajerosPorOrdenAlfabeticoInverso()) {
				System.out.println(s);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testpasajerosDeTodosLosVuelosPorApellidosYNombre(Aeropuerto a) {
		try {
			System.out.println("\n17f. testpasajerosDeTodosLosVuelosPorApellidosYNombre:");
			for(Persona s:a.pasajerosDeTodosLosVuelosPorApellidosYNombre()) {
				System.out.println(s);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testpasajerosSinRepetirDeTodosLosVuelosPorApellidosYNombre(Aeropuerto a) {
		try {
			System.out.println("\n17g. testpasajerosSinRepetirDeTodosLosVuelosPorApellidosYNombre:");
			for(Persona s:a.pasajerosSinRepetirDeTodosLosVuelosPorApellidosYNombre()) {
				System.out.println(s);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testnumeroVuelosADestino(Aeropuerto a) {
		try {
			System.out.println("\n18a. testnumeroVuelosADestino");
			System.out.println("A Malaga: "+a.numeroVuelosADestino("Málaga"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testnumeroPasajerosADestino(Aeropuerto a) {
		try {
			System.out.println("\n18b. testnumeroPasajerosADestino");
			System.out.println("A Barcelona: "+a.numeroPasajerosADestino("Barcelona"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testvueloMenorRecaudacionVuelosCompletos(Aeropuerto a) {
		try {
			System.out.println("\n18c. testvueloMenorRecaudacionVuelosCompletos");
			Vuelo v = a.vueloMenorRecaudacionVuelosCompletos();
			System.out.println(v);
			System.out.println(v.precio()*v.numeroPasajeros());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testcodigoDeAlgunVueloADestinoConPlazasLibres(Aeropuerto a) {
		try {
			System.out.println("\n18d. testcodigoDeAlgunVueloADestinoConPlazasLibres");
			System.out.println("Malaga: "+a.codigoDeAlgunVueloADestinoConPlazasLibres("Málaga"));
			System.out.println("Cuenca: "+a.codigoDeAlgunVueloADestinoConPlazasLibres("Cuenca"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testexisteVueloPrecioMenorQue(Aeropuerto a) {
		try {
			System.out.println("\n18e. testexisteVueloPrecioMenorQue");
			System.out.println("precio 100: "+a.existeVueloPrecioMenorQue(100d));
			System.out.println("precio 3: "+a.existeVueloPrecioMenorQue(3d));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testpromedioPreciosVuelosCompletos(Aeropuerto a) {
		try {
			System.out.println("\n19a. testpromedioPreciosVuelosCompletos");
			System.out.println(a.promedioPreciosVuelosCompletos());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testsumaPreciosDistintosVuelosCompletos(Aeropuerto a) {
		try {
			System.out.println("\n19b. testsumaPreciosDistintosVuelosCompletos");
			System.out.println(a.sumaPreciosDistintosVuelosCompletos());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testcontarDistintosPasajeros(Aeropuerto a) {
		try {
			System.out.println("\n19c. testcontarDistintosPasajeros");
			System.out.println(a.contarDistintosPasajeros());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testdistintosDNIPasajeroDeCompania(Aeropuerto a) {
		try {
			System.out.println("\n19d. testdistintosDNIPasajeroDeCompania");
			System.out.println(a.distintosDNIPasajeroDeCompania(Compania.IBE));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testnombresOrdenadosDistintosPasajeroDeCompania(Aeropuerto a) {
		try {
			System.out.println("\n19e. testnombresOrdenadosDistintosPasajeroDeCompania");
			System.out.println(a.nombresOrdenadosDistintosPasajeroDeCompania(Compania.VLG));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testmapListaVuelosPorDestinos(Aeropuerto a) {   
		try {
			System.out.println("\n20a. testmapListaVuelosPorDestinos");
			for(Entry<String, List<Vuelo>> par:a.mapListaVuelosPorDestinos().entrySet()) {
				System.out.println(par);
				System.out.println(par.getKey()+" ---> "+par.getValue()); // imprimir clave,valor separado
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testmapSetVuelosPorFechaLlegada(Aeropuerto a) {
		try {
			System.out.println("\n20b. testmapSetVuelosPorFechaLlegada");
			for (Entry<LocalDate, Set<Vuelo>> par: a.mapSetVuelosPorFechaLlegada().entrySet()) {
				System.out.println(par.getKey()+" ---> "+par.getValue());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testmapSetOrdenadoVuelosPorFechaSalida(Aeropuerto a) {
		try {
			System.out.println("\n20c. testmapSetOrdenadoVuelosPorFechaSalida");
			for(Entry <LocalDate, SortedSet<Vuelo>> par:
				a.mapSetOrdenadoVuelosPorFechaSalida().entrySet()) {
				System.out.println(par.getKey()+" ---> "+par.getValue());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testmapNumVuelosCompletosPorCompania(Aeropuerto a) {
		try {
			System.out.println("\n20d. testmapNumVuelosCompletosPorCompania");
			for(Entry <Compania, Integer> par : a.mapNumVuelosCompletosPorCompania().entrySet()) {
				System.out.println(par.getKey()+" ---> "+par.getValue());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testmapPreciosOrdenadosPorDestino(Aeropuerto a) {
		try {
			System.out.println("\n20e. testmapPreciosOrdenadosPorDestino");
			for(Entry <String, SortedSet<Double>> par: a.mapPreciosOrdenadosPorDestino().entrySet()) {
				System.out.println(par.getKey()+" ---> "+par.getValue());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testmapPrecioMedioPorCompania(Aeropuerto a) {
		try {
			System.out.println("\n21a. testmapPrecioMedioPorCompania");
			for(Entry <Compania, Double> par: a.mapPrecioMedioPorCompania().entrySet()) {
				System.out.println(par.getKey()+" ---> "+par.getValue());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testmapPrecioVuelosConEscalasMasBaratoPorDestino(Aeropuerto a) {
		try {
			System.out.println("\n21b. testmapPrecioVuelosConEscalasMasBaratoPorDestino");
			for(Entry <String, Double> par: a.mapPrecioVuelosConEscalasMasBaratoPorDestino().entrySet()) {
				System.out.println(par.getKey()+" ---> "+par.getValue());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testmapSumaPlazasLibresPorHoraDeLlegada(Aeropuerto a) {
		try {
			System.out.println("\n21c. testmapSumaPlazasLibresPorHoraDeLlegada");
			for(Entry <LocalTime, Integer> par : a.mapSumaPlazasLibresPorHoraDeLlegada(Compania.IBE)
					.entrySet()) {
				System.out.println(par.getKey()+" ---> "+par.getValue());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testdestinoMayorNumeroDeplazasLibres(Aeropuerto a) {
		try {
			System.out.println("\n22a. testdestinoMayorNumeroDeplazasLibres");
			System.out.println(a.destinoMayorNumeroDeplazasLibres());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testpromediosDePasajerosPorFechasDeSalida(Aeropuerto a) {
		try {
			System.out.println("\n22b. testpromediosDePasajerosPorFechasDeSalida");
			System.out.println(a.promediosDePasajerosPorFechasDeSalida());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testpromediosDePasajerosPorFechasDeSalida2(Aeropuerto a) {
		try {
			System.out.println("\n22c. testpromediosDePasajerosPorFechasDeSalida2");
			System.out.println(a.promediosDePasajerosPorFechasDeSalida2());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testmapDestinosPorDuracion(Aeropuerto a) {
		try {
			System.out.println("\n23a. testmapDestinosPorDuracion");
			for(Entry <Duration, SortedSet<String>> par: a.mapDestinosPorDuracion().entrySet()) {
				System.out.println(par.getKey()+" --> "+par.getValue());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testcompaniaConMayorSumaDePlazasLibres(Aeropuerto a) {
		try {
			System.out.println("\n23b. testcompaniaConMayorSumaDePlazasLibres");
			System.out.println(a.companiaConMayorSumaDePlazasLibres());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testsegundoDiaConMenosVuelos(Aeropuerto a) {
		try {
			System.out.println("\n23c. testsegundoDiaConMenosVuelos");
			System.out.println(a.segundoDiaConMenosVuelos());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testmapPreciosSuperioresPorFechas(Aeropuerto a) {
		try {
			System.out.println("\n23d. testmapPreciosSuperioresPorFechas");
			for(Entry <LocalDate, Set<Double>> par: a.mapPreciosSuperioresPorFechas().entrySet()) {
				System.out.println(par.getKey()+" --> "+ par.getValue());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testdestinoConMayorDiferenciaDePrecio(Aeropuerto a) {
		try {
			System.out.println("\n23e. testdestinoConMayorDiferenciaDePrecio");
			System.out.println(a.destinoConMayorDiferenciaDePrecio());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testfechaEnQueLLeganMasConDistintoNombrePasajerosConDestinos(Aeropuerto a) {
		try {
			System.out.println("\n24a. testfechaEnQueLLeganMasConDistintoNombrePasajerosConDestinos");
			Set<String>destinos = new HashSet<String>();
			Collections.addAll(destinos, "Málaga", "Barcelona", "Oviedo");
			System.out.println(a.fechaEnQueLLeganMasConDistintoNombrePasajerosConDestinos(destinos));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testtopNMediaPromediosPorDestino(Aeropuerto a) {
		try {
			System.out.println("\n24b. testtopNMediaPromediosPorDestino");
			for(Entry <String, Double> par : a.topNMediaPromediosPorDestino(10).entrySet()) {
				System.out.println(par.getKey()+" --> "+par.getValue());
			}
		}
			catch(Exception e) {
				e.printStackTrace();
			}
	}
	
	private static void testvuelosPorPasajero(Aeropuerto a) {
		try {
			System.out.println("\n24c. testvuelosPorPasajero");
			for(Entry<Persona, List<Vuelo>> par: a.vuelosPorPasajero().entrySet()) {
				System.out.println(par.getKey() + " --> " + par.getValue());
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private static void testtiempoDeVuelosPorPasajero(Aeropuerto a) {
		try {
			System.out.println("\n24d. testtiempoDeVuelosPorPasajero");
			for(Entry <Persona, Duration> par:a.tiempoDeVuelosPorPasajero().entrySet()) {
				System.out.println(par.getKey()+" --> "+ par.getValue());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testdniPasajeroMasTiempoDeVuelo(Aeropuerto a) {
		try {
			System.out.println("\n24e. testdniPasajeroMasTiempoDeVuelo");
			System.out.println(a.dniPasajeroMasTiempoDeVuelo());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
