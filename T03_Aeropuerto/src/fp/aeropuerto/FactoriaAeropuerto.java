package fp.aeropuerto;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import utiles.Checkers;

public class FactoriaAeropuerto {
	public static Aeropuerto creaAeropuerto(String nombre, String localidad, String ruta) {
		List<Vuelo> vuelos = new ArrayList<Vuelo>();
		try {
			List<String> lineas = Files.readAllLines(Paths.get(ruta));
			for (String linea: lineas.subList(1, lineas.size())) {
				vuelos.add(parseaVuelo(linea));
			}
		}
		catch (Exception e) {
			System.out.println("Error al abrir el fichero: "+e);
		}
		return new Aeropuerto(nombre, localidad, vuelos);
	}
	
	 public static Aeropuerto crearAeropuerto2(String nombre, String localidad, String ruta){
		 Stream<Vuelo> vuelos=null;
		 try {
			 vuelos=Files.lines(Paths.get(ruta)).map(FactoriaAeropuerto::parseaVuelo);
		}catch(Exception e) {
			 System.out.println("Error procesando el fichero: "+e);
		}
		//Devuelve un Stream<String>
		return new Aeropuerto(nombre, localidad, vuelos);
		// Tiene que existir un constructor apropiado que pueda recibir un Stream como 3er. parámetro
	}
				
	private static Vuelo parseaVuelo(String linea) {
		Checkers.checkNoNull(linea);
		String[] trozos = linea.split(";");
		Checkers.check("El vuelo no esta bien troceado-->"+linea, trozos.length==9);
		String codigo = trozos[0].trim();
		String destino = trozos[1].trim();
		LocalDateTime fhs =LocalDateTime.parse(trozos[2].trim(), 
				DateTimeFormatter.ofPattern("d/M/y-H:m"));
		Duration duracion = Duration.ofMinutes(Long.valueOf(trozos[3].trim()));
		Double velocidad = Double.valueOf(trozos[4].trim());
		Double precio = Double.valueOf(trozos[5].trim());
		Integer nPlazas = Integer.valueOf(trozos[6].trim());
		Boolean conEscalas = (trozos[7].trim().equals("S"));
		List<Persona> pasajeros = parseaPasajeros(trozos[8].trim());
		return new Vuelo(codigo, destino, fhs, duracion, velocidad, precio, nPlazas, conEscalas, 
				pasajeros);
	}

	private static List<Persona> parseaPasajeros(String linea) {
		Checkers.checkNoNull(linea);
		List<Persona> res = new ArrayList<Persona>();
		String[] trozos = linea.split("#");
		for(String pasajeros: trozos) {
			String[] partes = pasajeros.split(",");
			String nombre = partes[0].trim();
			String apellidos = partes[1].trim();
			String dni = partes[2].trim();
			res.add(new Persona(dni, nombre, apellidos));
		}
		return res;
	}
}
